package com.example.technologydevicemanagement.controller;


import com.example.technologydevicemanagement.SaleManagementApp;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.model.QuantityCell;
import database.DaoDevice;
import database.DaoOrder;
import database.DaoOrderDevices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateOrderController {


    public TableView<Device> billTable;
    @FXML
    private TableView<Device> stocktable;
    @FXML
    Button payment_btn;
    @FXML
    private TableColumn<Device, String> idCol, idBillCol;
    @FXML
    private TableColumn<Device, String> nameCol, nameBillCol;
    @FXML
    private TableColumn<Device, String> imgBillCol, imgCol;
    @FXML
    private TableColumn<Device, Double> weightCol, weightBillCol;
    @FXML
    private TableColumn<Device, String> cateCol, cateBillCol;
    @FXML
    private TableColumn<Device, Integer> qISCol;
    @FXML
    private TableColumn<Device, Integer> quantityBillCol;
    @FXML
    private TableColumn<Device, Double> priceCol, amountBillCol;
    @FXML
    private TableColumn<Device, String> brandCol, brandBillCol;
    @FXML
    private TableColumn<Device, Device> confirmCol, editBillCol;
    ObservableList<Device> billDevices = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(cellData -> cellData.getValue().idDeviceProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameDeviceProperty());
        imgCol.setCellValueFactory(cellData -> cellData.getValue().urlImgProperty());
        brandCol.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        cateCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        weightCol.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        qISCol.setCellValueFactory(cellData -> cellData.getValue().quantityInStockProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        idBillCol.setCellValueFactory(cellData -> cellData.getValue().idDeviceProperty());
        nameBillCol.setCellValueFactory(cellData -> cellData.getValue().nameDeviceProperty());
        imgBillCol.setCellValueFactory(cellData -> cellData.getValue().urlImgProperty());
        brandBillCol.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        cateBillCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        weightBillCol.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        quantityBillCol.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        quantityBillCol.setCellFactory(col -> new QuantityCell());
        amountBillCol.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());

        idCol.setStyle("-fx-alignment: CENTER;");
        brandCol.setStyle("-fx-alignment: CENTER;");
        imgCol.setStyle("-fx-alignment: CENTER;");
        cateCol.setStyle("-fx-alignment: CENTER;");
        nameCol.setStyle("-fx-alignment: CENTER;");
        weightCol.setStyle("-fx-alignment: CENTER;");
        qISCol.setStyle("-fx-alignment: CENTER;");
        priceCol.setStyle("-fx-alignment: CENTER;");
        confirmCol.setStyle("-fx-alignment: CENTER;");
        idBillCol.setStyle("-fx-alignment: CENTER;");
        brandBillCol.setStyle("-fx-alignment: CENTER;");
        imgBillCol.setStyle("-fx-alignment: CENTER;");
        cateBillCol.setStyle("-fx-alignment: CENTER;");
        nameBillCol.setStyle("-fx-alignment: CENTER;");
        weightBillCol.setStyle("-fx-alignment: CENTER;");
        quantityBillCol.setStyle("-fx-alignment: CENTER;");
        amountBillCol.setStyle("-fx-alignment: CENTER;");
        editBillCol.setStyle("-fx-alignment: CENTER;");
        confirmCol.setCellFactory(param -> new TableCell<Device, Device>() {
            final Button btn = new Button("Add To Order");

            @Override
            protected void updateItem(Device item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                    {
                        btn.setOnAction(event -> {
                            Device device = getTableView().getItems().get(getIndex());
                            if (device != null) {
                                // Xử lý sự kiện khi nút được click
                                System.out.println("Add To Order button clicked for item: " + device.getIdDevice());
                                // Thêm mã xử lý của bạn ở đây, ví dụ:
                                // orderItem(device);
                                editBillCol.setCellFactory(param -> new TableCell<Device, Device>() {
                                    final Button btn = new Button("Delete");

                                    @Override
                                    protected void updateItem(Device item, boolean empty) {
                                        super.updateItem(item, empty);
                                        if (empty) {
                                            setGraphic(null);
                                        } else {
                                            setGraphic(btn);
                                            btn.setOnAction(actionEvent -> {
                                                billDevices.remove(getIndex());
                                                billTable.refresh();
                                            });
                                        }
                                    }
                                });
                                if (billDevices.contains(device)) {
                                    device.setQuantity(device.getQuantity() + 1);
                                    device.setAmount(device.getAmount() + device.getPrice());
                                    billTable.refresh();
                                } else {

                                    billDevices.add(device);
                                    billTable.setItems(billDevices);


                                }
                                device.setQuantityInStock(device.getQuantityInStock() - 1);
                                stocktable.refresh();
                            }
                        });
                    }

                }
            }
        });

        ObservableList<Device> products = FXCollections.observableArrayList();
        products.addAll(new DaoDevice().getAll());
        stocktable.setItems(products);
    }
    public void payment(){

            String id = new DaoOrder().insert();
            System.out.println(id);
            DaoOrderDevices daoOrderDevices = new DaoOrderDevices();
            DaoDevice daoDevice = new DaoDevice();
        for (Device device : billDevices) {
            for (int i = 0 ; i < device.getQuantity() ; i++) {
                daoOrderDevices.insert(device, id);
                daoDevice.decreaseQuantity(device, device.getQuantityInStock());
            }
        }
            billDevices.clear();
            billTable.refresh();
//            new MainViewController().refreshTable();
        restartApplication();
    }
    public void restartApplication() {
        Platform.runLater(() -> {
            try {
                // Tạo một FXMLLoader mới để tải lại cùng một fxml file
                Parent root = FXMLLoader.load(SaleManagementApp.class.getResource("view/sale-management.fxml"));
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
        });
    }

    public TableView<Device> getStocktable() {

        return stocktable;
    }
    //inner class
    public class QuantityCell extends TableCell<Device, Integer> {
        private final TextField textField = new TextField();
        private final Button addButton = new Button("+");
        private final Button minusButton = new Button("-");
        public QuantityCell() {

            textField.setPrefWidth(60);
            textField.setStyle("-fx-alignment: center");
            addButton.setPrefWidth(25);
            minusButton.setPrefWidth(25);
            addButton.setOnAction(event -> {
                int newValue = Integer.parseInt(textField.getText()) + 1;
                if(newValue<=getTableView().getItems().get(getIndex()).getQuantityInStock()&& newValue>0) {
                    commitEdit(newValue);
                }
            });

            minusButton.setOnAction(event -> {
                int newValue = Integer.parseInt(textField.getText()) - 1;
                if(newValue<=getTableView().getItems().get(getIndex()).getQuantityInStock()&& newValue>0) {
                    commitEdit(newValue);
                }
            });

            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        int newVal = Integer.parseInt(textField.getText());
                        if(newVal<=getTableView().getItems().get(getIndex()).getQuantityInStock()&& newVal>0) {
                            commitEdit(newVal);
                        }else{
                            cancelEdit();
                        }
                    }
                }
            });
        }

        @Override
        protected void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);

            if (empty || item == null) {
                setGraphic(null);
            } else {

                textField.setText(item.toString());
                setGraphic(new HBox(3,minusButton , textField, addButton));

            }
        }


        @Override
        public void startEdit() {
            super.startEdit();
            textField.requestFocus();
            textField.selectAll();
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();
            textField.setText(1+"");
            setGraphic(new HBox(3,minusButton , textField, addButton));
        }
        @Override
        public void commitEdit(Integer newValue) {
            int  intialQuantityInStock = new DaoDevice().getById(getTableRow().getItem().getIdDevice()).getQuantityInStock();
            super.commitEdit(newValue);
            textField.setText(newValue+"");
            getTableRow().getItem().setQuantity(newValue);
            int quantityInstock = intialQuantityInStock-newValue;
            getTableRow().getItem().setQuantityInStock(quantityInstock);
            getTableRow().getItem().setQuantity(newValue);
            updateAmount(newValue);
            setGraphic(new HBox(3,minusButton , textField, addButton));;

        }
        private void updateAmount(Integer newValue){
            double amount = newValue * ((Device) getTableRow().getItem()).getPrice();
            System.out.println(amount);
            getTableRow().getItem().setAmount(amount);
            getTableView().refresh();

        }
    }
}
