package com.example.technologydevicemanagement;

import Model.Device;
import Model.Order;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;

public class SaleManagement extends Application {
    TableView tableView = new TableView();
    TableColumn<Order, String> orderIdColumn,productListColumn,paymentDateColumn,totalAmountColumn;

    private void getdata(){
        ObservableList<Order> orders = FXCollections.observableArrayList();
        LinkedHashMap<Device, Integer> products = new LinkedHashMap<>();
        products.put(new Device("1", "Laptop", "Electronics", 1000.0, "Brand A", new Date(), 2.5, "url1", 10), 1);
        products.put(new Device("2", "Smartphone", "Electronics", 800.0, "Brand B", new Date(), 0.3, "url2", 20), 2);
        orders.add(new Order("1", products, new Date(), 100.50));
        orders.add(new Order("1", products, new Date(), 100.50));
        orders.add(new Order("1", products, new Date(), 100.50));
        tableView.setItems(orders);
    }

    @Override
    public void start(Stage primaryStage) {

            BorderPane root = new BorderPane();

            // Top
            FlowPane topPane = new FlowPane();
            topPane.setId("topPane");
            topPane.setPrefWidth(600);
            topPane.setPrefHeight(61);
            topPane.setAlignment(javafx.geometry.Pos.CENTER_RIGHT);
            Label label = new Label("Sale Management");
            label.setPrefHeight(18.0);
            label.setPrefWidth(298.0);
            label.setFont(new Font("Century Schoolbook", 33.0));
            FlowPane.setMargin(label, new Insets(0, 100.0, 0, 0));
             topPane.getChildren().add(label);
            TextField searchField = new TextField();
            searchField.setId("searchField");
            searchField.setPromptText("search");
            searchField.setPrefWidth(184);
            searchField.setPrefHeight(30);
            topPane.getChildren().add(searchField);

            Button searchButton = new Button("Search");
            searchButton.setPrefWidth(70);
            searchButton.setPrefHeight(30);
            topPane.getChildren().add(searchButton);

            Button createOrderButton = new Button("Tạo đơn hàng");
        FlowPane.setMargin(createOrderButton, new Insets(0, 20, 0, 20));
            createOrderButton.setPrefWidth(120);
            createOrderButton.setPrefHeight(30);
            topPane.getChildren().add(createOrderButton);

            Button button = new Button("Button");
            button.setPrefHeight(30);
            FlowPane.setMargin(button, new Insets(0, 30, 0, 0));
            topPane.getChildren().add(button);

            root.setTop(topPane);

            // Center

            tableView.setPrefWidth(980);
            tableView.setPrefHeight(618);
            orderIdColumn = new TableColumn<>("Mã đơn hàng");
            productListColumn = new TableColumn<>("Danh sách sản phẩm");
             paymentDateColumn = new TableColumn<>("Ngày thanh toán");
            totalAmountColumn = new TableColumn<>("Tổng tiền");
            // Thêm cột chứa nút
            TableColumn<Order, Order> actionColumn = new TableColumn<>("Xem thêm");


                orderIdColumn.setPrefWidth(100);
                productListColumn.setPrefWidth(468);
                paymentDateColumn.setPrefWidth(170);
                totalAmountColumn.setPrefWidth(140);
                actionColumn.setPrefWidth(100);
                // can giua cac o
            orderIdColumn.setStyle("-fx-alignment: CENTER;");
            productListColumn.setStyle("-fx-alignment: CENTER;");
            paymentDateColumn.setStyle("-fx-alignment: CENTER;");
            totalAmountColumn.setStyle("-fx-alignment: CENTER;");
            actionColumn.setStyle("-fx-alignment: CENTER;");
            // thiêt lập du liêu cho các cột
            orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idOrderProperty());
            productListColumn.setCellValueFactory(cellData -> cellData.getValue().productListProperty());
            paymentDateColumn.setCellValueFactory(cellData -> cellData.getValue().invoiceDateProperty());
            totalAmountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty());



        tableView.getColumns().addAll(orderIdColumn, productListColumn, paymentDateColumn, totalAmountColumn, actionColumn);
        actionColumn.setCellFactory(param -> new TableCell<>() {
            final Button btn = new Button("Xem thêm");
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

            ScrollPane centerScrollPane = new ScrollPane(tableView);
            centerScrollPane.setPrefWidth(995);
            centerScrollPane.setPrefHeight(486);
            root.setCenter(centerScrollPane);

            Scene scene = new Scene(root, 995, 680);
            // Load CSS file

            primaryStage.setScene(scene);
             scene.getStylesheets().add("com/example/technologydevicemanagement/styles.css");
            primaryStage.setTitle("JavaFX Application");
            primaryStage.show();
        }


    public static void main(String[] args) {
        launch(args);
    }

}
