package service;

import com.example.technologydevicemanagement.model.Device;
import database.DAODevice;
import database.DAOOrderDevices;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

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
