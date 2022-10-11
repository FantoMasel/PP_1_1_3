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

    public UserDaoJDBCImpl createUsersTable() {
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users(Id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "FirstName varchar(100), LastName varchar(100), Age Integer)");
        } catch (SQLException E) {
            System.out.println("Ошибка при создании новой таблицы! :(");
        }
        if (util.getConnection() != null) {
            try {
                util.getConnection().close();
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытие соединения :(");
                ;
            }
        }

        return null;
    }

    public void dropUsersTable() {
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("DROP TABLE IF  EXISTS users");
        } catch (SQLException E) {
            System.out.println("Ошибка при удалении таблицы! :(");
        }
        if (util.getConnection() != null) {
            try {
                util.getConnection().close();
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытие соединения :(");
                ;
            }
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        try {
            PreparedStatement ps = util.getConnection().prepareStatement("INSERT INTO users(FirstName, Lastname, Age) " +
                    "VALUES (?,?,?)");
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();

        } catch (SQLException E) {
            System.out.println("Ошибка при добавлении пользователя! :(");
        }
        if (util.getConnection() != null) {
            try {
                util.getConnection().close();
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытие соединения :(");
                ;
            }
        }

    }

    public void removeUserById(long id) {
        Util util = new Util();
        try {
            String sql = "DELETE FROM users WHERE Id = ' " + id + " '";
            Statement statement = util.getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException E) {
            System.out.println("Ошибка при удалении пользователя по Id! :(");
        }
        if (util.getConnection() != null) {
            try {
                util.getConnection().close();
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытие соединения :(");
                ;
            }
        }

    }

    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> AllUsers = new ArrayList<>();
        try {
            Statement statement = util.getConnection().createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();
                user.setId((long) rs.getInt(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge((byte) rs.getInt(4));
                AllUsers.add(user);
            }
        } catch (SQLException E) {
            System.out.println("Ошибка при получении всех пользователей :(");
        }
        if (util.getConnection() != null) {
            try {
                util.getConnection().close();
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытие соединения :(");
                ;
            }
        }
        return AllUsers;
    }

    public void cleanUsersTable() {
        Util util = new Util();
        try {
            Statement statement = util.getConnection().createStatement();
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException E) {
            System.out.println("Ошибка при удалении пользователя по Id! :(");
        }
        if (util.getConnection() != null) {
            try {
                util.getConnection().close();
            } catch (SQLException e) {
                System.out.println("Ошибка при закрытие соединения :(");
                ;
            }
        }
    }
}
