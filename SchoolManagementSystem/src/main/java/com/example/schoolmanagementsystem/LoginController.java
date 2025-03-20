package com.example.schoolmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    public BorderPane context;
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label error;

    @FXML
    public void handleLogin() throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException {

        if (username.getText().toString().equals("admin") && password.getText().toString().equals("123")) {
            setUI("dashboard-view");

        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            error.setText("Please enter valid data.");
        } else {
            error.setText("Wrong username or password!");
        }
    }
    private void setUI(String location) throws IOException {
        Stage stage =(Stage)context.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource("/com/example/schoolmanagementsystem/view/"+location +".fxml"));
        stage.setScene(new Scene(parent));
    }
}