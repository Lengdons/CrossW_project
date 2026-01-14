package Work1;
 

	import java.sql.*;
	import java.util.ArrayList;

	public class Dictionary {

	    public static ArrayList<Entry> getByLevel(Connection c, String lvl, int n)
	            throws SQLException {

	        ArrayList<Entry> list = new ArrayList<>();
	        String sql = "SELECT vards, definicija FROM vards WHERE lvl=? LIMIT ?";

	        PreparedStatement ps = c.prepareStatement(sql);
	        ps.setString(1, lvl);
	        ps.setInt(2, n);

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            list.add(new Entry(
	                    rs.getString("vards"),
	                    rs.getString("definicija")
	            ));
	        }
	        return list;
	        .
	    }
	}