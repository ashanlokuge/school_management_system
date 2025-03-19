package com.example.schoolmanagementsystem;

import com.example.schoolmanagementsystem.Database.DatabaseConnection;
import com.example.schoolmanagementsystem.Model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentListController {

    @FXML private TableView<Student> studentsTable;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> studentIdColumn;
    @FXML private TableColumn<Student, String> gradeColumn;
    @FXML private TableColumn<Student, String> mobileNumberColumn;
    @FXML private TextField searchField;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        configureTableColumns();
        loadStudents();
    }

    private void configureTableColumns() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        studentIdColumn.setCellValueFactory(cellData -> cellData.getValue().studentIdProperty());
        gradeColumn.setCellValueFactory(cellData -> cellData.getValue().gradeProperty());
        mobileNumberColumn.setCellValueFactory(cellData -> cellData.getValue().mobileNumberProperty());
    }

    private void loadStudents() {
        List<Student> students = fetchStudentsFromDatabase("");
        studentsTable.getItems().setAll(students);
    }

    private List<Student> fetchStudentsFromDatabase(String searchTerm) {
        List<Student> students = new ArrayList<>();
        String query;

        if (searchTerm.isEmpty()) {
            query = "SELECT name, student_id, grade, mobile_number FROM students";
        } else {
            query = "SELECT name, student_id, grade, mobile_number FROM students WHERE " +
                    "name LIKE ? OR " +
                    "student_id LIKE ? OR " +
                    "grade LIKE ? OR " +
                    "mobile_number LIKE ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if (!searchTerm.isEmpty()) {
                stmt.setString(1, "%" + searchTerm + "%");
                stmt.setString(2, "%" + searchTerm + "%");
                stmt.setString(3, "%" + searchTerm + "%");
                stmt.setString(4, "%" + searchTerm + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getString("name"),
                            rs.getString("student_id"),
                            rs.getString("grade"),
                            rs.getString("mobile_number")
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText().trim();
        List<Student> students = fetchStudentsFromDatabase(searchTerm);

        if (students.isEmpty()) {
            messageLabel.setText("No students found matching: " + searchTerm);
            messageLabel.setStyle("-fx-text-fill: red;");
        } else {
            messageLabel.setText("Showing " + students.size() + " results");
            messageLabel.setStyle("-fx-text-fill: green;");
        }

        studentsTable.getItems().setAll(students);
    }

    @FXML
    private void handleReset() {
        searchField.clear();
        loadStudents();
        messageLabel.setText("");
    }
}
