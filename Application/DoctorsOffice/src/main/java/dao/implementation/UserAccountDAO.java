package dao.implementation;

import dao.BaseDAO;
import dao.IUserDAO;
import entities.User;
import entities.UserAccount;
import manager.MyEntityManager;

import javax.persistence.NoResultException;

public class UserAccountDAO extends BaseDAO<UserAccount> {

    public UserAccountDAO() {

        super.setClass( UserAccount.class );
    }
}
