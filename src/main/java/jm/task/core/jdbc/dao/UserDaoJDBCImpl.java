package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users(Id INTEGER PRIMARY KEY AUTO_INCREMENT, " +
                    "FirstName varchar(100), LastName varchar(100), Age Integer)");
        } catch (SQLException E) {
            System.out.println("Ошибка при создании новой таблицы! :(");
        }

    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF  EXISTS users");
        } catch (SQLException E) {
            System.out.println("Ошибка при удалении таблицы! :(");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = connection.prepareStatement("INSERT INTO users(FirstName, Lastname, Age) " +
                "VALUES (?,?,?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();

        } catch (SQLException E) {
            System.out.println("Ошибка при добавлении пользователя! :(");
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE Id = ?")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException E) {
            System.out.println("Ошибка при удалении пользователя по Id! :(");
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User();
                user.setId((long) rs.getInt(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge((byte) rs.getInt(4));
                allUsers.add(user);
            }
        } catch (SQLException E) {
            System.out.println("Ошибка при получении всех пользователей :(");
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("TRUNCATE TABLE users");
        } catch (SQLException E) {
            System.out.println("Ошибка при удалении пользователя по Id! :(");
        }

    }
}
