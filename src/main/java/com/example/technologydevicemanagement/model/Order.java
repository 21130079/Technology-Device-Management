package com.example.technologydevicemanagement.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private String idOrder;
    private LinkedHashMap<Device,Integer> listDevice;
    private Date invoiceDate;
    private double Amount;

    public Order(String idOrder, LinkedHashMap<Device, Integer> listProduct, Date invoiceDate, double amount) {
        this.idOrder = idOrder;
        this.listDevice = listProduct;
        this.invoiceDate = invoiceDate;
        Amount = amount;
    }

    public LinkedHashMap<Device, Integer> getListDevice() {
        return listDevice;
    }

    public void setListDevice(LinkedHashMap<Device, Integer> listDevice) {
        this.listDevice = listDevice;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    // Tạo StringProperty cho listProduct
    public StringProperty productListProperty() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Device, Integer> entry : listDevice.entrySet()) {
            Device device = entry.getKey();
            sb.append("ID: ").append(device.getIdProduct()).append(", ")
                    .append("Name: ").append(device.getNameProduct()).append(", ")
                    .append("Category: ").append(device.getCategory()).append(", ")
                    .append("Price: ").append(device.getPrice()).append(", ")
                    .append("Brand: ").append(device.getBrand()).append(", ")
                    .append("Manufacturing Date: ").append(device.getManufacturingDate()).append(", ")
                    .append("Weight: ").append(device.getWeight()).append(", ")
                    .append("URL Image: ").append(device.getUrlImg()).append(", ")
                    .append("Quantity in Stock: ").append(device.getQuantityInStock()).append("\n");
        }
        return new SimpleStringProperty(sb.toString());
    }

    // Tạo StringProperty cho idOrder
    public StringProperty idOrderProperty() {
        return new SimpleStringProperty(idOrder);
    }

    public StringProperty invoiceDateProperty() {
        // Convert invoiceDate to String
        return new SimpleStringProperty(invoiceDate.toString());
    }
    // Tạo StringProperty cho Amount
    public StringProperty amountProperty() {
        return new SimpleStringProperty(Double.toString(Amount));
    }
}
