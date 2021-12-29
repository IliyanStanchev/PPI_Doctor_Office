package utils;


import controllers.MainPageController;
import entities.Doctor;
import entities.User;
import enums.RoleEnum;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.DoctorService;

import java.io.IOException;

public class OpenForm {

    public static FXMLLoader openNewForm(final String fxmlFileName, final String formTitle, final boolean openOnTop) {
        FXMLLoader fxmlLoader = null;
        try {
            fxmlLoader = new FXMLLoader(
                    new OpenForm().getClass().getResource(fxmlFileName));

            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(formTitle);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setAlwaysOnTop(openOnTop);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't load new window. ");
        }
        return fxmlLoader;
    }

    public static FXMLLoader openNewForm(final String fxmlFileName, final String formTitle) {

        return OpenForm.openNewForm(fxmlFileName, formTitle, false);
    }

    public static FXMLLoader buildInForm(final String fxmlFileName, AnchorPane anchorPane) {

        FXMLLoader fxmlLoader = null;
        try {

            fxmlLoader = new FXMLLoader(
                    new OpenForm().getClass().getResource(fxmlFileName));
            AnchorPane pane = fxmlLoader.load();
            anchorPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cant built-in form.");
        }

        return fxmlLoader;
    }

    public static void openCurrentUserForm(User user) {

        final RoleEnum roleUid = user.getRole().getRoleUid();

        boolean isDoctorConfirmed = false;

        if( roleUid == RoleEnum.Doctor ) {

            DoctorService doctorService = new DoctorService();
            Doctor doctor = doctorService.getDoctorByUserID( user.getId() );

            isDoctorConfirmed = doctor.isConfirmed();
        }

        FXMLLoader fxmlLoader = null;
        MainPageController mainPageController = null;

        switch (roleUid) {

            case Administrator:
                fxmlLoader = openNewForm("/AdminMainPage.fxml", "Administrator main page");
                break;

            case Patient:
                fxmlLoader = openNewForm("/PatientMainPage.fxml", "Patient main page");
                break;

            case Doctor:
                if( isDoctorConfirmed )
                    fxmlLoader = openNewForm("/DoctorMainPage.fxml", "Doctor main page");
                else
                    fxmlLoader = openNewForm("/PatientMainPage.fxml", "Patient main page");
                break;

            default:
                return;

        }

        mainPageController = fxmlLoader.getController();
        mainPageController.setCurrentUser(user);
    }
}
