package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.MessageManager;
import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.dao.UserDao;
import com.epam.medicalsystem.model.dao.impl.UserDaoImpl;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.factory.impl.UserFactory;
import com.epam.medicalsystem.model.service.UserService;
import com.epam.medicalsystem.model.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class RegisterCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String firstName = request.getParameter(RequestParameter.FIRST_NAME);
        String lastName = request.getParameter(RequestParameter.LAST_NAME);
        String middleName = request.getParameter(RequestParameter.MIDDLE_NAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String userRole = request.getParameter(RequestParameter.USER_ROLE);

        Map<String, String> requestFields = new LinkedHashMap<>();

        requestFields.put(RequestParameter.FIRST_NAME, firstName);
        requestFields.put(RequestParameter.LAST_NAME, lastName);
        requestFields.put(RequestParameter.MIDDLE_NAME, middleName);
        requestFields.put(RequestParameter.EMAIL, email);
        requestFields.put(RequestParameter.USER_ROLE, userRole);

        UserService service = UserServiceImpl.getInstance();

        try {
            if (service.register(requestFields)) {

            } else {
                request.setAttribute("errorRegister",
                        MessageManager.getProperty("Fail reg"));
            }
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        return PagePath.LOGIN;
    }
}