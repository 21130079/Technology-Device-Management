package database;

import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.model.Order;
import com.example.technologydevicemanagement.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DaoOrder {
    public String insert(){
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("insert into Orders(idOrder,invoiceDate) values (?,?)");
           String id = DBUtil.generateUniqueId();
            stm.setString(1, id);
            stm.setDate(2, Date.valueOf(LocalDate.now()));
            stm.executeUpdate();
            return id;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Order getById(String id ){
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from orders where idOrder = ?");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if(rs.next()){
                Date invoiceDate = rs.getDate("invoiceDate");
                LinkedHashMap<Device,Integer> listDevices = new DaoOrderDevices().getListDevice(id);
                return new Order(id,listDevices,invoiceDate);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public ArrayList<Order> getAll(){
        Connection connection = DBUtil.getConnection();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from orders");
            ResultSet resultSet = stm.executeQuery();
            while(resultSet.next()){

                String idOrder = resultSet.getString("idOrder");
                Date invoiceDate = resultSet.getDate("invoiceDate");
                LinkedHashMap<Device,Integer> listDevices = new DaoOrderDevices().getListDevice(idOrder);
                Order order = new Order(idOrder,listDevices,invoiceDate);

                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;

    }

    public static void main(String[] args) {
        System.out.println(new DaoOrder().getAll());
    }

}
