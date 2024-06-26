package com.example.technologydevicemanagement.dao;

import com.example.technologydevicemanagement.model.Account;
import com.example.technologydevicemanagement.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;

public class DAOAccount {
    Connection connection = DBUtil.getConnection();

    public ArrayList<Account> getAll() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("select * from accounts");
            ResultSet resultSet = stm.executeQuery();
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("passwd");

                // Tạo một PreparedStatement mới để lấy roles từ bảng roles_account
                PreparedStatement rolesStm = connection.prepareStatement("select role from roles_account where username = ?");
                rolesStm.setString(1, username);
                ResultSet rolesResultSet = rolesStm.executeQuery();

                // Tạo một ArrayList để lưu trữ roles
                ArrayList<String> roles = new ArrayList<>();

                // Thêm mỗi role vào ArrayList
                while (rolesResultSet.next()) {
                    roles.add(rolesResultSet.getString("role"));
                }

                // Tạo đối tượng Account từ các giá trị lấy được từ ResultSet
                Account account = new Account(username, password, roles);

                // Thêm account vào danh sách accounts
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return accounts;
    }

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
    public int update(Account account) {
        try {
            // First, delete the old roles from the roles_account table
            PreparedStatement stm = connection.prepareStatement("delete from roles_account where username = ?");
            stm.setString(1, account.getUsername());
            stm.executeUpdate();

            // Then, insert the new roles into the roles_account table
            for (String role : account.getRoles()) {
                // Check if the username-role pair already exists in the database
                stm = connection.prepareStatement("select * from roles_account where username = ? and role = ?");
                stm.setString(1, account.getUsername());
                stm.setString(2, role);
                ResultSet resultSet = stm.executeQuery();

                // If the username-role pair does not exist, insert it into the database
                if (!resultSet.next()) {
                    stm = connection.prepareStatement("insert into roles_account(username, role) values (?, ?)");
                    stm.setString(1, account.getUsername());
                    stm.setString(2, role);
                    stm.executeUpdate();
                }
            }

            // Finally, update the account in the accounts table
            stm = connection.prepareStatement("update accounts set passwd = ? where username = ?");
            stm.setString(1, account.getPasswd());
            stm.setString(2, account.getUsername());
            stm.executeUpdate();

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public Account getByUsername(String username){
        try {
            PreparedStatement stm = connection.prepareStatement("select * from accounts where username = ?");
            stm.setString(1, username);
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("passwd");

                // Tạo một PreparedStatement mới để lấy roles từ bảng roles_account
                PreparedStatement rolesStm = connection.prepareStatement("select role from roles_account where username = ?");
                rolesStm.setString(1, username);
                ResultSet rolesResultSet = rolesStm.executeQuery();

                // Tạo một ArrayList để lưu trữ roles
                ArrayList<String> roles = new ArrayList<>();

                // Thêm mỗi role vào ArrayList
                while (rolesResultSet.next()) {
                    roles.add(rolesResultSet.getString("role"));
                }

                // Tạo đối tượng Account từ các giá trị lấy được từ ResultSet
                Account account = new Account(username, password, roles);

                return account;
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public int delete(String username){
        try {
            // First, delete the corresponding records from the roles_account table
            PreparedStatement stm = connection.prepareStatement("delete from roles_account where username = ?");
            stm.setString(1, username);
            stm.executeUpdate();

            // Then, delete the account from the accounts table
            stm = connection.prepareStatement("delete from accounts where username = ?");
            stm.setString(1, username);
            stm.executeUpdate();

            return 1;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
