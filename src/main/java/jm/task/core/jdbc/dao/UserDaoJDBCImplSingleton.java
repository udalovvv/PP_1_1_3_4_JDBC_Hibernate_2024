package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.exeption.CleanUsersTableException;
import jm.task.core.jdbc.exeption.GetAllUsersException;
import jm.task.core.jdbc.exeption.RemoveUserByIdException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDaoJDBCImplSingleton implements UserDao {

    private static final UserDaoJDBCImplSingleton INSTANCE = new UserDaoJDBCImplSingleton();

    private final Connection connection = Util.getConnection();

    private static final Logger LOGGER = Logger.getLogger("jm.task.core.jdbc.dao.UserDaoJDBCImplSingleton");

    private UserDaoJDBCImplSingleton() {}

    public static UserDaoJDBCImplSingleton getInstance() {
        return INSTANCE;
    }

    public void createUsersTable() {
        String sql = """
                CREATE TABLE IF NOT EXISTS UsersTable(
                id MEDIUMINT not null AUTO_INCREMENT,
                name VARCHAR(255),
                lastName VARCHAR(255),
                age INTEGER,
                PRIMARY KEY (id)
                )
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "DB created successful by JDBC");
    }

    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS UsersTable";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "DB deleted successfully by JDBC");
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = """
                INSERT INTO UsersTable (
                name, lastName, age
                ) VALUES (?, ?, ?);
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        LOGGER.log(Level.INFO, "User successfully saved by JDBC");
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM UsersTable WHERE ID=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RemoveUserByIdException("error deleting user by id", e);
        }
        LOGGER.log(Level.INFO, "User successfully deleted by id by JDBC");
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT id, name, lastName, age from kata.UsersTable";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            throw new GetAllUsersException("error getting all users", e);
        }
        LOGGER.log(Level.INFO, "All users were successfully retrieved from the DB by JDBC");
        return userList;
    }

    public void cleanUsersTable() {

        String sql = "DELETE FROM userstable";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new CleanUsersTableException("error cleaning table", e);
        }
        LOGGER.log(Level.INFO, "User table cleared successfully by JDBC");
    }
}
