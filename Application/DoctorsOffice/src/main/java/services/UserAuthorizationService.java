package services;

import dao.implementation.UserDAO;
import entities.User;


public class UserAuthorizationService {


    private final UserDAO userDAO = new UserDAO();

    public User authorizeUser(String username, String password) {

        return userDAO.authenticateUser(username, password);
    }

    public boolean registerUser(User user) {

        User registeredUser = userDAO.saveOrUpdate(user);

        return registeredUser != null;
    }

}
