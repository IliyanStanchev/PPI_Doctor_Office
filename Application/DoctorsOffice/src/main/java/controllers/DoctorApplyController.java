package controllers;
import entities.Specialization;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.SpecializationService;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class DoctorApplyController implements Initializable  {

    private ObservableList< Specialization > specializationList = FXCollections.observableArrayList();

    @FXML
    private ComboBox< Specialization > specializationCombo;

    @FXML
    private TextArea description;

    @FXML
    private Label resultLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpecializationService specializationService = new SpecializationService();

        for( Specialization specialization : specializationService.getAllSpecializations() )
        {
            specializationList.add( specialization );
        }

        specializationCombo.setItems( specializationList );
    }

    @FXML
    private void uploadFile( ActionEvent event ){

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Open File");
        File file = chooser.showOpenDialog( new Stage() );
    }
}
