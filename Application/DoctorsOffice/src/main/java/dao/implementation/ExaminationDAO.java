package dao.implementation;

import dao.BaseDAO;
import entities.Examination;
import manager.MyEntityManager;

import javax.persistence.NoResultException;

public class ExaminationDAO extends BaseDAO<Examination> {

    public ExaminationDAO() {

        super.setClass(Examination.class);
    }

    public Examination getExaminationByReservedHourID(int reservedHourId) {

        Examination examination;
        try {
            examination = (Examination) MyEntityManager.getEntityManager().createQuery(
                            "FROM EXAMINATIONS examination WHERE examination.reservedHour.id =: reservedHourId ")
                    .setParameter("reservedHourId", reservedHourId)
                    .getSingleResult();

        } catch (NoResultException e) {
            examination = null;
        }
        return examination;
    }
}
