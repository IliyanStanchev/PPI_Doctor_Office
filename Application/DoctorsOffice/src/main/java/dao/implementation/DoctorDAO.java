package dao.implementation;

import dao.BaseDAO;
import entities.Doctor;

public class DoctorDAO extends BaseDAO<Doctor> {

    public DoctorDAO(){

        super.setClass( Doctor.class );
    }
}
