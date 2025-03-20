package com.example.schoolmanagementsystem;

import com.example.schoolmanagementsystem.Model.StudentMarks;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StudentMarksController implements Initializable {
    public TableColumn<StudentMarks, Integer> studentIdCol;
    public TableColumn<StudentMarks, String> nameCol;
    public TableColumn<StudentMarks, Integer> totalCol;
    public TableColumn<StudentMarks, Double> averageCol;
    public TableColumn<StudentMarks, Integer> rankCol;
    public TableView<StudentMarks> tableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentIdCol.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));
        averageCol.setCellValueFactory(new PropertyValueFactory<>("average"));
        rankCol.setCellValueFactory(new PropertyValueFactory<>("rank"));

        // Load data for grade 10 (or any selected grade)
        loadGradeData(10);
    }

    private void loadGradeData(int grade) {
        tableView.getColumns().removeIf(column -> !column.equals(studentIdCol) && !column.equals(nameCol) &&
                !column.equals(totalCol) && !column.equals(averageCol) && !column.equals(rankCol));

        List<String> subjects = getSubjectsForGrade(grade);

        for (String subject : subjects) {
            TableColumn<StudentMarks, Integer> subjectCol = new TableColumn<>(subject);
            subjectCol.setCellValueFactory(cellData -> {
                StudentMarks student = cellData.getValue();
                return javafx.beans.binding.Bindings.createObjectBinding(() -> student.getMarksForSubject(subject));
            });
            tableView.getColumns().add(tableView.getColumns().size() - 3, subjectCol); // Add before fixed columns
        }

        ObservableList<StudentMarks> data = FXCollections.observableArrayList();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db", "root", "1234")) {
            String query = "SELECT s.student_id, s.name, m.subject_id, sub.subject_name, m.marks " +
                    "FROM Students s " +
                    "JOIN Marks m ON s.student_id = m.student_id " +
                    "JOIN Subjects sub ON m.subject_id = sub.subject_id " +
                    "WHERE s.grade = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, grade);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt("student_id");
                String name = rs.getString("name");
                String subjectName = rs.getString("subject_name");
                int marks = rs.getInt("marks");

                StudentMarks student = data.stream()
                        .filter(s -> s.getStudentId() == studentId)
                        .findFirst()
                        .orElse(new StudentMarks(studentId, name));

                student.addSubjectMarks(subjectName, marks);

                if (!data.contains(student)) {
                    data.add(student);
                }
            }

            calculateResults(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tableView.setItems(data);
    }

    private List<String> getSubjectsForGrade(int grade) {
        List<String> subjects = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/school_db", "root", "1234")) {
            String query = "SELECT subject_name FROM Subjects WHERE grade = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, grade);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                subjects.add(rs.getString("subject_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

    private void calculateResults(ObservableList<StudentMarks> data) {
        for (StudentMarks student : data) {
            int total = student.getSubjectMarks().values().stream().mapToInt(Integer::intValue).sum();
            double average = total / (double) student.getSubjectMarks().size();
            student.setTotal(total);
            student.setAverage(average);
        }

        data.sort((s1, s2) -> Integer.compare(s2.getTotal(), s1.getTotal()));

        for (int i = 0; i < data.size(); i++) {
            data.get(i).setRank(i + 1);
        }
    }

    public void backGradesOnAction(ActionEvent actionEvent) {
        System.out.println("Back to Grades clicked");

    }
}
