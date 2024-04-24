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
    public ArrayList<Device> getAll(){
        Connection connection = DBUtil.getConnection();
        ArrayList<Device> devices = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from devices");
            ResultSet resultSet = stm.executeQuery();
            while(resultSet.next()){

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
    public Device getById(String idDevice){
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from devices where idDevice = ?");
            stm.setString(1,idDevice);
            ResultSet resultSet = stm.executeQuery();
            if(resultSet.next()){
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
    public int decreaseQuantity(Device device,int quantity){
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("update devices set quantityInStock = ? where idDevice =?");
            stm.setInt(1,quantity);
            stm.setString(2,device.getIdDevice());
            stm.executeUpdate();
                return quantity;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        System.out.println(new DAODevice().getAll());
    }

}
