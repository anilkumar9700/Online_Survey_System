package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/anildb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Anil@123";
    public static void main(String[] args) {
        Connection conn = null;
        try {
            // Step 1: Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Establish a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            if (conn != null) {
                System.out.println("Connected to the database");
                // Step 3: Execute SQL queries
                // Example: executeQuery("SELECT * FROM table_name");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Step 4: Close the connection
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
