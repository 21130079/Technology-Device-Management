package com.example.technologydevicemanagement.model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;

public class Device {
    private String idProduct;
    private String nameProduct;
    private String category; // phone, laptop ,tablet,...
    private double price;
    private String brand;
    private Date manufacturingDate;
    private double weight;
    private String urlImg;
    private int quantityInStock;

        public Device(String idProduct, String nameProduct, String category, double price, String brand, Date manufacturingDate, double weight, String urlImg, int quantityInStock) {
            this.idProduct = idProduct;
            this.nameProduct = nameProduct;
            this.category = category;
            this.price = price;
            this.brand = brand;
            this.manufacturingDate = manufacturingDate;
            this.weight = weight;
            this.urlImg = urlImg;
            this.quantityInStock = quantityInStock;
        }

    @Override
    public String toString() {
        return "Device{" +
                "idProduct='" + idProduct + '\'' +
                ", nameProduct='" + nameProduct + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", manufacturingDate=" + manufacturingDate +
                ", weight=" + weight +
                ", urlImg='" + urlImg + '\'' +
                ", quantityInStock=" + quantityInStock +
                '}';
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
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
        return new SimpleStringProperty(idProduct);
    }
    public StringProperty nameDeviceProperty() {
        return new SimpleStringProperty(nameProduct);
    }
    public StringProperty quantityDeviceProperty() {
        return new SimpleStringProperty(String.valueOf(quantityInStock));
    }
    public StringProperty priceDeviceProperty() {
        return new SimpleStringProperty(String.valueOf(price));
    }

}


