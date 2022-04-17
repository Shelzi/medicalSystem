package com.epam.medicalsystem.model.service;

import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.Visit;

import java.util.List;
import java.util.Map;

public interface VisitService {


    boolean create(Map<String, String> fields) throws ServiceException;

    List<Visit> findByPatientId(long patientId) throws ServiceException;

}