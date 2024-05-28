package com.example.technologydevicemanagement.view;

import com.example.technologydevicemanagement.util.DBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApp extends Application {
    Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        DBUtil.doConnection();
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/login.fxml"));
        stage.setTitle("Technology Equipment Sales Management System");
        scene = new Scene(root, 1200, 700);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}