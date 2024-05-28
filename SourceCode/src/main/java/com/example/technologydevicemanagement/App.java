package com.example.technologydevicemanagement;

import com.example.technologydevicemanagement.util.DBUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        DBUtil.doConnection();
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/login.fxml"));
        Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));

        stage.setTitle("Technology Equipment Sales Management System");
        stage.getIcons().add(icon);

        scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}