package Work1;

import javax.swing.JOptionPane;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class sqldatabase {

    private static final String URL =
            "jdbc:mysql://localhost:3306/crossword?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String SQL_DUMP_PATH = "vards_clean.sql";

    public static java.sql.Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            java.sql.Connection conn =
                    java.sql.DriverManager.getConnection(URL, USER, PASSWORD);

            if (!tableExists(conn, "vards")) {
                runSqlFile(conn, SQL_DUMP_PATH);
            }

            return conn;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DB kļūda: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static boolean tableExists(java.sql.Connection conn, String tableName) throws java.sql.SQLException {
        java.sql.DatabaseMetaData meta = conn.getMetaData();
        try (java.sql.ResultSet rs = meta.getTables(null, null, tableName, new String[]{"TABLE"})) {
            return rs.next();
        }
    }

    private static void runSqlFile(java.sql.Connection conn, String sqlDumpPath) throws Exception {
        String script = Files.readString(Path.of(sqlDumpPath), StandardCharsets.UTF_8);

        try (java.sql.Statement st = conn.createStatement()) {
            for (String part : script.split(";")) {
                String sql = part.trim();
                if (!sql.isEmpty()) st.execute(sql);
            }
        }
    }
}
