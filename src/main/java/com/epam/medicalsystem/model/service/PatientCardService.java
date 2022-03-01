package com.epam.medicalsystem.model.service;

import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.PatientCard;

import java.util.List;
import java.util.Map;

public interface PatientCardService {
    List<PatientCard> findAllCards() throws ServiceException;
    boolean createPatientCard(Map<String, String> fields) throws ServiceException;
}
