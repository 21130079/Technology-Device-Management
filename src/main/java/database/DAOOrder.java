package database;

import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.model.Order;
import com.example.technologydevicemanagement.util.DBUtil;
import javafx.beans.property.DoubleProperty;
import javafx.scene.chart.XYChart;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DAOOrder {
    public String insert() {
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

    public Order getById(String id) {
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from orders where idOrder = ?");
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Date invoiceDate = rs.getDate("invoiceDate");
                LinkedHashMap<Device, Integer> listDevices = new DAOOrderDevices().getListDevice(id);
                return new Order(id, listDevices, invoiceDate);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public ArrayList<Order> getAll() {
        Connection connection = DBUtil.getConnection();
        ArrayList<Order> orders = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from orders");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {

                String idOrder = resultSet.getString("idOrder");
                Date invoiceDate = resultSet.getDate("invoiceDate");
                LinkedHashMap<Device, Integer> listDevices = new DAOOrderDevices().getListDevice(idOrder);
                Order order = new Order(idOrder, listDevices, invoiceDate);

                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return orders;
    }

    public double getTIByDate(Date date) {
        Connection connection = DBUtil.getConnection();
        double ti = 0;
        try {
            PreparedStatement stm = connection.prepareStatement("select * from orders where invoiceDate = '" + date + "'");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {

                String idOrder = resultSet.getString("idOrder");
                Date invoiceDate = resultSet.getDate("invoiceDate");
                LinkedHashMap<Device, Integer> listDevices = new DAOOrderDevices().getListDevice(idOrder);
                Order order = new Order(idOrder, listDevices, invoiceDate);

                ti += order.amountProperty().get();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ti;
    }

    public double getTI() {
        Connection connection = DBUtil.getConnection();
        double ti = 0;
        try {
            PreparedStatement stm = connection.prepareStatement("select * from orders");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {

                String idOrder = resultSet.getString("idOrder");
                Date invoiceDate = resultSet.getDate("invoiceDate");
                LinkedHashMap<Device, Integer> listDevices = new DAOOrderDevices().getListDevice(idOrder);
                Order order = new Order(idOrder, listDevices, invoiceDate);

                ti += order.amountProperty().get();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return ti;
    }

    public void setNOCChartByDate(XYChart.Series chart) {
        Connection connection = DBUtil.getConnection();
        double ti = 0;
        try {
            PreparedStatement stm = connection.prepareStatement("select invoiceDate, count(idOrder) from orders group by invoiceDate order by timestamp(invoiceDate) desc limit 5");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                chart.getData().add(new XYChart.Data<>(resultSet.getString(1), resultSet.getInt(2)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
