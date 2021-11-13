package services;

import dao.implementation.UserDAO;
import entities.User;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public User findById(int userId) {

        return userDAO.findById(userId);
    }
}
