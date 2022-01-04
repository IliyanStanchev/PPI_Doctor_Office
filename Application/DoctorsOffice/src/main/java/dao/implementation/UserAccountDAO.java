package dao.implementation;

import dao.BaseDAO;
import entities.UserAccount;
import manager.EntityManagerExtender;

import javax.persistence.NoResultException;

public class UserAccountDAO extends BaseDAO<UserAccount> {

    public UserAccountDAO() {

        super.setClass( UserAccount.class );
    }

    public UserAccount getUserAccountByUserID(int userID) {

        UserAccount userAccount;
        try {
            userAccount = ( UserAccount ) EntityManagerExtender.getEntityManager().createQuery("FROM USER_ACCOUNTS u WHERE  u.user.id =: userID ")
                    .setParameter("userID", userID )
                    .getSingleResult();

        } catch (NoResultException e) {
            userAccount = null;
        }
        return userAccount;
    }
}
