package controllers;

import entities.ReservedHour;
import entities.Specialization;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.ReservedHourService;
import utils.AlertHelper;
import view.ReservedHourView;

import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminViewSavedHoursController implements Initializable {

    public DatePicker dateField;
    public TextField patientIdentifierField;
    public TextField doctorIdentifierField;

    public TableView< ReservedHourView > hoursView;
    public TableColumn< ReservedHourView, String > dateColumn;
    public TableColumn< ReservedHourView, String > doctorIdentifierColumn;
    public TableColumn< ReservedHourView, String > patientIdentifierColumn;
    public TableColumn< ReservedHourView, String > visitReasonColumn;
    public Label resultLabel;

    private ObservableList<ReservedHourView>    reservedHoursList = FXCollections.observableArrayList();
    private FilteredList<ReservedHourView>      filteredData = new FilteredList<>(reservedHoursList, b -> true);

    public void onCancelHour(ActionEvent actionEvent) {

        ReservedHourView reservedHour = hoursView.getSelectionModel().getSelectedItem();

        if (reservedHour == null)
            return;

        if( reservedHour.getDate().isBefore( LocalDate.now() ) || reservedHour.getDate().isEqual( LocalDate.now() ) ) {

            AlertHelper alertHelper = new AlertHelper( Alert.AlertType.INFORMATION );
            alertHelper.show("Information", "Past reserved hours or reserved hours for today cannot be canceled.");

            return;
        }

        AlertHelper alertHelper = new AlertHelper(Alert.AlertType.CONFIRMATION);
        if (!alertHelper.show("Confirmation", "Are you sure you want to cancel the examination hour?"))
            return;

        ReservedHourService reservedHourService = new ReservedHourService();

        if (!reservedHourService.cancelReservedHour(reservedHour.getId()))
            resultLabel.setText("Error occurred while canceling reserved hour.");

        reservedHoursList.remove(reservedHour);
        hoursView.setItems(filteredData);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        doctorIdentifierColumn.setCellValueFactory(new PropertyValueFactory<>("doctorIdentifier"));
        patientIdentifierColumn.setCellValueFactory(new PropertyValueFactory<>("patientIdentifier"));
        visitReasonColumn.setCellValueFactory(new PropertyValueFactory<>("visitReason"));
        
        InitTableView();
    }

    private void InitTableView() {

        ReservedHourService reservedHourService = new ReservedHourService();

        for (ReservedHour reservedHour : reservedHourService.getAllReservedHours() )
            reservedHoursList.add( new ReservedHourView(reservedHour) );

        hoursView.setItems(reservedHoursList);
        hoursView.setItems(filteredData);

        dateField.valueProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue != null)
                filteredData.setPredicate(reservedHourView -> reservedHourView.getDate().equals(newValue));
        });

        doctorIdentifierField.textProperty().addListener(obs -> {

            String identifier = doctorIdentifierField.getText();
            if (identifier == null || identifier.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate(reservedHourView -> reservedHourView.getDoctorIdentifier().toLowerCase(Locale.ROOT).contains(identifier.toLowerCase(Locale.ROOT)));
        });

        patientIdentifierField.textProperty().addListener(obs -> {

            String identifier = patientIdentifierField.getText();
            if (identifier == null || identifier.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate(reservedHourView -> reservedHourView.getPatientIdentifier().toLowerCase(Locale.ROOT).contains(identifier.toLowerCase(Locale.ROOT)));
        });
    }
}
