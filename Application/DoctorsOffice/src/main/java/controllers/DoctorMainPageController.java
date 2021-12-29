package controllers;

import entities.Doctor;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.DoctorService;
import utils.CloseForm;
import utils.OpenForm;

public class DoctorMainPageController extends MainPageController {

    private Doctor currentDoctor;

    @FXML
    private AnchorPane workPane;

    @Override
    public void setCurrentUser(User user) {

        super.setCurrentUser(user);

        DoctorService doctorService = new DoctorService();

        currentDoctor = doctorService.getDoctorByUserID(user.getId());
    }

    public void onViewProfile(ActionEvent actionEvent) {
        FXMLLoader loader = OpenForm.buildInForm("/DoctorProfile.fxml", workPane);
        DoctorProfileController controller = loader.getController();
        controller.setCurrentDoctor(currentDoctor);
    }

    public void onPatientReview(ActionEvent actionEvent) {
        FXMLLoader loader = OpenForm.buildInForm("/DoctorPatientReview.fxml", workPane);
        DoctorPatientReviewController controller = loader.getController();
        controller.setCurrentDoctor(currentDoctor);
    }

    public void onViewTodayReservations(ActionEvent actionEvent) {
        FXMLLoader loader = OpenForm.buildInForm("/DoctorViewTodayReservations.fxml", workPane);
        DoctorViewTodayReservationsController controller = loader.getController();
        controller.setCurrentDoctor(currentDoctor);
    }

    public void onLogout(MouseEvent mouseEvent) {
        OpenForm.openNewForm("/Login.fxml", "Login Page");
        CloseForm.closeForm(mouseEvent);

    }

    public void onPatientProfile(ActionEvent actionEvent) {

        FXMLLoader fxmlLoader = OpenForm.openNewForm("/PatientMainPage.fxml", "Patient main page" );
        PatientMainPageController controller = fxmlLoader.getController();
        controller.setCurrentUser( super.getCurrentUser() );

        CloseForm.closeForm( actionEvent );
    }
}
