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
    VIEW_ALL_PATIENTS {
        {
            this.command = new ViewAllPatientsCommand();
        }
    },
    TO_ADD_PATIENT {
        {
            this.command = new ToAddPatientCommand();
        }
    },
    CREATE_PATIENT {
        {
            this.command = new CreatePatientCommand();
        }
    },
    VIEW_ALL_VISITS {
        {
            this.command = new ViewAllPatientVisitsCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}