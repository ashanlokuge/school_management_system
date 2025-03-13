package com.example.schoolmanagementsystem;

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
    }

    public void backOnAction(ActionEvent actionEvent) throws IOException {
        setUI("");
    }
    private void setUI(String location) throws IOException {
        Stage stage =(Stage)context.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource(""+location +".fxml"));
        stage.setScene(new Scene(parent));
    }
}
