package view;

public class DoctorView {

    private int id;
    private String city;
    private String doctorName;
    private String speciality;

    public DoctorView( int id, String city, String doctorName, String speciality ) {

        this.id         = id;
        this.city       = city;
        this.doctorName = doctorName;
        this.speciality = speciality;
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
}
