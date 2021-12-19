package controllers;

import entities.Doctor;
import entities.Specialization;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import services.DoctorService;
import services.SpecializationService;
import utils.OpenForm;
import view.DoctorView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Locale;
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
    private ListView<DoctorView> doctorView;

    @FXML
    private ScrollPane scrollPane;

    private ObservableList<Specialization> specializationList = FXCollections.observableArrayList();

    private ObservableList<DoctorView>  doctorViews     = FXCollections.observableArrayList();
    private FilteredList<DoctorView>    filteredData    = new FilteredList<>( doctorViews, b -> true );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initSpecializationCombo();

        initListView();
    }

    private void initSpecializationCombo(){

        SpecializationService specializationService = new SpecializationService();

        for (Specialization specialization : specializationService.getAllSpecializations()) {

            specializationList.add(specialization);
        }

        specializationCombo.setItems(specializationList);
    }

    private void initListView() {

        DoctorService doctorService = new DoctorService();
        for( Doctor doctor: doctorService.getAllDoctors() ) {

            DoctorView doctorView = new DoctorView( doctor.getId(), doctor.getAddress().getCity(), doctor.getUser().getFullName(), doctor.getSpecialization().getName(), doctor.getPhotoPath() );
            doctorViews.add( doctorView );
        }

        SortedList< DoctorView > sortedData = new SortedList<>(filteredData);

        cityField.textProperty().addListener( obs->{

            String city = cityField.getText();
            if( city == null || city.length() == 0 )
                filteredData.setPredicate( s-> true );
            else
                filteredData.setPredicate( s -> s.getCity().toLowerCase(Locale.ROOT).contains(city.toLowerCase(Locale.ROOT)) );
        });

        doctorNameField.textProperty().addListener( obs->{

            String doctorName = doctorNameField.getText();
            if( doctorName == null || doctorName.length() == 0 )
                filteredData.setPredicate( s-> true );
            else
                filteredData.setPredicate( doctor -> doctor.getDoctorName().toLowerCase(Locale.ROOT).contains(doctorName.toLowerCase(Locale.ROOT)) );
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

        doctorView.setItems(sortedData);

        doctorView.setCellFactory(param -> new ListCell<DoctorView>() {
            private ImageView imageView = new ImageView();

            @Override
            public void updateItem( DoctorView doctorView, boolean empty) {

                super.updateItem(doctorView, empty);

                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {

                    try {
                        imageView.setImage(new Image(new FileInputStream(doctorView.getImagePath()),100, 150, false, false));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    Rectangle rectangle = new Rectangle(0, 0, 100, 120);
                    rectangle.setArcWidth(30.0);
                    rectangle.setArcHeight(30.0);

                    ImagePattern pattern = new ImagePattern( imageView.getImage() );

                    rectangle.setFill(pattern);
                    rectangle.setEffect(new DropShadow(20, Color.BLACK));  // Shadow
                    setText(doctorView.toString());
                    setGraphic(rectangle);
                }
            }
        });

        doctorView.setOnMouseClicked( new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                if ( event.getClickCount() == 2 ) {
                    DoctorView currentDoctor = doctorView.getSelectionModel().getSelectedItem();
                    FXMLLoader fxmlLoader = OpenForm.openNewForm( "/PatientSelectAppointment.fxml", "Select an appointment");
                    PatientSelectAppointmentController controller = fxmlLoader.getController();
                    controller.loadCurrentDoctor( currentDoctor.getId() );
                    controller.setCurrentUser( currentUser );
                }
            }
        });

        scrollPane.setContent( doctorView );
        scrollPane.setFitToWidth( true );
    }

    public void setCurrentUser(User currentUser) {

        this.currentUser = currentUser;
    }
}
