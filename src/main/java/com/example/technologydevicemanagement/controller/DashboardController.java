package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.LoginApp;
import database.DAOOrder;
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

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

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

//    public void dashboardICChartByDate() {
//        dashboard_ICChart.getData().clear();
//        XYChart.Series chart = new XYChart.Series<>();
//        new DAOOrder().setICChartByDate(chart);
//        dashboard_ICChart.getData().add(chart);
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        dashboardNC();
        dashboardTIByDate();
        dashboardTI();
        dashboardNOCChart();
//        dashboardICChartByDate();
    }
}
