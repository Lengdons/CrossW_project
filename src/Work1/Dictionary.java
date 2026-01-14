package Work1;
 

	import java.sql.*;
	import java.util.ArrayList;

	public class Dictionary {

	    public static ArrayList<Entry> getByLevel(Connection c, String lvl, int n)
	            throws SQLException {

	        ArrayList<Entry> list = new ArrayList<>();
	        String sql = "SELECT vards, definicija FROM vards WHERE lvl=? LIMIT ?";

	       