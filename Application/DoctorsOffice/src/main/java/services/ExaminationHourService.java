package services;

import dao.implementation.ExaminationHourDAO;
import entities.ExaminationHour;

import java.util.List;

public class ExaminationHourService {

    private final ExaminationHourDAO examinationHourDAO = new ExaminationHourDAO();

    public List<ExaminationHour> getDoctorExaminationHours( final int doctorId ){

        return examinationHourDAO.getDoctorExaminationHours( doctorId );
    }
}
