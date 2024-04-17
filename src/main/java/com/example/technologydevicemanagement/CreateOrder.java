package com.example.technologydevicemanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateOrder extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(CreateOrder.class.getResource("CreateOrder.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 610, 400);
        stage.setTitle("Create Order");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


}
