package com.epam.medicalsystem.model.dao.impl;

public class SqlQuery {
    public static final String SQL_INSERT_USER = "INSERT INTO users(firstName, lastName," +
            "middleName, email, password, userRoleId) VALUES (?, ?, ?, ?, ?, ?);";

    public static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users where email = ?";

    public static final String SQL_FIND_EMAIL = "SELECT * FROM users where email = ?";

    public static final String SQL_FIND_PASSWORD_BY_EMAIL = "SELECT users.password FROM users where email = ?";
}
