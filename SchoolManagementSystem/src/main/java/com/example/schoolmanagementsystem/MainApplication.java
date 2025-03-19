package com.example.schoolmanagementsystem;

import com.example.schoolmanagementsystem.Database.DatabaseConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseConnection.initializeDatabase();
      
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("view/gradeDashboard-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 866,528 );
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}