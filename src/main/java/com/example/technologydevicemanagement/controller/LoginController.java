package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.CreateOrderApp;
import com.example.technologydevicemanagement.LoginApp;
import com.example.technologydevicemanagement.model.Account;
import database.DAOAccount;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    public void login() throws IOException {
        DAOAccount daoAccount = new DAOAccount();
        boolean isAccountValid = daoAccount.checkValidAccount(new Account(username.getText().trim(), password.getText().trim()));
        Alert alert;

        if (isAccountValid) {
            Data.username = username.getText().trim();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ngon");
            alert.setHeaderText(null);
            alert.setContentText("Ngon");
            alert.showAndWait();

            loginBtn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(LoginApp.class.getResource("view/dashboard.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ga`");
            alert.setHeaderText(null);
            alert.setContentText("Ga`");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
