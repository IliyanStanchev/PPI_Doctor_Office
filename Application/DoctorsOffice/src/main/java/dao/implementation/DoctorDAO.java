package dao.implementation;

import dao.BaseDAO;
import entities.Doctor;
import entities.User;
import manager.MyEntityManager;

import javax.persistence.NoResultException;


public class DoctorDAO extends BaseDAO<Doctor> {

    public DoctorDAO(){

        super.setClass( Doctor.class );
    }

    public Doctor getDoctorByUserID( int userId ) {

        Doctor doctor;
        try {
            doctor = (Doctor) MyEntityManager.getEntityManager().createQuery("FROM DOCTORS doctors WHERE doctors.user.id=: userId ")
                    .setParameter("userId", userId )
                    .getSingleResult();

        } catch ( NoResultException e ) {
            doctor = null;
        }

        return doctor;
    }
}
