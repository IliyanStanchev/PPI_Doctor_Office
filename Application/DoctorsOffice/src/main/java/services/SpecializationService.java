package services;

import dao.implementation.SpecializationDAO;
import entities.Specialization;

import java.util.List;

public class SpecializationService {

    private final SpecializationDAO specializationDAO = new SpecializationDAO();

    public List<Specialization> getAllSpecializations() {

        return specializationDAO.getAll();
    }
}
