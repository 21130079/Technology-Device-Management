package com.example.technologydevicemanagement.service;

import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.dao.DAOOrderDevices;

import java.sql.SQLException;
import java.util.LinkedHashMap;

public class OrderDevicesService {
    DAOOrderDevices daoOrderDevices = new DAOOrderDevices();
    public void addDeviceToOrder(Device device, String orderId) {
        daoOrderDevices.insert(device, orderId);
    }


    public void updateDevicesInOrder(LinkedHashMap<Device, Integer> devices, String orderId) {
        daoOrderDevices.update(devices, orderId);
    }



    public void addAll(LinkedHashMap<Device, Integer> devices, String idOrder) {
        try {
            daoOrderDevices.insertAll(devices, idOrder);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
