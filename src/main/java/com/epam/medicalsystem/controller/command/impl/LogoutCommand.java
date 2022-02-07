package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.command.ActionCommand;

import javax.servlet.http.HttpServletRequest;


public class LogoutCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) {
        String page = PagePath.INDEX;
// уничтожение сессии
        request.getSession().invalidate();
        return page;
    }
}