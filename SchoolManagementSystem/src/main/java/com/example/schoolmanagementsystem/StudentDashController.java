package com.example.schoolmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentDashController {
    public Button addButton;
    public Button viewButton;
    public AnchorPane context;

    public void addOnAction(ActionEvent actionEvent) throws IOException {
        setUI("addStudent-view");

    }

    public void ViewOnAction(ActionEvent actionEvent) throws IOException {
        setUI("student-list-view");
    }
    private void setUI(String location) throws IOException {
        Stage stage =(Stage)context.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/com/example/schoolmanagementsystem/view/"+location +".fxml"));
        stage.setScene(new Scene(parent));
    }
}
