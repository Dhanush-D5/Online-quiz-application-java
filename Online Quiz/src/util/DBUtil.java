package util;

import java.sql.*;

public class DBUtil {

    // Method to get the connection to the database
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/online_quiz"; // Make sure your database name is correct
        String user = "root";  // Replace with your MySQL username if different
        String password = "W7301@jqir#"; // Use the correct password for your MySQL user

        return DriverManager.getConnection(url, user, password);
    }
}
