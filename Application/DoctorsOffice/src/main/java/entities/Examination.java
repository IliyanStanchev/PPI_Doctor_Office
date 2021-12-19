package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity( name = "EXAMINATIONS" )
public class Examination implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID" )
    private int id;

    @OneToOne
    private ReservedHour reservedHour;

    @Column( name = "DOCUMENT_PATH" )
    private String documentPath;

    @Column( name = "DESCRIPTION" )
    private String description;

    @Column( name = "SHARED_WITH_PATIENT" )
    private boolean sharedWithPatient;

    public Examination(){

    }

    public Examination(ReservedHour reservedHour, String documentPath, String description) {
        this.reservedHour = reservedHour;
        this.documentPath = documentPath;
        this.description = description;
    }

    public boolean isSharedWithPatient() {
        return sharedWithPatient;
    }

    public void setSharedWithPatient(boolean sharedWithPatient) {
        this.sharedWithPatient = sharedWithPatient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ReservedHour getReservedHour() {
        return reservedHour;
    }

    public void setReservedHour(ReservedHour reservedHour) {
        this.reservedHour = reservedHour;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Examination that = (Examination) o;
        return id == that.id && Objects.equals(reservedHour, that.reservedHour) && Objects.equals(documentPath, that.documentPath) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reservedHour, documentPath, description);
    }
}
