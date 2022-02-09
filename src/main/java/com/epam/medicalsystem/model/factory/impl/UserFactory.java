package com.epam.medicalsystem.model.factory.impl;

import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.entity.UserRole;
import com.epam.medicalsystem.model.factory.EntityFactory;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UserFactory implements EntityFactory<User> {
    private static final Lock locker = new ReentrantLock();
    private static EntityFactory<User> instance;

    private UserFactory() {
    }

    public static EntityFactory<User> getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new UserFactory();
            }
            locker.unlock();
        }
        return instance;
    }

    public Optional<User> create(Map<String, String> fields) {
        String email = fields.get(RequestParameter.EMAIL);
        String firstName = fields.get(RequestParameter.FIRST_NAME);
        String lastName = fields.get(RequestParameter.LAST_NAME);
        String middleName = fields.get(RequestParameter.MIDDLE_NAME);
        UserRole userRole = UserRole.valueOf(fields.get(RequestParameter.USER_ROLE).toUpperCase(Locale.ROOT));
        return Optional.of(new User(firstName, lastName, middleName, email, userRole));
    }
}