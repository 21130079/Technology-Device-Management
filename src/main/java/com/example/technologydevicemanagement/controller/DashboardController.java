package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.CreateOrderApp;
import com.example.technologydevicemanagement.DashboardApp;
import com.example.technologydevicemanagement.LoginApp;
import com.example.technologydevicemanagement.SaleManagementApp;
import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.model.Order;
import database.DAOOrder;
import database.DAOOrderDevices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

public class DashboardController implements Initializable {

    private Order orderUpdate;
    @FXML
    TableView historyOrderTable;
    @FXML
    TableColumn<Order, String> orderIdColumn, productListColumn, paymentDateColumn;
    @FXML
    TableColumn<Order, Double> amountColumn;

    @FXML
    TableColumn<Order, Order> featuresColumn;


    @FXML
    private AreaChart<?, ?> dashboard_ICChart;

    @FXML
    private Label dashboard_NC;

    @FXML
    private BarChart<?, ?> dashboard_NOCChart;

    @FXML
    private Label dashboard_TI;

    @FXML
    private Label dashboard_TIncome;


    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane historyOrders;
    @FXML
    private Label username;

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

    public void displayUsername() {
        String name = Data.username;
        name = name.toUpperCase();
        username.setText(name);
    }

    public void logout() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Logout");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logout.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader(LoginApp.class.getResource("view/login.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void dashboardNC() {
        int nc = new DAOOrder().getAll().size();
        dashboard_NC.setText(String.valueOf(nc));
    }

    public void dashboardTIByDate() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        double ti = new DAOOrder().getTIByDate(sqlDate);
        dashboard_TI.setText("$" + String.valueOf(ti));
    }

    public void dashboardTI() {
        dashboard_TIncome.setText("$" + String.valueOf(new DAOOrder().getTI()));
    }

    public void dashboardNOCChart() {
        dashboard_NOCChart.getData().clear();
        XYChart.Series chart = new XYChart.Series<>();
        new DAOOrder().setNOCChartByDate(chart);
        dashboard_NOCChart.getData().add(chart);
    }

    public void dashboardICChartByDate() {
        Date date = new Date();
        long currentDate = date.getTime();
        ArrayList<Long> sqlDates = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);

        dates.add(formattedDate);
        sqlDates.add(currentDate);



        // Lấy ra 5 ngày gần nhất
        for (int i = 0; i < 4; i++) {
            long oneDayInMillis = 24 * 60 * 60 * 1000; // 1 ngày tính bằng milliseconds
            Date recentDate = new Date(date.getTime() - (i + 1) * oneDayInMillis);
            sqlDates.add(recentDate.getTime());

            String formattedRecentDate = dateFormat.format(recentDate);
            dates.add(formattedRecentDate);
        }


        dashboard_ICChart.getData().clear();
        XYChart.Series chart = new XYChart.Series<>();
        chart.getData().add(new XYChart.Data<>(dates.getFirst(), new DAOOrder().getTIByDate(new java.sql.Date(sqlDates.getFirst()))));
        chart.getData().add(new XYChart.Data<>(dates.get(1), new DAOOrder().getTIByDate(new java.sql.Date(sqlDates.get(1)))));
        chart.getData().add(new XYChart.Data<>(dates.get(2), new DAOOrder().getTIByDate(new java.sql.Date(sqlDates.get(2)))));
        chart.getData().add(new XYChart.Data<>(dates.get(3), new DAOOrder().getTIByDate(new java.sql.Date(sqlDates.get(3)))));
        chart.getData().add(new XYChart.Data<>(dates.get(4), new DAOOrder().getTIByDate(new java.sql.Date(sqlDates.get(4)))));

        dashboard_ICChart.getData().add(chart);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      displayUsername();
        dashboardNC();
        dashboardTIByDate();
        dashboardTI();
        dashboardNOCChart();
        dashboardICChartByDate();
        data();
    }

    public void openCreateOrder() {

            try {
                // Tạo một FXMLLoader mới để tải lại cùng một fxml file
                Parent root = FXMLLoader.load(LoginApp.class.getResource("view/create-order.fxml"));
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

        Stage stage = (Stage) dashboard_TI.getScene().getWindow();
        stage.hide();
    }
    public void openUpdateOrder() {

        try {
            // Tạo một FXMLLoader mới để tải lại cùng một fxml file
            Parent root = FXMLLoader.load(LoginApp.class.getResource("view/update-order.fxml"));
            // Tạo một Scene mới
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            // Đặt scene cho stage
            stage.setScene(scene);

            // Hiển thị stage
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) dashboard_TI.getScene().getWindow();
        stage.hide();

    }



    @FXML
    private void showHistoryOrders() {
        // Hide other forms
        dashboard_form.setVisible(false);

        // Show history orders
        historyOrders.setVisible(true);
    }
    @FXML
    private void showDashboard(){
        dashboard_form.setVisible(true);
        historyOrders.setVisible(false);
    }
    public void data() {


        // can giua cac o
        orderIdColumn.setStyle("-fx-alignment: CENTER;");
        productListColumn.setStyle("-fx-alignment: CENTER;");
        paymentDateColumn.setStyle("-fx-alignment: CENTER;");
        amountColumn.setStyle("-fx-alignment: CENTER;");

        featuresColumn.setStyle("-fx-alignment: CENTER;");
        // thiêt lập du liêu cho các cột
        orderIdColumn.setCellValueFactory(cellData -> cellData.getValue().idOrderProperty());
        productListColumn.setCellValueFactory(cellData -> cellData.getValue().productListProperty());
        paymentDateColumn.setCellValueFactory(cellData -> cellData.getValue().invoiceDateProperty());
        amountColumn.setCellValueFactory(cellData -> cellData.getValue().amountProperty().asObject());
//        Image upimg = new Image(DashboardController.class.getResourceAsStream("../resources/img/edit.png"));
//        ImageView iconUpdate = new ImageView(upimg);
//        iconUpdate.setFitWidth(16);
//        iconUpdate.setFitHeight(16);
//        ImageView iconDelete = new ImageView(new Image(getClass().getResourceAsStream("../resources/img/edit.png")));
//        iconDelete.setFitWidth(16);
//        iconDelete.setFitHeight(16);


        featuresColumn.setCellFactory(param -> new TableCell<Order, Order>() {

            final Button btnUpdate = new Button("Update");

            final Button btnDelete = new Button("Delete");
            final HBox buttonsBox = new HBox(btnUpdate, btnDelete);

//                btnUpdate.setGraphic(iconUpdate);
//                btnDelete.setGraphic(iconDelete);
                {
                    // Thiết lập hành động cho nút Update
                    btnUpdate.setOnAction(event -> {
                        Order order = getTableView().getItems().get(getIndex());
                        updateOrder(order);
                    });

                    // Thiết lập hành động cho nút Delete
                    btnDelete.setOnAction(event -> {
                        Order order = getTableView().getItems().get(getIndex());

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirm Delete");
                        alert.setHeaderText(null);
                        alert.setContentText("Are you sure you want to delete order?");
                        Optional<ButtonType> option = alert.showAndWait();

                        if (option.get().equals(ButtonType.OK)) {
                            deleteOrder(order);
                        }
                    });
                    buttonsBox.setStyle("-fx-alignment: CENTER;");

                }


            @Override
            protected void updateItem(Order item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonsBox);

                }
            }

            private void updateOrder(Order order) {
                // Thêm logic để hiển thị chi tiết đơn hàng
                System.out.println("Order details: " + order);
                Data.getInstance().setUpdatedOrder(order);
                openUpdateOrder();

                // Bạn có thể mở một cửa sổ mới hoặc cập nhật giao diện để hiển thị chi tiết đơn hàng
            }

            private void deleteOrder(Order order) {
                // Thêm logic để xóa đơn hàng
                System.out.println("Delete order: " + order);
                new DAOOrder().delete(order.getIdOrder());
                getdata();
                refreshTable();
                // Bạn có thể xóa đơn hàng khỏi dữ liệu hoặc thực hiện hành động tương ứng
            }
        });


        getdata();

    }



    @FXML
    private void getdata() {
        ObservableList<Order> orders = FXCollections.observableArrayList();
        orders.addAll(new DAOOrder().getAll());
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

    public void refreshTable() {
        historyOrderTable.refresh();
    }

    public Order getOrderUpdate() {
        return orderUpdate;
    }
}
