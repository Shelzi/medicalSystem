package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.Patient;
import com.epam.medicalsystem.model.entity.Visit;
import com.epam.medicalsystem.model.service.PatientService;
import com.epam.medicalsystem.model.service.VisitService;
import com.epam.medicalsystem.model.service.impl.PatientServiceImpl;
import com.epam.medicalsystem.model.service.impl.VisitServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ViewAllPatientVisitsCommand implements ActionCommand {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        VisitService visitService = VisitServiceImpl.getInstance();
        PatientService patientService = PatientServiceImpl.getInstance();
        String patientIdStr = request.getParameter(RequestParameter.PATIENT_ID);
        try {
            long patientId = Long.parseLong(patientIdStr);
            List<Visit> visitSet = visitService.findByPatientId(patientId);
            Patient patient = patientService.findById(patientId);
            request.setAttribute(RequestParameter.VISITS, visitSet);
            request.setAttribute(RequestParameter.PATIENT, patient);
        } catch (ServiceException e) {
            throw new CommandException(e);
        } catch (NumberFormatException e) {
            logger.log(Level.ERROR, "Couldn't convert from string to long str = " + patientIdStr + ": " + e);
            return new CommandResult(PagePath.VISITS, CommandResult.Type.FORWARD);
        }

        return new CommandResult(PagePath.VISITS, CommandResult.Type.FORWARD);
    }
}