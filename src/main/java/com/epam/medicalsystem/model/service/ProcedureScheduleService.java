package com.epam.medicalsystem.model.service;

import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.ProcedureSchedule;

import java.util.List;

public interface ProcedureScheduleService {
    List<ProcedureSchedule.Procedure> findAllProcedures() throws ServiceException;

}