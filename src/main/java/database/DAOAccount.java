package database;

import com.example.technologydevicemanagement.model.Account;
import com.example.technologydevicemanagement.model.Device;
import com.example.technologydevicemanagement.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class DAOAccount {
    public ArrayList<Account> getAll(){
        Connection connection = DBUtil.getConnection();
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from accounts");
            ResultSet resultSet = stm.executeQuery();
            while(resultSet.next()){

                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                // Tạo đối tượng Device từ các giá trị lấy được từ ResultSet
                Account account = new Account(username, password);

                // Thêm device vào danh sách devices
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }
    public boolean checkValidAccount(Account account){
        Connection connection = DBUtil.getConnection();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from accounts where username = ? and password = ?");
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPassword());
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }


    public static void main(String[] args) {
        System.out.println(new DAODevice().getAll());
    }
}
