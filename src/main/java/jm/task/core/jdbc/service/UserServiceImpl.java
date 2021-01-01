package jm.task.core.jdbc.service;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private String LINE_NEW;
    private PreparedStatement pr;

    public void createUsersTable() {
        LINE_NEW = "CREATE TABLE IF NOT EXISTS user (id int AUTO_INCREMENT primary key, name varchar(50), lastName varchar (50), age int);";
        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);

            pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Util.getConnect().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        LINE_NEW = "DROP TABLE IF EXISTS user;";
        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);
            pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Util.getConnect().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        LINE_NEW = "INSERT INTO user(name, lastName, age) VALUES (?, ?, ?);";
        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);

            pr.setString(1, name);
            pr.setString(2, lastName);
            pr.setInt(3, age);
            pr.execute();


        } catch (SQLException throwables) {
            System.out.println("Ошибка записи USER");

        }
        try {
            Util.getConnect().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public void removeUserById(long id) {
        LINE_NEW = "DELETE FROM user WHERE id = " + id;
        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);
            pr.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            Util.getConnect().close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public List<User> getAllUsers() {
        String name, lastName;
        int age;

        LINE_NEW = "SELECT * FROM user;";
        List<User> userList = new ArrayList<>();
        try {
            pr = Util.getConnect().prepareStatement(LINE_NEW);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("name");
                lastName = resultSet.getString("lastName");
                age = resultSet.getInt("age");
                userList.add(new User(name, lastName, (byte) age));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
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
        }


    }
}
