package com.epam.medicalsystem.controller.command;

import com.epam.medicalsystem.controller.atribute.PagePath;
import com.epam.medicalsystem.controller.atribute.RequestParameter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandResult {
    public static final String DEFAULT_PATH = PagePath.MAIN;

    private String path;
    private Type type;

    public enum Type {
        FORWARD,
        REDIRECT,
        RETURN_WITH_REDIRECT
    }

    public CommandResult(String path, Type type) {
        this.path = path;
        this.type = type;
    }

    public CommandResult(String path) {
        this.path = path;
        this.type = Type.FORWARD;
    }

    public CommandResult(Type type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String providePath() {
        return (path == null || path.isEmpty() ? DEFAULT_PATH : path);
    }

    public void redirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        switch (this.getType()) {
            case FORWARD: {
                request.getRequestDispatcher(this.providePath()).forward(request, response);
                break;
            }
            case REDIRECT: {
                response.sendRedirect(request.getContextPath() + this.providePath());
                break;
            }
            case RETURN_WITH_REDIRECT: {
                String previousUrl = request.getHeader("referer");
                if (previousUrl == null || previousUrl.isEmpty()) {
                    previousUrl = request.getContextPath() + CommandResult.DEFAULT_PATH;
                }
                response.sendRedirect(previousUrl);
                break;
            }
            default: {
                response.sendRedirect(request.getContextPath() + CommandResult.DEFAULT_PATH);
            }
        }
    }
}