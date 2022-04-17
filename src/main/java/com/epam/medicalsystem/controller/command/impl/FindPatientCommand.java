package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.JspAttribute;
import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.dao.PatientDao;
import com.epam.medicalsystem.model.dao.impl.PatientDaoImpl;
import com.epam.medicalsystem.model.entity.Patient;
import com.epam.medicalsystem.model.service.PatientService;
import com.epam.medicalsystem.model.service.impl.PatientServiceImpl;
import com.epam.medicalsystem.util.MessageManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindPatientCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String birthday = request.getParameter(RequestParameter.BIRTHDAY);

        Map<String, String> parameters = new HashMap<>();

        parameters.put(RequestParameter.LAST_NAME, lastName);
        parameters.put(RequestParameter.BIRTHDAY, birthday);

        PatientService patientService = PatientServiceImpl.getInstance();
        try {
            List<Patient> patientList = patientService.findByFields(parameters);
            if (patientList.isEmpty()) {
                request.setAttribute(JspAttribute.ERROR_PATIENT_NOT_FOUND, MessageManager.getProperty("message.patientNotFoundError"));
            }
            return new CommandResult(PagePath.FIND_PATIENT, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}
