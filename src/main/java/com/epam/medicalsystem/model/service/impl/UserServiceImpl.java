package com.epam.medicalsystem.model.service.impl;

import com.epam.medicalsystem.controller.atribute.JspAttribute;
import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.dao.UserDao;
import com.epam.medicalsystem.model.dao.impl.UserDaoImpl;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.factory.EntityFactory;
import com.epam.medicalsystem.model.factory.impl.UserFactory;
import com.epam.medicalsystem.model.service.UserService;
import com.epam.medicalsystem.util.Encryptor;
import com.epam.medicalsystem.util.MessageManager;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final EntityFactory<User> userFactory = UserFactory.getInstance();
    private static final UserDao dao = UserDaoImpl.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile UserServiceImpl instance = UserServiceImpl.getInstance();


    public static UserServiceImpl getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new UserServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean register(Map<String, String> fields) throws ServiceException {
        try {
            Optional<User> userOptional = userFactory.create(fields);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                if (dao.isEmailAvailable(user.getEmail())) {
                    String password = fields.get(RequestParameter.PASSWORD);
                    String encryptedPassword = Encryptor.encrypt(password);
                    return dao.add(user, encryptedPassword);
                } else {
                    fields.put(RequestParameter.EMAIL, MessageManager.getProperty("message.emailIsTakenError"));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public Optional<User> login(String email, String password) throws ServiceException {
        try {
            Optional<String> resultPasswordOptional = dao.findPasswordByEmail(email);
            if (resultPasswordOptional.isPresent() && Encryptor.check(password, resultPasswordOptional.get())) {
                return dao.findUserByEmail(email);
            } else {
                return Optional.empty();
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}