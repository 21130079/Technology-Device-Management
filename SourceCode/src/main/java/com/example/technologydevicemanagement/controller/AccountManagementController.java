package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.App;
import com.example.technologydevicemanagement.model.Account;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import service.AccountService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class AccountManagementController {
    @FXML
    private TableView<Account> stocktable;
    @FXML
    private TableColumn<Account, String> usernameCol;
    @FXML
    private TableColumn<Account, String> passwordCol;
    @FXML
    private TableColumn<Account, String> roleCol;
    @FXML
    private TableColumn<Account, String> actionsCol;

    @FXML
    private CheckBox adminCheckBox, saleCheckBox, warehouseCheckBox;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    private ArrayList<String> selectedRoles = new ArrayList<>();

    private AccountService accountService = new AccountService();

    private ObservableList<Account> accounts = FXCollections.observableArrayList();


    public void initialize() {
        adminCheckBox.setOnAction(event -> handleCheckBox(adminCheckBox));
        saleCheckBox.setOnAction(event -> handleCheckBox(saleCheckBox));
        warehouseCheckBox.setOnAction(event -> handleCheckBox(warehouseCheckBox));
        usernameCol.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        passwordCol.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        roleCol.setCellValueFactory(cellData -> cellData.getValue().rolesProperty());
        actionsCol.setCellFactory(param -> new TableCell<Account, String>() {
            final Button editBtn = new Button("Edit");
            final Button deleteBtn = new Button("Delete");

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    editBtn.setOnAction(event -> {
                        Account account = getTableView().getItems().get(getIndex());
                        usernameField.setText(account.getUsername());
                        passwordField.setText(account.getPasswd());
                        selectedRoles.clear();
                        statusLabel.setText("Will update account");
                    });
                    deleteBtn.setOnAction(event -> {
                        Account account = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmation Dialog");
                        alert.setHeaderText("Delete Account");
                        alert.setContentText("Are you sure you want to delete this account?");

                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK){
                            accountService.deleteData(account.getUsername());
                            ObservableList<Account> accounts = FXCollections.observableArrayList();
                            accounts.addAll(accountService.getAllData());
                            stocktable.setItems(accounts);
                        } else {
                            // User chose CANCEL or closed the dialog
                        }

                    });

                    HBox buttons = new HBox(5, editBtn, deleteBtn); // 5 is the spacing between buttons
                    buttons.setAlignment(Pos.CENTER); // Set the alignment to center
                    setGraphic(buttons);
                }
            }
        });

        accounts.addAll(accountService.getAllData());
        stocktable.setItems(accounts);
        searchHandle();

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
        if(accountService.isExistUsername(username)){
            statusLabel.setText("username is exist");
            return;
        }

        accountService.saveData(new Account(username,passwd,selectedRoles));
        usernameField.clear();
        passwordField.clear();
        accounts.clear();
        accounts.addAll(accountService.getAllData());
        stocktable.setItems(accounts);
        statusLabel.setText("Create account successfully");
    }
    public void updateAccount() {
        Account account = accountService.getDataById(usernameField.getText());
        System.out.println(account.getRoles());
        if (account != null) {
            account.setPasswd(passwordField.getText());
            account.setRoles(selectedRoles);
            System.out.println(account.getRoles());
            accountService.updateData(account);
            usernameField.clear();
            passwordField.clear();
            statusLabel.setText("Update account successfully");
            accounts.clear();
            accounts.addAll(accountService.getAllData());
            stocktable.setItems(accounts);
        } else{
            statusLabel.setText("Account not found");
        }
    }

    @FXML
    private void searchHandle() {
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {

            accounts.addAll(new AccountService().getAllData());
            if (newValue.isEmpty() || newValue.isBlank()) {
                stocktable.setItems(accounts);
                return;
            }

            String keyword = newValue.toLowerCase();
            accounts.clear();
            for (Account account : new AccountService().getAllData()) {
                if (account.getUsername().contains(keyword)) {
                    accounts.add(account);
                }
            }
            stocktable.setItems(accounts);
        });
    }
    public void restartApplication() {
        try {
            // Tạo một FXMLLoader mới để tải lại cùng một fxml file
            Parent root = FXMLLoader.load(App.class.getResource("view/dashboard.fxml"));
            // Tạo một Scene mới
            Scene scene = new Scene(root, 1200, 700);
            Stage stage = new Stage();

            Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));

            stage.setTitle("Technology Equipment Sales Management System");
            stage.getIcons().add(icon);
            // Đặt scene cho stage
            stage.setScene(scene);

            // Hiển thị stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void backToDashboard() {
        restartApplication();
        Stage stage = (Stage) statusLabel.getScene().getWindow();
        stage.close();
    }
}
