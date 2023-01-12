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
    String comand;
    Statement statement = Util.getConnection().createStatement();

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {
    }

    public void createUsersTable() throws SQLException {
         comand = "CREATE TABLE IF NOT EXISTS users (ID INT PRIMARY KEY AUTO_INCREMENT , NAME VARCHAR(20), LASTNAME VARCHAR(20), AGE INT)";

        statement.execute(comand);
        statement.close();

    }

    public void dropUsersTable() throws SQLException {
        comand = "DROP TABLE users";
        statement.execute(comand);
        statement.close();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement;
        comand = "INSERT INTO users (name, lastname, age) VALUES( ?, ?, ?)";
        preparedStatement = Util.getConnection().prepareStatement(comand);
        preparedStatement.setLong(1, 1);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, lastName);
        preparedStatement.setByte(3, age);

        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void removeUserById(long id) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement;
        comand = "DELETE FROM users WHERE ID=?";
        preparedStatement = Util.getConnection().prepareStatement(comand);
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        comand = "SELECT id, name, lastname, age";
        ResultSet rs = statement.executeQuery(comand);
        while (rs.next()) {
           users.add((User) rs);
        }
        statement.close();
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        comand = "TRUNCATE TABLE users";
        statement.execute(comand);
        statement.close();
    }
}
