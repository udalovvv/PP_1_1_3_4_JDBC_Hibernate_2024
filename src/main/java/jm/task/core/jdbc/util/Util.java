package jm.task.core.jdbc.util;

import jm.task.core.jdbc.exeption.JDBCConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    private static final Logger LOGGER = Logger.getLogger("jm.task.core.jdbc.util.Util");

    private Util() {}

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(
                    PropertiesUtil.get(URL_KEY),
                    PropertiesUtil.get(USERNAME_KEY),
                    PropertiesUtil.get(PASSWORD_KEY));
            LOGGER.log(Level.INFO, "Connection OK");
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "database connection error");
            throw new JDBCConnectionException("database connection error", e);
        }
        return connection;
    }

}
