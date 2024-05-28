package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.App;
import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.model.Order;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import com.example.technologydevicemanagement.service.OrderService;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

public class DashboardController implements Initializable {
    private static Stage dashboardStage;
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

    @FXML
    private Button date_btn;

    @FXML
    private Button month_btn;

    @FXML
    private Button year_btn;

    @FXML
    private Button createOrderBtn, historyOrderBtn, accountBtn, importBtn;

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
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/login.fxml"));
                Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));
                Stage stage = new Stage();
                stage.setTitle("Technology Equipment Sales Management System");
                stage.getIcons().add(icon);
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void dashboardNC() {
        int nc = new OrderService().getAllData().size();
        dashboard_NC.setText(String.valueOf(nc));
    }

    public void dashboardTIByDate() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        double ti = new OrderService().getTIByDate(sqlDate);
        dashboard_TI.setText("$" + String.valueOf(ti));
    }

    public void dashboardTIByMonth() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        double ti = new OrderService().getTIByMonth(sqlDate);
        dashboard_TI.setText("$" + String.valueOf(ti));
    }

    public void dashboardTIByYear() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        double ti = new OrderService().getTIByYear(sqlDate);
        dashboard_TI.setText("$" + String.valueOf(ti));
    }

    public void dashboardTI() {
        dashboard_TIncome.setText("$" + String.valueOf(new OrderService().getTI()));
    }

    public void dashboardNOCChartByDate() {
        dashboard_NOCChart.getData().clear();
        XYChart.Series chart = new XYChart.Series<>();
        new OrderService().setNOCChartByDate(chart);
        dashboard_NOCChart.getData().add(chart);
        dashboard_NOCChart.setLegendVisible(false);
    }

    public void dashboardNOCChartByMonth() {
        dashboard_NOCChart.getData().clear();
        XYChart.Series chart = new XYChart.Series<>();
        new OrderService().setNOCChartByMonth(chart);
        dashboard_NOCChart.getData().add(chart);
        dashboard_NOCChart.setLegendVisible(false);
    }

    public void dashboardNOCChartByYear() {
        dashboard_NOCChart.getData().clear();
        XYChart.Series chart = new XYChart.Series<>();
        new OrderService().setNOCChartByYear(chart);
        dashboard_NOCChart.getData().add(chart);
        dashboard_NOCChart.setLegendVisible(false);
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
        dashboard_ICChart.setLegendVisible(false);
        XYChart.Series chart = new XYChart.Series<>();
        chart.getData().add(new XYChart.Data<>(dates.get(0), new OrderService().getTIByDate(new java.sql.Date(sqlDates.get(0)))));
        chart.getData().add(new XYChart.Data<>(dates.get(1), new OrderService().getTIByDate(new java.sql.Date(sqlDates.get(1)))));
        chart.getData().add(new XYChart.Data<>(dates.get(2), new OrderService().getTIByDate(new java.sql.Date(sqlDates.get(2)))));
        chart.getData().add(new XYChart.Data<>(dates.get(3), new OrderService().getTIByDate(new java.sql.Date(sqlDates.get(3)))));
        chart.getData().add(new XYChart.Data<>(dates.get(4), new OrderService().getTIByDate(new java.sql.Date(sqlDates.get(4)))));

        dashboard_ICChart.getData().add(chart);
    }

    public void dashboardICChartByMonth() {
        Date date = new Date();
        long currentDate = date.getTime();
        ArrayList<Long> sqlDates = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);

        dates.add(formattedDate);
        sqlDates.add(currentDate);


        // Lấy ra 5 tháng gần nhất
        for (int i = 0; i < 4; i++) {
            long oneMonthInMillis = 24 * 60 * 60 * 1000 * 30; // 1 tháng tính bằng milliseconds
            Date recentMonth = new Date(date.getTime() - (i + 1) * oneMonthInMillis);
            sqlDates.add(recentMonth.getTime());

            String formattedRecentMonth = dateFormat.format(recentMonth);
            dates.add(formattedRecentMonth);
        }


        dashboard_ICChart.getData().clear();
        dashboard_ICChart.setLegendVisible(false);
        XYChart.Series chart = new XYChart.Series<>();
        chart.getData().add(new XYChart.Data<>(dates.get(0), new OrderService().getTIByMonth(new java.sql.Date(sqlDates.get(0)))));
        chart.getData().add(new XYChart.Data<>(dates.get(1), new OrderService().getTIByMonth(new java.sql.Date(sqlDates.get(1)))));
        chart.getData().add(new XYChart.Data<>(dates.get(2), new OrderService().getTIByMonth(new java.sql.Date(sqlDates.get(2)))));
        chart.getData().add(new XYChart.Data<>(dates.get(3), new OrderService().getTIByMonth(new java.sql.Date(sqlDates.get(3)))));
        chart.getData().add(new XYChart.Data<>(dates.get(4), new OrderService().getTIByMonth(new java.sql.Date(sqlDates.get(4)))));

        dashboard_ICChart.getData().add(chart);
    }

    public void dashboardICChartByYear() {
        Date date = new Date();
        long currentDate = date.getTime();
        ArrayList<Long> sqlDates = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(currentDate);

        dates.add(formattedDate);
        sqlDates.add(currentDate);


        // Lấy ra 5 năm gần nhất
        for (int i = 0; i < 4; i++) {
            long oneYearInMillis = 24 * 60 * 60 * 1000 * 30 * 12; // 1 năm tính bằng milliseconds
            Date recentYear = new Date(date.getTime() - (i + 1) * oneYearInMillis);
            sqlDates.add(recentYear.getTime());

            String formattedRecentYear = dateFormat.format(recentYear);
            dates.add(formattedRecentYear);
        }


        dashboard_ICChart.getData().clear();
        dashboard_ICChart.setLegendVisible(false);
        XYChart.Series chart = new XYChart.Series<>();
        chart.getData().add(new XYChart.Data<>(dates.get(0), new OrderService().getTIByYear(new java.sql.Date(sqlDates.get(0)))));
        chart.getData().add(new XYChart.Data<>(dates.get(1), new OrderService().getTIByYear(new java.sql.Date(sqlDates.get(1)))));
        chart.getData().add(new XYChart.Data<>(dates.get(2), new OrderService().getTIByYear(new java.sql.Date(sqlDates.get(2)))));
        chart.getData().add(new XYChart.Data<>(dates.get(3), new OrderService().getTIByYear(new java.sql.Date(sqlDates.get(3)))));
        chart.getData().add(new XYChart.Data<>(dates.get(4), new OrderService().getTIByYear(new java.sql.Date(sqlDates.get(4)))));

        dashboard_ICChart.getData().add(chart);
    }

    public void handleRole() {
        if (Data.role.equals("Sale staff")) {
            accountBtn.setVisible(false);
            importBtn.setVisible(false);
            month_btn.setVisible(false);
            year_btn.setVisible(false);
            date_btn.setVisible(false);

        } else if (Data.role.equals("Warehouse staff")) {
            createOrderBtn.setVisible(false);
            historyOrderBtn.setVisible(false);
            month_btn.setVisible(false);
            year_btn.setVisible(false);
            date_btn.setVisible(false);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        handleRole();
        dashboardNC();
        dashboardTIByDate();
        dashboardTI();
        dashboardNOCChartByDate();
        dashboardICChartByDate();
        data();
    }

    public void openCreateOrder() {
            try {
                // Tạo một FXMLLoader mới để tải lại cùng một fxml file
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/create-order.fxml"));
                // Tạo một Scene mới
                Scene scene = new Scene(root, 1200, 700);
                Stage stage = new Stage();
                stage.setTitle("Create Order");
                // Đặt scene cho stage
                stage.setScene(scene);
                Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));
            stage.setTitle("Technology Equipment Sales Management System");
            stage.getIcons().add(icon);
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
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/update-order.fxml"));
            // Tạo một Scene mới
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Update Order");
            // Đặt scene cho stage
            Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));

            stage.setTitle("Technology Equipment Sales Management System");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            Data.getInstance().setHistoryTable(historyOrderTable);
            // Hiển thị stage
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
//        Stage stage = (Stage) dashboard_TI.getScene().getWindow();
//        stage.hide();

    }


    @FXML
    private void showHistoryOrders() {
        // Hide other forms
        dashboard_form.setVisible(false);

        // Show history orders
        historyOrders.setVisible(true);
    }

    @FXML
    private void showDashboard() {
        dashboard_form.setVisible(true);
        historyOrders.setVisible(false);
    }

    @FXML
    private void openAccountManagement() {
        try {
            // Tạo một FXMLLoader mới để tải lại cùng một fxml file
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/account-management.fxml"));
            // Tạo một Scene mới
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Account Management");
            // Đặt scene cho stage
            Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));

            stage.setTitle("Technology Equipment Sales Management System");
            stage.getIcons().add(icon);
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
    private void openImportProduct() {
        try {
            // Tạo một FXMLLoader mới để tải lại cùng một fxml file
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/import-product.fxml"));
            // Tạo một Scene mới
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Import Product");
            // Đặt scene cho stage
            Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));

            stage.setTitle("Technology Equipment Sales Management System");
            stage.getIcons().add(icon);
            stage.setScene(scene);

            // Hiển thị stage
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }

        dashboardStage = (Stage) dashboard_TI.getScene().getWindow();
        dashboardStage.hide();
//        Stage stage = (Stage) dashboard_TI.getScene().getWindow();
//        stage.hide();
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


        featuresColumn.setCellFactory(param -> new TableCell<Order, Order>() {
            final Button btnUpdate = new Button("Update");
            final Button btnDelete = new Button("Delete");
            final HBox buttonsBox = new HBox(btnUpdate, btnDelete);

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

                buttonsBox.setAlignment(Pos.CENTER);
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
                new OrderService().deleteData(order.getIdOrder());
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
        orders.addAll(new OrderService().getAllData());
        historyOrderTable.setItems(orders);
    }

    public void showCreateOrderView(javafx.event.ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/create-order.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        // Lấy Stage hiện tại từ button
        Stage stage = new Stage();

        Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));

        stage.setTitle("Technology Equipment Sales Management System");
        stage.getIcons().add(icon);
        // Set giao diện mới
        stage.setScene(scene);
        stage.show();
    }

    public void showRevenueByDate() {
        dashboardTIByDate();
        dashboardNOCChartByDate();
        dashboardICChartByDate();
    }

    public void showRevenueByMonth() {
        dashboardTIByMonth();
        dashboardNOCChartByMonth();
        dashboardICChartByMonth();
    }

    public void showRevenueByYear() {
        dashboardTIByYear();
        dashboardNOCChartByYear();
        dashboardICChartByYear();
    }

    public void refreshTable() {
        historyOrderTable.refresh();
    }

    public Order getOrderUpdate() {
        return orderUpdate;
    }

    public static void getDashboard() {
        if (dashboardStage != null) {
            dashboardStage.show();
        }
    }
}
