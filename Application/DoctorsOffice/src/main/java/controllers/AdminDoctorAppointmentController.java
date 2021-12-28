package controllers;

import entities.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.DoctorService;
import utils.OpenForm;
import view.DoctorView;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminDoctorAppointmentController implements Initializable {

    public TextField    doctorNameField;
    public TextField    doctorIdentifierField;
    public TextField    cityField;

    public TableView< DoctorView >    doctorTableView;
    public TableColumn< DoctorView, String >  doctorNameColumn;
    public TableColumn< DoctorView, String >  doctorIdentifierColumn;
    public TableColumn< DoctorView, String >  cityColumn;
    public TableColumn< DoctorView, String >  registrationDate;

    private ObservableList< DoctorView >    doctorsList     = FXCollections.observableArrayList();
    private FilteredList< DoctorView >      filteredData    = new FilteredList<>(doctorsList, b -> true);

    public Label resultLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
        doctorNameColumn.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        doctorIdentifierColumn.setCellValueFactory(new PropertyValueFactory<>("identifier"));
        cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        registrationDate.setCellValueFactory(new PropertyValueFactory<>("registrationDate"));
        
        InitTableView();
    }

    private void InitTableView() {
        
        DoctorService doctorService = new DoctorService();

        for ( Doctor doctor : doctorService.getDoctorAppointments())
            doctorsList.add(new DoctorView( doctor ));

        doctorTableView.setItems(doctorsList);
        doctorTableView.setItems(filteredData);

        doctorNameField.textProperty().addListener(obs -> {

            String doctorName = doctorNameField.getText();
            if (doctorName == null || doctorName.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate( doctorView -> doctorView.getDoctorName().toLowerCase(Locale.ROOT).contains(doctorName.toLowerCase(Locale.ROOT)));
        });
        
        doctorIdentifierField.textProperty().addListener(obs -> {

            String identifier = doctorIdentifierField.getText();
            if ( identifier == null || identifier.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate( doctorView -> doctorView.getIdentifier().toLowerCase(Locale.ROOT).contains( identifier.toLowerCase(Locale.ROOT)));
        });
        
        cityField.textProperty().addListener(obs -> {

            String city = cityField.getText();
            if ( city == null || city.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate( doctorView -> doctorView.getCity().toLowerCase(Locale.ROOT).contains( city.toLowerCase(Locale.ROOT)));
        });

        doctorTableView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if (event.getClickCount() == 2) {

                    DoctorView doctorView = doctorTableView.getSelectionModel().getSelectedItem();
                    FXMLLoader fxmlLoader = OpenForm.openNewForm("/AdminDoctorApprove.fxml", "Approve doctor");
                    AdminDoctorApproveController controller = fxmlLoader.getController();
                    controller.setCurrentDoctor( doctorView.getId() );
                }
            }
        });
    }
}
