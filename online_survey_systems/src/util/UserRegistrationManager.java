package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserRegistrationManager {
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/anildb";
	    private static final String DB_USER = "root";
	    private static final String DB_PASSWORD = "Anil@123";
	    private static final String CHECK_USERNAME_QUERY = "SELECT COUNT(*) FROM users WHERE username = ?";
	    private static final String INSERT_USER_QUERY = "INSERT INTO users (username, password) VALUES (?, ?)";
	    public boolean registerUser(String username, String password, JFrame frame) {
	        if (isUsernameExists(username)) {
	            JOptionPane.showMessageDialog(frame, "Registration failed: Username already exists. Please try another username.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }

	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	             PreparedStatement stmt = conn.prepareStatement(INSERT_USER_QUERY)) {
	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            int rowsAffected = stmt.executeUpdate();
	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(frame, "Registration successful!", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
	                return true;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        JOptionPane.showMessageDialog(frame, "Registration failed. Please try again.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }

	    private boolean isUsernameExists(String username) {
	        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	             PreparedStatement stmt = conn.prepareStatement(CHECK_USERNAME_QUERY)) {
	            stmt.setString(1, username);
	            try (ResultSet rs = stmt.executeQuery()) {
	                if (rs.next()) {
	                    int count = rs.getInt(1);
	                    return count > 0;
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	}