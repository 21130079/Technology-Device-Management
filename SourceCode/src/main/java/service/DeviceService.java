package service;

import com.example.technologydevicemanagement.model.Device;
import database.DAODevice;

import java.util.List;

public class DeviceService implements IService<Device> {
    DAODevice daoDevice = new DAODevice();
    @Override
    public void saveData(Device model) {
        daoDevice.insert(model);
    }

    @Override
    public Device getDataById(String id) {
        return daoDevice.getById(id);
    }

    @Override
    public List<Device> getAllData() {
        return daoDevice.getAll();
    }

    @Override
    public void updateData(Device model) {
        daoDevice.update(model);
    }

    @Override
    public void deleteData(String id) {

    }
    public void updateQuantity(Device device, int quantity) {
        daoDevice.updateQuantity(device, quantity);
    }
}
