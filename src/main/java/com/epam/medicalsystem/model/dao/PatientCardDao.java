package com.epam.medicalsystem.model.dao;

import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.entity.PatientCard;

import java.util.List;
import java.util.Optional;

public interface PatientCardDao {
    List<PatientCard> findAllCards() throws DaoException;

    boolean add(PatientCard patientCard) throws DaoException;

    boolean addTown(String name) throws DaoException;

    Optional<Integer> findTownIdByName(String name) throws DaoException;

    boolean isPatientCardExists(PatientCard patientCard) throws DaoException;
}
