package com.epam.medicalsystem.controller.command;

import com.epam.medicalsystem.controller.command.impl.*;

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
    },
    FIND_ALL_PATIENT_CARDS {
        {
            this.command = new FindAllPatientCardsCommand();
        }
    },
    TO_ADD_PATIENT{
        {
            this.command = new ToAddPatientCommand();
        }
    },
    CREATE_PATIENT_CARD{
        {
            this.command = new CreatePatientCardCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}