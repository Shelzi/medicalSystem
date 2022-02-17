package com.epam.medicalsystem.model.dao.impl;

import com.epam.medicalsystem.exception.ConnectionPoolException;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.dao.UserDao;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.entity.UserRole;
import com.epam.medicalsystem.model.pool.ConnectionPool;

import java.sql.*;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.medicalsystem.model.dao.impl.SqlQuery.*;

public class UserDaoImpl implements UserDao {
    private static final Lock locker = new ReentrantLock();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static final UserRole DEFAULT_USER_ROLE = UserRole.NURSE;
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
    public boolean add(User user, String password) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getMiddleName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, password);
            preparedStatement.setInt(6, user.getUserRole().getRoleId());
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> userOptional;
        try (Connection connection = pool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            userOptional = (resultSet.next() ? Optional.of(createUserFromResultSet(resultSet)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return userOptional;
    }

    @Override
    public boolean isEmailAvailable(String email) throws DaoException {
        try (Connection connection = pool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet set = preparedStatement.executeQuery();
            return !set.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) throws DaoException {
        try (Connection connection = pool.takeConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_PASSWORD_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getString(1)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("userId");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String middleName = resultSet.getString("middleName");
        String email = resultSet.getString("email");
        Optional<UserRole> userRole = UserRole.valueOfRoleId(resultSet.getInt("userRoleId"));
        boolean isBanned = resultSet.getBoolean("isBanned");
        return (new User(id, firstName, lastName, middleName, email, userRole.orElse(DEFAULT_USER_ROLE), isBanned));
    }
}