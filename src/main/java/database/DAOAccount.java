package database;

import com.example.technologydevicemanagement.model.Account;
import com.example.technologydevicemanagement.util.DBUtil;

import java.sql.*;

public class DAOAccount {
    Connection connection = DBUtil.getConnection();

    public boolean checkExits(String username, String passwd) {

        try {
            PreparedStatement stm = connection.prepareStatement("select 1 from accounts where username = ? and passwd = ?");
            stm.setString(1, username);
            stm.setString(2, passwd);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public boolean checkExitsUsername(String username) {
        try {
            PreparedStatement stm = connection.prepareStatement("select 1 from accounts where username = ?");
            stm.setString(1, username);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {

                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public int insert(Account account) {
        try {
            PreparedStatement stm = connection.prepareStatement("insert into accounts(username,passwd) values (?,?)");
            stm.setString(1, account.getUsername());
            stm.setString(2, account.getPasswd());
            stm.executeUpdate();
            stm = connection.prepareStatement("insert into roles_account(username,role) values (?,?)");
            for (String role :
                    account.getRoles()) {
                stm.setString(1, account.getUsername());
                stm.setString(2, role);
                stm.executeUpdate();
            }

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
