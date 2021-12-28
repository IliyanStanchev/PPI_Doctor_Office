package services;

import dao.implementation.RoleDAO;
import entities.Role;

import java.util.List;

public class RoleService {

    private final RoleDAO roleDAO = new RoleDAO();

    public List<Role> getAllRoles() {

        return roleDAO.getAll();
    }
}
