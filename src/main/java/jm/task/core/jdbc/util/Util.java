package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/ForKataBase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Msmu1488";
    private static Connection connection;

    public Util() {

    }

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Проблемы с созданием баззы данных! :(");
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (!Util.getConnection().isClosed()) {
                Util.getConnection().close();
            }
            //System.out.println(connection.isClosed()); проверил закрывается ли соединение
        } catch (SQLException e) {
            System.out.println("Проблемы при закрытии соеденения :(");
        }

    }
}
