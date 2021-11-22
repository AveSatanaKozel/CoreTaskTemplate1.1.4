package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session session = Util.getSession();


    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS mydbtest.users" +
                        "(id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                        " name VARCHAR(45), lastName VARCHAR(100), age TINYINT)")
                .executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void dropUsersTable() {
        session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS mydbtest.users")
                .executeUpdate();
        session.getTransaction().commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        if (session.isOpen()) {
            System.out.println("Сессия открыта");
        } else {
            System.out.println("сессия закрыта");
        }

        session.getTransaction().begin();
        session.save(user);
        session.getTransaction().commit();

        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        session.beginTransaction();
        session.createQuery("delete User WHERE id = :id")
                .setParameter("id", id)
                .executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public List<User> getAllUsers() {
        session.beginTransaction();
        List<User> userList = session
                .createQuery("from User")
                .getResultList();
        session.getTransaction().commit();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        session.beginTransaction();
        session.createQuery("delete User")
                .executeUpdate();
        session.getTransaction().commit();
    }
}
