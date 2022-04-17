package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.controller.atribute.SessionAttribute;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.service.VisitService;
import com.epam.medicalsystem.model.service.impl.VisitServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreateVisitCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String anamnesis = request.getParameter(RequestParameter.ANAMNESIS);
        String complaints = request.getParameter(RequestParameter.COMPLAINTS);
        String diagnosis = request.getParameter(RequestParameter.DIAGNOSIS);
        String medicines = request.getParameter(RequestParameter.MEDICINES);
        String nextVisitDay = request.getParameter(RequestParameter.NEXT_DATE_VISIT);
        User doctor = (User)request.getSession().getAttribute(SessionAttribute.USER);
        String patientId = request.getParameter(RequestParameter.PATIENT_ID);
        Map<String, String> requestFields = new LinkedHashMap<>();

        requestFields.put(RequestParameter.ANAMNESIS, anamnesis);
        requestFields.put(RequestParameter.COMPLAINTS, complaints);
        requestFields.put(RequestParameter.DIAGNOSIS, diagnosis);
        requestFields.put(RequestParameter.MEDICINES, medicines);
        requestFields.put(RequestParameter.NEXT_DATE_VISIT, nextVisitDay);

         VisitService visitService = VisitServiceImpl.getInstance();
        try {
            if (visitService.create(requestFields)) {

            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return null;
    }
}
