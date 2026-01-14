package Work1;

import java.sql.*;
import java.util.ArrayList;

public class Dictionary {

    public static ArrayList<Entry> getByLevels(
            java.sql.Connection c, String[] levels, int n)
            throws SQLException {

        ArrayList<Entry> list = new ArrayList<>();

        StringBuilder in = new StringBuilder();
        for (int i = 0; i < levels.length; i++) {
            if (i > 0) in.append(",");
            in.append("?");
        }

        String sql =
            "SELECT vards, definicija FROM vards " +
            "WHERE lvl IN (" + in + ") " +
            "ORDER BY RAND() LIMIT ?";

        try (PreparedStatement ps = c.prepareStatement(sql)) {

            int idx = 1;
            for (String l : levels) ps.setString(idx++, l);
            ps.setInt(idx, n);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Entry(
                        rs.getString("vards"),
                        rs.getString("definicija")
                    ));
                }
            }
        }
        return list;
    }
}
