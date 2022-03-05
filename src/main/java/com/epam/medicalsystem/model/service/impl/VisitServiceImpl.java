package com.epam.medicalsystem.model.service.impl;

import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.dao.VisitDao;
import com.epam.medicalsystem.model.dao.impl.VisitDaoImpl;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.entity.Visit;
import com.epam.medicalsystem.model.factory.EntityFactory;
import com.epam.medicalsystem.model.factory.impl.UserFactory;
import com.epam.medicalsystem.model.service.VisitService;

import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VisitServiceImpl implements VisitService {
    private static final EntityFactory<User> userFactory = UserFactory.getInstance();
    private static final VisitDao visitDao = VisitDaoImpl.getInstance();
    private static final Lock locker = new ReentrantLock();
    private static volatile VisitService instance = VisitServiceImpl.getInstance();

    public static VisitService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new VisitServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean add(Visit visit) throws ServiceException {
        return false;
        // TODO: 02.03.2022
    }

    @Override
    public List<Visit> findVisitsByPatientId(long patientId) throws ServiceException {
        try {
            // TODO: 06.03.2022 необходимо сортировать сет, нужен сортированный сет, лучше переехать на лист
            return visitDao.findVisitsByPatientId(patientId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
