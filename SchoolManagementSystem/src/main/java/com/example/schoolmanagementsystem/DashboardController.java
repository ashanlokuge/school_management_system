package com.example.schoolmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    public AnchorPane context;

    public void studentOnAction(ActionEvent actionEvent) throws IOException {
        setUI("studentDash-view");
    }

    public void gradesOnAction(ActionEvent actionEvent) throws IOException {
        setUI("");
    }

    public void subjectsOnAction(ActionEvent actionEvent) throws IOException {
        setUI("");
    }
    private void setUI(String location) throws IOException {
        Stage stage =(Stage)context.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/com/example/schoolmanagementsystem/view/"+location +".fxml"));
        stage.setScene(new Scene(parent));
    }
}
