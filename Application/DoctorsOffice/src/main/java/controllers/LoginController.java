package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.UserAuthorizationService;
import utils.CloseForm;
import utils.OpenForm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {

    private final UserAuthorizationService userAuthorizationService = new UserAuthorizationService();

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

        User user = userAuthorizationService.authorizeUser(username, password);

        if (user == null) {
            resultLabel.setText("Wrong username or password!");
            usernameField.requestFocus();

            return;
        }

        CloseForm.closeForm(event);
    }

    @FXML
    private void register(){

        OpenForm.openNewFormOnTop("/Register.fxml", "Register");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
