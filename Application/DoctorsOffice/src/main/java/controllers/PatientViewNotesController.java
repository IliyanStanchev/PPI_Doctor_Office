package controllers;

import entities.Examination;
import entities.ReservedHour;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ExaminationService;
import services.ReservedHourService;
import utils.DocumentVisualizer;
import view.ReservedHourView;

import java.io.File;

public class PatientViewNotesController {

    private final ExaminationService examinationService = new ExaminationService();

    public TextField    visitReasonField;
    public TextArea     examinationNotesField;
    public TextField    doctorNameField;
    public TextField    dateField;
    public Label resultLabel;

    private Examination       examination;

    public void onViewAttachedDocument( ActionEvent actionEvent ) throws Exception {

        if( examination == null )
            return;

        File notesFile = new File( examination.getDocumentPath() );

        DocumentVisualizer.ShowDocument( notesFile, resultLabel );
    }

    public void setCurrentReservedHour( int reservedHourID ) {

        ReservedHourService reservedHourService = new ReservedHourService();

        ReservedHour reservedHour = reservedHourService.getReservedHourByID( reservedHourID );

        dateField.setText(reservedHour.getExaminationHour().getDate().toString());
        doctorNameField.setText(reservedHour.getExaminationHour().getDoctor().getUser().getFullName());
        visitReasonField.setText(reservedHour.getVisitReason().toString());

        dateField.setDisable(true);
        doctorNameField.setDisable(true);
        visitReasonField.setDisable(true);
        examinationNotesField.setDisable(true);

        examination = examinationService.getExaminationByReservedHourID( reservedHour.getId() );

        if (examination == null) {
            resultLabel.setText("No doctor notes added yet.");
            return;
        }

        examinationNotesField.setText(examination.getDescription());
    }
}
