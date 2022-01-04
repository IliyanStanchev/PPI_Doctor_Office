package dao.implementation;

import dao.BaseDAO;
import entities.Doctor;
import manager.EntityManagerExtender;

import javax.persistence.NoResultException;
import java.util.List;


public class DoctorDAO extends BaseDAO<Doctor> {

    public DoctorDAO() {

        super.setClass(Doctor.class);
    }

    public Doctor getDoctorByUserID(int userId) {

        Doctor doctor;
        try {
            doctor = (Doctor) EntityManagerExtender.getEntityManager().createQuery("FROM DOCTORS doctors WHERE doctors.user.id=: userId ")
                    .setParameter("userId", userId)
                    .getSingleResult();

        } catch (NoResultException e) {
            doctor = null;
        }

        return doctor;
    }

    public List<Doctor> getDoctorAppointments() {

        return EntityManagerExtender.getEntityManager().createQuery("FROM DOCTORS doctor WHERE doctor.confirmed = false order by doctor.registrationDate desc ")
                .getResultList();
    }

    public List<Doctor> getAllConfirmedDoctors() {

        return EntityManagerExtender.getEntityManager().createQuery("FROM DOCTORS doctor WHERE doctor.confirmed = true")
                .getResultList();
    }
}
