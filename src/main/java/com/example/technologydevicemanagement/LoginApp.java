package com.example.technologydevicemanagement;

import com.example.technologydevicemanagement.controller.LoginController;
import com.example.technologydevicemanagement.util.DBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;

public class LoginApp extends Application {
    Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        DBUtil.doConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApp.class.getResource("view/login.fxml"));

        stage.setTitle("Technology Equipment Sales Management System");

        scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Sale Management");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}