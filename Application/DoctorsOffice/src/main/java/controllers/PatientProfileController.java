package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import org.w3c.dom.Text;
import services.UserService;
import validators.FieldValidator;

public class PatientProfileController {

    private static final int PASSWORD_FIELD_LENGTH = 8;
    private User currentUser;

    private Label resultLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label secondNameLabel;

    @FXML
    private Label phoneNumberLabel;

    public TextField firstNameField;
    public TextField lastNameField;
    public TextField emailField;
    public TextField phoneNumberField;
    public TextField passwordField;
    public TextField confirmPasswordField;

    public void setCurrentUser( User currentUser ) {

        this.currentUser = currentUser;
        firstNameField.setText( currentUser.getFirstName() );
        lastNameField.setText(currentUser.getSecondName());
        emailField.setText(currentUser.getEmail() );
        phoneNumberField.setText(currentUser.getPhoneNumber());
        passwordField.setText(currentUser.getPassword());
        confirmPasswordField.setText(currentUser.getPassword());

    }

    private boolean validateData() {

        if (!FieldValidator.validatePersonName(firstNameField, firstNameLabel))
            return false;

        if (!FieldValidator.validatePersonName(lastNameField, secondNameLabel))
            return false;

        if (!FieldValidator.validateEmail(emailField, emailLabel))
            return false;

        if (!FieldValidator.validateFieldLength(passwordField, passwordLabel, PASSWORD_FIELD_LENGTH))
            return false;

        if (!FieldValidator.comparePassword(passwordField, confirmPasswordField, confirmPasswordLabel))
            return false;

        if (!FieldValidator.validateNumericField(phoneNumberField, phoneNumberLabel))
            return false;


        return true;

    }

    public void onUpdate(ActionEvent actionEvent) {

        if (!validateData())
            return;

        fillCurrentUser();

        UserService userService = new UserService();
        userService.saveUser( currentUser );
        resultLabel.setText("Successfully updated data. ");

    }

    private void fillCurrentUser() {

        final String email = emailField.getText();
        final String password = passwordField.getText();
        final String firstName= firstNameField.getText();
        final String secondName = lastNameField.getText();
        final String phoneNumber = phoneNumberField.getText();

        currentUser.setEmail(email);
        currentUser.setPassword(password);
        currentUser.setFirstName(firstName);
        currentUser.setSecondName(secondName);
        currentUser.setPhoneNumber(phoneNumber);
    }

}
