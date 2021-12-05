package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity( name = "DOCTORS" )
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "ID" )
    private int id;

    @OneToOne
    private User user;

    @ManyToOne
    private Specialization specialization;

    @Column( name = "DOCUMENT_PATH" )
    private String documentPath;

    @Column( name = "PHOTO_PATH" )
    private String photoPath;

    @Column( name = "DESCRIPTION" )
    private String description;

    @Column( name = "CONFIRMED" )
    private boolean confirmed;

    @OneToOne
    private Address address;

    public Doctor() {

    }

    public Doctor(User user, Specialization specialization, String documentPath, String photoPath, String description, Address address) {
        this.user = user;
        this.specialization = specialization;
        this.documentPath = documentPath;
        this.photoPath = photoPath;
        this.description = description;
        this.address = address;
        this.confirmed = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Specialization specialization) {
        this.specialization = specialization;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && confirmed == doctor.confirmed && Objects.equals(user, doctor.user) && Objects.equals(specialization, doctor.specialization) && Objects.equals(documentPath, doctor.documentPath) && Objects.equals(photoPath, doctor.photoPath) && Objects.equals(description, doctor.description) && Objects.equals(address, doctor.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, specialization, documentPath, photoPath, description, confirmed, address);
    }
}
