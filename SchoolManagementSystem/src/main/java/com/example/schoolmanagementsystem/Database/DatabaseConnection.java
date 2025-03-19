package com.example.schoolmanagementsystem.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnection {

    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "school_db";
    private static final String URL = BASE_URL + DATABASE_NAME;
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        createDatabaseIfNotExists(); // Create the database if it doesn't exist
        createTableIfNotExists();    // Create the table if it doesn't exist
    }

    private static void createDatabaseIfNotExists() {
        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;

        try (Connection conn = DriverManager.getConnection(BASE_URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createDatabaseSQL);
            System.out.println("Database '" + DATABASE_NAME + "' checked/created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTableIfNotExists() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "student_id VARCHAR(20) NOT NULL, "
                + "grade VARCHAR(10) NOT NULL, "
                + "mobile_number VARCHAR(15) NOT NULL"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Table 'students' checked/created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}