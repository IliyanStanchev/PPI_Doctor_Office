package controllers;

import entities.Doctor;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class DoctorProfileController {

    public ImageView    imageView;
    public Label        resultLabel;

    public TextField    cityField;
    public TextField    addressField;
    public TextArea     descriptionField;

    Doctor currentDoctor;

    public void setCurrentDoctor( Doctor currentDoctor ) {

        this.currentDoctor = currentDoctor;

        cityField.setText(currentDoctor.getAddress().getCity());
        cityField.setDisable(true);

        addressField.setText(currentDoctor.getAddress().getAddress());
        addressField.setDisable(true);

        descriptionField.setText(currentDoctor.getDescription());
        descriptionField.setDisable(true);

        Image image = null;

        try {
            image = new Image(new FileInputStream(currentDoctor.getPhotoPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageView.setImage(image);
    }

    public void onUploadImage(ActionEvent actionEvent) {
    }

    public void onUpdateProfile(ActionEvent actionEvent) {
    }

    public void onCityReleased(KeyEvent keyEvent) {
    }

    public void onAddressReleased(KeyEvent keyEvent) {
    }

    public void onDescriptionReleased(KeyEvent keyEvent) {
    }
}
