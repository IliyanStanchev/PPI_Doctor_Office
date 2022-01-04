package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import listeners.PostFormActionListener;
import org.w3c.dom.Text;
import security.BCryptPasswordEncoderService;
import services.UserService;
import validators.FieldValidator;

public class PatientProfileController {

    private final static int PASSWORD_FIELD_LENGTH = 8;

    private User currentUser;
    private PostFormActionListener listener;

    private boolean isEditMode;

    @FXML
    private Button updateButton;
    
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;


    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label phoneNumberLabel;


    @FXML
    private PasswordField oldPasswordField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label oldPasswordLabel;

    @FXML
    private Label newPasswordLabel;

    @FXML
    private Label confirmPasswordLabel;

    public void setCurrentUser(User currentUser, PostFormActionListener listener ) {

        this.listener   = listener;
        this.currentUser = currentUser;

        this.firstNameField.setText( currentUser.getFirstName() );
        this.lastNameField.setText(currentUser.getSecondName());
        this.emailField.setText(currentUser.getEmail() );
        this.phoneNumberField.setText(currentUser.getPhoneNumber());

        this.isEditMode = true;
        setEditMode();
    }

    private boolean validateData() {

        emptyResultLabels();

        UserService userService = new UserService();

        if (!FieldValidator.validatePersonName( firstNameField, firstNameLabel ))
            return false;

        if (!FieldValidator.validatePersonName( lastNameField, lastNameLabel ))
            return false;

        if (!FieldValidator.validateEmail( emailField, emailLabel ))
            return false;

        if( userService.getUserByEmail( emailField.getText() ).getId() != currentUser.getId() ){

            emailLabel.setText("This email is already taken by another user");
            return false;
        }

        if (!FieldValidator.validateNumericField(phoneNumberField, phoneNumberLabel))
            return false;

        if( userService.getUserByPhoneNumber( phoneNumberField.getText() ).getId() != currentUser.getId() ){
            emailLabel.setText("This phone number is already taken by another user");
            return false;
        }

        if( oldPasswordField.getText().isEmpty() )
            return true;

        if ( !FieldValidator.validateFieldLength( oldPasswordField, oldPasswordLabel, PASSWORD_FIELD_LENGTH ))
            return false;

        if( !FieldValidator.compareOldPassword( oldPasswordField.getText(), currentUser.getPassword(), oldPasswordLabel ))
            return false;

        if ( !FieldValidator.validateFieldLength( newPasswordField, newPasswordLabel, PASSWORD_FIELD_LENGTH ))
            return false;

        if (!FieldValidator.comparePassword( newPasswordField, confirmPasswordField, confirmPasswordLabel))
            return false;

        return true;
    }

    private void emptyResultLabels() {

        firstNameLabel.setText("");
        lastNameLabel.setText("");
        emailLabel.setText("");
        phoneNumberLabel.setText("");
        oldPasswordLabel.setText("");
        newPasswordLabel.setText("");
        confirmPasswordLabel.setText("");
    }

    public void onUpdate(ActionEvent actionEvent) {

        if( !isEditMode )
        {
            setEditMode();
            return;
        }

        if (!validateData())
            return;

        fillCurrentUser();

        UserService userService = new UserService();
        userService.saveUser( currentUser );

        confirmPasswordLabel.setText("Successfully updated data. ");
        confirmPasswordLabel.setTextFill( Color.GREEN );

        setEditMode();

        listener.handlePostFormActionListener();
    }

    private void setEditMode() {

        this.isEditMode = !this.isEditMode;

        this.firstNameField.setDisable( !isEditMode );
        this.lastNameField.setDisable( !isEditMode);
        this.phoneNumberField.setDisable( !isEditMode );
        this.emailField.setDisable( !isEditMode );

        this.oldPasswordField.setVisible( isEditMode );
        this.newPasswordField.setVisible( isEditMode );
        this.confirmPasswordField.setVisible( isEditMode );

        updateButton.setText("Edit profile");

        if( isEditMode )
            updateButton.setText("Update profile");
    }

    private void fillCurrentUser() {

        final String email          = emailField.getText();
        final String firstName      = firstNameField.getText();
        final String secondName     = lastNameField.getText();
        final String phoneNumber    = phoneNumberField.getText();
        final String password       = newPasswordField.getText();

        currentUser.setEmail(email);
        currentUser.setFirstName(firstName);
        currentUser.setSecondName(secondName);
        currentUser.setPhoneNumber(phoneNumber);

        if( password.isEmpty() )
            return;

        BCryptPasswordEncoderService bCryptPasswordEncoderService = new BCryptPasswordEncoderService();
        currentUser.setPassword(bCryptPasswordEncoderService.encode( password ));
    }
}
