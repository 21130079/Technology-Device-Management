package com.example.technologydevicemanagement.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class DBUtil {
   
    private static String url = "jdbc:mysql://localhost:3306/tdm";
    private static String user = "root";
    private static String password = "";

    public static Connection getConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
             throw new RuntimeException(e);
        }


    }

    public static String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }



    public static void main(String[] args) {
        getConnection();
    }
}
