package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity( name = "RESERVED_HOURS" )
public class ReservedHour implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID" )
    private int id;

    @OneToOne
    private User patient;

    @OneToOne
    private ExaminationHour examinationHour;

    @ManyToOne
    private VisitReason visitReason;

    public ReservedHour(){

    }

    public ReservedHour(User patient, ExaminationHour examinationHour, VisitReason visitReason) {
        this.patient = patient;
        this.examinationHour = examinationHour;
        this.visitReason = visitReason;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public ExaminationHour getExaminationHour() {
        return examinationHour;
    }

    public void setExaminationHour(ExaminationHour examinationHour) {
        this.examinationHour = examinationHour;
    }

    public VisitReason getVisitReason() {
        return visitReason;
    }

    public void setVisitReason(VisitReason visitReason) {
        this.visitReason = visitReason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservedHour that = (ReservedHour) o;
        return id == that.id && Objects.equals(patient, that.patient) && Objects.equals(examinationHour, that.examinationHour) && Objects.equals(visitReason, that.visitReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, patient, examinationHour, visitReason);
    }
}
