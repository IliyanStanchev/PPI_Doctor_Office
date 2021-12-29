package services;

import dao.implementation.UserAccountDAO;
import entities.UserAccount;

import java.util.List;

public class UserAccountService {

    private final UserAccountDAO userAccountDAO = new UserAccountDAO();

    public List< UserAccount > getAllUserAccounts() {

        return userAccountDAO.getAll();
    }

    public UserAccount changeUserState(int userAccountID ) {

        UserAccount userAccount = userAccountDAO.findById( userAccountID );
        userAccount.setBlocked( !userAccount.isBlocked() );

        return userAccountDAO.saveOrUpdate( userAccount );
    }

    public UserAccount getUserAccountByUserID( int userID ) {

        return userAccountDAO.getUserAccountByUserID( userID );
    }
}
