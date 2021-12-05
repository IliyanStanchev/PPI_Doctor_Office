package controllers;

import entities.Doctor;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.DoctorService;
import utils.CloseForm;
import utils.OpenForm;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PatientSelectAppointmentController {

    private User currentUser;

    @FXML
    private TextField nameField;

    @FXML
    private TextField specializationField;

    @FXML
    private TextField cityField;

    @FXML
    private TextField addressField;

    @FXML
    private ImageView imageView;

    @FXML
    private TextArea descriptionField;

    private Doctor currentDoctor;

    public void loadCurrentDoctor( int doctorId ) {

        DoctorService doctorService = new DoctorService();
        currentDoctor = doctorService.getDoctorByID( doctorId );

        nameField.setText( currentDoctor.getUser().getFullName() );
        nameField.setDisable( true );

        specializationField.setText( currentDoctor.getSpecialization().getName() );
        specializationField.setDisable( true );

        cityField.setText( currentDoctor.getCity() );
        cityField.setDisable( true );

        addressField.setText( currentDoctor.getAddress() );
        addressField.setDisable( true );

        descriptionField.setText( currentDoctor.getDescription() );
        descriptionField.setDisable( true );

        Image image = null;

        try {
            image = new Image(new FileInputStream( currentDoctor.getPhotoPath() ));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageView.setImage( image );
    }

    public void onMakeAnAppointment(ActionEvent actionEvent) {

        FXMLLoader loader = OpenForm.openNewForm( "/PatientReserveHour.fxml", "Make an appointment" );
        PatientReserveHourController controller = loader.getController();
        controller.setCurrentDoctor( currentDoctor );
        controller.setCurrentUser( currentUser );

    }

    public void onGoBack(MouseEvent mouseEvent) {

        CloseForm.closeForm( mouseEvent );
    }

    public void setCurrentUser(User user) {

        currentUser = user;
    }
}
