package com.example.technologydevicemanagement.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ImportProductApp extends Application {
    Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/import-product.fxml"));
        Scene scene = new Scene(root, 1200, 700);
        stage.setTitle("Import Product");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public Scene getScene() {
        return scene;
    }
}
