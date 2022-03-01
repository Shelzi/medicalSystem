package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.model.entity.PatientCard;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class FindAllPatientCardsCommand implements ActionCommand {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        
        //request.setAttribute("patientCards", );
        return new CommandResult(PagePath.PATIENTS, CommandResult.Type.FORWARD);
    }
}
