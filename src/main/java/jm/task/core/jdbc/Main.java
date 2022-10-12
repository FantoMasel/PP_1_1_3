package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        UserService usi = new UserServiceImpl();
        usi.createUsersTable();
        usi.saveUser("Ivan", "Ivanov", (byte) 11);
        usi.saveUser("Petr", "Petrov", (byte) 22);
        usi.saveUser("Andrey", "Sidorov", (byte) 33);
        usi.saveUser("Alexey", "Maslov", (byte) 44);
        usi.getAllUsers();
        usi.cleanUsersTable();
        usi.dropUsersTable();
        Util.closeSessionFactory();
    }
}
