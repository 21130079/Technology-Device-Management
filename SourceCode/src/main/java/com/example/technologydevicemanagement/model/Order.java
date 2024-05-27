package com.example.technologydevicemanagement.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class Order {
    private String idOrder;
    private LinkedHashMap<Device,Integer> listDevice;
    private Date invoiceDate;


    public Order(String idOrder, LinkedHashMap<Device, Integer> listProduct, Date invoiceDate) {
        this.idOrder = idOrder;
        this.listDevice = listProduct;
        this.invoiceDate = invoiceDate;

    }

    public LinkedHashMap<Device, Integer> getListDevice() {
        return listDevice;
    }

    public void setListDevice(LinkedHashMap<Device, Integer> listDevice) {
        this.listDevice = listDevice;
    }

    public java.sql.Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
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
            sb.append("Quantity: ").append(entry.getValue()).append(", Device [ ");
            sb.append("ID: ").append(device.getIdDevice()).append(", ")
                    .append("Name: ").append(device.getNameDevice()).append(", ")
                    .append("Category: ").append(device.getCategory()).append(", ")
                    .append("Price: ").append(device.getPrice()).append(", ")
                    .append("Brand: ").append(device.getBrand()).append(", ")
                    .append("Manufacturing Date: ").append(device.getManufacturingDate()).append(", ")
                    .append("Weight: ").append(device.getWeight()).append(", ")
                    .append("URL Image: ").append(device.getUrlImg()).append(", ")
                    .append("Quantity in Stock: ").append(device.getQuantityInStock()).append(" ]\n");
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
    public DoubleProperty amountProperty(){
        double result = 0 ;
        for (Map.Entry<Device, Integer> entry : listDevice.entrySet()) {
            Device key = entry.getKey();
            Integer val = entry.getValue();
            // Xử lý các khóa và giá trị ở đây
            result += key.getPrice()*val;
        }
        return new SimpleDoubleProperty(result);
    }
}
