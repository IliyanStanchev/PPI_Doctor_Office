package controllers;

import entities.User;

public abstract class MainPageController {

    private User currentUser;

    public User getCurrentUser() {

        return currentUser;
    }

    public void setCurrentUser(User user) {

        currentUser = user;
    }
}
