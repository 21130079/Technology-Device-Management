package com.example.technologydevicemanagement.dao;

import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.util.DBUtil;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DAOOrderDevices {
    Connection connection = DBUtil.getConnection();

    public int insert(Device device, String idOrder) {
        try {
            PreparedStatement stm = connection.prepareStatement("insert into OrderDevices(idDevice,idOrder) values (?,?)");
            stm.setString(1, device.getIdDevice());
            stm.setString(2, idOrder);
            stm.executeUpdate();

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public void delete(String orderId) throws SQLException {
        PreparedStatement stm = connection.prepareStatement("DELETE FROM OrderDevices WHERE idOrder = ?");
        stm.setString(1, orderId);
        stm.executeUpdate();
    }

    public void update(LinkedHashMap<Device, Integer> devices, String idOrder) {
        try {
            delete(idOrder);
            insertAll(devices, idOrder);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int insertAll(LinkedHashMap<Device, Integer> devices, String idOrder) throws SQLException {
        int count = 0;
        for (Map.Entry<Device, Integer> entry : devices.entrySet()) {
            Device device = entry.getKey(); // Lấy ra key (Device)
            int quantity = entry.getValue(); // Lấy ra value (Integer)
            for (int i = 0; i < quantity; i++) {
                insert(device, idOrder);
                count++;
            }
        }

        return count;
    }

    public LinkedHashMap<String, Integer> getListIdDevice(String idOrder) {
        Connection connection = DBUtil.getConnection();
        LinkedHashMap<String, Integer> devices = new LinkedHashMap<>();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from Orderdevices where idOrder=?");
            stm.setString(1, idOrder);
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                String idDevice = resultSet.getString("idDevice");
                if (devices.containsKey(idDevice)) {
                    devices.put(idDevice, devices.get(idDevice) + 1);
                } else {
                    devices.put(idDevice, 1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return devices;
    }
    public LinkedHashMap<Device, Integer> getListDevice(String idOrder) {
        DAODevice daoDevice = new DAODevice();
        LinkedHashMap<Device, Integer> result = new LinkedHashMap<Device, Integer>();
        for (Map.Entry<String, Integer> entry : getListIdDevice(idOrder).entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            result.put(daoDevice.getById(key), value);

        }

        return result;
    }

}
