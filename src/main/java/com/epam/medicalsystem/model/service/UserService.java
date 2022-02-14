package com.epam.medicalsystem.model.service;

import com.epam.medicalsystem.exception.ServiceException;

import java.util.Map;

public interface UserService {
    boolean register(Map<String, String> fields) throws ServiceException;
}
