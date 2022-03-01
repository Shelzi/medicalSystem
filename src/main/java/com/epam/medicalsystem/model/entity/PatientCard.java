package com.epam.medicalsystem.model.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PatientCard {
    private Patient patient;
    private Set<Visit> visitsSet;

    public PatientCard(Patient patient, Set<Visit> visitsSet) {
        this.patient = patient;
        this.visitsSet = visitsSet;
    }

    public PatientCard(Patient patient) {
        this.patient = patient;
        this.visitsSet = new HashSet<>();
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