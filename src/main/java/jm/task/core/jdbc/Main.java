package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {

    public static void main(String[] args) {
//        SessionFactory factory = new Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(User.class)
//                .buildSessionFactory();
//
//        try {
//            Session session = factory.getCurrentSession();
//
//
//            User hiberUser = new User("Hiber", "User", (byte) 80);
//            session.beginTransaction();
//            session.save(hiberUser);
//            session.getTransaction().commit();
//        } finally {
//            factory.close();
//        }

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Steve", "0", (byte) 30);
        userService.saveUser("Stepanida", "Andreevna", (byte) 30);
        userService.saveUser("Kris", "Hamsword", (byte) 30);//
        userService.saveUser("Bob", "Ugly", (byte) 15);

        for (User s : userService.getAllUsers()) {
            System.out.println(s);
        }

//        userService.removeUserById(2);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
