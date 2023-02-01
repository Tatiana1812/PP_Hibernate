package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.createConnection(); Statement statement = connection.createStatement()){
            statement.execute("CREATE  TABLE IF NOT EXISTS User(id INT AUTO_INCREMENT," +
                    "name VARCHAR(50), lastName VARCHAR (50), age INT not NULL, PRIMARY KEY (id));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.createConnection(); Statement statement = connection.createStatement()){
             statement.executeUpdate("DROP TABLE IF EXISTS User;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.createConnection(); Statement statement = connection.createStatement()){
            statement.executeUpdate("INSERT INTO User (name, lastName, age)\n"
                    + "VALUES (\"" + name + "\"," + " \"" + lastName + "\"," + age + ");");
            System.out.println("User с именем " + name + " добавлен");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.createConnection(); Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM User WHERE id = (" + id + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.createConnection(); Statement statement = connection.createStatement()){
            ResultSet res = statement.executeQuery("SELECT * FROM User;");
            while (res.next()) {
                User user = new User();
                user.setId(res.getLong("id"));
                user.setName(res.getString("name"));
                user.setLastName(res.getString("lastName"));
                user.setAge(res.getByte("age"));

                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.createConnection(); Statement statement = connection.createStatement()){
            statement.executeUpdate("DELETE FROM User;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
