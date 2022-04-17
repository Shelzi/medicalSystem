package com.epam.medicalsystem.model.factory.impl;

import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.model.entity.User;
import com.epam.medicalsystem.model.entity.UserRole;
import com.epam.medicalsystem.model.entity.Visit;
import com.epam.medicalsystem.model.factory.EntityFactory;
import com.epam.medicalsystem.validation.UserValidator;
import com.epam.medicalsystem.validation.VisitValidator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VisitFactory implements EntityFactory<Visit> {
    private static final Lock locker = new ReentrantLock();
    private static EntityFactory<Visit> instance;

    private VisitFactory() {
    }

    public static EntityFactory<Visit> getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new VisitFactory();
            }
            locker.unlock();
        }
        return instance;
    }

    @Override
    public Optional<Visit> create(Map<String, String> fields) {
        Optional<Visit> result = Optional.empty();
        if (VisitValidator.isVisitFormValid(fields)) {
            String anamnesis = fields.get(RequestParameter.ANAMNESIS);
            String complaints = fields.get(RequestParameter.COMPLAINTS);
            String diagnosis = fields.get(RequestParameter.DIAGNOSIS);
            String medicines = fields.get(RequestParameter.MEDICINES);
            String nextVisitDayStr = fields.get(RequestParameter.NEXT_DATE_VISIT);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            LocalDateTime nextVisitDay = LocalDateTime.parse(nextVisitDayStr, formatter);
            result = Optional.of(new Visit(anamnesis, complaints, diagnosis, medicines, nextVisitDay));
        }
        return result;
    }
}
