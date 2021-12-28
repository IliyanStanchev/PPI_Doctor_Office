package dao.implementation;

import dao.BaseDAO;
import entities.VisitReason;

public class VisitReasonDAO extends BaseDAO<VisitReason> {

    public VisitReasonDAO() {

        super.setClass(VisitReason.class);
    }
}
