package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;


public class Util {
    private static final String url = "jdbc:mysql://localhost/tables_users?useUnicode=true&serverTimezone=UTC&useSSL=true&verifyServerCertificate=false";
    private static final String userName = "root";
    private static final String pass = "1234";
    private static Connection connection;
    private static SessionFactory sessionFactory;

    public static Connection getConnect() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, userName, pass);
            if (!connection.isClosed()) {
                return connection;
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
