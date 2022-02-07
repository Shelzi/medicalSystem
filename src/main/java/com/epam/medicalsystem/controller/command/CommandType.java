package com.epam.medicalsystem.controller.command;

import com.epam.medicalsystem.controller.command.impl.LoginCommand;
import com.epam.medicalsystem.controller.command.impl.LogoutCommand;
import com.epam.medicalsystem.controller.command.impl.RegisterCommand;

public enum CommandType {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTER {
        {
            this.command = new RegisterCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}