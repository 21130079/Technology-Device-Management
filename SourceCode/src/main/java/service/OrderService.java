package service;

import com.example.technologydevicemanagement.model.Order;
import database.DAOOrder;
import javafx.scene.chart.XYChart;

import java.sql.Date;
import java.util.List;

public class OrderService implements IService<Order>{
    DAOOrder daoOrder = new DAOOrder();
    @Override
    public void saveData(Order model) {

    }
    public String addOrder(){
       return daoOrder.insert();
    }

    @Override
    public Order getDataById(String id) {
        return daoOrder.getById(id);
    }

    @Override
    public List<Order> getAllData() {
        return daoOrder.getAll();
    }

    @Override
    public void updateData(Order model) {
        daoOrder.update(model);
    }

    @Override
    public void deleteData(String id) {
        daoOrder.delete(id);
    }
    public double getTIByDate(Date date) {
        return daoOrder.getTIByDate(date);
    }
    public double getTIByMonth(Date date) {
        return daoOrder.getTIByMonth(date);
    }
    public double getTIByYear(Date date) {
        return daoOrder.getTIByYear(date);
    }
    public double getTI() {
        return daoOrder.getTI();
    }
    public void setNOCChartByDate(XYChart.Series chart) {
        daoOrder.setNOCChartByDate(chart);
    }
    public void setNOCChartByMonth(XYChart.Series chart) {
        daoOrder.setNOCChartByMonth(chart);
    }
    public void setNOCChartByYear(XYChart.Series chart) {
        daoOrder.setNOCChartByYear(chart);
    }
}
