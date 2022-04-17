package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.JspAttribute;
import com.epam.medicalsystem.controller.atribute.SessionAttribute;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.util.MessageManager;
import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.service.UserService;
import com.epam.medicalsystem.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegisterCommand implements ActionCommand {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String middleName = request.getParameter(RequestParameter.MIDDLE_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String repeatedPassword = request.getParameter(RequestParameter.REPEATED_PASSWORD);
        String userRole = request.getParameter(RequestParameter.USER_ROLE);

        Map<String, String> requestFields = new LinkedHashMap<>();

        requestFields.put(RequestParameter.FIRST_NAME, firstName);
        requestFields.put(RequestParameter.LAST_NAME, lastName);
        requestFields.put(RequestParameter.MIDDLE_NAME, middleName);
        requestFields.put(RequestParameter.EMAIL, email);
        requestFields.put(RequestParameter.PASSWORD, password);
        requestFields.put(RequestParameter.REPEATED_PASSWORD, repeatedPassword);
        requestFields.put(RequestParameter.USER_ROLE, userRole);

        UserService service = UserServiceImpl.getInstance();
        CommandResult result = new CommandResult(SessionAttribute.MAIN_PAGE, CommandResult.Type.REDIRECT);

        try {
            if (service.create(requestFields)) {
                HttpSession session = request.getSession();
                session.setAttribute(SessionAttribute.SUCCESS_MESSAGE, Boolean.TRUE);
            } else {
                request.setAttribute(RequestParameter.FIRST_NAME, requestFields.get(RequestParameter.FIRST_NAME));
                request.setAttribute(RequestParameter.LAST_NAME, requestFields.get(RequestParameter.LAST_NAME));
                request.setAttribute(RequestParameter.MIDDLE_NAME, requestFields.get(RequestParameter.MIDDLE_NAME));
                request.setAttribute(RequestParameter.EMAIL, requestFields.get(RequestParameter.EMAIL));
                request.setAttribute(RequestParameter.PASSWORD, requestFields.get(RequestParameter.PASSWORD));
                request.setAttribute(RequestParameter.REPEATED_PASSWORD, requestFields.get(RequestParameter.REPEATED_PASSWORD));
                request.setAttribute(JspAttribute.ERROR_INPUT_DATA_ATTRIBUTE,
                        MessageManager.getProperty("message.registrationError"));
                return new CommandResult(PagePath.REGISTER, CommandResult.Type.FORWARD);
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return result;
    }
}