package controllers;

import entities.Doctor;
import entities.ReservedHour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.NotificationService;
import services.ReservedHourService;
import utils.AlertHelper;
import view.ReservedHourView;

import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class DoctorViewSavedHoursController implements Initializable {

    private Doctor currentDoctor;

    public TextField patientNameField;
    public DatePicker dateField;

    public TableView<ReservedHourView> hoursView;
    public TableColumn< ReservedHourView, String > dateColumn;
    public TableColumn< ReservedHourView, String > patientNameColumn;
    public TableColumn< ReservedHourView, String > visitReasonColumn;
    public Label resultLabel;

    private ObservableList<ReservedHourView> reservedHoursList = FXCollections.observableArrayList();
    private FilteredList<ReservedHourView> filteredData = new FilteredList<>(reservedHoursList, b -> true);

    public void onCancelHour(ActionEvent actionEvent) {

        ReservedHourView reservedHourView = hoursView.getSelectionModel().getSelectedItem();

        if (reservedHourView == null)
            return;

        if( reservedHourView.getDate().isBefore( LocalDate.now() ) || reservedHourView.getDate().isEqual( LocalDate.now() ) ) {

            AlertHelper alertHelper = new AlertHelper( Alert.AlertType.INFORMATION );
            alertHelper.show("Information", "Past reserved hours or reserved hours for today cannot be canceled.");

            return;
        }

        AlertHelper alertHelper = new AlertHelper(Alert.AlertType.CONFIRMATION);
        if (!alertHelper.show("Confirmation", "Are you sure you want to cancel the examination hour?"))
            return;

        NotificationService notificationService = new NotificationService();

        notificationService.addCanceledReservedHourNotification( reservedHourView );

        ReservedHourService reservedHourService = new ReservedHourService();

        if (!reservedHourService.cancelReservedHour(reservedHourView.getId()))
            resultLabel.setText("Error occurred while canceling reserved hour.");

        reservedHoursList.remove(reservedHourView);
        hoursView.setItems(filteredData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        visitReasonColumn.setCellValueFactory(new PropertyValueFactory<>("visitReason"));

    }

    private void InitTableView() {

        ReservedHourService reservedHourService = new ReservedHourService();

        for (ReservedHour reservedHour : reservedHourService.getDoctorReservedHours( currentDoctor.getId() ) )
            reservedHoursList.add( new ReservedHourView(reservedHour) );

        hoursView.setItems(reservedHoursList);
        hoursView.setItems(filteredData);

        dateField.valueProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue != null)
                filteredData.setPredicate(reservedHourView -> reservedHourView.getDate().equals(newValue));
        });

        patientNameColumn.textProperty().addListener(obs -> {

            String patientName = patientNameField.getText();
            if (patientName == null || patientName.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate(reservedHourView -> reservedHourView.getPatientName().toLowerCase(Locale.ROOT).contains(patientName.toLowerCase(Locale.ROOT)));
        });
    }

    public void setCurrentDoctor(Doctor currentDoctor) {

        this.currentDoctor = currentDoctor;

        InitTableView();
    }
}
