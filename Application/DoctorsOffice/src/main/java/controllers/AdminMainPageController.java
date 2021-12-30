package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.CloseForm;
import utils.OpenForm;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMainPageController extends MainPageController implements Initializable {

    public AnchorPane workPane;

    public void onViewDoctorAppointments(ActionEvent actionEvent) {

        OpenForm.buildInForm("/AdminDoctorAppointment.fxml", workPane);
    }

    public void onReservedHours(ActionEvent actionEvent) {

        OpenForm.buildInForm("/AdminViewSavedHours.fxml", workPane);
    }

    public void onUsersOverview(ActionEvent actionEvent) {

        OpenForm.buildInForm("/AdminUsersOverview.fxml", workPane);
    }

    public void onLogout(MouseEvent mouseEvent) {
        OpenForm.openNewForm("/Login.fxml", "Login Page");
        CloseForm.closeForm(mouseEvent);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OpenForm.buildInForm("/AdminUsersOverview.fxml", workPane);
    }
}
