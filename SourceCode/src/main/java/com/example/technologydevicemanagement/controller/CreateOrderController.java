package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import com.example.technologydevicemanagement.model.Device;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import com.example.technologydevicemanagement.service.DeviceService;
import com.example.technologydevicemanagement.service.OrderDevicesService;
import com.example.technologydevicemanagement.service.OrderService;


import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;

import static com.example.technologydevicemanagement.controller.DashboardController.getDashboard;

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
    private TextField searchField;
    @FXML
    private Button clearBtn;

    @FXML
    public void initialize() {

        idCol.setCellValueFactory(cellData -> cellData.getValue().idDeviceProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameDeviceProperty());
        setImageOnCol(imgCol);
        brandCol.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        cateCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        weightCol.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        qISCol.setCellValueFactory(cellData -> cellData.getValue().quantityInStockProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());

        idBillCol.setCellValueFactory(cellData -> cellData.getValue().idDeviceProperty());
        nameBillCol.setCellValueFactory(cellData -> cellData.getValue().nameDeviceProperty());
        setImageOnCol(imgBillCol);
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

                                System.out.println("Add To Order button clicked for item: " + device.getIdDevice());

                                editBillCol.setCellFactory(param -> new TableCell<Device, Device>() {
                                    final Button btn = new Button("Delete");

                                    @Override
                                    protected void updateItem(Device item1, boolean empty) {
                                        super.updateItem(item1, empty);
                                        if (empty) {
                                            setGraphic(null);
                                        } else {
                                            setGraphic(btn);
                                            btn.setOnAction(actionEvent -> {
                                                billDevices.remove(getIndex());
                                                // reset quantity
                                                System.out.println(device);
                                                device.setQuantityInStock(device.getQuantityInStock() + device.getQuantity());
                                                device.setQuantity(1);

                                                billTable.refresh();
                                                stocktable.refresh();

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
        products.addAll(new DeviceService().getAllData());
        stocktable.setItems(products);

        searchHandle();
        clearBtn.setOnAction(event -> {
            searchField.setText("");

        });
    }


    public void backToDashboard() {
        restartApplication();

    }
    @FXML
    private HashMap<String, Image> imageCache = new HashMap<>();

    private void setImageOnCol(TableColumn<Device, String> imgCol) {
        imgCol.setCellValueFactory(cellData -> cellData.getValue().urlImgProperty());
        imgCol.setCellFactory(column -> {
            TableCell<Device, String> cell = new TableCell<Device, String>() {
                private final ImageView imageView = new ImageView();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.equals("null")) {
                        setGraphic(null);
                    } else {
                        Image cachedImage = imageCache.get(item);
                        if (cachedImage != null) {
                            imageView.setImage(cachedImage);
                        } else {
                            URL imageUrl = getClass().getResource(item);
                            if (imageUrl != null) {
                                Image image = new Image(imageUrl.toExternalForm());
                                imageView.setImage(image);
                                imageCache.put(item, image); // Lưu hình ảnh vào cache
                            } else {
                                // Xử lý khi không thể tải hình ảnh
                                System.err.println("Không thể tải hình ảnh từ URL: " + item);
                            }
                        }
                        imageView.setFitHeight(70);
                        imageView.setPreserveRatio(true);
                        setGraphic(imageView);
                    }
                }
            };
            return cell;
        });
    }
    public void payment() {
        String id = new OrderService().addOrder();
        System.out.println(id);
        OrderDevicesService OrderDevicesService = new OrderDevicesService();
        DeviceService DeviceService = new DeviceService();
        for (Device device : billDevices) {
            for (int i = 0; i < device.getQuantity(); i++) {
                OrderDevicesService.addDeviceToOrder(device, id);
                DeviceService.updateQuantity(device, device.getQuantityInStock());
            }
        }
        billDevices.clear();
        billTable.refresh();

        restartApplication();
        showPaymentSuccessAlert();
    }
    private void showPaymentSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Successful");
        alert.setHeaderText(null);
        alert.setContentText("Your payment has been successfully processed.");
        alert.showAndWait();
    }

    public void restartApplication() {
        try {
            // Tạo một FXMLLoader mới để tải lại cùng một fxml file
            Parent root = FXMLLoader.load(getClass().getResource("/com/example/technologydevicemanagement/view/dashboard.fxml"));
            // Tạo một Scene mới
            Scene scene = new Scene(root, 1200, 700);
            Stage stage = new Stage();
            Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));
            stage.getIcons().add(icon);
            stage.setTitle("Technology Equipment Sales Management System");
            // Đặt scene cho stage
            stage.setScene(scene);

            // Hiển thị stage
            stage.show();
            searchField.getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
                if (newValue <= getTableView().getItems().get(getIndex()).getQuantityInStock() && newValue > 0) {
                    commitEdit(newValue);
                }
            });

            minusButton.setOnAction(event -> {
                int newValue = Integer.parseInt(textField.getText()) - 1;
                if (newValue <= getTableView().getItems().get(getIndex()).getQuantityInStock() && newValue > 0) {
                    commitEdit(newValue);
                }
            });

            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {

                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if (!newValue) {
                        int newVal = Integer.parseInt(textField.getText());
                        if (newVal <= getTableView().getItems().get(getIndex()).getQuantityInStock() && newVal > 0) {
                            commitEdit(newVal);
                        } else {
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
                HBox hBox = new HBox(3, minusButton, textField, addButton);
                hBox.setStyle("-fx-alignment: center");
                setGraphic(hBox);

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
            textField.setText(1 + "");
            HBox hBox = new HBox(3, minusButton, textField, addButton);
            hBox.setStyle("-fx-alignment: center");
            setGraphic(hBox);
        }

        @Override
        public void commitEdit(Integer newValue) {
            int intialQuantityInStock = new DeviceService().getDataById(getTableRow().getItem().getIdDevice()).getQuantityInStock();
            super.commitEdit(newValue);
            textField.setText(newValue + "");
            getTableRow().getItem().setQuantity(newValue);
            int quantityInstock = intialQuantityInStock - newValue;
            getTableRow().getItem().setQuantityInStock(quantityInstock);
            getTableRow().getItem().setQuantity(newValue);
            updateAmount(newValue);
            HBox hBox = new HBox(3, minusButton, textField, addButton);
            hBox.setStyle("-fx-alignment: center");
            setGraphic(hBox);
            ;

        }

        private void updateAmount(Integer newValue) {
            double amount = newValue * ((Device) getTableRow().getItem()).getPrice();
            System.out.println(amount);
            getTableRow().getItem().setAmount(amount);
            getTableView().refresh();

        }
    }

    @FXML
    private void searchHandle() {
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            ObservableList<Device> filteredList = FXCollections.observableArrayList();
            filteredList.addAll(new DeviceService().getAllData());
            if (newValue.isEmpty() || newValue.isBlank()) {
                stocktable.setItems(filteredList);
                return;
            }

            String keyword = newValue.toLowerCase();
            filteredList.clear();
            for (Device device : new DeviceService().getAllData()) {
                if (device.getIdDevice().toLowerCase().contains(keyword)) {
                    filteredList.add(device);
                }
            }
            stocktable.setItems(filteredList);
        });
    }

    public void confirmPayment() {
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirm Payment");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you have made the payment ?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                payment();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
