package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import services.UserService;

public class PatientProfileController {

    private User currentUser;

    public TextField firstNameField;

    public void setCurrentUser( User currentUser ) {

        this.currentUser = currentUser;

        firstNameField.setText( currentUser.getFirstName() );
    }

    public void onUpdate(ActionEvent actionEvent) {

        UserService userService = new UserService();

        userService.saveUser( currentUser );

    }
}
