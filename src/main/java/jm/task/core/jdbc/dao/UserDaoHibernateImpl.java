package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Session session = null;

    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        try {
            session = Util.getfactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user (id int AUTO_INCREMENT primary key, " +
                    "name varchar(50), lastName varchar (50), age int);")
                    .addEntity(User.class)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session = Util.getfactory().openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user;")
                    .executeUpdate();
            System.out.println("Таблица удалена");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            session = Util.getfactory().openSession();
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            System.out.println("Пользователь с именем - " + name + " добавлен в базу данных");
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            session = Util.getfactory().openSession();
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            System.out.println("Из Базы Данных удален " + user);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            session = Util.getfactory().openSession();
            session.beginTransaction();
            userList = session.createQuery("from User").getResultList();
            for (User i : userList) {
                System.out.println(i);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            session = Util.getfactory().openSession();
            session.beginTransaction();
            session.createQuery("delete User")
                    .executeUpdate();
            System.out.println("Таблица очищенна");
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
