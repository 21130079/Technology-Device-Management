package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.model.Account;
import database.DaoAccount;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;

public class CreateAccountController {
    @FXML
    private CheckBox adminCheckBox;

    @FXML
    private CheckBox userCheckBox;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private ArrayList<String> selectedRoles = new ArrayList<>();
    @FXML
    private void initialize() {
        // Gắn sự kiện cho các CheckBox
        adminCheckBox.setOnAction(event -> handleCheckBox(adminCheckBox));
        userCheckBox.setOnAction(event -> handleCheckBox(userCheckBox));
    }

    private void handleCheckBox(CheckBox checkBox) {
        String role = checkBox.getText();
        if (checkBox.isSelected()) {
            // Nếu CheckBox được chọn, thêm vai trò vào danh sách đã chọn
            selectedRoles.add(role);
        } else {
            // Nếu CheckBox không được chọn, loại bỏ vai trò khỏi danh sách đã chọn
            selectedRoles.remove(role);
        }
    }

    @FXML
    private void createAccount() {
        // Sử dụng danh sách các vai trò đã chọn
        DaoAccount daoAccount =   new DaoAccount();
        String username= usernameField.getText();
        String passwd = passwordField.getText();
        if(username.trim().equals((""))){
            statusLabel.setText("Please enter username");
            return;
        }
        if(passwd.trim().equals((""))){
            statusLabel.setText("Please enter Password");
            return;
        }
        if(selectedRoles.size()==0){
            statusLabel.setText("Please check role");
            return;
        }
        if(daoAccount.checkExitsUsername(username)){
            statusLabel.setText("username is exist");
            return;
        }
        daoAccount.insert(new Account(username,passwd,selectedRoles));
        // Xóa danh sách đã chọn sau khi sử dụng
        selectedRoles.clear();
        passwordField.getScene().getWindow().hide();
        showAccountCreatedAlert();
    }
    private void showAccountCreatedAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Notification");
        alert.setHeaderText(null);
        alert.setContentText("Account has been created successfully!");
        alert.showAndWait();
    }
}
