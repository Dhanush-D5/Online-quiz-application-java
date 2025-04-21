package dao;

import util.DBUtil;
import java.sql.*;

public class UserDAO {

    // Method to get User ID based on username
    public int getUserId(String username) {
        int userId = -1;
        try (Connection connection = DBUtil.getConnection()) {
            String query = "SELECT id FROM users WHERE username = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    userId = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    // Method for user login
    public boolean login(String username, String password) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            return rs.next(); // true if user found
        } catch (SQLException e) {
            System.out.println("Login failed due to DB error.");
            e.printStackTrace();
            return false;
        }
    }

    // Method for user registration
    public boolean register(String username, String password) {
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate(); // Executes INSERT
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists. Please choose a different one.");
            return false;
        } catch (SQLException e) {
            System.out.println("Registration failed due to DB error.");
            e.printStackTrace();
            return false;
        }
    }
}
