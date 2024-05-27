package com.example.technologydevicemanagement.model;


import javafx.beans.property.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Device {
    private String idDevice;
    private String nameDevice;
    private String category; // phone, laptop ,tablet,...
    private double price;
    private String brand;
    private Date manufacturingDate;
    private double weight;
    private String urlImg;
    private int quantityInStock;
    private double amount ;
    private int quantity;

        public Device(String idProduct, String nameProduct, String category, double price, String brand, Date manufacturingDate, double weight, String urlImg, int quantityInStock) {
            this.idDevice = idProduct;
            this.nameDevice = nameProduct;
            this.category = category;
            this.price = price;
            this.brand = brand;
            this.manufacturingDate = manufacturingDate;
            this.weight = weight;
            this.urlImg = urlImg;
            this.quantityInStock = quantityInStock;
            this.amount=price;
            this.quantity=1;
        }

        public Device(String nameProduct, String category, double price, String brand, Date manufacturingDate, double weight, String urlImg, int quantityInStock) {
            this.nameDevice = nameProduct;
            this.category = category;
            this.price = price;
            this.brand = brand;
            this.manufacturingDate = manufacturingDate;
            this.weight = weight;
            this.urlImg = urlImg;
            this.quantityInStock = quantityInStock;
            this.amount=price;
        }



    @Override
    public String toString() {
        return "Device{" +
                "idProduct='" + idDevice + '\'' +
                ", nameProduct='" + nameDevice + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", weight=" + weight +
                ", urlImg='" + urlImg + '\'' +
                ", quantityInStock=" + quantityInStock +
                '}';
    }

    public String getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(String idDevice) {
        this.idDevice = idDevice;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Date getManufacturingDate() {
        return manufacturingDate;
    }

    public void setManufacturingDate(Date manufacturingDate) {
        this.manufacturingDate = manufacturingDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
    public StringProperty idDeviceProperty() {
        return new SimpleStringProperty(idDevice);
    }
    public StringProperty nameDeviceProperty() {
        return new SimpleStringProperty(nameDevice);
    }

    public StringProperty categoryProperty() {
        return new SimpleStringProperty(category);
    }

    public DoubleProperty priceProperty() {
        return new SimpleDoubleProperty(price);
    }

    public StringProperty brandProperty() {
        return new SimpleStringProperty(brand);
    }

    public StringProperty manufacturingDateProperty() {
        return new SimpleStringProperty(manufacturingDate+"");
    }

    public DoubleProperty weightProperty() {
        return new SimpleDoubleProperty(weight);
    }

    public StringProperty urlImgProperty() {
        return new SimpleStringProperty(urlImg);
    }

    public IntegerProperty quantityInStockProperty() {
        return new SimpleIntegerProperty(quantityInStock);
    }
   public boolean isContainedBy(LinkedHashMap<Device,Integer> mapDevice){
       for (Map.Entry<Device, Integer> entry : mapDevice.entrySet()) {
           Device key = entry.getKey();
           Integer value = entry.getValue();
           if(key.toString().equals(this.toString()))
               return true;
       }
       return false;
   }
    public DoubleProperty amountProperty() {
        return new SimpleDoubleProperty(amount);
    }


    public double getAmount() {
        return amount;
    }


    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }
    public IntegerProperty quantityProperty() {
        return new SimpleIntegerProperty(quantity);
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


