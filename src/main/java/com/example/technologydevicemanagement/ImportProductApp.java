package com.example.technologydevicemanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ImportProductApp extends Application {
    Scene scene;

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateOrderApp.class.getResource("view/import-product.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 700);
        stage.setTitle("Create Order");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }
    public Scene getScene() {
        return scene;
    }
}
