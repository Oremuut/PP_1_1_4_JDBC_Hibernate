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

    public void createUsersTable() {
        String create = "CREATE TABLE IF NOT EXISTS users (" +
                "  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "  name VARCHAR(45) NOT NULL," +
                "  lastName VARCHAR(45) NULL," +
                "  age INT NOT NULL);";
        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.execute(create);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String drop = "DROP TABLE IF EXISTS users";
        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.execute(drop);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insert = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (PreparedStatement statement = Util.getConnection().prepareStatement(insert)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String delete = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement statement = Util.getConnection().prepareStatement(delete)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String select = "SELECT * FROM users";

        try (Statement stmt = Util.getConnection().createStatement()) {
            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastName"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String delete = "TRUNCATE TABLE users";

        try (Statement stmt = Util.getConnection().createStatement()) {
            stmt.execute(delete);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
