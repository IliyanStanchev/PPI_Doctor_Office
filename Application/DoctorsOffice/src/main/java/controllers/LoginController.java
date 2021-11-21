package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import services.UserService;
import utils.CloseForm;
import utils.OpenForm;

import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label resultLabel;

    @FXML
    private void login(ActionEvent event) {

        resultLabel.setText("");

        boolean bUsernameIsEmpty = this.usernameField.getText().isEmpty();
        boolean bPasswordIsEmpty = this.passwordField.getText().isEmpty();

        if (bUsernameIsEmpty && bPasswordIsEmpty) {
            resultLabel.setText("Please fill all fields!");
            usernameField.requestFocus();

            return;
        }

        String username = usernameField.getText();
        String password = passwordField.getText();

        UserService userService = new UserService();

        User user = userService.authorizeUser(username, password);

        if (user == null) {
            resultLabel.setText("Wrong username or password. Try again.");
            usernameField.requestFocus();

            return;
        }

        FXMLLoader loader = OpenForm.openNewForm("/DoctorApply.fxml", "Apply for doctor");
        DoctorApplyController doctorApplyController = loader.getController();
        doctorApplyController.setCurrentUser( user );

        CloseForm.closeForm(event);
    }

    @FXML
    private void register() {

        OpenForm.openNewForm("/Register.fxml", "Register", true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void sendMessage( String registrationResult ) {

        resultLabel.setText( registrationResult );
        resultLabel.setTextFill( Color.BLUE );
    }
}
