package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {

        if (connection != null) {
            return connection;
        }

        Connection dbConnection = null;
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connection = DriverManager.getConnection("jdbc:h2:./db", "", "");
        return connection;
    }

    public static void close() throws SQLException {
        if (connection == null) {
            return;
        }
        connection.close();
        connection = null;
    }

}
