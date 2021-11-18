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
    public User getUserByUsername( String username ) {

        User user;
        try {
            user = (User) MyEntityManager.getEntityManager().createQuery("FROM Users u WHERE  u.username=: username ")
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }

}
