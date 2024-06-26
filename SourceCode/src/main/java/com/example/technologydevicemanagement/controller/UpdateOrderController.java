package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.App;

import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.model.Order;


import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import com.example.technologydevicemanagement.service.OrderDevicesService;
import com.example.technologydevicemanagement.service.OrderService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Optional;


public class UpdateOrderController {
    @FXML
    private TextField orderid;

    @FXML
    private TableView<Device> productsTable;

    @FXML
    private DatePicker dateT;
    @FXML
    private TableColumn<Device, String> id;

    @FXML
    private TableColumn<Device, String> name;

    @FXML
    private TableColumn<Device, Integer> quantity;

    @FXML
    private TableColumn<Device, Double> price;

    @FXML
    private TableColumn<Device, Void> delete;
    LinkedHashMap<Device, Integer> productList;

    @FXML
    private void initialize() {
        getDataToUpdateOrder(Data.getInstance().getUpdatedOrder());
    }

    ObservableList<Device> products = FXCollections.observableArrayList();

    private void getDataToUpdateOrder(Order order) {
        // Điền dữ liệu từ đối tượng Order vào các trường tương ứng trên giao diện cập nhật đơn hàng
        orderid.setText(order.getIdOrder());

        // Lấy danh sách sản phẩm từ đối tượng Order
        productList = order.getListDevice();
        productsTable.setEditable(true);
        // Xóa dữ liệu cũ trong bảng
        id.setCellValueFactory(cellData -> cellData.getValue().idDeviceProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().nameDeviceProperty());
        quantity.setCellValueFactory(cellData -> {
            Device device = cellData.getValue();
            quantity.setEditable(true);
            int quantity = productList.getOrDefault(device, 0);
            return new SimpleIntegerProperty(quantity).asObject();
        });
        quantity.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        quantity.setOnEditCommit(event -> {
            Device device = event.getRowValue(); // Lấy ra sản phẩm từ dòng hiện tại

            int newQuantity = event.getNewValue(); // Lấy giá trị mới từ ô chỉnh sửa
            // Cập nhật số lượng trong productList

            productList.put(device, newQuantity);
        });

        price.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        delete.setCellFactory(column -> new TableCell<Device, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    products.remove(getIndex());
                    productList.remove(products.get(getIndex()));
                    productsTable.refresh();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });


        products.addAll(productList.keySet());
        productsTable.setItems(products);

        // Đặt ngày hóa đơn
        Date invoiceDate = order.getInvoiceDate(); // Giả sử điều này trả về một đối tượng Date
        LocalDate localDate = ((java.sql.Date) invoiceDate).toLocalDate();
        dateT.setValue(localDate);
    }

    @FXML
    public void close() {
        ((Stage) orderid.getScene().getWindow()).close();
    }

    @FXML
    public void update() {
        Alert ale = new Alert(Alert.AlertType.CONFIRMATION);
        ale.setTitle("Update Confirm");
        ale.setHeaderText(null);
            ale.setContentText("\n" +
                    "Are you sure you want to update order?");
        Optional<ButtonType> option = ale.showAndWait();
        if (option.get() == ButtonType.OK) {
            String id = orderid.getText();
            Date date = Date.valueOf(dateT.getValue());
            Order order = new Order(id, productList, date);
            new OrderService().updateData(order);
            ArrayList<Device> devices = new ArrayList<>();
            devices.addAll(products);
            new OrderDevicesService().updateDevicesInOrder(productList, id);
            ObservableList<Order> orders = FXCollections.observableArrayList();
            orders.addAll(new OrderService().getAllData());
            Data.getInstance().getHistoryTable().setItems(orders);
            Data.getInstance().getHistoryTable().refresh();
            close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update Order");
                alert.setHeaderText(null);
                alert.setContentText("Your order has been updated");
             alert.showAndWait();
        }
    }

    public void showDashBoardView() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/dashboard.fxml"));
        Scene scene = new Scene(root);

        // Lấy Stage hiện tại từ button
        Stage stage = (Stage) orderid.getScene().getWindow();

        Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Technology Equipment Sales Management System");

        // Set giao diện mới
        stage.setScene(scene);
        stage.setTitle("Technology Equipment Sales Management System");
        stage.show();
    }

}
