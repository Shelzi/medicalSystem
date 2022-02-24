package com.epam.medicalsystem.model.entity;

import java.time.LocalDate;

public class Visit {
    private LocalDate visitDate;
    private User doctor;

    public Visit(LocalDate visitDate, User doctor) {
        this.visitDate = visitDate;
        this.doctor = doctor;
    }

    public Visit(User doctor) {
        this.visitDate = LocalDate.now();
        this.doctor = doctor;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }
}
