package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.LoginLogic;
import com.epam.medicalsystem.controller.MessageManager;
import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;

public class LoginCommand implements ActionCommand {
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    @Override
    public String execute(HttpServletRequest request) {
        String page = null;
// извлечение из запроса логина и пароля
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
// проверка логина и пароля
        if (LoginLogic.checkLogin(login, pass)) {
            request.setAttribute("user", login);
// определение пути к main.jsp
            page = PagePath.MAIN;
        } else {
            request.setAttribute("errorLoginPassMessage",
                    MessageManager.getProperty("message.loginerror"));
            page = PagePath.ERROR;
        }
        return page;
    }
}