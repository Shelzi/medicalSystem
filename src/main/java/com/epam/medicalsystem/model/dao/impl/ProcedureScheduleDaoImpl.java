package com.epam.medicalsystem.model.dao.impl;

import com.epam.medicalsystem.exception.ConnectionPoolException;
import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.dao.ProcedureScheduleDao;
import com.epam.medicalsystem.model.entity.ProcedureSchedule;
import com.epam.medicalsystem.model.pool.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.epam.medicalsystem.model.dao.impl.SqlQuery.SQL_FIND_ALL_PROCEDURES;

public class ProcedureScheduleDaoImpl implements ProcedureScheduleDao {
    private static final Lock locker = new ReentrantLock();
    private static final ConnectionPool pool = ConnectionPool.getInstance();
    private static ProcedureScheduleDao instance;

    public static ProcedureScheduleDao getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new ProcedureScheduleDaoImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public List<ProcedureSchedule.Procedure> findAllProcedures() throws DaoException {
        List<ProcedureSchedule.Procedure> procedureList = new ArrayList<>();
        try (Connection connection = pool.takeConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_ALL_PROCEDURES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                procedureList.add(createProcedureFromResultSet(resultSet));
            }
            return procedureList;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    private ProcedureSchedule.Procedure createProcedureFromResultSet(ResultSet resultSet) throws SQLException {
        return new ProcedureSchedule.Procedure(
                resultSet.getString("procedureName"),
                resultSet.getString("procedureDescription"));
    }
}