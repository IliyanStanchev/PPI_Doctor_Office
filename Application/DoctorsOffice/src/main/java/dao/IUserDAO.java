package dao;

import entities.User;

public interface IUserDAO {

    User authenticateUser(String username, String password);
}
