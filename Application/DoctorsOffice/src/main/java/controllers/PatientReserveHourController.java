package controllers;

import controls.ExaminationHourButton;
import entities.Doctor;
import entities.ExaminationHour;
import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.ExaminationHourService;
import utils.CloseForm;
import utils.OpenForm;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PatientReserveHourController implements Initializable  {

    private Doctor  currentDoctor;
    private User    currentUser;

    private HBox    hBox = new HBox();

    private List    examinationHours;

    private ExaminationHour selectedExaminationHour;

    @FXML
    private DatePicker  datePicker;

    @FXML
    private GridPane    hoursGrid;

    @FXML
    private ScrollPane  scrollPane;

    @FXML
    private Label       resultLabel;

    public void onMakeAnAppointment(ActionEvent actionEvent) {



    }

    public void onGoBack(MouseEvent mouseEvent) {

        FXMLLoader fxmlLoader = OpenForm.openNewForm( "/PatientSelectAppointment.fxml", "Select an appointment");
        PatientSelectAppointmentController controller = fxmlLoader.getController();
        controller.loadCurrentDoctor( currentDoctor.getId() );
        controller.setCurrentUser( currentUser );

        CloseForm.closeForm( mouseEvent );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        datePicker.valueProperty().addListener(( ov, oldValue, newValue ) -> {

            initializeExaminationHours( newValue );
        });
    }

    private void addExamination( int hourIndex, int columnIndex, int rowIndex ) {

        ExaminationHour examinationHour = ( ExaminationHour )examinationHours.get( hourIndex );

        ExaminationHourButton button = new ExaminationHourButton( examinationHour );

        setOnClickEvent( button, columnIndex, rowIndex );
    }

    private void setOnClickEvent( ExaminationHourButton button, int columnIndex, int rowIndex ) {

        hBox.getChildren().add( button );

        GridPane.setConstraints( button, columnIndex, rowIndex, 1, 1, HPos.CENTER, VPos.CENTER );
        hoursGrid.getChildren().addAll( button );

        button.setOnMouseClicked( mouseEvent -> {

            if( button.isDisabled() )
                return;

            final boolean isChosenByCustomer = button.isChosenByCustomer();

            if( selectedExaminationHour != null && !isChosenByCustomer ){

                resultLabel.setText( "You have already chosen a hour." );
                return;
            }

            ExaminationHourButton newButton = new ExaminationHourButton( button.getExaminationHour() );
            newButton.setChosenByCustomer( !isChosenByCustomer );

            selectedExaminationHour = isChosenByCustomer? null : newButton.getExaminationHour();

            resultLabel.setText("");

            setOnClickEvent( newButton, columnIndex, rowIndex );

        });
    }

    public void setCurrentDoctor(Doctor currentDoctor) {

        this.currentDoctor  = currentDoctor;

        LocalDate localDate = LocalDate.now();

        datePicker.setValue( LocalDate.now() );

        initializeExaminationHours( localDate );

    }

    public void setCurrentUser(User user) {

        this.currentUser = user;
    }

    private void initializeExaminationHours( LocalDate localDate ){

        hoursGrid.getChildren().clear();
        selectedExaminationHour = null;

        hBox = new HBox();

        hoursGrid.setPadding(new Insets(7, 7, 7, 7));
        hoursGrid.setHgap(10);
        hoursGrid.setVgap(10);

        ExaminationHourService examinationHourService = new ExaminationHourService();

        examinationHours = examinationHourService.getDoctorExaminationHours( currentDoctor.getId(), localDate );

        final int columns   = 3;
        final int rows      = examinationHours.size() / columns;

        int hourIndex = 0;

        for( int i = 0; i < rows; i++ ){
            for( int j = 0; j < columns; j++ ){
                if( hourIndex < examinationHours.size() ){
                    addExamination( hourIndex, j, i );
                    hourIndex ++;
                }
            }
        }

        scrollPane.setContent( hoursGrid );
        scrollPane.setFitToWidth( true );
    }
}
