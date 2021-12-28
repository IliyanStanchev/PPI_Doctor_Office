package controllers;

import entities.Examination;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import services.ExaminationService;
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

    private ReservedHourView  reservedHourView;
    private Examination       examination;

    public void onViewAttachedDocument( ActionEvent actionEvent ) throws Exception {

        if( examination == null )
            return;

        File notesFile = new File( examination.getDocumentPath() );

        DocumentVisualizer.ShowDocument( notesFile, resultLabel );
    }

    public void setCurrentReservedHour( ReservedHourView reservedHourView ) {

        this.reservedHourView = reservedHourView;

        dateField.setText(reservedHourView.getDate().toString());
        doctorNameField.setText(reservedHourView.getDoctorName());
        visitReasonField.setText(reservedHourView.getVisitReason());

        dateField.setDisable(true);
        doctorNameField.setDisable(true);
        visitReasonField.setDisable(true);

        examination = examinationService.getExaminationByReservedHourID(reservedHourView.getId());

        if (examination == null) {
            resultLabel.setText("No doctor notes added yet.");
            return;
        }

        examinationNotesField.setText(examination.getDescription());
    }
}
