package core;

	import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
	
	
	public class sqldatabase {
	
private static final String URL = "jdbc:mysql://localhost:3306/crossword?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8";
private static final String USER = "root";
private static final String PASSWORD = "";
private static final String SQL_DUMP_PATH = "vards_clean.sql";

public static Connection getConnection() {
	
	Connection conn = null;
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	conn = DriverManager.getConnection(URL, USER, PASSWORD);

    	if (!tableExists(conn, "vards") || isTableEmpty(conn, "vards")) {
    	    System.out.println("Table missing or empty. Running SQL script...");
    	    runSqlFile(conn, SQL_DUMP_PATH);
    	}
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "DB kļūda: " + e.getMessage());
        e.printStackTrace();
        return null;
    }
    return conn;
}

public static Map<String, String> getGameWords(Connection conn) {
    Map<String, String> dictionary = new HashMap<>();
    String query = "SELECT vards, definicija FROM vards"; 

    try (Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        
        while (rs.next()) {
            String word = rs.getString("vards");
            String desc = rs.getString("definicija");
            if (word != null && desc != null) {
                dictionary.put(word.toUpperCase(), desc);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return dictionary;
}
private static void runSqlFile(Connection conn, String sqlDumpPath) throws Exception {
    File file = new File(sqlDumpPath);
    if (!file.exists()) {
        System.err.println("SQL File not found: " + file.getAbsolutePath());
        return;
    }

    StringBuilder sb = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("--") || line.startsWith("/*") || line.trim().isEmpty()) continue;
            sb.append(line).append("\n");
            if (line.trim().endsWith(";")) {
                try (Statement st = conn.createStatement()) {
                    st.execute(sb.toString());
                }
                sb.setLength(0);
            }
        }
    }
    System.out.println("Database initialized successfully!");
}

private static boolean tableExists(Connection conn, String tableName) throws SQLException {
    DatabaseMetaData meta = conn.getMetaData();
    try (ResultSet rs = meta.getTables(null, null, tableName, null)) {
        return rs.next();
    }
	}
private static boolean isTableEmpty(Connection conn, String tableName) {
 try (Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM " + tableName)) {
     if (rs.next()) {
         return rs.getInt(1) == 0; 
     }
 } catch (SQLException e) {
     e.printStackTrace();
 }
 return true; 
}
}






