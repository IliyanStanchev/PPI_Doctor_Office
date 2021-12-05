package controllers;

import entities.Doctor;
import entities.Specialization;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import services.DoctorService;
import services.SpecializationService;
import utils.OpenForm;
import view.DoctorView;
import java.net.URL;
import java.util.ResourceBundle;

public class PatientMakeAnAppointmentController implements Initializable  {

    private User currentUser;

    @FXML
    private TextField cityField;

    @FXML
    private TextField doctorNameField;

    @FXML
    private ComboBox<Specialization> specializationCombo;

    @FXML
    private TableView<DoctorView> doctorView;

    @FXML
    private TableColumn<DoctorView, String> cityColumn;

    @FXML
    private TableColumn<DoctorView, String> doctorNameColumn;

    @FXML
    private TableColumn<DoctorView, String> specialityColumn;

    private ObservableList<Specialization> specializationList = FXCollections.observableArrayList();

    private ObservableList<DoctorView>  doctorViews     = FXCollections.observableArrayList();
    private FilteredList<DoctorView>    filteredData    = new FilteredList<>( doctorViews, b -> true );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpecializationService specializationService = new SpecializationService();

        for (Specialization specialization : specializationService.getAllSpecializations()) {

            specializationList.add(specialization);
        }

        specializationCombo.setItems(specializationList);

        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("speciality"));

        DoctorService doctorService = new DoctorService();
        for( Doctor doctor: doctorService.getAllDoctors() ) {


            DoctorView doctorView = new DoctorView( doctor.getId(), doctor.getUser().getFullName(), doctor.getCity(), doctor.getSpecialization().getName() );
            doctorViews.add( doctorView );
        }

        SortedList< DoctorView > sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(doctorView.comparatorProperty());

        doctorView.setItems(doctorViews);
        wrapListAndAddFiltering();
        doctorView.setItems(filteredData);

        doctorView.setRowFactory( action -> {
            TableRow<DoctorView> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(event -> {
                if ( event.getClickCount() == 2 && (! tableRow.isEmpty()) ) {
                    DoctorView doctorView = tableRow.getItem();
                    FXMLLoader fxmlLoader = OpenForm.openNewForm( "/PatientSelectAppointment.fxml", "Select an appointment");
                    PatientSelectAppointmentController controller = fxmlLoader.getController();
                    controller.loadCurrentDoctor( doctorView.getId() );
                    controller.setCurrentUser( currentUser );
                }
            });
            return tableRow ;
        });
    }

    private void wrapListAndAddFiltering() {

        cityField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate( doctorView ->
            {

                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseFilter = newValue.toLowerCase();

                if (doctorView.getCity().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                    return true;

                return false;

            });
        });

        doctorNameField.textProperty().addListener((observable, oldValue, newValue) ->
        {
            filteredData.setPredicate( doctorView ->
            {

                if (newValue == null || newValue.isEmpty())
                    return true;

                String lowerCaseFilter = newValue.toLowerCase();

                if (doctorView.getDoctorName().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                    return true;

                return false;

            });
        });

        specializationCombo.setOnAction( actionEvent -> {

            Specialization specialization = specializationCombo.getSelectionModel().getSelectedItem();

            filteredData.setPredicate( doctorView ->
            {

                if ( specialization == null )
                    return true;

                String lowerCaseFilter = specialization.getName().toLowerCase();

                if (doctorView.getSpeciality().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                    return true;

                return false;
            });
        } );
    }

    public void setCurrentUser(User currentUser) {

        this.currentUser = currentUser;
    }
}
