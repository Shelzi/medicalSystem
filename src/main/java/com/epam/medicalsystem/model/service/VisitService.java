package com.epam.medicalsystem.model.service;

import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.Visit;

import java.util.List;

public interface VisitService {

    boolean add(Visit visit) throws ServiceException;

    List<Visit> findVisitsByPatientId(long patientId) throws ServiceException;

}