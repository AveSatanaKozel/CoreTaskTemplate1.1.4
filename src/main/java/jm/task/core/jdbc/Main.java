package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {

    public static void main(String[] args) {

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
