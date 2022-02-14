package com.epam.medicalsystem.controller.command;

import com.epam.medicalsystem.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {
    String execute(HttpServletRequest request) throws CommandException;
}