package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.JspAttribute;
import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.service.PatientCardService;
import com.epam.medicalsystem.model.service.impl.PatientCardServiceImpl;
import com.epam.medicalsystem.util.MessageManager;
import com.epam.medicalsystem.validation.PatientCardValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

public class CreatePatientCardCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String firstName = request.getParameter(RequestParameter.FIRST_NAME); // TODO: 24.02.2022 Какие-то проблемы с кодировкой на стороне jsp вроде 
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String middleName = request.getParameter(RequestParameter.MIDDLE_NAME);
        String gender = request.getParameter(RequestParameter.GENDER);
        String birthday = request.getParameter(RequestParameter.BIRTHDAY);
        String homeTown = request.getParameter(RequestParameter.HOME_TOWN);
        String homeAddress = request.getParameter(RequestParameter.HOME_ADDRESS);
        String homeNumber = request.getParameter(RequestParameter.HOME_NUMBER);
        String apartmentNumber = request.getParameter(RequestParameter.APARTMENT_NUMBER);
        String phoneNumber = request.getParameter(RequestParameter.PHONE_NUMBER);

        Map<String, String> requestFields = new LinkedHashMap<>();

        requestFields.put(RequestParameter.FIRST_NAME, firstName);
        requestFields.put(RequestParameter.LAST_NAME, lastName);
        requestFields.put(RequestParameter.MIDDLE_NAME, middleName);
        requestFields.put(RequestParameter.GENDER, gender);
        requestFields.put(RequestParameter.BIRTHDAY, birthday);
        requestFields.put(RequestParameter.HOME_TOWN, homeTown);
        requestFields.put(RequestParameter.HOME_ADDRESS, homeAddress);
        requestFields.put(RequestParameter.HOME_NUMBER, homeNumber);
        requestFields.put(RequestParameter.APARTMENT_NUMBER, apartmentNumber);
        requestFields.put(RequestParameter.PHONE_NUMBER, phoneNumber);

        PatientCardService service = PatientCardServiceImpl.getInstance();
        try {
            if (service.createPatientCard(requestFields)) {
                request.setAttribute(JspAttribute.SUCCESS_PATIENT_CARD_REGISTRATION,
                        MessageManager.getProperty("message.successPatientCardRegistration"));
                return new CommandResult(PagePath.ADD_PATIENT, CommandResult.Type.FORWARD);
            } else {
                if (PatientCardValidator.isPatientCardFormValid(requestFields)) {
                    request.setAttribute(JspAttribute.ERROR_PATIENT_CARD_IS_EXIST, MessageManager.getProperty("message.cardIsExistError"));
                }
                request.setAttribute(RequestParameter.FIRST_NAME, requestFields.get(RequestParameter.FIRST_NAME));
                request.setAttribute(RequestParameter.LAST_NAME, requestFields.get(RequestParameter.LAST_NAME));
                request.setAttribute(RequestParameter.MIDDLE_NAME, requestFields.get(RequestParameter.MIDDLE_NAME));
                request.setAttribute(RequestParameter.BIRTHDAY, requestFields.get(RequestParameter.BIRTHDAY));
                request.setAttribute(RequestParameter.HOME_TOWN, requestFields.get(RequestParameter.HOME_TOWN));
                request.setAttribute(RequestParameter.HOME_ADDRESS, requestFields.get(RequestParameter.HOME_ADDRESS));
                request.setAttribute(RequestParameter.HOME_NUMBER, requestFields.get(RequestParameter.HOME_NUMBER));
                request.setAttribute(RequestParameter.APARTMENT_NUMBER, requestFields.get(RequestParameter.APARTMENT_NUMBER));
                request.setAttribute(RequestParameter.PHONE_NUMBER, requestFields.get(RequestParameter.PHONE_NUMBER));
                request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE,
                        MessageManager.getProperty("message.registrationError"));
            }
            return new CommandResult(PagePath.ADD_PATIENT, CommandResult.Type.FORWARD);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}