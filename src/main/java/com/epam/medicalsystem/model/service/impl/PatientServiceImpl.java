package com.epam.medicalsystem.model.service.impl;

import com.epam.medicalsystem.controller.atribute.JspAttribute;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.dao.PatientDao;
import com.epam.medicalsystem.model.dao.impl.PatientDaoImpl;
import com.epam.medicalsystem.model.entity.Patient;
import com.epam.medicalsystem.model.factory.impl.PatientFactory;
import com.epam.medicalsystem.model.service.PatientService;
import com.epam.medicalsystem.util.MessageManager;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PatientServiceImpl implements PatientService {
    private static final Lock locker = new ReentrantLock();
    private static final PatientDao patientDao = PatientDaoImpl.getInstance();
    private static PatientService instance;

    public static PatientService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new PatientServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    public boolean create(Map<String, String> fields) throws ServiceException {
        try {
            Optional<Patient> patientOptional = PatientFactory.getInstance().create(fields);
            if (patientOptional.isPresent()) {
                Patient patient = patientOptional.get();
                if (!patientDao.isPatientExists(patient)) {
                    return (addTownIfNotExists(patient.getAddress().getHomeTown()) && patientDao.add(patient));
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
    public Patient findById(long id) throws ServiceException {
        try {
            return patientDao.findPatientById(id).orElseThrow(() -> new ServiceException("Patient not exist"));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Patient> findByFields(Map<String, String> fields) throws ServiceException {
        return null;
    }

    @Override
    public List<Patient> findAll() throws ServiceException {
        try {
            return patientDao.findAllPatients();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private boolean addTownIfNotExists(String town) throws DaoException {
        return (patientDao.findTownIdByName(town.toUpperCase(Locale.ROOT)).isPresent() ||
                patientDao.addTown(town.toUpperCase(Locale.ROOT)));
    }
}