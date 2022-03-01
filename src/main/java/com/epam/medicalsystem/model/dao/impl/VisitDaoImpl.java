package com.epam.medicalsystem.model.dao.impl;

import com.epam.medicalsystem.exception.ConnectionPoolException;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.dao.PatientCardDao;
import com.epam.medicalsystem.model.dao.VisitDao;
import com.epam.medicalsystem.model.entity.Gender;
import com.epam.medicalsystem.model.entity.Visit;
import com.epam.medicalsystem.model.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.medicalsystem.model.dao.impl.SqlQuery.SQL_FIND_VISITS_BY_PATIENT_CARD_ID;
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
    public Set<Visit> findVisitsByPatientCardId(long cardId) throws DaoException {
        Set<Visit> visits = new HashSet<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_VISITS_BY_PATIENT_CARD_ID)) {
                preparedStatement.setLong(1, cardId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    visits.add();
                }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private Visit createVisitFromResultSet(ResultSet resultSet) {
        return new Visit(

        );
    }
}
