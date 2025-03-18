package com.example.schoolmanagementsystem;

import com.example.schoolmanagementsystem.Database.DatabaseConnection;
import com.example.schoolmanagementsystem.Model.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
        List<Student> students = fetchStudentsFromDatabase();
        studentsTable.getItems().setAll(students);
    }

    private List<Student> fetchStudentsFromDatabase() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
