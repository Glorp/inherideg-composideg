package init;

import db.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = Database.getConnection();
            Statement stmt = null;
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            stmt.execute("CREATE TABLE deg(dogId varchar(255) primary key, name varchar(255), x int, y int)");
            stmt.execute("INSERT INTO deg(dogId, name, x, y) VALUES('dog-123', 'Fido', 10, 12)");
            stmt.close();
            connection.commit();
            Database.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
