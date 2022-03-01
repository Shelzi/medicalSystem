package com.epam.medicalsystem.model.dao;

import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.entity.Visit;

import java.util.Set;

public interface VisitDao {
    boolean add(Visit visit) throws DaoException;

    Set<Visit> findVisitsByPatientCardId(long cardId) throws DaoException;
}
