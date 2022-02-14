package com.epam.medicalsystem.controller;

import com.epam.medicalsystem.controller.atribute.JspAttribute;
import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.exception.CommandException;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
        try {
            page = command.execute(request);
        } catch (CommandException e) {
            throw new ServletException(e);
        }
        //page = null;
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = PagePath.INDEX;
            request.getSession().setAttribute("nullPage", JspAttribute.ERROR_NULL_PAGE);
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}