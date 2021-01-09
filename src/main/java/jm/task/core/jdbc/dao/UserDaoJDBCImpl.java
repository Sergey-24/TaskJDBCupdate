package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }
    private String LINE_NEW;
    private PreparedStatement pr;

    public void createUsersTable() {
        LINE_NEW = "CREATE TABLE IF NOT EXISTS user (id int AUTO_INCREMENT primary key, " +
                "name varchar(50), lastName varchar (50), age int);";
        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);
            pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Util.getConnect().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        LINE_NEW = "DROP TABLE IF EXISTS user;";
        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);
            pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Util.getConnect().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        LINE_NEW = "INSERT INTO user(name, lastName, age) VALUES (?, ?, ?);";
        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);
            pr.setString(1, name);
            pr.setString(2, lastName);
            pr.setInt(3, age);
            pr.execute();
            System.out.println("Пользователь с именем - " + name + " добавлен в базу данных");
        } catch (SQLException throwables) {
            System.out.println("Ошибка записи USER");
        } finally {
            try {
                Util.getConnect().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        LINE_NEW = "DELETE FROM user WHERE id = " + id;
        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);
            System.out.println("Пользователь с id = " + id + " удален" );
            pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Util.getConnect().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Statement statement = null;

        LINE_NEW = "SELECT * FROM user;";
        List<User> userList = new ArrayList<>();
        try {
            statement = Util.getConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(LINE_NEW);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Util.getConnect().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        for (User a: userList){
            System.out.println(a);
        }
        return userList;
    }

    public void cleanUsersTable() {
        LINE_NEW = "DELETE FROM user;";

        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);
            pr.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Util.getConnect().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                Util.getConnect().close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
