package com.epam.medicalsystem.controller;

import com.epam.medicalsystem.controller.command.ActionCommand;
import com.epam.medicalsystem.controller.command.CommandProvider;
import com.epam.medicalsystem.controller.command.CommandResult;
import com.epam.medicalsystem.exception.CommandException;
import com.epam.medicalsystem.model.pool.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "*.do", name = "Controller", loadOnStartup = 1)
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void init() {
        ConnectionPool.getInstance().init();
    }

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
        Optional<ActionCommand> command = CommandProvider.defineCommand(request);
        try {
            CommandResult commandResult = command.isPresent() ? command.get().execute(request, response) : new CommandResult(CommandResult.DEFAULT_PATH);
            commandResult.redirect(request, response);
        } catch (CommandException e) {
            logger.log(Level.ERROR, "Couldn't process request: " + e);
            throw new ServletException(e);
        }
    }
}