package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.App;
import com.example.technologydevicemanagement.model.Device;
import database.DAODevice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.util.HashMap;

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
    private Button addBtn, resetBtn, addNewBtn, updateBtn, chooseImgBtn;

    @FXML
    private TextField inputID, inputName, inputWeight, inputCate, inputQuantity, inputPrice, inputBrand;
    @FXML
    private DatePicker inputManufacturing;
    @FXML
    private ImageView imageView;
    TextField inputImg = new TextField();
    private DAODevice daoDevice = new DAODevice();
    private HashMap<String, Image> imageCache = new HashMap<>();
    private Path imagePath;

    private void centerAlignColumns() {
        String style = "-fx-alignment: CENTER;";
        idCol.setStyle(style);
        nameCol.setStyle(style);
        imgCol.setStyle(style);
        weightCol.setStyle(style);
        cateCol.setStyle(style);
        qISCol.setStyle(style);
        priceCol.setStyle(style);
        brandCol.setStyle(style);
    }

    public void initialize() {
        addNewBtn.setOnAction(event -> selectAddNewBtn());
        updateBtn.setOnAction(event -> selectUpdateBtn());
        idCol.setCellValueFactory(cellData -> cellData.getValue().idDeviceProperty());
        nameCol.setCellValueFactory(cellData -> cellData.getValue().nameDeviceProperty());
        setImageOnCol(imgCol);
        brandCol.setCellValueFactory(cellData -> cellData.getValue().brandProperty());
        cateCol.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        weightCol.setCellValueFactory(cellData -> cellData.getValue().weightProperty().asObject());
        qISCol.setCellValueFactory(cellData -> cellData.getValue().quantityInStockProperty().asObject());
        priceCol.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        centerAlignColumns();

        refreshTable();

        inputID.textProperty().addListener((observable, oldValue, newValue) -> filterDevices(newValue));

        resetBtn.setOnAction(event -> resetInputs());

        addBtn.setOnAction(event -> addOrUpdateDevice());
    }

    private void selectAddNewBtn(){
        inputID.clear();
        inputID.setDisable(true);
        inputName.setDisable(false);
        inputWeight.setDisable(false);
        inputCate.setDisable(false);
        inputQuantity.setDisable(false);
        inputPrice.setDisable(false);
        inputBrand.setDisable(false);
        inputManufacturing.setDisable(false);
        imageView.setDisable(false);
        chooseImgBtn.setDisable(false);
        addBtn.setDisable(false);
        resetBtn.setDisable(false);
    }

    private void selectUpdateBtn(){
        inputID.setDisable(false);
        inputName.setDisable(false);
        inputWeight.setDisable(false);
        inputCate.setDisable(false);
        inputQuantity.setDisable(false);
        inputPrice.setDisable(false);
        inputBrand.setDisable(false);
        inputManufacturing.setDisable(false);
        imageView.setDisable(false);
        chooseImgBtn.setDisable(false);
        addBtn.setDisable(false);
        resetBtn.setDisable(false);
    }

    private void refreshTable() {
        ObservableList<Device> products = FXCollections.observableArrayList(daoDevice.getAll());
        stocktable.setItems(products);
    }

    private void filterDevices(String keyword) {
        ObservableList<Device> filteredList = FXCollections.observableArrayList(daoDevice.getAll());
        if (keyword.isEmpty() || keyword.isBlank()) {
            stocktable.setItems(filteredList);
            return;
        }
        filteredList.clear();
        keyword = keyword.toLowerCase();
        for (Device device : daoDevice.getAll()) {
            if (device.getIdDevice().toLowerCase().contains(keyword)) {
                filteredList.add(device);
            }
        }

        stocktable.setItems(filteredList);
        if(filteredList.size()==1){
            Device device = filteredList.get(0);
            inputID.setText(device.getIdDevice());
            inputID.setEditable(true);
            inputName.setText(device.getNameDevice());
            inputWeight.setText(String.valueOf(device.getWeight()));
            inputCate.setText(device.getCategory());
            inputQuantity.setText(String.valueOf(device.getQuantityInStock()));
            inputPrice.setText(String.valueOf(device.getPrice()));
            inputBrand.setText(device.getBrand());
            java.util.Date utilDate = new java.util.Date(device.getManufacturingDate().getTime());
            inputManufacturing.setValue(utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            inputImg.setText(device.getUrlImg());
            Image image = new Image(getClass().getResource(device.getUrlImg()).toExternalForm());
            imageView.setImage(image);

        }
    }

    private void resetInputs() {
        inputID.clear();
        inputName.clear();
        inputWeight.clear();
        inputCate.clear();
        inputQuantity.clear();
        inputPrice.clear();
        inputBrand.clear();
        inputManufacturing.setValue(null);
        imageView.setImage(null);
    }

    private void addOrUpdateDevice() {
        try {
            String id = inputID.getText();
            String name = inputName.getText();
            String category = inputCate.getText();
            double price = Double.parseDouble(inputPrice.getText());
            String brand = inputBrand.getText();
            java.sql.Date manufacturingDate = java.sql.Date.valueOf(inputManufacturing.getValue());
            double weight = Double.parseDouble(inputWeight.getText());
            String img = inputImg.getText();
            int quantity = Integer.parseInt(inputQuantity.getText());

            Device device = daoDevice.getById(id);

            if (device == null) {
                device = new Device(name, category, price, brand, manufacturingDate, weight, img, quantity);
                daoDevice.insert(device);
                showInforrAlert(null,"The device imported successfully");
            } else {
                Device updateDevice = new Device(id, name, category, price, brand, manufacturingDate, weight, img, quantity);
                daoDevice.update(updateDevice);
                showInforrAlert(null,"The device updated successfully");
            }


        } catch (NumberFormatException e) {
            showErrorAlert("Input Error", "Please enter valid numerical values for price, weight, and quantity.");
        } catch (Exception e) {
            showErrorAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void showInforrAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void backToDashboard() {
        restartApplication();
        Stage stage = (Stage) stocktable.getScene().getWindow();
        stage.close();
    }

    private void setImageOnCol(TableColumn<Device, String> imgCol) {
        imgCol.setCellValueFactory(cellData -> cellData.getValue().urlImgProperty());
        imgCol.setCellFactory(column -> new TableCell<Device, String>() {
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
                            try {
                                imagePath = Paths.get(imageUrl.toURI());
                                Image image = new Image(imageUrl.toExternalForm());
                                imageView.setImage(image);
                                imageCache.put(item, image);

                            } catch (URISyntaxException e) {
                                System.err.println("Invalid image path URI: " + e.getMessage());
                                e.printStackTrace();
                            }
                        } else {
                            System.err.println("Cannot load image from URL: " + item);
                        }
                    }
                    imageView.setFitHeight(70);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView);
                }
            }
        });
    }

    public void restartApplication() {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("view/dashboard.fxml"));
            Scene scene = new Scene(root, 1200, 700);
            Stage stage = new Stage();

            Image icon = new Image(App.class.getResourceAsStream("/img/logo.png"));

            stage.setTitle("Technology Equipment Sales Management System");
            stage.getIcons().add(icon);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg")
        );
        File selectedFile = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            imageView.setImage(image);
            saveImage(selectedFile);
        }
    }

    @FXML
    private void saveImage(File selectedFile) {
        if (selectedFile != null) {
            try {

                Path parentPath = imagePath.getParent();
                Image image = imageView.getImage();
                String imageName = getImageNameFromUrl(image.getUrl());
                System.out.println(imageName);
                Path imagePath = parentPath.resolve(imageName);
                File imageFile = imagePath.toFile();
                inputImg.setText("/img/devices/"+imageName);
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
                ImageIO.write(bufferedImage, "jpg", imageFile);
                System.out.println(imageFile+ " bytes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String getImageNameFromUrl(String imageUrl) {
        String[] parts = imageUrl.split("/");
        return parts[parts.length - 1];
    }

    private int findDeviceIndex(Device device) {
        for (int i = 0; i < stocktable.getItems().size(); i++) {
            if (stocktable.getItems().get(i).getIdDevice().equals(device.getIdDevice())) {
                return i;
            }
        }
        return -1;
    }
}
