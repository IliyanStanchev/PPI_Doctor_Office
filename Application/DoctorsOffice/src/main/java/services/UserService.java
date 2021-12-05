package services;

import dao.implementation.RoleDAO;
import dao.implementation.UserDAO;
import entities.Role;
import entities.User;
import enums.RoleEnum;
import security.BCryptPasswordEncoderService;

public class UserService {

    private final UserDAO userDAO = new UserDAO();
    private final RoleDAO roleDAO = new RoleDAO();

    public User findById(int userId) {

        return userDAO.findById(userId);

    }

    public User authorizeUser(String username, String password) {

        User user = userDAO.getUserByUsername(username);

        if (user == null)
            return null;

        BCryptPasswordEncoderService bCryptPasswordEncoderService = new BCryptPasswordEncoderService();

        if (!bCryptPasswordEncoderService.matches(password, user.getPassword()))
            return null;

        return user;
    }

    public boolean registerUser(User user) {

        Role role = roleDAO.getRoleByUID( RoleEnum.Patient );
        user.setRole( role );

        BCryptPasswordEncoderService bCryptPasswordEncoderService = new BCryptPasswordEncoderService();
        user.setPassword(bCryptPasswordEncoderService.encode(user.getPassword()));

        User registeredUser = userDAO.saveOrUpdate(user);

        if( registeredUser == null)
            return false;

        return true;
    }
}
