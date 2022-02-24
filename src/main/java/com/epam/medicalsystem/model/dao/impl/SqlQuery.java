package com.epam.medicalsystem.model.dao.impl;

public class SqlQuery {
    public static final String SQL_INSERT_USER = "INSERT INTO users(firstName, lastName," +
            "middleName, email, password, userRoleId) VALUES (?, ?, ?, ?, ?, ?);";

    public static final String SQL_FIND_USER_BY_EMAIL = "SELECT * FROM users where email = ?";

    public static final String SQL_FIND_EMAIL = "SELECT * FROM users where email = ?";

    public static final String SQL_FIND_PASSWORD_BY_EMAIL = "SELECT users.password FROM users where email = ?";

    public static final String SQL_INSERT_PATIENT_CARD = "INSERT INTO patients(firstName, lastName, middleName," +
            "gender, birthday, homeTownId_fk, homeAddress, homeNumber, apartmentNumber, phoneNumber) VALUES " +
            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_INSERT_TOWN = "INSERT INTO cities(city) VALUES (?);";

    public static final String SQL_FIND_TOWN_ID_BY_NAME = "SELECT city_id FROM cities WHERE city = ?;";

    public static final String SQL_CHECK_VACANCY_FOR_EXISTENCE = "SELECT patientId FROM patients" +
            " WHERE firstName = ? AND lastName = ? AND middleName = ? AND birthday = ?;";
}
