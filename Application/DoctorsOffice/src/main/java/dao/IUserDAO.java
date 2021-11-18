package dao;

import entities.User;

public interface IUserDAO {

    User getUserByUsername(String username );
}
