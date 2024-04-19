package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.model.Device;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Date;

public class CreateOrderController {


    @FXML
    private TableView<Device> tableView;

    @FXML
    private TableColumn<Device, String> idColumn;

    @FXML
    private TableColumn<Device, String> nameColumn;

    @FXML
    private TableColumn<Device, String> quantityColumn;

    @FXML
    private TableColumn<Device, String> priceColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idDeviceProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameDeviceProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityDeviceProperty());
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceDeviceProperty());


        ObservableList<Device> products = FXCollections.observableArrayList(
                new Device("1", "Laptop", "Electronics", 1000.0, "Brand A", new Date(), 2.5, "url1", 10)
        );
        tableView.setItems(products);
    }
}
