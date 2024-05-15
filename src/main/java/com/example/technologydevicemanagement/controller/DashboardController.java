package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.LoginApp;
import database.DAOOrder;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class DashboardController implements Initializable {

    @FXML
    private Button avaialbeFD_btn;

    @FXML
    private Button availableFD_addBtn;

    @FXML
    private Button availableFD_clearBtn;

    @FXML
    private Button availableFD_deleteBtn;

    @FXML
    private AnchorPane availableFD_form;

    @FXML
    private TextField availableFD_productID;

    @FXML
    private TextField availableFD_productName;

    @FXML
    private TextField availableFD_productPrice;

    @FXML
    private ComboBox<?> availableFD_productStatus;

    @FXML
    private ComboBox<?> availableFD_productType;

    @FXML
    private TextField availableFD_search;

    @FXML
    private Button availableFD_updateBtn;

    @FXML
    private Button close;

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
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane main_form;

    @FXML
    private Button minimize;

    @FXML
    private Button order_addBtn;

    @FXML
    private TextField order_amount;

    @FXML
    private Label order_balance;

    @FXML
    private AnchorPane order_form;

    @FXML
    private Button order_payBtn;

    @FXML
    private ComboBox<?> order_productID;

    @FXML
    private ComboBox<?> order_productName;

    @FXML
    private Spinner<?> order_quantity;

    @FXML
    private Button order_receiptBtn;

    @FXML
    private Button order_removeBtn;

    @FXML
    private Label order_total;

    @FXML
    private Label username;

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
    }

    public void openCreateOrder() {
        Platform.runLater(() -> {
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
        });
        Stage stage = (Stage) dashboard_TI.getScene().getWindow();
        stage.hide();
    }
}
