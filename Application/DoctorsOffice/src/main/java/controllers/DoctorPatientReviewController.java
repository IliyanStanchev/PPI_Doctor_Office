package controllers;

import entities.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ReservedHourService;
import utils.OpenForm;
import view.PatientView;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class DoctorPatientReviewController implements Initializable {

    public TableView<PatientView> patientTableView;

    public TableColumn<PatientView, String> patientNameColumn;
    public TableColumn<PatientView, String> patientIdentifierColumn;
    public TableColumn<PatientView, Integer> totalVisitsColumn;
    public Label resultLabel;
    public TextField patientIdentifierField;
    private ObservableList<PatientView> patientsList = FXCollections.observableArrayList();
    private FilteredList<PatientView> filteredData = new FilteredList<>(patientsList, b -> true);
    @FXML
    private TextField patientNameField;

    private Doctor currentDoctor;

    public void setCurrentDoctor(Doctor currentDoctor) {
        this.currentDoctor = currentDoctor;

        initTableView();
    }

    private void initTableView() {

        ReservedHourService reservedHourService = new ReservedHourService();

        for (PatientView patientView : reservedHourService.getDoctorPatients(currentDoctor.getId()))
            patientsList.add(patientView);

        patientTableView.setItems(patientsList);
        patientTableView.setItems(filteredData);

        patientNameField.textProperty().addListener(obs -> {

            String patientName = patientNameField.getText();
            if (patientName == null || patientName.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate(reservedHourView -> reservedHourView.getPatientName().toLowerCase(Locale.ROOT).contains(patientName.toLowerCase(Locale.ROOT)));
        });

        patientIdentifierField.textProperty().addListener(obs -> {

            String identifier = patientIdentifierField.getText();
            if (identifier == null || identifier.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate(patientView -> patientView.getIdentifier().toLowerCase(Locale.ROOT).contains(identifier.toLowerCase(Locale.ROOT)));
        });

        patientTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {
                    PatientView patientView = patientTableView.getSelectionModel().getSelectedItem();
                    FXMLLoader fxmlLoader = OpenForm.openNewForm("/DoctorViewPatientHistory.fxml", "Patient history");
                    DoctorViewPatientHistoryController controller = fxmlLoader.getController();
                    controller.setCurrentHistoryData(currentDoctor, patientView);
                }
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        patientNameColumn.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        patientIdentifierColumn.setCellValueFactory(new PropertyValueFactory<>("identifier"));
        totalVisitsColumn.setCellValueFactory(new PropertyValueFactory<>("totalVisits"));


    }
}
