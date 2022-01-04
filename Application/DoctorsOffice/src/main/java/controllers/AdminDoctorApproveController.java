package controllers;

import entities.Doctor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import listeners.PostFormActionListener;
import services.DoctorService;
import utils.CloseForm;
import utils.DocumentVisualizer;
import java.io.File;

public class AdminDoctorApproveController extends PatientSelectAppointmentController {

    public Label attachLabel;
    private File documentaryFile;
    private PostFormActionListener listener;

    public void setCurrentDoctor( int doctorID, PostFormActionListener listener ) {

        super.loadCurrentDoctor( doctorID );

        this.listener = listener;

        documentaryFile = new File( getCurrentDoctor().getDocumentPath() );
    }

    public void onViewRegistrationFile(ActionEvent actionEvent) throws Exception {

        DocumentVisualizer.ShowDocument( documentaryFile, attachLabel );
    }

    public void onDoctorApprove(ActionEvent actionEvent) {

        DoctorService doctorService = new DoctorService();
        doctorService.approveDoctor( super.getCurrentDoctor() );

        listener.handlePostFormActionListener();

        CloseForm.closeForm( actionEvent );
    }

    public void onGoBack(MouseEvent mouseEvent) {

        CloseForm.closeForm(mouseEvent);
    }
}
