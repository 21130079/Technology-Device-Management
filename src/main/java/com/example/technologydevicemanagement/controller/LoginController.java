package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.LoginApp;
import database.DAOAccount;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username_f;

    @FXML
    private PasswordField password_f;

    @FXML
    private Button loginButton;
    @FXML
    private Label error_lb;
    public void checkLogin(){
        String username = username_f.getText();
        String passwd  = password_f.getText();

        if(username.trim().equals((""))){
            error_lb.setText("Please enter username");
            return;
        }
        if(passwd.trim().equals((""))){
            error_lb.setText("Please enter Password");
            return;
        }
        if(!new DAOAccount().checkExits(username,passwd)){

            error_lb.setText("username or password is wrong !");
            return;
        }
        Data.username = username;
        hideApplication();
        restartApplication();
    }
    @FXML
    public void initialize() {
        LoginByKey();
    }
    public void LoginByKey(){
        password_f.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                // Kiểm tra xem phím Enter được nhấn
                if (event.getCode() == KeyCode.ENTER) {
                    // Xử lý khi phím Enter được nhấn
                    checkLogin();
                    System.out.println("Enter key pressed in PasswordField");
                    // Thực hiện các hành động khác ở đây...
                }
            }


        });
    }


    public void restartApplication() {

            try {
                // Tạo một FXMLLoader mới để tải lại cùng một fxml file
                Parent root = FXMLLoader.load(LoginApp.class.getResource("view/dashboard.fxml"));
                // Tạo một Scene mới
                Scene scene = new Scene(root, 1200, 700);
                Stage stage = new Stage();
                // Đặt scene cho stage
                stage.setScene(scene);

                // Hiển thị stage
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
    @FXML
    private void hideApplication() {
        Stage stage = (Stage) username_f.getScene().getWindow();
        stage.hide();
    }
}
