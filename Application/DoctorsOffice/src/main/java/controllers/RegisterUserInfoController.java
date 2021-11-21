package controllers;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.UserService;
import utils.CloseForm;
import utils.OpenForm;

import java.net.URL;
import java.util.ResourceBundle;


public class RegisterUserInfoController implements Initializable {

    @FXML
    private Label emailField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Label confirmPasswordField;

    @FXML
    private void onNext(){

        OpenForm.openNewForm("/RegisterUserInfo.fxml", "Register", true );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
