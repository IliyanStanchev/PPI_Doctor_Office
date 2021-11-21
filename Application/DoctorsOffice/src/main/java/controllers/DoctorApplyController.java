package controllers;

import entities.Specialization;
import entities.User;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.DoctorService;
import services.SpecializationService;
import utils.CloseForm;
import utils.FileManager;
import utils.OpenForm;
import validators.DataValidator;
import validators.FieldValidator;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorApplyController implements Initializable {

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
    private ComboBox<Specialization> specializationCombo;

    @FXML
    private TextField city;

    @FXML
    private TextField address;

    @FXML
    private ImageView imageView;

    private ObservableList<Specialization> specializationList = FXCollections.observableArrayList();

    private File imageFile;

    private File documentaryFile;

    private User currentUser;

    @FXML
    private void onUploadImage(ActionEvent event) throws MalformedURLException {

        File tempFile = FileManager.choosePictureFile(uploadLabel);

        if ( tempFile == null )
            return;

        imageFile = tempFile;

        final String filePath = imageFile.toURI().toURL().toString();

        Image image = new Image(filePath);
        imageView.setImage(image);
    }

    @FXML
    private void onAttachRegistrationFile(ActionEvent event) {

        File tempFile = FileManager.chooseDocumentaryFile(attachLabel);

        if( tempFile == null )
            return;

        documentaryFile = tempFile;
    }

    @FXML
    private void onViewRegistrationFile() throws Exception {

        if (documentaryFile == null) {
            attachLabel.setText("No document attached.");
            return;
        }

        Application application = new Application() {
            @Override
            public void start(Stage stage) throws Exception {

                HostServices hostServices = getHostServices();
                hostServices.showDocument(documentaryFile.getAbsolutePath());
            }
        };

        application.start( new Stage() );
    }

    @FXML
    private void onApplyForDoctor( ActionEvent event ) {

        if( !validateFields() )
            return;

        Specialization specialization = specializationCombo.getSelectionModel().getSelectedItem();

        DoctorService doctorService = new DoctorService();

        if (!doctorService.addDoctorApply( currentUser
                , imageFile
                , documentaryFile
                , specialization
                , description.getText()
                , city.getText()
                , address.getText())) {
            addressLabel.setText( "Error processing doctor's apply. Please try again. ");
            return;
        }

        FXMLLoader loader = OpenForm.openNewForm("/Login.fxml", "Login page");
        LoginController loginController = loader.getController();
        loginController.sendMessage( "Successful registration and doctor apply. You can login now. " );

        CloseForm.closeForm(event);
    }

    private boolean validateFields() {

        if( documentaryFile == null ){

            attachLabel.setText( "No registration document attached");
            return false;
        }

        if( imageFile == null ){

            uploadLabel.setText( "Choose image" );
            return false;
        }

        if ( specializationCombo.getSelectionModel().getSelectedItem() == null) {

            specializationLabel.setText("Pick a specialization.");
            return false;
        }

        if( !DataValidator.isFieldEmpty( description, descriptionLabel ) )
            return false;

        if( !DataValidator.isFieldEmpty( city, cityLabel ) )
            return false;

        if( !DataValidator.isFieldEmpty( address, addressLabel ) )
            return false;

        return true;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpecializationService specializationService = new SpecializationService();

        for (Specialization company : specializationService.getAllSpecializations()) {

            specializationList.add(company);
        }

        specializationCombo.setItems(specializationList);
    }

    public void setCurrentUser(User user) {

        this.currentUser = user;
    }
}
