package com.example.technologydevicemanagement.util;
import java.sql.*;
public class DBUtil {
    public static Connection connectDb() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/TechnologyDeviceManagement", "root", "");

            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}