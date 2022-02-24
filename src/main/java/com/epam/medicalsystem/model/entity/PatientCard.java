package com.epam.medicalsystem.model.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PatientCard {
    private long cardId;
    private Patient patient;
    private Set<Visit> visitsSet;

    public PatientCard(long cardId, Patient patient, Set<Visit> visitsSet) {
        this.cardId = cardId;
        this.patient = patient;
        this.visitsSet = visitsSet;
    }

    public PatientCard(Patient patient) {
        this.patient = patient;
        this.visitsSet = new HashSet<>();
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Visit> getVisitsSet() {
        return visitsSet;
    }

    public void setVisitsSet(Set<Visit> visitsSet) {
        this.visitsSet = visitsSet;
    }
}