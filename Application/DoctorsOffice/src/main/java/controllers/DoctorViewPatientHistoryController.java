package controllers;

import entities.Doctor;
import entities.ReservedHour;
import entities.VisitReason;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import services.ReservedHourService;
import services.VisitReasonService;
import utils.AlertHelper;
import utils.OpenForm;
import view.PatientView;
import view.ReservedHourView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DoctorViewPatientHistoryController implements Initializable {

    public ComboBox<VisitReason> visitReasonCombo;
    public TableView<ReservedHourView> reservationsTableView;
    public TableColumn dateColumn;
    public TableColumn visitReasonColumn;
    public Text patientIdentifierLabel;
    public Text patientNameLabel;
    private Doctor currentDoctor;
    private PatientView currentPatient;
    @FXML
    private DatePicker datePicker;
    private ObservableList<VisitReason> visitReasonList = FXCollections.observableArrayList();
    private ObservableList<ReservedHourView> reservationsList = FXCollections.observableArrayList();
    private FilteredList<ReservedHourView> filteredData = new FilteredList<>(reservationsList, b -> true);

    public void setCurrentHistoryData(Doctor currentDoctor, PatientView currentPatient) {

        this.currentDoctor = currentDoctor;
        this.currentPatient = currentPatient;

        patientNameLabel.setText(currentPatient.getPatientName());
        patientIdentifierLabel.setText(currentPatient.getIdentifier());

        InitTableView();
    }

    private void InitTableView() {

        ReservedHourService reservedHourService = new ReservedHourService();

        for (ReservedHour reservedHour : reservedHourService.getDoctorReservedHours( currentDoctor.getId(), currentPatient.getId() ))
            reservationsList.add(new ReservedHourView(reservedHour));

        reservationsTableView.setItems(reservationsList);
        reservationsTableView.setItems(filteredData);

        datePicker.valueProperty().addListener((ov, oldValue, newValue) -> {

            filteredData.setPredicate(reservedHourView ->
            {
                if (newValue == null)
                    return true;

                if (newValue.equals(reservedHourView.getDate()))
                    return true;

                return false;
            });
        });

        visitReasonCombo.setOnAction(actionEvent -> {

            VisitReason visitReason = visitReasonCombo.getSelectionModel().getSelectedItem();

            filteredData.setPredicate(reservedHourView ->
            {
                if (visitReason == null)
                    return true;

                String lowerCaseFilter = visitReason.getReason().toLowerCase();

                if (reservedHourView.getVisitReason().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;

                return false;
            });
        });

        reservationsTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    ReservedHourView reservedHourView = reservationsTableView.getSelectionModel().getSelectedItem();

                    if( reservedHourView.getDate().isAfter( LocalDate.now() ) ) {

                        AlertHelper alertHelper = new AlertHelper( Alert.AlertType.INFORMATION );
                        alertHelper.show("Information", "Notes for future reserved hours cannot be added.");

                        return;
                    }

                    FXMLLoader fxmlLoader = OpenForm.openNewForm("/DoctorAddNotes.fxml", "Add notes");
                    DoctorAddNotesController controller = fxmlLoader.getController();
                    controller.setCurrentReservedHour(reservedHourView);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        visitReasonColumn.setCellValueFactory(new PropertyValueFactory<>("visitReason"));

        VisitReasonService visitReasonService = new VisitReasonService();

        for (VisitReason visitReason : visitReasonService.getAllVisitReasons()) {

            visitReasonList.add(visitReason);
        }

        visitReasonCombo.setItems(visitReasonList);
    }
}
