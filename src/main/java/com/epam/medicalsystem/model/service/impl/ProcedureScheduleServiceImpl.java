package com.epam.medicalsystem.model.service.impl;

import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.dao.PatientDao;
import com.epam.medicalsystem.model.dao.ProcedureScheduleDao;
import com.epam.medicalsystem.model.dao.impl.PatientDaoImpl;
import com.epam.medicalsystem.model.dao.impl.ProcedureScheduleDaoImpl;
import com.epam.medicalsystem.model.entity.ProcedureSchedule;
import com.epam.medicalsystem.model.service.PatientService;
import com.epam.medicalsystem.model.service.ProcedureScheduleService;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProcedureScheduleServiceImpl implements ProcedureScheduleService {
    private static final Lock locker = new ReentrantLock();
    private static final ProcedureScheduleDao procedureScheduleDao = ProcedureScheduleDaoImpl.getInstance();
    private static ProcedureScheduleService instance;

    public static ProcedureScheduleService getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new ProcedureScheduleServiceImpl();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public List<ProcedureSchedule.Procedure> findAllProcedures() throws ServiceException {
        try {
            return procedureScheduleDao.findAllProcedures();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
