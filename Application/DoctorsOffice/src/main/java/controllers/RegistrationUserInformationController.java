package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import services.UserService;
import utils.CloseForm;
import utils.OpenForm;
import validators.FieldValidator;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationUserInformationController implements Initializable {

    private static final int PASSWORD_FIELD_LENGTH = 8;

    private User currentUser;

    @FXML
    private Label emailLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    @FXML
    private Label confirmPasswordLabel;

    @FXML
    private TextField emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField confirmPasswordField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        currentUser = new User();
    }

    @FXML
    public void onEmailReleased(KeyEvent keyEvent) {

        if (FieldValidator.validateEmail(emailField, emailLabel))
            emailLabel.setText("");
    }

    @FXML
    public void onUsernameReleased(KeyEvent keyEvent) {

        if (FieldValidator.validateUsername(usernameField, usernameLabel))
            usernameLabel.setText("");
    }

    @FXML
    public void onPasswordReleased(KeyEvent keyEvent) {

        if (FieldValidator.validateFieldLength(passwordField, passwordLabel, 8))
            passwordLabel.setText("");
    }

    @FXML
    public void onConfirmPasswordReleased(KeyEvent keyEvent) {

        if (FieldValidator.comparePassword(passwordField, confirmPasswordField, confirmPasswordLabel))
            confirmPasswordLabel.setText("");
    }

    @FXML
    private void onNext(ActionEvent actionEvent) {

        if (!validateData())
            return;

        fillCurrentUser();

        FXMLLoader fxmlLoader = OpenForm.openNewForm("/RegisterPersonalInformation.fxml", "Personal Information", true);
        RegistrationPersonalInformationController controller = fxmlLoader.getController();
        controller.setCurrentUser(currentUser);

        CloseForm.closeForm(actionEvent);
    }

    private void fillCurrentUser() {

        final String email = emailField.getText();
        final String username = usernameField.getText();
        final String password = passwordField.getText();

        currentUser.setEmail(email);
        currentUser.setUsername(username);
        currentUser.setPassword(password);
    }

    private boolean validateData() {

        if (!FieldValidator.validateEmail(emailField, emailLabel))
            return false;

        if (!FieldValidator.validateUsername(usernameField, usernameLabel))
            return false;

        if (!FieldValidator.validateFieldLength(passwordField, passwordLabel, PASSWORD_FIELD_LENGTH))
            return false;

        if (!FieldValidator.comparePassword(passwordField, confirmPasswordField, confirmPasswordLabel))
            return false;

        UserService userService = new UserService();

        if( userService.getUserByEmail( emailField.getText( )) != null ){
            emailLabel.setText("User with this email already exist.");
            return false;
        }

        return true;
    }

    public void setCurrentUser(User user) {

        this.currentUser = user;

        emailField.setText(user.getEmail());
        usernameField.setText(user.getUsername());

    }

    public void onGoBack(MouseEvent mouseEvent) {

        CloseForm.closeForm(mouseEvent);

        OpenForm.openNewForm("/Login.fxml", "Login page");
    }
}
