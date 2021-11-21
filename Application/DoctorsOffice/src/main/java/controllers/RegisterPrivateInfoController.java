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


public class RegisterPrivateInfoController implements Initializable {

    @FXML
    private Label nameField;

    @FXML
    private TextField familyField;

    @FXML
    private TextField phoneField;

    @FXML
    private Label pinField;

    @FXML
    private Label dateField;

    @FXML
    private void register(){

        OpenForm.openNewForm("/RegisterPrivateInfo.fxml", "Register", true );
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
