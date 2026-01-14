package Work1;



	import java.sql.*;
	import java.util.ArrayList;
	import java.util.List;
	import javax.swing.JOptionPane;
	import java.nio.charset.StandardCharsets;
	import java.nio.file.Files;
	import java.nio.file.Path;
	
	
	public class sqldatabase {
	
private static final String URL = "jdbc:mysql://localhost:3306/crossword?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8";
private static final String USER = "root";
private static final String PASSWORD = "";
private static final String SQL_DUMP_PATH = "vards_clean.sql";

public static Connection getConnection() {
    try {
 Class.forName("com.mysql.cj.jdbc.Driver");
 Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

if (!tableExists(conn, "vards")) {
 runSqlFile(conn, SQL_DUMP_PATH);
 }

 return conn;
	
        
    
	








