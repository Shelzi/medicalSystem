package com.epam.medicalsystem.model.dao.impl;

import com.epam.medicalsystem.exception.ConnectionPoolException;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.dao.VisitDao;
import com.epam.medicalsystem.model.entity.Patient;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.entity.Visit;
import com.epam.medicalsystem.model.pool.ConnectionPool;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.medicalsystem.model.dao.impl.SqlQuery.SQL_FIND_VISITS_BY_PATIENT_ID;

public class VisitDaoImpl implements VisitDao {
    private static final Lock locker = new ReentrantLock();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static VisitDao instance;

    public static VisitDao getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new VisitDaoImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public boolean add(Visit visit) throws DaoException {
        return false;
    }

    @Override
    public List<Visit> findVisitsByPatientId(long patientId) throws DaoException {
        List<Visit> visits = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_VISITS_BY_PATIENT_ID)) {
            preparedStatement.setLong(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                visits.add(createVisitFromResultSet(resultSet));
            }
            return visits;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private Visit createVisitFromResultSet(ResultSet resultSet) throws SQLException {
        return new Visit(
                resultSet.getLong("visitId"),
                new Timestamp(resultSet.getLong("visitDate")).toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime(),
                new Patient(resultSet.getLong("patientId_fk")),
                new User(resultSet.getLong("doctorId_fk")),
                resultSet.getString("anamnesis"),
                resultSet.getString("complaints"),
                resultSet.getString("diagnosis"),
                resultSet.getString("medicines"),
                new Timestamp(resultSet.getLong("nextVisitDate")).toInstant().atZone(ZoneId.of("UTC")).toLocalDateTime());
    }
}
