package dao.implementation;

import dao.BaseDAO;
import entities.User;
import manager.EntityManagerExtender;

import javax.persistence.NoResultException;

public class UserDAO extends BaseDAO< User > {

    public UserDAO() {

        super.setClass(User.class);
    }

    public User getUserByUsername(String username) {

        User user;
        try {
            user = (User) EntityManagerExtender.getEntityManager().createQuery("FROM USERS u WHERE  u.username=: username ")
                    .setParameter("username", username)
                    .getSingleResult();

        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }

    public User getUserByEmail(String email) {

        User user;
        try {
            user = (User) EntityManagerExtender.getEntityManager().createQuery("FROM USERS u WHERE  u.email =: email ")
                    .setParameter("email", email)
                    .getSingleResult();

        } catch (NoResultException e) {
            user = null;
        }

        return user;
    }

    public User getUserByPhoneNumber(String phoneNumber) {

        User user;
        try {
            user = (User) EntityManagerExtender.getEntityManager().createQuery("FROM USERS u WHERE  u.phoneNumber =: phoneNumber ")
                    .setParameter("phoneNumber", phoneNumber)
                    .getSingleResult();

        } catch (NoResultException e) {
            user = null;
        }

        return user;
    }

    public User getUserByIdentifier( String identifier ) {

        User user;
        try {
            user = (User) EntityManagerExtender.getEntityManager().createQuery("FROM USERS u WHERE  u.identifier =: identifier ")
                    .setParameter("identifier", identifier)
                    .getSingleResult();

        } catch (NoResultException e) {
            user = null;
        }

        return user;
    }
}
