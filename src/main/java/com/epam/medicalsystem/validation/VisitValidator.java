package com.epam.medicalsystem.validation;

import com.epam.medicalsystem.controller.atribute.RequestParameter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitValidator {
    private static final Logger logger = LogManager.getLogger();
    private static final Pattern TEXT_PATTERN = Pattern.compile("[А-Яа-я\\w\\s\\p{Punct}]{3,10000}");

    private VisitValidator() {
    }

    public static boolean isVisitFormValid(Map<String, String> fields) {
        boolean result = true;
        String anamnesis = fields.get(RequestParameter.ANAMNESIS);
        if (!isVisitTextFieldValid(anamnesis)) {
            fields.put(RequestParameter.ANAMNESIS, "");
            result = false;
        }
        String complaints = fields.get(RequestParameter.COMPLAINTS);
        if (!isVisitTextFieldValid((complaints))) {
            fields.put(RequestParameter.COMPLAINTS, "");
            result = false;
        }
        String diagnosis = fields.get(RequestParameter.DIAGNOSIS);
        if (!isVisitTextFieldValid((diagnosis))) {
            fields.put(RequestParameter.DIAGNOSIS, "");
            result = false;
        }
        String medicines = fields.get(RequestParameter.MEDICINES);
        if (!isVisitTextFieldValid((medicines))) {
            fields.put(RequestParameter.MEDICINES, "");
            result = false;
        }
        String date = fields.get(RequestParameter.NEXT_DATE_VISIT);
        if (!PatientValidator.isDateUpcomingAndValid(date)) {
            fields.put(RequestParameter.NEXT_DATE_VISIT, "");
            result = false;
        }
        return result;
    }

    public static boolean isVisitTextFieldValid(String field) {
        if (field == null) {
            return false;
        }
        Matcher matcher = TEXT_PATTERN.matcher(field);
        boolean result = matcher.matches();
        if (!result) {
            logger.log(Level.DEBUG, "Text field is invalid: " + field);
        }
        return result;
    }
}