package com.example.schoolmanagementsystem.Database;

import com.example.schoolmanagementsystem.Model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDAO {

    // Method to add a new student to the database
    public static void addStudent(Student student) {
        String sql = "INSERT INTO students (name, student_id, grade, mobile_number) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the values for the SQL query
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getStudentId());
            pstmt.setString(3, student.getGrade());
            pstmt.setString(4, student.getMobileNumber());

            // Execute the query
            pstmt.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
