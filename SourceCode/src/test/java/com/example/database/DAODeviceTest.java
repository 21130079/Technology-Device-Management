package com.example.database;

import com.example.technologydevicemanagement.model.Device;
import database.DAODevice;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DAODeviceTest {
    DAODevice daoDevice = new DAODevice();
    @Test
    void insert() {
        Device device = new Device("id tự tạo", "Device1", "Category1", 100.0, "Brand1", new java.util.Date(), 1.5, "/img/devices/acerpredatorhelios300.jpg", 10);
        assertEquals(1,  daoDevice.insert(device));
        // id này 3 số sau là random cho nên xem trước trong database mới test
        System.out.println(daoDevice.getById("dev027"));


    }

    @Test
    void getAll() {
        List<Device> devices = daoDevice.getAll();
        assertNotNull(devices);
        System.out.println(devices);
    }

    @Test
    void getById() {
        assertNotNull(daoDevice.getById("ace017"));
        System.out.println(daoDevice.getById("ace017"));
    }

    @Test
    void updateQuantity() {
        // hàm được test trả về giá trị quantity bị thay đổi
        assertEquals(10, daoDevice.updateQuantity(daoDevice.getById("ace017"), 10));

    }
}