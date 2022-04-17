package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.ProcedureSchedule;
import com.epam.medicalsystem.model.service.ProcedureScheduleService;
import com.epam.medicalsystem.model.service.impl.ProcedureScheduleServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ToAddVisitCommand implements ActionCommand {
    private static final ProcedureScheduleService procedureScheduleService = ProcedureScheduleServiceImpl.getInstance();

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        try {
            List<String> procedureNames = procedureScheduleService.findAllProcedures().stream()
                    .map(ProcedureSchedule.Procedure::getName)
                    .collect(Collectors.toList());
            request.setAttribute("procedures", procedureNames);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return new CommandResult(PagePath.ADD_VISIT, CommandResult.Type.FORWARD);
    }
}