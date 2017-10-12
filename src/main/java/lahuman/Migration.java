package lahuman;

import java.sql.*;

public class Migration {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        Class.forName("org.postgresql.Driver");
        try (
            Connection originalDB = DriverManager.getConnection("URL", "ID", "PW");
            Connection targetDB = DriverManager.getConnection("URL", "ID", "PW");
        ){
            copyTableUsingSQL(originalDB, targetDB, "select A, B, C from T1", "insert into T2(A,B,C)values(?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void copyTableUsingSQL(Connection originalDB, Connection targetDB, String selectSql, String insertSql) throws SQLException {
        try (
                PreparedStatement ops = originalDB.prepareStatement(selectSql);
                PreparedStatement tps = targetDB.prepareStatement(insertSql);
        ) {
            ResultSet rs = ops.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            while(rs.next()){
                for(int i=0; i<columnCount ; i++ ) {
                    tps.setObject((i + 1), rs.getObject((i + 1)));
                }
                tps.addBatch();
            }
            tps.executeBatch();
        }
    }
}
