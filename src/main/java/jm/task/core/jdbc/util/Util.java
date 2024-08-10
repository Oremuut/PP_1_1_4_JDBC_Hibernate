package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/new_schema";
    private final static String USER = "root";
    private final static String PASSWORD = "root";

    public static Connection getConnection() {
        Connection conn;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
}
