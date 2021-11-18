package dao.implementation;

import dao.BaseDAO;
import dao.IUserDAO;
import entities.Specialization;
import entities.User;

public class SpecializationDAO extends BaseDAO<Specialization>  {

    public SpecializationDAO() {
        setClass(Specialization.class);
    }
}
