package com.epam.medicalsystem.model.service.impl;

import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.dao.UserDao;
import com.epam.medicalsystem.model.dao.VisitDao;
import com.epam.medicalsystem.model.dao.impl.UserDaoImpl;
import com.epam.medicalsystem.model.dao.impl.VisitDaoImpl;
import com.epam.medicalsystem.model.entity.Patient;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.entity.Visit;
import com.epam.medicalsystem.model.factory.EntityFactory;
import com.epam.medicalsystem.model.factory.impl.UserFactory;
import com.epam.medicalsystem.model.factory.impl.VisitFactory;
import com.epam.medicalsystem.model.service.PatientService;
import com.epam.medicalsystem.model.service.VisitService;
import com.epam.medicalsystem.validation.VisitValidator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VisitServiceImpl implements VisitService {
    private static final EntityFactory<User> userFactory = UserFactory.getInstance();
    private static final VisitDao visitDao = VisitDaoImpl.getInstance();
    private static final PatientService patientService = PatientServiceImpl.getInstance();
    private static final UserDao userDao = UserDaoImpl.getInstance();
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
    public boolean create(Map<String, String> fields) throws ServiceException {
        try {
            if (VisitValidator.isVisitFormValid(fields)){
                Optional<Visit> visitOptional = VisitFactory.getInstance().create(fields);
                if (visitOptional.isPresent()) {
                    visitDao.add(visitOptional.get());
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<Visit> findByPatientId(long patientId) throws ServiceException {
        try {
            List<Visit> visitList = visitDao.findVisitsByPatientId(patientId);
            Patient patient = patientService.findById(patientId);
            for (Visit visit : visitList) {
                visit.setDoctor(userDao.findUserById(visit.getDoctor().getId())
                        .orElseThrow(() -> new ServiceException("User with current doctorId doesn't exist")));
            }
            return visitList;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}