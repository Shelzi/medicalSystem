package com.epam.medicalsystem.controller.listener;

import com.epam.medicalsystem.controller.atribute.SessionAttribute;
import com.epam.medicalsystem.model.entity.UserRole;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Locale;

public class SessionListener implements HttpSessionListener {
    @Override
     public void sessionCreated(HttpSessionEvent sessionEvent) {
         HttpSession session = sessionEvent.getSession();
         session.setAttribute(SessionAttribute.CURRENT_LOCALE, new Locale("ru", "RU"));
         session.setAttribute(SessionAttribute.CURRENT_ROLE, UserRole.GUEST);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        sessionEvent.getSession().invalidate();
    }
}
