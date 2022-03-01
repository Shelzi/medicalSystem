package com.epam.medicalsystem.model.service.impl;

import com.epam.medicalsystem.controller.atribute.JspAttribute;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.dao.PatientCardDao;
import com.epam.medicalsystem.model.dao.impl.PatientCardDaoImpl;
import com.epam.medicalsystem.model.entity.Patient;
import com.epam.medicalsystem.model.entity.PatientCard;
import com.epam.medicalsystem.model.factory.impl.PatientFactory;
import com.epam.medicalsystem.model.pool.ConnectionPool;
import com.epam.medicalsystem.model.service.PatientCardService;
import com.epam.medicalsystem.util.MessageManager;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PatientCardServiceImpl implements PatientCardService {
    private static final Lock locker = new ReentrantLock();
    private static final PatientCardDao patientCardDao = PatientCardDaoImpl.getInstance();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static PatientCardService instance;

    public static PatientCardService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new PatientCardServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    public boolean createPatientCard(Map<String, String> fields) throws ServiceException {
        try {
            Optional<Patient> patientOptional = PatientFactory.getInstance().create(fields);
            if (patientOptional.isPresent()) {
                PatientCard patientCard = new PatientCard(patientOptional.get());
                if (!patientCardDao.isPatientCardExists(patientCard)) {
                    return (addTownIfNotExists(patientCard.getPatient().getAddress().getHomeTown()) && patientCardDao.add(patientCard));
                }
            } else {
                fields.put(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE, MessageManager.getProperty("message.inputFieldsInvalid"));
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public List<PatientCard> findAllCards() throws ServiceException {
        try {

            PatientCard patientCard = new PatientCard();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return null;
    }

    private boolean addTownIfNotExists(String town) throws DaoException {
        return (patientCardDao.findTownIdByName(town.toUpperCase(Locale.ROOT)).isPresent() ||
                patientCardDao.addTown(town.toUpperCase(Locale.ROOT)));
    }
}