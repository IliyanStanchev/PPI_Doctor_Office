package services;

import dao.implementation.ExaminationHourDAO;
import entities.ExaminationHour;

import java.time.LocalDate;
import java.util.List;

public class ExaminationHourService {

    private final ExaminationHourDAO examinationHourDAO = new ExaminationHourDAO();

    public List<ExaminationHour> getDoctorExaminationHours(final int doctorId, LocalDate localDate){

        return examinationHourDAO.getDoctorExaminationHours( doctorId, localDate );
    }
}
