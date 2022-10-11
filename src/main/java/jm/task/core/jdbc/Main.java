package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl  usi= new UserServiceImpl();

        usi.createUsersTable();
        usi.saveUser("Ivan", "Ivanov", (byte) 11);
        usi.saveUser("Petr", "Petrov", (byte) 22);
        usi.saveUser("Andrey", "Sidorov", (byte) 33);
        usi.saveUser("Alexey", "Maslov", (byte) 44);
        usi.getAllUsers();
        usi.cleanUsersTable();
        usi.dropUsersTable();
    }
}
