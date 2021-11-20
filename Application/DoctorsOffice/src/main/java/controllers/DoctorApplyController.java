package controllers;
import entities.Specialization;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.SpecializationService;
import utils.FileManager;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorApplyController implements Initializable  {

    @FXML
    private Label uploadLabel;

    @FXML
    private Label attachLabel;

    @FXML
    private Label specializationLabel;

    @FXML
    private Label descriptionLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private Label addressLabel;

    @FXML
    private TextArea description;

    @FXML
    private ComboBox< Specialization > specializationCombo;

    @FXML
    private TextField city;

    @FXML
    private TextField address;

    @FXML private ImageView imageView;

    private ObservableList< Specialization > specializationList = FXCollections.observableArrayList();

    private File imageFile;

    private File documentaryFile;

    @FXML
    private void onUploadImage(ActionEvent event) throws MalformedURLException {

        imageFile = FileManager.choosePictureFile( uploadLabel );

        if ( imageFile == null )
            return;

        final String filePath = imageFile.toURI().toURL().toString();

        Image image = new Image( filePath );
        imageView.setImage( image );
    }

    @FXML
    private void onAttachRegistrationFile(ActionEvent event) {

        documentaryFile = FileManager.chooseDocumentaryFile( attachLabel );
    }

    @FXML
    private void onViewRegistrationFile() throws Exception {

        Application application = new Application() {
            @Override
            public void start(Stage stage) throws Exception {

                HostServices hostServices = getHostServices();
                hostServices.showDocument( documentaryFile.getAbsolutePath());;
            }
        };

        application.start( new Stage() );
    }

    @FXML
    private void onApplyForDoctor(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpecializationService specializationService = new SpecializationService();

        for ( Specialization company : specializationService.getAllSpecializations() ) {

            specializationList.add( company );
        }

        specializationCombo.setItems(specializationList);
    }
}
