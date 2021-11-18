package services;

import dao.implementation.UserDAO;
import entities.User;
import security.BCryptPasswordEncoderService;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    public User findById(int userId) {

        return userDAO.findById(userId);

    }

    public User authorizeUser( String username, String password ) {

        User user = userDAO.getUserByUsername( username );

        if( user == null )
            return null;

        BCryptPasswordEncoderService bCryptPasswordEncoderService = new BCryptPasswordEncoderService();

        if( !bCryptPasswordEncoderService.matches( password, user.getPassword() ))
            return null;

        return user;
    }

    public boolean registerUser(User user) {

        User registeredUser = userDAO.saveOrUpdate(user);

        return registeredUser != null;
    }
}
