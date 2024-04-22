package com.example.technologydevicemanagement.controller;
import com.example.technologydevicemanagement.CreateOrderApp;
import com.example.technologydevicemanagement.model.Order;
import database.DaoOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController {
    CreateOrderApp createOrder = new CreateOrderApp();
    @FXML
    TableView historyOrderTable;
    @FXML
    TableColumn<Order, String> orderIdColumn,productListColumn,paymentDateColumn;
    @FXML
    TableColumn<Order, Double> amountColumn;
    @FXML
    TableColumn<Order, Order> actionColumn;
    @FXML
    Button createOrder_btn;
    @FXML
    public void initialize(){

        // Thêm cột chứa nút

        orderIdColumn.setPrefWidth(130);
        productListColumn.setPrefWidth(555);
        paymentDateColumn.setPrefWidth(200);
        amountColumn.setPrefWidth(180);
        actionColumn.setPrefWidth(130);
        // can giua cac o
        orderIdColumn.setStyle("-fx-alignment: CENTER;");
        productListColumn.setStyle("-fx-alignment: CENTER;");
        paymentDateColumn.setStyle("-fx-alignment: CENTER;");
        amountColumn.setStyle("-fx-alignment: CENTER;");
        actionColumn.setStyle("-fx-alignment: CENTER;");
        // thiêt lập du liêu cho các cột
        orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idOrderProperty());
        productListColumn.setCellValueFactory(cellData -> cellData.getValue().productListProperty());
        paymentDateColumn.setCellValueFactory(cellData -> cellData.getValue().invoiceDateProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());


        actionColumn.setCellFactory(param -> new TableCell<Order, Order>() {
            final Button btn = new Button("See Details");

            @Override
            protected void updateItem(Order item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }

        });


        getdata();

    }
    @FXML
   private void getdata(){
       ObservableList<Order> orders = FXCollections.observableArrayList();
        orders.addAll(new DaoOrder().getAll());
       historyOrderTable.setItems(orders);
   }
    public void showCreateOrderView(javafx.event.ActionEvent actionEvent) throws IOException {
      
        FXMLLoader loader = new FXMLLoader(CreateOrderApp.class.getResource("view/create-order.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Lấy Stage hiện tại từ button
        Stage stage = new Stage();
        // Set giao diện mới
        stage.setScene(scene);
        stage.show();
    }
    public void refreshTable(){
        historyOrderTable.refresh();
    }
}
