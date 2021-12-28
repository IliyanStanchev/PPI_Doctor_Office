package view;

import entities.ReservedHour;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservedHourView {

    private int id;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    private String formattedDate;

    private String formattedTime;

    private String doctorName;

    private String patientName;

    private String specialization;

    private String visitReason;

    private String doctorIdentifier;

    private String patientIdentifier;

    public ReservedHourView() {
    }

    public ReservedHourView(ReservedHour reservedHour) {

        this.id                 = reservedHour.getId();
        this.date               = reservedHour.getExaminationHour().getDate();
        this.startTime          = reservedHour.getExaminationHour().getStartTime();
        this.endTime            = reservedHour.getExaminationHour().getEndTime();
        this.doctorName         = reservedHour.getExaminationHour().getDoctor().getUser().getFullName();
        this.patientName        = reservedHour.getPatient().getFullName();
        this.formattedDate      = date.toString() + " " + startTime.toString() + " - " + endTime.toString();
        this.formattedTime      = startTime + " - " + endTime;
        this.specialization     = reservedHour.getExaminationHour().getDoctor().getSpecialization().toString();
        this.visitReason        = reservedHour.getVisitReason().toString();
        this.doctorIdentifier   = reservedHour.getExaminationHour().getDoctor().getUser().getIdentifier();
        this.patientIdentifier  = reservedHour.getPatient().getIdentifier();
    }

    public String getFormattedTime() {
        return formattedTime;
    }

    public void setFormattedTime(String formattedTime) {
        this.formattedTime = formattedTime;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(String visitReason) {
        this.visitReason = visitReason;
    }

    public String getPatientIdentifier() {
        return patientIdentifier;
    }

    public void setPatientIdentifier(String patientIdentifier) {
        this.patientIdentifier = patientIdentifier;
    }

    public String getDoctorIdentifier() {
        return doctorIdentifier;
    }

    public void setDoctorIdentifier(String doctorIdentifier) {
        this.doctorIdentifier = doctorIdentifier;
    }
}
