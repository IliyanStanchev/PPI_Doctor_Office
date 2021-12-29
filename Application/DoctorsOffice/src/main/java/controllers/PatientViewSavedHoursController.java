package controllers;

import entities.Examination;
import entities.ReservedHour;
import entities.Specialization;
import entities.User;
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
import javafx.scene.text.Text;
import services.ExaminationService;
import services.ReservedHourService;
import services.SpecializationService;
import utils.AlertHelper;
import utils.OpenForm;
import view.PatientView;
import view.ReservedHourView;

import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.ResourceBundle;

public class PatientViewSavedHoursController implements Initializable {

    private ExaminationService  examinationService  = new ExaminationService();
    private ReservedHourService reservedHourService = new ReservedHourService();

    public Text specialityCombo;
    public TextField doctorNameField;
    public DatePicker dateField;
    public Label resultLabel;
    public ComboBox<Specialization> specializationCombo;

    public TableView<ReservedHourView> reservedHoursView;
    public TableColumn<ReservedHourView, String> dateColumn;
    public TableColumn<ReservedHourView, String> doctorNameColumn;
    public TableColumn<ReservedHourView, String> specialityColumn;
    public TableColumn<ReservedHourView, String> viewReasonColumn;
    private User currentUser;

    private ObservableList<Specialization> specializationList = FXCollections.observableArrayList();
    private ObservableList<ReservedHourView> savedHours = FXCollections.observableArrayList();
    private FilteredList<ReservedHourView> filteredData = new FilteredList<>(savedHours, b -> true);

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDate"));
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("specialization"));
        viewReasonColumn.setCellValueFactory(new PropertyValueFactory<>("visitReason"));

        SpecializationService specializationService = new SpecializationService();

        for (Specialization specialization : specializationService.getAllSpecializations()) {

            specializationList.add(specialization);
        }

        specializationCombo.setItems(specializationList);
    }

    public void setCurrentUser(User user) {

        this.currentUser = user;

        initTableView();
    }

    private void initTableView() {

        for (ReservedHour reservedHour : reservedHourService.getPatientReservedHours(currentUser.getId()))
            savedHours.add(new ReservedHourView(reservedHour));

        reservedHoursView.setItems(savedHours);
        reservedHoursView.setItems(filteredData);

        dateField.valueProperty().addListener((ov, oldValue, newValue) -> {

            if (newValue != null)
                filteredData.setPredicate(reservedHourView -> reservedHourView.getDate().equals(newValue));
        });

        doctorNameField.textProperty().addListener(obs -> {

            String doctorName = doctorNameField.getText();
            if (doctorName == null || doctorName.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate(reservedHourView -> reservedHourView.getDoctorName().toLowerCase(Locale.ROOT).contains(doctorName.toLowerCase(Locale.ROOT)));
        });

        specializationCombo.setOnAction(actionEvent -> {

            Specialization specialization = specializationCombo.getSelectionModel().getSelectedItem();

            filteredData.setPredicate(reservedHourView ->
            {
                if (specialization == null)
                    return true;

                String lowerCaseFilter = specialization.getName().toLowerCase();

                if (reservedHourView.getSpecialization().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;

                return false;
            });
        });

        reservedHoursView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {

                    ReservedHourView reservedHourView = reservedHoursView.getSelectionModel().getSelectedItem();

                    if( reservedHourView == null )
                        return;

                    FXMLLoader fxmlLoader = OpenForm.openNewForm("/PatientViewNotes.fxml", "Examination details");
                    PatientViewNotesController controller = fxmlLoader.getController();
                    controller.setCurrentReservedHour( reservedHourView.getId() );
                }
            }
        });
    }

    public void onCancelHour(ActionEvent actionEvent) {

        ReservedHourView reservedHour = reservedHoursView.getSelectionModel().getSelectedItem();

        if (reservedHour == null)
            return;

        if( reservedHour.getDate().isBefore( LocalDate.now() ) || reservedHour.getDate().isEqual( LocalDate.now() ) ) {

            AlertHelper alertHelper = new AlertHelper( Alert.AlertType.INFORMATION );
            alertHelper.show("Information", "Past reserved hours or reserved hours for today cannot be canceled.");

            return;
        }

        AlertHelper alertHelper = new AlertHelper(Alert.AlertType.CONFIRMATION);
        if (!alertHelper.show("Confirmation", "Are you sure you want to cancel your examination hour with Dr. " + reservedHour.getDoctorName() + "?"))
            return;

        ReservedHourService reservedHourService = new ReservedHourService();

        if (!reservedHourService.cancelReservedHour(reservedHour.getId()))
            resultLabel.setText("Error occurred while canceling reserved hour.");

        savedHours.remove(reservedHour);
        reservedHoursView.setItems(savedHours);
    }
}
