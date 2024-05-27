package com.example.technologydevicemanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccountManagement extends Application {
    Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateOrderApp.class.getResource("view/account-management.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Account Management");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public Scene getScene() {
        return scene;
    }
}
