package services;

import dao.implementation.VisitReasonDAO;
import entities.VisitReason;

import java.util.List;

public class VisitReasonService {

    private final VisitReasonDAO visitReasonDAO = new VisitReasonDAO();

    public List<VisitReason> getAllVisitReasons() {

        return visitReasonDAO.getAll();
    }
}
