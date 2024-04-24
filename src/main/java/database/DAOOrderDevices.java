package database;

import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class DAOOrderDevices {
    public int insert(Device device, String idOrder){
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("insert into OrderDevices(idDevice,idOrder) values (?,?)");
            stm.setString(1,device.getIdDevice());
            stm.setString(2,idOrder);
            stm.executeUpdate();
            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
    public int insertAll(ArrayList<Device> devices, String idOrder){
       int count =0;
        for (Device device: devices) {
            insert(device,idOrder);
            count++;
        }

        return count;
    }
    public LinkedHashMap<String,Integer> getListIdDevice(String idOrder){
            Connection connection = DBUtil.getConnection();
        LinkedHashMap<String,Integer> devices = new LinkedHashMap<>();
            try {
                PreparedStatement stm = connection.prepareStatement("select * from Orderdevices where idOrder=?");
                stm.setString(1,idOrder);
                ResultSet resultSet = stm.executeQuery();
                while(resultSet.next()){
                    String idDevice = resultSet.getString("idDevice");
                    Device device = new DAODevice().getById(idDevice);

                    if(devices.containsKey(idDevice)){
                        devices.put(idDevice,devices.get(idDevice)+1);
                    }else{
                        devices.put(idDevice,1);
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return devices;
    }
    public LinkedHashMap<Device, Integer> getListDevice(String idOrder){
        DAODevice daoDevice = new DAODevice();
        LinkedHashMap<Device,Integer> result = new  LinkedHashMap<Device,Integer>();
        for (Map.Entry<String, Integer> entry : getListIdDevice(idOrder).entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            result.put(daoDevice.getById(key),value);
            // Thực hiện các thao tác bạn cần với key và value ở đây
        }

        return result;
    }
    public static void main(String[] args) {
        System.out.println(new DAOOrderDevices().getListDevice("1"));
    }
}
