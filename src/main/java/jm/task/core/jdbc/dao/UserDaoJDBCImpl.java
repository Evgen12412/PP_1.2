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
        String sql = "CREATE TABLE if not exists users(`id` INT  NOT NULL AUTO_INCREMENT,`firstName` VARCHAR(45) NOT NULL,`lastName` VARCHAR(45) NOT NULL,`age` INT NOT NULL,PRIMARY KEY (`id`));";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(sql);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void dropUsersTable() {
        String sql = "Drop TABLE IF EXISTS users";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        PreparedStatement preparedStatement;
        comand = "INSERT INTO users (firstname, lastname, age) VALUES( ?, ?, ?)";
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
        ArrayList<User> users = new ArrayList<>();
        try ( ResultSet resultSet = Util.getConnection().createStatement().executeQuery("SELECT * from users")){

            while (resultSet.next()) {
                users.add(new User((long) resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4)));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement=Util.getConnection().createStatement()){
            statement.executeUpdate("truncate  users");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
