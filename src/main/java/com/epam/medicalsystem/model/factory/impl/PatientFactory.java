package com.epam.medicalsystem.model.factory.impl;

import com.epam.medicalsystem.controller.atribute.RequestParameter;
import com.epam.medicalsystem.model.entity.Gender;
import com.epam.medicalsystem.model.entity.Patient;
import com.epam.medicalsystem.model.factory.EntityFactory;
import com.epam.medicalsystem.validation.PatientValidator;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PatientFactory implements EntityFactory<Patient> {
    private static final Lock locker = new ReentrantLock();
    private static EntityFactory<Patient> instance;

    private PatientFactory() {
    }

    public static EntityFactory<Patient> getInstance() {
        if (instance == null) {
            locker.lock();
            if (instance == null) {
                instance = new PatientFactory();
            }
            locker.unlock();
        }
        return instance;
    }

    public Optional<Patient> create(Map<String, String> fields) {
        Optional<Patient> result = Optional.empty();
        if (PatientValidator.isPatientFormValid(fields)) { // TODO: 20.02.2022 add new validation for patient
            String firstName = fields.get(RequestParameter.FIRST_NAME);
            String lastName = fields.get(RequestParameter.LAST_NAME);
            String middleName = fields.get(RequestParameter.MIDDLE_NAME);
            Gender gender = Gender.valueOf(fields.get(RequestParameter.GENDER).toUpperCase(Locale.ROOT));
            LocalDate birthday = LocalDate.parse(fields.get(RequestParameter.BIRTHDAY));
            String homeTown = fields.get(RequestParameter.HOME_TOWN);
            String homeAddress = fields.get(RequestParameter.HOME_ADDRESS);
            String homeNumber = fields.get(RequestParameter.HOME_NUMBER);
            String apartmentNumber = fields.get(RequestParameter.APARTMENT_NUMBER);
            String phoneNumber = fields.get(RequestParameter.PHONE_NUMBER);
            result = Optional.of(new Patient(firstName, lastName, middleName, gender, birthday, homeTown,
                    homeAddress, homeNumber, apartmentNumber, phoneNumber));
        }
        return result;
    }
}
