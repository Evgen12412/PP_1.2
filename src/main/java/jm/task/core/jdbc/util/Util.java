package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String PASSWORD = "root";
    private static final String USERNAME = "root";


    public static Connection getConnection() throws ClassNotFoundException, SQLException {
         Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
         System.out.println(" Connection OK");
         return connection;
    }
}
