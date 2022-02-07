package com.epam.medicalsystem.model.dao;

import com.epam.medicalsystem.model.entity.User;

import java.util.Optional;

public interface UserDao {
    boolean add(User user, String password);
}
