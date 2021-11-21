package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity(name = "Doctors")
public class Doctor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private User user;

    @ManyToOne
    private Specialization specialization;

    @Column( name = "documentary_path")
    private String documentaryPath;

    @Column( name = "photo_path")
    private String photoPath;

    private String description;

    private String city;

    private String address;

    private boolean confirmed;

    public Doctor() {

    }

    public Doctor(User user, Specialization specialization, String documentaryPath, String photoPath, String description, String city, String address ) {

        this.user               = user;
        this.specialization     = specialization;
        this.documentaryPath    = documentaryPath;
        this.photoPath          = photoPath;
        this.description        = description;
        this.city               = city;
        this.address            = address;
        this.confirmed          = false;
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

    public String getDocumentaryPath() {
        return documentaryPath;
    }

    public void setDocumentaryPath(String documentaryPath) {
        this.documentaryPath = documentaryPath;
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

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return id == doctor.id && confirmed == doctor.confirmed && Objects.equals(user, doctor.user) && Objects.equals(specialization, doctor.specialization) && Objects.equals(documentaryPath, doctor.documentaryPath) && Objects.equals(photoPath, doctor.photoPath) && Objects.equals(description, doctor.description) && Objects.equals(city, doctor.city) && Objects.equals(address, doctor.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, specialization, documentaryPath, photoPath, description, city, address, confirmed);
    }
}
