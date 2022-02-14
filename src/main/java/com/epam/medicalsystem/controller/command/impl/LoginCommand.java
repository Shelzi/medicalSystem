package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.MessageManager;
import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.SessionAttribute;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.exception.ServiceException;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.service.UserService;
import com.epam.medicalsystem.model.service.impl.UserServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private static final UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request) throws CommandException{
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            Optional<User> userOptional = userService.login(login, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                //if (user.getIsActive()) {
                    HttpSession session = request.getSession();
                    session.setAttribute(SessionAttribute.USER, user);
                    session.setAttribute(SessionAttribute.CURRENT_ROLE, user.getUserRole());
                    session.setAttribute(SessionAttribute.USER_ID, user.getId());
                    return PagePath.MAIN;
                    //result = new CommandResult(PagePath.MAIN, CommandResult.Type.REDIRECT);
                //}
            }
            return PagePath.LOGIN;
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
    }
}