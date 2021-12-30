package controllers;

import controls.ControlsCustomizer;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import services.UserService;
import utils.CloseForm;
import utils.DateHelper;
import utils.OpenForm;
import validators.FieldValidator;

import java.net.URL;
import java.util.ResourceBundle;


public class RegistrationPersonalInformationController implements Initializable {

    private static final int PERSONAL_IDENTIFIER_SIZE = 10;

    private User currentUser;

    @FXML
    private Label firstNameLabel;

    @FXML
    private Label lastNameLabel;

    @FXML
    private Label phoneNumberLabel;

    @FXML
    private Label personalIdentifierLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField personalIdentifierField;

    @FXML
    private DatePicker dateOfBirthField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ControlsCustomizer.customizeDatePicker(dateOfBirthField);
    }

    @FXML
    public void onApplyAsDoctor(ActionEvent actionEvent) {

        if (!validateData())
            return;

        fillCurrentUser();

        FXMLLoader loader = OpenForm.openNewForm("/DoctorApply.fxml", "Apply for Doctor");
        RegistrationDoctorApplyController controller = loader.getController();
        controller.setCurrentUser(currentUser);

        CloseForm.closeForm(actionEvent);
    }

    @FXML
    public void onRegisterAsPatient(ActionEvent actionEvent) {

        if (!validateData())
            return;

        fillCurrentUser();

        UserService userService = new UserService();

        if (!userService.registerUser(currentUser)) {

            resultLabel.setText("Something went wrong while registering patient. Please try again.");
            return;
        }

        FXMLLoader loader = OpenForm.openNewForm("/Login.fxml", "Login page");
        LoginController loginController = loader.getController();
        loginController.sendMessage("Successful registration as patient. You can login now. ");

        CloseForm.closeForm(actionEvent);
    }

    private void fillCurrentUser() {

        currentUser.setFirstName(firstNameField.getText());
        currentUser.setSecondName(lastNameField.getText());
        currentUser.setPhoneNumber(phoneNumberField.getText());
        currentUser.setIdentifier(personalIdentifierField.getText());
        currentUser.setBirthDate(dateOfBirthField.getValue());
    }

    private boolean validateData() {

        if (!FieldValidator.validatePersonName(firstNameField, firstNameLabel))
            return false;

        if (!FieldValidator.validatePersonName(lastNameField, lastNameLabel))
            return false;

        if (!FieldValidator.validateNumericField(phoneNumberField, phoneNumberLabel))
            return false;

        if (!FieldValidator.validateFieldLength(personalIdentifierField, personalIdentifierLabel, PERSONAL_IDENTIFIER_SIZE))
            return false;

        if (!FieldValidator.validateNumericField(personalIdentifierField, personalIdentifierLabel))
            return false;

        UserService userService = new UserService();

        if( userService.getUserByPhoneNumber( phoneNumberField.getText( )) != null ){
            phoneNumberLabel.setText("User with this phone number already exists.");
            return false;
        }

        if( userService.getUserByIdentifier( personalIdentifierField.getText() ) != null ){
            personalIdentifierLabel.setText("User with this personal identifier already exists.");
            return false;
        }

        return true;
    }

    public void setCurrentUser(User currentUser) {

        this.currentUser = currentUser;

        firstNameField.setText(currentUser.getFirstName());
        lastNameField.setText(currentUser.getSecondName());
        phoneNumberField.setText(currentUser.getPhoneNumber());
        personalIdentifierField.setText(currentUser.getIdentifier());
        dateOfBirthField.setValue(currentUser.getBirthDate());
    }

    public void onFirstNameReleased(KeyEvent keyEvent) {

        if (FieldValidator.validatePersonName(firstNameField, firstNameLabel))
            firstNameLabel.setText("");
    }

    public void onLastNameReleased(KeyEvent keyEvent) {

        if (FieldValidator.validatePersonName(lastNameField, lastNameLabel))
            lastNameLabel.setText("");
    }

    public void onPhoneNumberReleased(KeyEvent keyEvent) {

        if (FieldValidator.validateNumericField(phoneNumberField, phoneNumberLabel))
            phoneNumberLabel.setText("");
    }

    public void onPersonalIdentifierReleased(KeyEvent keyEvent) {

        if (!FieldValidator.validateFieldLength(personalIdentifierField, personalIdentifierLabel, PERSONAL_IDENTIFIER_SIZE))
            return;

        if (!FieldValidator.validateNumericField(personalIdentifierField, personalIdentifierLabel))
            return;

        personalIdentifierLabel.setText("");

        final String personalIdentifier = personalIdentifierField.getText();
        if (personalIdentifier.length() < PERSONAL_IDENTIFIER_SIZE) {
            dateOfBirthField.setDisable(false);
            return;
        }

        if (DateHelper.SetDateFromIdentifier(personalIdentifier, personalIdentifierLabel, dateOfBirthField))
            dateOfBirthField.setDisable(true);
    }

    public void onGoBack(MouseEvent mouseEvent) {

        CloseForm.closeForm(mouseEvent);

        FXMLLoader fxmlLoader = OpenForm.openNewForm("/RegisterUserInformation.fxml", "User information", true);
        RegistrationUserInformationController controller = fxmlLoader.getController();
        controller.setCurrentUser(currentUser);
    }
}
