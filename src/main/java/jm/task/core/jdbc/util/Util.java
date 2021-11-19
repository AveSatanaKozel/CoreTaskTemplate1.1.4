package jm.task.core.jdbc.util;


import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static Connection getConnect() {
        Connection connection = null;
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);

        } catch (SQLException e) {
            System.out.println("Проблема с драйвером");
        }
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

//            if (!connection.isClosed()) {
//                System.out.println("connect with BD");
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

