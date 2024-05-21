package com.example.technologydevicemanagement.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class DBUtil {
   
    private static String url = "jdbc:mysql://localhost:3307/tdm";
    private static String user = "root";
    private static String password = "";

    private static Connection connection = null;

    public static Connection doConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
             throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            return doConnection();
        }
        return connection;
    }



    public static String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
