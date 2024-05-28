package com.example.technologydevicemanagement.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccountManagementApp extends Application {
    Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/account-management.fxml"));
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Account Management");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public Scene getScene() {
        return scene;
    }
}
