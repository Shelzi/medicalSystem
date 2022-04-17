package com.epam.medicalsystem.controller.command.impl;

import com.epam.medicalsystem.controller.atribute.SessionAttribute;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class ChangeLanguageCommand implements ActionCommand {
    private static final Locale RU = new Locale("ru", "RU");
    private static final Locale EN = new Locale("en", "US");

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        HttpSession session = request.getSession();
        Locale locale = (Locale) session.getAttribute(SessionAttribute.CURRENT_LOCALE);
        if (locale.equals(EN)) {
            session.setAttribute(SessionAttribute.CURRENT_LOCALE, RU);
        } else {
            session.setAttribute(SessionAttribute.CURRENT_LOCALE, EN);
        }
        return new CommandResult(CommandResult.Type.RETURN_WITH_REDIRECT);
    }
}
