package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Dao {
    protected static Connection databaseConnection;

    protected Dao() {
        try {
            if (databaseConnection == null || databaseConnection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                createDatabase();
                databaseConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/elearning", "root", "1234");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            Audit.logAction("Exception in Dao.java: constructor: " + throwables);
        }
        createTable();
    }

    private void createDatabase() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "1234");
            final String query = "CREATE DATABASE IF NOT EXISTS elearning";
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException throwables) {
            Audit.logAction("Exception in Dao.java: createDatabase(create): " + throwables);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                Audit.logAction("Exception in Dao.java: createDatabase(close): " + throwables);
            }
        }
    }

    protected abstract void createTable();
}
