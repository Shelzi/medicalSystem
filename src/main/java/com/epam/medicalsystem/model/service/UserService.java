package com.epam.medicalsystem.model.service;

import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.User;

import java.util.Map;
import java.util.Optional;

public interface UserService {
    boolean register(Map<String, String> fields) throws ServiceException;
    Optional<User> login(String email, String password) throws ServiceException;
}
