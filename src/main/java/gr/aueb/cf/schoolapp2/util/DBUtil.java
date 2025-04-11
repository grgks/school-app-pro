package gr.aueb.cf.schoolapp2.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil {

    private static final BasicDataSource ds = new BasicDataSource();
    private static Connection connection;

    static {
        ds.setUrl("jdbc:mysql://localhost:3306/schooldb7staging?serverTimeZone=UTC");
        ds.setUsername("user7staging");
        ds.setPassword("12345");
        ds.setInitialSize(10);
        ds.setMinIdle(10);
        ds.setMaxIdle(15);
    }

    private DBUtil() {

    }


    public static Connection getConnection() throws SQLException {
        connection = ds.getConnection();
        //System.out.println("Connection given");
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null) connection.close();
    }
}