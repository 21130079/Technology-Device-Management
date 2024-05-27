package database;

import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

public class DAODevice {
    Connection connection = DBUtil.getConnection();

    public int insert(Device device) {
        try {
            PreparedStatement stm = connection.prepareStatement("insert into devices(idDevice,nameDevice,category,price,brand,manufacturingDate,weight,urlImg,quantityInStock) values(?,?,?,?,?,?,?,?,?)");
            stm.setString(1, device.getIdDevice());
            stm.setString(2, device.getNameDevice());
            stm.setString(3, device.getCategory());
            stm.setDouble(4, device.getPrice());
            stm.setString(5, device.getBrand());
            stm.setDate(6, new Date(device.getManufacturingDate().getTime()));
            stm.setDouble(7, device.getWeight());
            stm.setString(8, device.getUrlImg());
            stm.setInt(9, device.getQuantityInStock());
            stm.executeUpdate();
            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    public ArrayList<Device> getAll() {
        ArrayList<Device> devices = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from devices");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {

                String idDevice = resultSet.getString("idDevice");
                String nameDevice = resultSet.getString("nameDevice");
                String category = resultSet.getString("category");
                double price = resultSet.getDouble("price");
                String brand = resultSet.getString("brand");
                Date manufacturingDate = resultSet.getDate("manufacturingDate");
                double weight = resultSet.getDouble("weight");
                String urlImg = resultSet.getString("urlImg");
                int quantityInStock = resultSet.getInt("quantityInStock");

                // Tạo đối tượng Device từ các giá trị lấy được từ ResultSet
                Device device = new Device(idDevice, nameDevice, category, price, brand, manufacturingDate, weight, urlImg, quantityInStock);

                // Thêm device vào danh sách devices
                devices.add(device);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return devices;
    }

    public Device getById(String idDevice) {
        try {
            PreparedStatement stm = connection.prepareStatement("select * from devices where idDevice = ?");
            stm.setString(1, idDevice);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                String nameDevice = resultSet.getString("nameDevice");
                String category = resultSet.getString("category");
                double price = resultSet.getDouble("price");
                String brand = resultSet.getString("brand");
                Date manufacturingDate = resultSet.getDate("manufacturingDate");
                double weight = resultSet.getDouble("weight");
                String urlImg = resultSet.getString("urlImg");
                int quantityInStock = resultSet.getInt("quantityInStock");

                Device device = new Device(idDevice, nameDevice, category, price, brand, manufacturingDate, weight, urlImg, quantityInStock);

                return device;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void update(Device device){
        try {
            PreparedStatement stm = connection.prepareStatement("update devices set nameDevice = ?, category = ?, price = ?, brand = ?, manufacturingDate = ?, weight = ?, urlImg = ?, quantityInStock = ? where idDevice = ?");
            stm.setString(1, device.getNameDevice());
            stm.setString(2, device.getCategory());
            stm.setDouble(3, device.getPrice());
            stm.setString(4, device.getBrand());
            stm.setDate(5, new Date(device.getManufacturingDate().getTime()));
            stm.setDouble(6, device.getWeight());
            stm.setString(7, device.getUrlImg());
            stm.setInt(8, device.getQuantityInStock());
            stm.setString(9, device.getIdDevice());
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int updateQuantity(Device device, int quantity) {
        try {
            PreparedStatement stm = connection.prepareStatement("update devices set quantityInStock = ? where idDevice =?");
            stm.setInt(1, quantity);
            stm.setString(2, device.getIdDevice());
            stm.executeUpdate();

            return quantity;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
