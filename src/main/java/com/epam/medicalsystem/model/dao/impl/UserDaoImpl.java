package com.epam.medicalsystem.model.dao.impl;

import com.epam.medicalsystem.model.dao.UserDao;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.entity.UserRole;

import java.sql.*;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserDaoImpl implements UserDao {
    private static final Lock locker = new ReentrantLock();
    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new UserDaoImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean add(User user, String password) {
        String url = "jdbc:mysql://127.0.0.1:3306/medicalsystem";
        String usernameDB = "root";
        String passwordDB = "root";

        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String SQL_INSERT_USER = "INSERT INTO users(firstName, lastName," +
                    "middleName, email, password, userRole) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, user.getUserRole().toString());

            int rows = preparedStatement.executeUpdate();
            System.out.printf("%d rows added", rows);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        String url = "jdbc:mysql://127.0.0.1:3306/medicalsystem";
        String usernameDB = "root";
        String passwordDB = "root";
        String driverName = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(url, usernameDB, passwordDB)) {
            String SQL_INSERT_USER = "SELECT * FROM users where email = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (resultSet.next() ? Optional.of(createUserFromResultSet(resultSet)) : Optional.empty());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String middleName = resultSet.getString(4);
        String email = resultSet.getString(5);
        UserRole userRole = UserRole.valueOf(resultSet.getString(6).toUpperCase(Locale.ROOT));
        return (new User(id, firstName, lastName, middleName, email, userRole));
    }
}