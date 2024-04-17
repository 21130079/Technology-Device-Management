package com.example.technologydevicemanagement;

import Model.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
    @FXML
    public TableView historyOrders_table;

    public void initialize() {


        // Điền dữ liệu vào TableView
        ObservableList<Order> Orders = FXCollections.observableArrayList();
        Orders.add(new Order("1",null,null,1));
        historyOrders_table = new TableView();
        historyOrders_table.setItems(Orders);
    }

}