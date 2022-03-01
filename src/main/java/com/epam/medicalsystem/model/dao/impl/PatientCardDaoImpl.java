package com.epam.medicalsystem.model.dao.impl;

import com.epam.medicalsystem.exception.ConnectionPoolException;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.dao.PatientCardDao;
import com.epam.medicalsystem.model.entity.*;
import com.epam.medicalsystem.model.pool.ConnectionPool;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.medicalsystem.model.dao.impl.SqlQuery.SQL_INSERT_PATIENT_CARD;

public class PatientCardDaoImpl implements PatientCardDao {
    private static final Lock locker = new ReentrantLock();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static PatientCardDao instance;
    private static final Gender DEFAULT_GENDER = Gender.OTHER;

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
             PreparedStatement statement = connection.prepareStatement(SqlQuery.SQL_CHECK_CARD_FOR_EXISTENCE)) {
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

    @Override
    public List<PatientCard> findAllCards() throws DaoException {
        List<PatientCard> patientCards = new ArrayList<>();
        try(Connection connection = pool.takeConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SqlQuery.SQL_FIND_ALL_CARDS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Patient patient.add(createPatientFromResultSet(resultSet));
            }   // TODO: 28.02.2022 переписать
            return patientCards;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private PatientCard createPatientFromResultSet(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String middleName = resultSet.getString("middleName");
        Optional<Gender> gender = Gender.valueOfGenderId(resultSet.getInt("gender"));
        LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
        String homeTown = resultSet.getString("city");
        String homeAddress = resultSet.getString("homeAddress");
        String homeNumber = resultSet.getString("homeNumber");
        String apartmentNumber = resultSet.getString("apartmentNumber");
        String phoneNumber = resultSet.getString("phoneNumber");
        return (new PatientCard(new Patient(firstName, lastName, middleName, gender.orElse(DEFAULT_GENDER),
                birthday, homeTown, homeAddress, homeNumber, apartmentNumber, phoneNumber), new HashSet<Visit>()));
    }
}