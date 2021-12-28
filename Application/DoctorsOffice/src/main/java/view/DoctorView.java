package view;

import entities.Doctor;

public class DoctorView {

    private int         id;
    private String      city;
    private String      doctorName;
    private String      speciality;
    private String      imagePath;
    private String      registrationDate;
    private String      identifier;

    public DoctorView( Doctor doctor ) {

        this.id                 = doctor.getId();
        this.city               = doctor.getAddress().getCity();
        this.doctorName         = doctor.getUser().getFullName();
        this.speciality         = doctor.getSpecialization().getName();
        this.imagePath          = doctor.getPhotoPath();
        this.registrationDate   = doctor.getRegistrationDate().toString();
        this.identifier         = doctor.getUser().getIdentifier();
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getId() {
        return id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Override
    public String toString() {
        return "     Name: " + doctorName + "\n     Speciality: " + speciality + "\n     City: " + city;
    }
}
