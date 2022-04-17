package com.epam.medicalsystem.model.entity;

import java.time.LocalDateTime;
import java.util.Set;

public class Visit {
    private long id;
    private LocalDateTime visitDate;
    private Patient patient;
    private User doctor;
    private String anamnesis;
    private String complaints;
    private String diagnosis;
    private String medicines;
    private LocalDateTime nextVisitDate;
    private Set<ProcedureSchedule> procedureSchedulesSet;

    public Visit(long id, LocalDateTime visitDate, Patient patient, User doctor, String anamnesis,
                 String complaints, String diagnosis, String medicines, LocalDateTime nextVisitDate, Set<ProcedureSchedule> procedureScheduleSet) {
        this(visitDate, patient, doctor, anamnesis, complaints, diagnosis, medicines, nextVisitDate, procedureScheduleSet);
        this.id = id;
    }

    public Visit(LocalDateTime visitDate, Patient patient, User doctor, String anamnesis, String complaints,
                 String diagnosis, String medicines, LocalDateTime nextVisitDate, Set<ProcedureSchedule> procedureScheduleSet) {
        this(visitDate, patient, doctor, anamnesis, complaints, diagnosis, medicines, procedureScheduleSet);
        this.nextVisitDate = nextVisitDate;
    }

    public Visit(LocalDateTime visitDate, Patient patient, User doctor, String anamnesis, String complaints,
                 String diagnosis, String medicines, Set<ProcedureSchedule> procedureScheduleSet) {
        this.visitDate = visitDate;
        this.patient = patient;
        this.doctor = doctor;
        this.anamnesis = anamnesis;
        this.complaints = complaints;
        this.diagnosis = diagnosis;
        this.medicines = medicines;
        this.procedureSchedulesSet = procedureScheduleSet;
    }

    public Visit(String anamnesis, String complaints, String diagnosis, String medicines, LocalDateTime nextVisitDate) {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDateTime visitDate) {
        this.visitDate = visitDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public void setAnamnesis(String anamnesis) {
        this.anamnesis = anamnesis;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public LocalDateTime getNextVisitDate() {
        return nextVisitDate;
    }

    public void setNextVisitDate(LocalDateTime nextVisitDate) {
        this.nextVisitDate = nextVisitDate;
    }

    public Set<ProcedureSchedule> getProcedureSchedulesSet() {
        return procedureSchedulesSet;
    }

    public void setProcedureSchedulesSet(Set<ProcedureSchedule> procedureSchedulesSet) {
        this.procedureSchedulesSet = procedureSchedulesSet;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "id=" + id +
                ", visitDate=" + visitDate +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", anamnesis='" + anamnesis + '\'' +
                ", complaints='" + complaints + '\'' +
                ", diagnosis='" + diagnosis + '\'' +
                ", medicines='" + medicines + '\'' +
                ", nextVisitDate=" + nextVisitDate +
                ", procedureSchedulesSet=" + procedureSchedulesSet +
                '}';
    }
}