package com.epam.medicalsystem.model.dao.impl;

import com.epam.medicalsystem.model.dao.UserDao;
import com.epam.medicalsystem.model.entity.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
}