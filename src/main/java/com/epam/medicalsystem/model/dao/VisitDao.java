package com.epam.medicalsystem.model.dao;

import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.entity.Visit;

import java.util.List;

public interface VisitDao {
    boolean add(Visit visit) throws DaoException;

    List<Visit> findVisitsByPatientId(long patientId) throws DaoException;
}
