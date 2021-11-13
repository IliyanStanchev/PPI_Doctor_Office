package dao.implementation;

import dao.BaseDAO;
import dao.IUserDAO;
import entities.User;
import manager.MyEntityManager;

import javax.persistence.NoResultException;

public class UserDAO extends BaseDAO<User> implements IUserDAO {

    public UserDAO() {
        setClass(User.class);
    }

    @Override
    public User authenticateUser(String username, String password) {

        User user;
        try {
            user = (User) MyEntityManager.getEntityManager().createQuery("FROM User u WHERE  u.username=: username AND u.password=: password")
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }
}
