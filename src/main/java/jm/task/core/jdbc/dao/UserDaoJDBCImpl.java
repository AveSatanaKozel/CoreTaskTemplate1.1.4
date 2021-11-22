package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private PreparedStatement preparedStatement = null;
//    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS mydbtest.users" +
//            "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
//            " name VARCHAR(45), lastName VARCHAR(100), age TINYINT)";
//    private static final String DROP_TABLE = "DROP TABLE IF EXISTS mydbtest.users";
//    private static final String INSERT_NEW = "INSERT INTO mydbtest.users (name, lastName, age) VALUES (?, ?, ?)";
//    private static final String GET_ALL = "SELECT * FROM mydbtest.users";
//    private static final String DELETE_USER = "DELETE FROM mydbtest.users WHERE ID = ?";
//    private static final String CLEAN_USERS = "TRUNCATE TABLE mydbtest.users";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            preparedStatement = Util.getConnect().prepareStatement("CREATE TABLE IF NOT EXISTS mydbtest.users" +
                    "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                    " name VARCHAR(45), lastName VARCHAR(100), age TINYINT)");
            preparedStatement.executeUpdate();
//            System.out.println("Table created");
        } catch (SQLException e) {
            System.out.println("Table is not created");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {

        try {
            preparedStatement = Util.getConnect().prepareStatement("DROP TABLE IF EXISTS mydbtest.users");
            preparedStatement.executeUpdate();
//            System.out.println("Table dropped");
        } catch (SQLException e) {
            System.out.println("Table is not dropped");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try {
            preparedStatement = Util.getConnect().prepareStatement("INSERT INTO mydbtest" +
                    ".users (name, lastName, age) VALUES (?, ?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");

//            System.out.println("User saved");

        } catch (SQLException e) {
            System.out.println("User not saved");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {

        try {
            preparedStatement = Util.getConnect().prepareStatement("DELETE FROM mydbtest.users WHERE ID = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
//            System.out.println("User deleted");
        } catch (SQLException e) {
            System.out.println("User not deleted");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try {
            preparedStatement = Util.getConnect().prepareStatement("SELECT * FROM mydbtest.users");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age"));
                user.setId(resultSet.getLong("id"));
                userList.add(user);
            }
//            System.out.println("All users received");
        } catch (SQLException e) {
            System.out.println("Trouble getting users");

        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            preparedStatement = Util.getConnect().prepareStatement("TRUNCATE TABLE mydbtest.users");
            preparedStatement.executeUpdate();
//            System.out.println("All users deleted from table");
        } catch (SQLException e) {
            System.out.println("Users are not deleted");
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
