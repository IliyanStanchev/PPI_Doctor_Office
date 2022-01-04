package controllers;

import entities.Doctor;
import entities.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import services.DoctorService;
import utils.FileManager;
import utils.FileNameGenerator;
import validators.DataValidator;
import validators.FieldValidator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public class DoctorProfileController {

    private File        imageFile;
    
    public ImageView    imageView;
    public Label        resultLabel;

    public TextField    cityField;
    public TextField    addressField;
    public TextArea     descriptionField;

    public Label        cityLabel;
    public Label        addressLabel;
    public Label        descriptionLabel;

    Doctor currentDoctor;

    public void setCurrentDoctor( Doctor currentDoctor ) {

        this.currentDoctor = currentDoctor;

        cityField.setText(currentDoctor.getAddress().getCity());
        addressField.setText(currentDoctor.getAddress().getAddress());;
        descriptionField.setText(currentDoctor.getDescription());

        Image image = null;

        try {
            image = new Image(new FileInputStream(currentDoctor.getPhotoPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageView.setImage( image );
    }

    public void onUploadImage(ActionEvent actionEvent) throws MalformedURLException {

        Label uploadLabel = new Label();

        File tempFile = FileManager.choosePictureFile( uploadLabel );

        if (tempFile == null)
            return;

        imageFile = tempFile;

        final String filePath = imageFile.toURI().toURL().toString();

        Image image = new Image(filePath);
        imageView.setImage(image);
    }

    public void onUpdateProfile(ActionEvent actionEvent) {

        if (!validateFields())
            return;

        currentDoctor.getAddress().setCity(this.cityField.getText());
        currentDoctor.getAddress().setAddress(this.addressField.getText());
        currentDoctor.setDescription(this.descriptionField.getText());

        updateDoctorImage();

        DoctorService doctorService = new DoctorService();
        doctorService.updateDoctor( currentDoctor );

        descriptionLabel.setTextFill( Color.GREEN );
        descriptionLabel.setText( "Doctor updated successfully! ");
    }

    private void updateDoctorImage() {

        if( imageFile == null )
            return;

        final String pictureFileName = FileNameGenerator.generateCurrentTimeStampName(FileManager.doctorPicturesFileNameStarter);

        String picturePath = "";

        try {
            picturePath = FileManager.copyFileToPicturesDirectory( imageFile, pictureFileName );
        } catch (IOException e) {
            return;
        }

        FileManager.deleteFile( currentDoctor.getPhotoPath() );
        currentDoctor.setPhotoPath( picturePath );
    }

    private boolean validateFields() {

        clearLabels();

        if (!DataValidator.isFieldEmpty(cityField, cityLabel))
            return false;

        if (!DataValidator.isFieldEmpty(addressField, addressLabel))
            return false;

        if (!DataValidator.isFieldEmpty(descriptionField, descriptionLabel))
            return false;

        return true;
    }

    private void clearLabels() {

        descriptionLabel.setText("");
        cityLabel.setText("");
        addressLabel.setText("");
    }

    public void onCityReleased(KeyEvent keyEvent) {

        if (FieldValidator.validateAlphabetical(cityField, cityLabel))
            cityLabel.setText("");
    }

    public void onAddressReleased(KeyEvent keyEvent) {

        if (DataValidator.isFieldEmpty(addressField, addressLabel))
            addressLabel.setText("");
    }

    public void onDescriptionReleased(KeyEvent keyEvent) {

        if (DataValidator.isFieldEmpty(descriptionField, descriptionLabel))
            descriptionLabel.setText("");
    }
}
