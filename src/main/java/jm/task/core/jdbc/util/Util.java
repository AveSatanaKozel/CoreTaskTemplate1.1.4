package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

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

    public static SessionFactory getFactory() {
        SessionFactory factory = null;
//        Metadata factory = null;
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();

        } catch (Throwable e) {
            System.out.println("Initial SessionFactory creation failed" + e);

        }
        return factory;
    }

    public static Session getSession (){
        return Util.getFactory().openSession();
    }
}

