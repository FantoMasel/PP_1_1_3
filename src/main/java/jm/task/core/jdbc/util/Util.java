package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/ForKataBase";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Msmu1488";
    private Connection connection;

    public Util() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Проблемы с созданием баззы данных! :(");
        }
    }
    public Connection getConnection() {
        return connection;
    }
}
