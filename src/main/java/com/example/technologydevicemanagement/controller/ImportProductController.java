package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.model.Device;
import database.DAODevice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Date;

public class ImportProductController {
    @FXML
    private TableView<Device> stocktable;
    @FXML
    private TableColumn<Device, String> idCol;
    @FXML
    private TableColumn<Device, String> nameCol;
    @FXML
    private TableColumn<Device, String> imgCol;
    @FXML
    private TableColumn<Device, Double> weightCol;
    @FXML
    private TableColumn<Device, String> cateCol;
    @FXML
    private TableColumn<Device, Integer> qISCol;
    @FXML
    private TableColumn<Device, Double> priceCol;
    @FXML
    private TableColumn<Device, String> brandCol;

    @FXML
    private Button addBtn, reseetBtn;

    @FXML
    private TextField inputID, inputName, inputImg, inputWeight, inputCate, inputQuantity, inputPrice, inputBrand;
    @FXML
    private DatePicker inputManufacturing;

    private DAODevice daoDevice = new DAODevice();
    public void initialize() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idDeviceProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameDeviceProperty());
        imgCol.setCellValueFactory(cellData -> cellData.getValue().urlImgProperty());
        brandCol.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        cateCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        weightCol.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        qISCol.setCellValueFactory(cellData -> cellData.getValue().quantityInStockProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        ObservableList<Device> products = FXCollections.observableArrayList();
        products.addAll(new DAODevice().getAll());
        stocktable.setItems(products);

        inputID.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<Device> filteredList = FXCollections.observableArrayList();
            filteredList.addAll(new DAODevice().getAll());
            if(newValue.isEmpty() ||  newValue.isBlank()) {
                stocktable.setItems(filteredList);
                return;
            }

            String keyword = newValue.toLowerCase();
            filteredList.clear();
            for (Device device : new DAODevice().getAll()) {
                if (device.getIdDevice().toLowerCase().contains(keyword)) {
                    filteredList.add(device);
                }
            }
            stocktable.setItems(filteredList);
        });

        reseetBtn.setOnAction(event -> {
            inputID.clear();
            inputName.clear();
            inputImg.clear();
            inputWeight.clear();
            inputCate.clear();
            inputQuantity.clear();
            inputPrice.clear();
            inputBrand.clear();
            inputManufacturing.setValue(null);
        });
        addBtn.setOnAction(event -> {
            if (inputID.getText().isEmpty() || inputID.getText().isBlank()) {
                return;
            }
            if(daoDevice.getById(inputID.getText()) == null) {
                    Device device = new Device(inputID.getText(), inputName.getText(), inputCate.getText(), Double.parseDouble(inputPrice.getText()), inputBrand.getText(), java.sql.Date.valueOf(inputManufacturing.getValue()), Double.parseDouble(inputWeight.getText()), inputImg.getText(), Integer.parseInt(inputQuantity.getText()));
                    daoDevice.add(device);
                    stocktable.setItems(FXCollections.observableArrayList(daoDevice.getById(inputID.getText())));
            }else{
                Device device = daoDevice.getById(inputID.getText());
                int quantityInput = Integer.parseInt(inputQuantity.getText());
                int quantity = device.getQuantityInStock();
                daoDevice.decreaseQuantity(device, quantity+quantityInput);
                stocktable.setItems(FXCollections.observableArrayList(daoDevice.getById(inputID.getText())));
            }
        });
    }

}
