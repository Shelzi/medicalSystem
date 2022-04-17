package com.epam.medicalsystem.model.dao;

import com.epam.medicalsystem.exception.DaoException;
import com.epam.medicalsystem.model.entity.ProcedureSchedule;

import java.util.List;

public interface ProcedureScheduleDao {
    List<ProcedureSchedule.Procedure> findAllProcedures() throws DaoException;
}
