package database;

import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.util.DBUtil;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DAOOrderDevicesTest {
    private DAOOrderDevices daoOrderDevices;
    private DAODevice daoDevice;
    private String testOrderId;
    private Device testDevice;

    @BeforeAll
    void setUp() {
        daoOrderDevices = new DAOOrderDevices();
        daoDevice = new DAODevice();
        testDevice = new DAODevice().getById("ace017");
        testOrderId = new DAOOrder().insert();
    }



    @Test
    void insert() {
        int result = daoOrderDevices.insert(testDevice, testOrderId);
        assertEquals(1, result);
    }

    @Test
    void update() {
        LinkedHashMap<Device, Integer> devices = new LinkedHashMap<>();
        devices.put(testDevice, 2);

        daoOrderDevices.update(devices, testOrderId);

        LinkedHashMap<Device, Integer> retrievedDevices = daoOrderDevices.getListDevice(testOrderId);
        assertEquals(devices.size(), retrievedDevices.size());

    }

    @Test
    void getListIdDevice() {
        LinkedHashMap<String, Integer> deviceIds = daoOrderDevices.getListIdDevice(testOrderId);
        assertNotNull(deviceIds);
    }

    @Test
    void getListDevice() {
        LinkedHashMap<Device, Integer> devices = daoOrderDevices.getListDevice(testOrderId);
        assertNotNull(devices);

        for (Map.Entry<Device, Integer> entry : devices.entrySet()) {
            assertNotNull(entry.getKey());
            assertNotNull(entry.getValue());
        }
    }
}
