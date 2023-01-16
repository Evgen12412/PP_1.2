package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService userservice = new UserServiceImpl();



        userservice.createUsersTable();

        userservice.saveUser("Name10", "LastName10", (byte) 20);
        userservice.saveUser("Name20", "LastName20", (byte) 25);
        userservice.saveUser("Name30", "LastName30", (byte) 31);
        userservice.saveUser("Name40", "LastName40", (byte) 38);



        userservice.getAllUsers();
        userservice.cleanUsersTable();
        userservice.dropUsersTable();

    }
}
