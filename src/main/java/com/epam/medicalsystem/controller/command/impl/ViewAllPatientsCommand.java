package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.Patient;
import com.epam.medicalsystem.model.service.impl.PatientServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewAllPatientsCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            List<Patient> patients = PatientServiceImpl.getInstance().findAllPatients();;
            request.setAttribute(RequestParameter.PATIENTS, patients);
            return new CommandResult(PagePath.PATIENTS, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
