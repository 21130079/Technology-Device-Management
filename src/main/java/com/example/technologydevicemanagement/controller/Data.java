package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.model.Order;

public class Data {
    public static String username;
    public static String role;
    private Order updatedOrder;
    private static Data instance;

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }
    public Order getUpdatedOrder() {
        return updatedOrder;
    }

    public void setUpdatedOrder(Order updatedOrder) {
        this.updatedOrder = updatedOrder;
    }
}
