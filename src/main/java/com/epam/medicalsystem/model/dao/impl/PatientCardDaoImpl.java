package com.epam.medicalsystem.model.dao.impl;

import com.epam.medicalsystem.exception.ConnectionPoolException;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.dao.PatientCardDao;
import com.epam.medicalsystem.model.dao.UserDao;
import com.epam.medicalsystem.model.entity.PatientCard;
import com.epam.medicalsystem.model.pool.ConnectionPool;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.medicalsystem.model.dao.impl.SqlQuery.SQL_INSERT_PATIENT_CARD;

public class PatientCardDaoImpl implements PatientCardDao {
    private static final Lock locker = new ReentrantLock();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static PatientCardDao instance;

    public static PatientCardDao getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new PatientCardDaoImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public List<PatientCard> findAllCards() throws DaoException {
        return null;
    }

    @Override
    public boolean add(PatientCard patientCard) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_PATIENT_CARD)) {
            preparedStatement.setString(1, patientCard.getPatient().getFirstName());
            preparedStatement.setString(2, patientCard.getPatient().getLastName());
            preparedStatement.setString(3, patientCard.getPatient().getMiddleName());
            preparedStatement.setInt(4, patientCard.getPatient().getGender().getGenderId());
            preparedStatement.setDate(5, Date.valueOf(patientCard.getPatient().getBirthday()));
            preparedStatement.setInt(6, findTownIdByName(patientCard.getPatient().getAddress().getHomeTown()).orElseThrow(() -> new DaoException("Invalid city")));
            preparedStatement.setString(7, patientCard.getPatient().getAddress().getHomeAddress());
            preparedStatement.setString(8, patientCard.getPatient().getAddress().getHomeNumber());
            preparedStatement.setString(9, patientCard.getPatient().getAddress().getApartmentNumber());
            preparedStatement.setString(10, patientCard.getPatient().getAddress().getPhoneNumber());
            return (preparedStatement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean addTown(String name) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_INSERT_TOWN)) {
            statement.setString(1, name);
            return (statement.executeUpdate() == 1);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    public Optional<Integer> findTownIdByName(String name) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_FIND_TOWN_ID_BY_NAME)) {
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return (resultSet.next() ? Optional.of(resultSet.getInt(1)) : Optional.empty());
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean isPatientCardExists(PatientCard patientCard) throws DaoException {
        try (Connection connection = pool.takeConnection();
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_CHECK_VACANCY_FOR_EXISTENCE)) {
            statement.setString(1, patientCard.getPatient().getFirstName());
            statement.setString(2, patientCard.getPatient().getLastName());
            statement.setString(3, patientCard.getPatient().getMiddleName());
            statement.setDate(4, Date.valueOf(patientCard.getPatient().getBirthday()));
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }
}