package services;

import dao.implementation.ExaminationHourDAO;
import dao.implementation.ReservedHourDAO;
import entities.ExaminationHour;
import entities.ReservedHour;
import view.PatientView;

import java.util.ArrayList;
import java.util.List;

public class ReservedHourService {

    private final ReservedHourDAO reservedHourDAO = new ReservedHourDAO();

    private final ExaminationHourDAO examinationHourDAO = new ExaminationHourDAO();

    public boolean saveReservedHour(ReservedHour reservedHour) {

        ExaminationHour examinationHour = reservedHour.getExaminationHour();

        examinationHour.setTaken(true);

        ExaminationHour updatedExaminationHour = examinationHourDAO.saveOrUpdate(examinationHour);

        if (updatedExaminationHour == null)
            return false;

        reservedHour.setExaminationHour(updatedExaminationHour);

        if (reservedHourDAO.saveOrUpdate(reservedHour) == null)
            return false;

        return true;
    }

    public List<ReservedHour> getPatientReservedHours(final int patientID) {

        return reservedHourDAO.getPatientReservedHours(patientID);
    }

    public boolean cancelReservedHour(int reservedHourID) {

        ReservedHour reservedHour = reservedHourDAO.findById(reservedHourID);

        if (reservedHour == null)
            return false;

        ExaminationHour examinationHour = reservedHour.getExaminationHour();

        examinationHour.setTaken(false);

        if (examinationHourDAO.saveOrUpdate(examinationHour) == null)
            return false;

        reservedHourDAO.deleteById(reservedHourID);

        return true;
    }

    public List<ReservedHour> getDoctorReservedHours(int doctorId) {
        return reservedHourDAO.getDoctorReservedHours(doctorId);
    }

    public ReservedHour getReservedHourByID(int reservedHourID) {

        return reservedHourDAO.findById(reservedHourID);
    }

    public List<PatientView> getDoctorPatients(int doctorID) {

        List<PatientView> patientViewList = new ArrayList<PatientView>();

        List<Object[]> databaseList = reservedHourDAO.getDoctorPatients(doctorID);

        for (Object[] columns : databaseList) {
            int id = (Integer) columns[0];
            String patientFirstName = (String) columns[1];
            String patientLastName = (String) columns[2];
            String identifier = (String) columns[3];
            long totalVisits = (Long) columns[4];

            patientViewList.add(new PatientView(id, patientFirstName + " " + patientLastName, identifier, totalVisits));
        }
        return patientViewList;
    }

    public List< ReservedHour > getAllReservedHours() {

        return reservedHourDAO.getAll();
    }
}
