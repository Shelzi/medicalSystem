package com.epam.medicalsystem.validation;

import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.model.entity.Gender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PatientValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern DATE_FORMAT_PATTERN = Pattern.compile("([1-2][0-9]{3})-([0][1-9]|[1][0-2])-([0][1-9]|[12][0-9]|[3][01])");
    private static final Pattern HOME_TOWN_PATTERN = Pattern.compile("^[а-яА-Яa-zA-Z',.\\s-]{1,25}");
    private static final Pattern HOME_ADDRESS_PATTERN = Pattern.compile("^[а-яА-Яa-zA-Z',.\\s-]{1,40}");
    private static final Pattern HOME_NUMBER_PATTERN = Pattern.compile("^[а-яА-Яa-zA-Z1-9',\\s-]{1,20}");
    private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("(\\+?(((\\d+-\\d+)+)|(\\d{2,20})|((\\d+\\s\\d+)+)))|" +
            "(\\(\\+?\\d+\\)[-\\s]?(((\\d+-\\d+)+)|(\\d+)|((\\d+\\s\\d+)+)))");
    private static final int PHONE_NUMBER_MAX_LENGTH = 20;

    public static boolean isPatientFormValid(Map<String, String> fields) {
        boolean result = true;
        String firstName = fields.get(RequestParameter.FIRST_NAME);
        if (!UserValidator.isNameValid(firstName)) {
            fields.put(RequestParameter.FIRST_NAME, "");
            result = false;
        }
        String lastName = fields.get(RequestParameter.LAST_NAME);
        if (!UserValidator.isNameValid(lastName)) {
            fields.put(RequestParameter.LAST_NAME, "");
            result = false;
        }
        String middleName = fields.get(RequestParameter.MIDDLE_NAME);
        if (!UserValidator.isNameValid(middleName)) {
            fields.put(RequestParameter.MIDDLE_NAME, "");
            result = false;
        }
        String gender = fields.get(RequestParameter.GENDER);
        if (!isGenderValid(gender)) {
            fields.put(RequestParameter.USER_ROLE, "");
            result = false;
        }
        String birthday = fields.get(RequestParameter.BIRTHDAY);
        if (!isBirthdayDateValid(birthday)) {
            fields.put(RequestParameter.BIRTHDAY, "");
            result = false;
        }
        String homeTown = fields.get(RequestParameter.HOME_TOWN);
        if (!isHomeTownValid(homeTown)) {
            fields.put(RequestParameter.HOME_TOWN, "");
            result = false;
        }
        String homeAddress = fields.get(RequestParameter.HOME_ADDRESS);
        if (!isHomeAddressValid(homeAddress)) {
            fields.put(RequestParameter.HOME_ADDRESS, "");
            result = false;
        }
        String homeNumber = fields.get(RequestParameter.HOME_NUMBER);
        if (!isHomeNumberValid(homeNumber)) {
            fields.put(RequestParameter.HOME_NUMBER, "");
            result = false;
        }
        String phoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
        if (!isPhoneNumberValid(phoneNumber)) {
            fields.put(RequestParameter.PHONE_NUMBER, "");
            result = false;
        }
        return result;
    }

    private static boolean isHomeNumberValid(String homeNumber) {
        if (homeNumber == null) {
            return false;
        }
        Matcher matcher = HOME_NUMBER_PATTERN.matcher(homeNumber);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Home number is invalid: " + homeNumber);
        }
        return result;
    }

    public static boolean isPhoneNumberValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        Matcher matcher = PHONE_NUMBER_PATTERN.matcher(phoneNumber);
        boolean result = matcher.matches() && phoneNumber.length() <= PHONE_NUMBER_MAX_LENGTH;
        if (!result) {
            logger.log(Level.DEBUG, "Phone number isn't valid: " + phoneNumber);
        }
        return result;
    }

    private static boolean isHomeAddressValid(String homeAddress) {
        if (homeAddress == null) {
            return false;
        }
        Matcher matcher = HOME_ADDRESS_PATTERN.matcher(homeAddress);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Home address is invalid: " + homeAddress);
        }
        return result;
    }

    private static boolean isGenderValid(String gender) {
        if (gender == null || gender.isEmpty()) {
            return false;
        }
        boolean result = Arrays.stream(Gender.values())
                .map(Enum::toString)
                .collect(Collectors.toList())
                .contains(gender.toUpperCase());
        if (!result) {
            logger.log(Level.DEBUG, "Gender is invalid: " + gender);
        }
        return result;
    }

    public static boolean isBirthdayDateValid(String date) {
        if (date == null) {
            return false;
        }
        Matcher matcher = DATE_FORMAT_PATTERN.matcher(date);
        boolean result = matcher.matches() && LocalDate.parse(date).isBefore(LocalDate.now());
        if (!result) {
            logger.log(Level.DEBUG, "Date isn't valid: " + date);
        }
        return result;
    }

    public static boolean isHomeTownValid(String homeTown) {
        if (homeTown == null) {
            return false;
        }
        Matcher matcher = HOME_TOWN_PATTERN.matcher(homeTown);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Home town is invalid: " + homeTown);
        }
        return result;
    }
}