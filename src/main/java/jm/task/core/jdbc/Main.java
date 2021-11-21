package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceHibImpl;
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




        //hibernate start here


//        UserServiceHibImpl userServiceHib = new UserServiceHibImpl();
//        userServiceHib.createUsersTable();
//        userServiceHib.saveUser("Boris", "Britva", (byte) 45);
//        userServiceHib.saveUser("Ivan", "Petrov", (byte) 10);
//        userServiceHib.saveUser("Nyash", "Myash", (byte) 35);
////        userServiceHib.removeUserById(10);
//        for (User s : userServiceHib.getAllUsers()) {
//            System.out.println(s);
//        }
//
//        userServiceHib.cleanUsersTable();
//        userServiceHib.dropUsersTable();
    }
}
