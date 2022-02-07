package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.MessageManager;
import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.model.dao.UserDao;
import com.epam.medicalsystem.model.dao.impl.UserDaoImpl;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.factory.impl.UserFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class RegisterCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
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

        Optional<User> userOptional = UserFactory.getInstance().create(requestFields);

        if (userOptional.isPresent()) {
            UserDaoImpl.getInstance().add(userOptional.get(), password);
        } else {
            request.setAttribute("errorRegister",
                    MessageManager.getProperty("Fail reg"));
        }

        return PagePath.LOGIN;
    }
}