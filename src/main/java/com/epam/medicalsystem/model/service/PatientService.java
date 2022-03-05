package com.epam.medicalsystem.model.service;

import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.Patient;

import java.util.List;
import java.util.Map;

public interface PatientService {

    boolean createPatient(Map<String, String> fields) throws ServiceException;

    List<Patient> findAllPatients() throws ServiceException;

}