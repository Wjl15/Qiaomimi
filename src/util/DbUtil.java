package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    private String dbUrl = "jdbc:mysql://localhost:3306/db_inventory?useUnicode=true&amp;characterEncoding=utf8";
    private String dbUserName = "root";
    private String dbPassword = "123456";
    private String jdbcName = "com.mysql.jdbc.Driver";


    public DbUtil() {
    }

    public Connection getCon() throws Exception {
        Class.forName(this.jdbcName);
        Connection con = DriverManager.getConnection(this.dbUrl, this.dbUserName, this.dbPassword);
        return con;
    }

    public void closeCon(Connection con) throws Exception {
        if (con != null) {
            con.close();
        }

    }
}
