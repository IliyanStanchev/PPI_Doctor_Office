package controllers;

import entities.Doctor;
import entities.Notification;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.DoctorService;
import services.NotificationService;
import utils.CloseForm;
import utils.OpenForm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class PatientMainPageController extends MainPageController {

    private final static String notificationsEmptyIcon  = "src/main/resources/icons/NotificationsEmpty.png";
    private final static String notificationsFullIcon   = "src/main/resources/icons/NotificationsFull.png";

    public Button       doctorProfileButton;
    public Button       yourProfileButton;

    public Label        welcomeUser;

    public ImageView    notificationsImage;

    private Doctor      currentDoctor;

    @FXML
    private AnchorPane workPane;

    @Override
    public void setCurrentUser( User user ){

        super.setCurrentUser( user );

        DoctorService doctorService = new DoctorService();

        currentDoctor = doctorService.getDoctorByUserID( user.getId() );

        if( currentDoctor == null || !currentDoctor.isConfirmed() ) {
            doctorProfileButton.setVisible( false );
            yourProfileButton.setPrefWidth( 312 );
        }

        welcomeUser.setText("Welcome " + user.getFullName() + "!");

        setNotificationIcon();
    }

    private void setNotificationIcon() {

        NotificationService notificationService = new NotificationService();

        List<Notification> notificationList = notificationService.getUserNotifications( super.getCurrentUser().getId() );

        Image image = null;

        final String notificationIcon = notificationList.isEmpty() ? notificationsEmptyIcon : notificationsFullIcon;

        try {
            image = new Image( new FileInputStream( notificationIcon ) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        notificationsImage.setImage( image );
    }

    public void onMakeAppointment(ActionEvent actionEvent) {

        FXMLLoader loader = OpenForm.buildInForm("/PatientMakeAnAppointment.fxml", workPane);
        PatientMakeAnAppointmentController controller = loader.getController();
        controller.setCurrentUser(super.getCurrentUser());
    }

    public void onViewSavedHours(ActionEvent actionEvent) {

        FXMLLoader loader = OpenForm.buildInForm("/PatientViewSavedHours.fxml", workPane);
        PatientViewSavedHoursController controller = loader.getController();
        controller.setCurrentUser(super.getCurrentUser());
    }

    public void onViewProfile(ActionEvent actionEvent) {

        FXMLLoader loader = OpenForm.buildInForm("/PatientProfile.fxml", workPane);
        PatientProfileController controller = loader.getController();
        controller.setCurrentUser( super.getCurrentUser() );
    }

    public void onLogout(MouseEvent mouseEvent) {

        OpenForm.openNewForm("/Login.fxml", "Login Page");
        CloseForm.closeForm(mouseEvent);
    }

    public void onDoctorProfile(ActionEvent actionEvent) {

        if( currentDoctor == null )
            return;

        FXMLLoader fxmlLoader = OpenForm.openNewForm("/DoctorMainPage.fxml", "Doctor main page" );
        DoctorMainPageController controller = fxmlLoader.getController();
        controller.setCurrentUser( super.getCurrentUser() );

        CloseForm.closeForm( actionEvent );
    }

    public void onOpenNotifications(MouseEvent mouseEvent) {

        setNotificationIcon();

        FXMLLoader fxmlLoader = OpenForm.openNewForm("/PatientNotifications.fxml", "Notifications" );
        PatientNotificationsController controller = fxmlLoader.getController();
        controller.setCurrentUser( super.getCurrentUser() );
    }
}
