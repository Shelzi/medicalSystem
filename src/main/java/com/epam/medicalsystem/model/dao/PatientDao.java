package com.epam.medicalsystem.model.dao;

import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientDao {
    List<Patient> findAllPatients() throws DaoException;

    boolean add(Patient patient) throws DaoException;

    boolean addTown(String name) throws DaoException;

    Optional<Integer> findTownIdByName(String name) throws DaoException;

    boolean isPatientExists(Patient patient) throws DaoException;

    Optional<Patient> findPatientById(long id) throws DaoException;
}