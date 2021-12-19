package dao.implementation;

import dao.BaseDAO;
import entities.ReservedHour;
import manager.MyEntityManager;
import view.PatientView;

import java.time.LocalDate;
import java.util.List;

public class ReservedHourDAO extends BaseDAO< ReservedHour > {

    public ReservedHourDAO(){

        super.setClass( ReservedHour.class );
    }

    public List<ReservedHour> getPatientReservedHours(int patientID){

        return MyEntityManager.getEntityManager().createQuery("FROM RESERVED_HOURS reservedHours WHERE reservedHours.patient.id =: patientID ")
                .setParameter("patientID", patientID )
                .getResultList();
    }

    public List<ReservedHour> getDoctorReservedHours( int doctorID ) {

        return MyEntityManager.getEntityManager().createQuery("FROM RESERVED_HOURS reservedHours WHERE reservedHours.examinationHour.doctor.id =: doctorID and reservedHours.examinationHour.date =: date ")
                .setParameter("doctorID", doctorID )
                .setParameter("date", LocalDate.now() )
                .getResultList();
    }

    public List< Object[] > getDoctorPatients( int doctorID ) {

        return MyEntityManager.getEntityManager().createQuery(
                "SELECT reservedHours.patient.id, reservedHours.patient.firstName, reservedHours.patient.secondName, reservedHours.patient.identifier, count( reservedHours.patient.id ) FROM RESERVED_HOURS reservedHours WHERE reservedHours.examinationHour.doctor.id =: doctorID group by reservedHours.patient.id ")
                .setParameter("doctorID", doctorID )
                .getResultList();
    }
}
