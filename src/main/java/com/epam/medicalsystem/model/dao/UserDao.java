package com.epam.medicalsystem.model.dao;

import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.entity.User;

import java.util.Optional;

public interface UserDao {
    boolean add(User user, String password) throws DaoException;
    Optional<User> findUserByEmail(String email) throws DaoException;
    boolean isEmailAvailable(String email) throws DaoException;
}
