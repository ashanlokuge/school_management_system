package com.example.schoolmanagementsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label error;

    @FXML
    public void handleLogin() {
        checkLogin();
    }

    private void checkLogin(){

        if (username.getText().toString().equals("admin") && password.getText().toString().equals("123")) {
            error.setText("Login Success!");

        } else if (username.getText().isEmpty() && password.getText().isEmpty()) {
            error.setText("Please enter valid data.");
        } else {
            error.setText("Wrong username or password!");
        }
    }
}