package dao.implementation;

import dao.BaseDAO;
import entities.Specialization;

public class SpecializationDAO extends BaseDAO<Specialization> {

    public SpecializationDAO() {

        super.setClass(Specialization.class);
    }
}
