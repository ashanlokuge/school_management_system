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
        createTablesIfNotExists();   // Create the tables if they don't exist
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

    private static void createTablesIfNotExists() {
        // SQL statements to create tables
        String createStudentsTableSQL = "CREATE TABLE IF NOT EXISTS students ("
                + "student_id INT AUTO_INCREMENT PRIMARY KEY, "
                + "name VARCHAR(100) NOT NULL, "
                + "grade INT NOT NULL, "
                + "mobile_number VARCHAR(15) NOT NULL"
                + ");";

        String createSubjectsTableSQL = "CREATE TABLE IF NOT EXISTS subjects ("
                + "subject_id INT AUTO_INCREMENT PRIMARY KEY, "
                + "subject_name VARCHAR(100) NOT NULL, "
                + "grade INT NOT NULL"
                + ");";

        String createMarksTableSQL = "CREATE TABLE IF NOT EXISTS marks ("
                + "student_id INT, "
                + "subject_id INT, "
                + "marks INT, "
                + "PRIMARY KEY (student_id, subject_id), "
                + "FOREIGN KEY (student_id) REFERENCES students(student_id) ON DELETE CASCADE, "
                + "FOREIGN KEY (subject_id) REFERENCES subjects(subject_id) ON DELETE CASCADE"
                + ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            // Execute SQL statements to create tables
            stmt.execute(createStudentsTableSQL);
            stmt.execute(createSubjectsTableSQL);
            stmt.execute(createMarksTableSQL);

            System.out.println("Tables checked/created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}