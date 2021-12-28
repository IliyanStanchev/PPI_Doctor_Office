package controllers;

import entities.Doctor;
import entities.ReservedHour;
import entities.VisitReason;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ReservedHourService;
import services.VisitReasonService;
import utils.AlertHelper;
import utils.OpenForm;
import view.ReservedHourView;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class DoctorViewTodayReservationsController implements Initializable {

    public Label resultLabel;

    public TextField patientNameField;

    public TableView<ReservedHourView> reservationsTableView;

    public TableColumn<ReservedHourView, String> visitReasonColumn;
    public TableColumn<ReservedHourView, String> patientNameColumn;
    public TableColumn<ReservedHourView, String> timeColumn;
    public ComboBox<VisitReason> visitReasonCombo;
    private ObservableList<ReservedHourView> reservationsList = FXCollections.observableArrayList();
    private FilteredList<ReservedHourView> filteredData = new FilteredList<>(reservationsList, b -> true);
    private ObservableList<VisitReason> visitReasonList = FXCollections.observableArrayList();

    private Doctor currentDoctor;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("formattedTime"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        visitReasonColumn.setCellValueFactory(new PropertyValueFactory<>("visitReason"));

        VisitReasonService visitReasonService = new VisitReasonService();

        for (VisitReason visitReason : visitReasonService.getAllVisitReasons()) {

            visitReasonList.add(visitReason);
        }

        visitReasonCombo.setItems(visitReasonList);
    }

    private void initTableView() {

        ReservedHourService reservedHourService = new ReservedHourService();

        for (ReservedHour reservedHour : reservedHourService.getDoctorReservedHours(currentDoctor.getId()))
            reservationsList.add(new ReservedHourView(reservedHour));

        reservationsTableView.setItems(reservationsList);
        reservationsTableView.setItems(filteredData);

        patientNameField.textProperty().addListener(obs -> {

            String patientName = patientNameField.getText();
            if (patientName == null || patientName.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate(reservedHourView -> reservedHourView.getPatientName().toLowerCase(Locale.ROOT).contains(patientName.toLowerCase(Locale.ROOT)));
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
                    FXMLLoader fxmlLoader = OpenForm.openNewForm("/DoctorAddNotes.fxml", "Add notes");
                    DoctorAddNotesController controller = fxmlLoader.getController();
                    controller.setCurrentReservedHour(reservedHourView);
                }
            }
        });
    }

    public void onCancelHour(ActionEvent actionEvent) {

        ReservedHourView reservedHourView = reservationsTableView.getSelectionModel().getSelectedItem();

        if (reservedHourView == null)
            return;

        AlertHelper alertHelper = new AlertHelper(Alert.AlertType.CONFIRMATION);
        if (!alertHelper.show("Confirmation", "Are you sure you want to cancel your examination hour with " + reservedHourView.getPatientName() + "?"))
            return;

        ReservedHourService reservedHourService = new ReservedHourService();

        if (!reservedHourService.cancelReservedHour(reservedHourView.getId()))
            resultLabel.setText("Error occurred while canceling reserved hour.");

        reservationsList.remove(reservedHourView);

        reservationsTableView.setItems(reservationsList);
    }

    public void setCurrentDoctor(Doctor currentDoctor) {

        this.currentDoctor = currentDoctor;

        initTableView();
    }

}
