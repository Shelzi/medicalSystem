package com.epam.medicalsystem.controller.command;

import com.epam.medicalsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ActionCommand {
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}