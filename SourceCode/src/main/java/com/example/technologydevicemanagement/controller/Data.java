package com.example.technologydevicemanagement.controller;

import com.example.technologydevicemanagement.model.Order;
import javafx.scene.control.TableView;


public class Data {
    public static String username;
    public static String role;
    private Order updatedOrder;
    private static Data instance;
    private TableView historyTable;

    public static Data getInstance() {
        if (instance == null) {
            instance = new Data();
        }
        return instance;
    }

    public TableView getHistoryTable() {
        return historyTable;
    }

    public void setHistoryTable(TableView historyTable) {
        this.historyTable = historyTable;
    }

    public Order getUpdatedOrder() {
        return updatedOrder;
    }

    public void setUpdatedOrder(Order updatedOrder) {
        this.updatedOrder = updatedOrder;
    }
}
