package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import utils.OpenForm;

public class PatientMainPageController extends MainPageController {

    @FXML private AnchorPane workPane;


    public void onMakeAppointment(ActionEvent actionEvent) {

        FXMLLoader loader = OpenForm.buildInForm("/PatientMakeAnAppointment.fxml", workPane);
        PatientMakeAnAppointmentController controller = loader.getController();
        controller.setCurrentUser( super.getCurrentUser() );
    }

    public void onViewSavedHours(ActionEvent actionEvent) {

    }

    public void onViewProfile(ActionEvent actionEvent) {

    }

    public void onLogout(MouseEvent mouseEvent) {

    }
}