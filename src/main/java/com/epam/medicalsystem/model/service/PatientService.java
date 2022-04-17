package com.epam.medicalsystem.model.service;

import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.Patient;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PatientService {

    boolean create(Map<String, String> fields) throws ServiceException;

    List<Patient> findAll() throws ServiceException;

    Patient findById(long id) throws ServiceException;

    List<Patient> findByFields(Map<String, String> fields) throws ServiceException;
}