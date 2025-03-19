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
    @FXML private TableColumn<Student, Integer> idColumn;
    @FXML private TableColumn<Student, String> firstNameColumn;
    @FXML private TableColumn<Student, String> lastNameColumn;
    @FXML private TableColumn<Student, String> emailColumn;
    @FXML private TextField searchField;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        configureTableColumns();
        loadStudents();
    }

    private void configureTableColumns() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    }

    private void loadStudents() {
        List<Student> students = fetchStudentsFromDatabase("");
        studentsTable.getItems().setAll(students);
    }

    private List<Student> fetchStudentsFromDatabase(String searchTerm) {
        List<Student> students = new ArrayList<>();
        String query;

        if(searchTerm.isEmpty()) {
            query = "SELECT * FROM students";
        } else {
            query = "SELECT * FROM students WHERE " +
                    "id = ? OR " +
                    "CONCAT(first_name, ' ', last_name) LIKE ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            if(!searchTerm.isEmpty()) {
                // Try to parse as ID
                try {
                    int id = Integer.parseInt(searchTerm);
                    stmt.setInt(1, id);
                } catch (NumberFormatException e) {
                    stmt.setInt(1, -1); // Invalid ID to ensure no match
                }
                stmt.setString(2, "%" + searchTerm + "%");
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    students.add(new Student(
                            rs.getInt("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("grade"),
                            rs.getDate("birth_date").toLocalDate(),
                            rs.getDate("enrollment_date").toLocalDate()
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

        if(students.isEmpty()) {
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
