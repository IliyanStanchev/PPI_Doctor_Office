package dao.implementation;

import dao.BaseDAO;
import entities.ExaminationHour;
import manager.MyEntityManager;

import java.util.List;

public class ExaminationHourDAO extends BaseDAO< ExaminationHour > {

    public ExaminationHourDAO(){

        super.setClass( ExaminationHour.class );
    }

    public List< ExaminationHour > getDoctorExaminationHours( final int doctorId ) {

        return MyEntityManager.getEntityManager().createQuery("FROM EXAMINATION_HOURS e WHERE e.doctor.id =: doctorId ")
                .setParameter("doctorId", doctorId )
                .getResultList();
    }

}
