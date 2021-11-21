package sample;

import dao.implementation.*;
import entities.*;
import security.BCryptPasswordEncoderService;


public class DatabaseFiller {

    public void fillDatabase() {

        Specialization specialization = new Specialization("Ortoped");
        Specialization specialization1 = new Specialization("Hirurg");

        SpecializationDAO specializationDAO = new SpecializationDAO();
        specializationDAO.saveOrUpdate(specialization);
        specializationDAO.saveOrUpdate(specialization1);

        User user = new User("ench3r@gmail.com", "sach", "123", "Iliyan", "Stanchev", "0897875640");
        BCryptPasswordEncoderService bCryptPasswordEncoderService = new BCryptPasswordEncoderService();

        user.setPassword(bCryptPasswordEncoderService.encode(user.getPassword()));
        UserDAO userDAO = new UserDAO();

        Role role = new Role("Patient");

        RoleDAO roleDAO = new RoleDAO();

        Role newRole = roleDAO.saveOrUpdate(role);
        user.setRole(newRole);

        user = userDAO.saveOrUpdate(user);
    }
}
