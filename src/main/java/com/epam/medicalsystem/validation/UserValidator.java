package com.epam.medicalsystem.validation;

import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.model.entity.UserRole;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UserValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{3,255}");
    private static final Pattern EMAIL_PATTERN = Pattern.compile("((\\w)([-.](\\w))?){1,64}@((\\w)([-.](\\w))?){1,251}.[a-zA-Z]{2,4}");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[а-яА-Я\\w\\s\\p{Punct}]{6,255}");


    private UserValidator() {
    }

    public static boolean isRegisterFormValid(Map<String, String> fields) {
        boolean result = true;
        String firstName = fields.get(RequestParameter.FIRST_NAME);
        if (!isNameValid(firstName)) {
            fields.put(RequestParameter.FIRST_NAME, "");
            result = false;
        }
        String lastName = fields.get(RequestParameter.LAST_NAME);
        if (!isNameValid(lastName)) {
            fields.put(RequestParameter.LAST_NAME, "");
            result = false;
        }
        String middleName = fields.get(RequestParameter.MIDDLE_NAME);
        if (!isNameValid(middleName)) {
            fields.put(RequestParameter.MIDDLE_NAME, "");
            result = false;
        }
        String email = fields.get(RequestParameter.EMAIL);
        if (!isEmailValid(email)) {
            fields.put(RequestParameter.EMAIL, "");
            result = false;
        }
        String password = fields.get(RequestParameter.PASSWORD);
        if (!isPasswordValid(password)) {
            fields.put(RequestParameter.PASSWORD, "");
            result = false;
        }
        String repeatPassword = fields.get(RequestParameter.REPEATED_PASSWORD);
        if (!isRepeatPasswordValid(password, repeatPassword)) {
            fields.put(RequestParameter.REPEATED_PASSWORD, "");
            result = false;
        }
        String userRole = fields.get(RequestParameter.USER_ROLE);
        if (!isUserRoleValid(userRole)){
            fields.put(RequestParameter.USER_ROLE, "");
            result = false;
        }
        return result;
    }

    public static boolean isUserRoleValid(String role) {
        if (role == null || role.isEmpty()) {
            return false;
        }
        boolean result = Arrays.stream(UserRole.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(role.toUpperCase());
        if (!result) {
            logger.log(Level.DEBUG, "Role is invalid: " + role);
        }
        return result;
    }

    public static boolean isNameValid(String name) {
        if (name == null) {
            return false;
        }
        Matcher matcher = NAME_PATTERN.matcher(name);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Name is invalid: " + name);
        }
        return result;
    }

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Email is invalid: " + email);
        }
        return result;
    }

    public static boolean isRepeatPasswordValid(String password, String repeatPassword) {
        if (repeatPassword == null) {
            return false;
        }
        boolean result = isPasswordValid(password) && password.equals(repeatPassword);
        if (!result) {
            logger.log(Level.DEBUG, "Repeat password is invalid: " + repeatPassword);
        }
        return result;
    }

    public static boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Password is invalid: " + password);
        }
        return result;
    }
}