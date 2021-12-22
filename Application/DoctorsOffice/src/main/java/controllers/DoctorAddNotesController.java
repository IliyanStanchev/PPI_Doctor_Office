package controllers;

import entities.Examination;
import entities.ReservedHour;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import services.ExaminationService;
import services.ReservedHourService;
import utils.DocumentVisualizer;
import utils.FileManager;
import validators.DataValidator;
import view.ReservedHourView;

import java.io.File;

public class DoctorAddNotesController {

    public TextField    visitReasonField;
    public TextArea     examinationNotesField;
    public TextField    patientNameField;
    public TextField    dateField;

    @FXML
    private Label       attachLabel;

    @FXML
    private Label       resultLabel;

    private final ExaminationService    examinationService  = new ExaminationService();
    private final ReservedHourService   reservedHourService = new ReservedHourService();

    private ReservedHourView    reservedHourView;
    private Examination         examination;

    private File notesFile;

    public void setCurrentReservedHour(ReservedHourView reservedHourView) {
        this.reservedHourView = reservedHourView;

        dateField.setText( reservedHourView.getDate().toString() );
        patientNameField.setText( reservedHourView.getPatientName() );
        visitReasonField.setText( reservedHourView.getVisitReason() );

        dateField.setDisable( true );
        patientNameField.setDisable( true );
        visitReasonField.setDisable( true );

        examination = examinationService.getExaminationByReservedHourID( reservedHourView.getId() );

        if( examination == null )
            return;

        examinationNotesField.setText( examination.getDescription() );

        notesFile = new File( examination.getDocumentPath() );
    }

    public void onAttachFile(ActionEvent actionEvent) {

        File tempFile = FileManager.chooseDocumentaryFile( attachLabel );

        if( tempFile == null )
            return;

        notesFile = tempFile;
    }

    public void onViewAttachedDocument(ActionEvent actionEvent) throws Exception {

        DocumentVisualizer.ShowDocument( notesFile, attachLabel );
    }

    public void onShareWithPatient(ActionEvent actionEvent) {

        if( !validateData() )
            return;

        if( examinationService.saveExamination( reservedHourView.getId(), notesFile, examinationNotesField.getText(), true ) == null ){
            resultLabel.setText( "Error sharing examination. Please try again.");
            return;
        }

        resultLabel.setTextFill( Color.GREEN );
        resultLabel.setText("Examination notes shared successfully.");

    }

    public void onSaveNotes(ActionEvent actionEvent) {

        if( !validateData() )
            return;

        if( examinationService.saveExamination( reservedHourView.getId(), notesFile, examinationNotesField.getText(), false ) == null ){
           resultLabel.setText( "Error processing examination. Please try again.");
           return;
       }

       resultLabel.setTextFill( Color.GREEN );
       resultLabel.setText("Examination notes saved successfully.");
    }

    public boolean validateData(){

        if( notesFile == null ){
            attachLabel.setText( "No file attached." );
            return false;
        }

        if( !DataValidator.isFieldEmpty( examinationNotesField, resultLabel ))
            return false;

        return true;
    }
}
