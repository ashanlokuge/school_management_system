package com.example.schoolmanagementsystem;

import com.example.schoolmanagementsystem.Database.StudentDAO;
import com.example.schoolmanagementsystem.Model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddStudentController {

    public TextField txtName;
    public TextField txtStudentId;
    public TextField txtGrade;
    public TextField txtMobileNumber;
    public AnchorPane context;

    public void saveStudentOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String studentId = txtStudentId.getText();
        String grade = txtGrade.getText();
        String mobileNumber = txtMobileNumber.getText();

        Student student = new Student(name, studentId, grade, mobileNumber);

        StudentDAO.addStudent(student);

        clearForm();
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUI("studentDash-view");
    }
    private void setUI(String location) throws IOException {
        Stage stage =(Stage)context.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/com/example/schoolmanagementsystem/view/"+location +".fxml"));
        stage.setScene(new Scene(parent));
    }
    private void clearForm(){
        txtName.clear();
        txtStudentId.clear();
        txtGrade.clear();
        txtMobileNumber.clear();
    }
}
