package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        User user = new User();
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Ivan", "Ivanov", (byte) 30);
        userDao.saveUser("Anton", "Lavrov", (byte) 35);
        userDao.saveUser("Kirill", "Konev", (byte) 30);
        userDao.saveUser("Oleg", "Sushuk", (byte) 30);
        System.out.println(userDao.getAllUsers());
        userDao.removeUserById(2);
        System.out.println("Из Базы Данных удален Anton Lavrov");
        System.out.println(userDao.getAllUsers());
        userDao.cleanUsersTable();
        System.out.println("Таблица очищенна");
        userDao.dropUsersTable();
        System.out.println("Таблица удалена");
        

    }


}
