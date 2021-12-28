package controllers;

import entities.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import services.DoctorService;
import utils.CloseForm;
import utils.DocumentVisualizer;
import java.io.File;

public class AdminDoctorApproveController extends PatientSelectAppointmentController {

    public Label attachLabel;
    private File documentaryFile;

    public void setCurrentDoctor( int doctorID ) {

        super.loadCurrentDoctor( doctorID );

        documentaryFile = new File( getCurrentDoctor().getDocumentPath() );
    }

    public void onViewRegistrationFile(ActionEvent actionEvent) throws Exception {

        DocumentVisualizer.ShowDocument( documentaryFile, attachLabel );
    }

    public void onDoctorApprove(ActionEvent actionEvent) {

        DoctorService doctorService = new DoctorService();
        doctorService.approveDoctor( super.getCurrentDoctor() );

        CloseForm.closeForm( actionEvent );
    }

    public void onGoBack(MouseEvent mouseEvent) {

        CloseForm.closeForm(mouseEvent);
    }
}
