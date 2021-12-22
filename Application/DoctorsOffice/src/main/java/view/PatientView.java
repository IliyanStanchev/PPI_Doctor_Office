package view;

public class PatientView {

    private int     id;
    private String  patientName;
    private String  patientLastName;
    private String  identifier;
    private long    totalVisits;

    public PatientView(){

    }
    public PatientView(int id, String patientName, String identifier, long totalVisits) {
        this.id = id;
        this.patientName    = patientName;
        this.identifier     = identifier;
        this.totalVisits    = totalVisits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public long getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(long totalVisits) {
        this.totalVisits = totalVisits;
    }
}
