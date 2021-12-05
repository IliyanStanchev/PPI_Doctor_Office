package dao.implementation;

import dao.BaseDAO;
import entities.ExaminationHour;
import manager.MyEntityManager;

import java.time.LocalDate;
import java.util.List;

public class ExaminationHourDAO extends BaseDAO< ExaminationHour > {

    public ExaminationHourDAO(){

        super.setClass( ExaminationHour.class );
    }

    public List< ExaminationHour > getDoctorExaminationHours( final int doctorId, final LocalDate localDate ) {

        return MyEntityManager.getEntityManager().createQuery("FROM EXAMINATION_HOURS e WHERE e.doctor.id =: doctorId and e.date =: localDate ")
                .setParameter("doctorId", doctorId )
                .setParameter( "localDate", localDate )
                .getResultList();
    }
}
