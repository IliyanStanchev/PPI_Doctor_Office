package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity(name = "EXAMINATION_HOURS")
public class ExaminationHour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "START_TIME")
    private LocalTime startTime;

    @Column(name = "END_TIME")
    private LocalTime endTime;

    @Column(name = "TAKEN")
    private boolean taken;

    @ManyToOne
    private Doctor doctor;

    public ExaminationHour() {
    }

    public ExaminationHour(LocalDate date, LocalTime startTime, LocalTime endTime, boolean taken, Doctor doctor) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taken = taken;
        this.doctor = doctor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExaminationHour that = (ExaminationHour) o;
        return id == that.id && taken == that.taken && Objects.equals(date, that.date) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(doctor, that.doctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, startTime, endTime, taken, doctor);
    }
}
