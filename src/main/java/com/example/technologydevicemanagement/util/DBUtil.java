package com.example.technologydevicemanagement.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class DBUtil {
    private static Connection connection;
    private static String url = "jdbc:mysql://localhost:3306/tdm";
    private static String user = "root";
    private static String password = "";

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static String generateUniqueId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static void main(String[] args) {
        getConnection();
    }
}
