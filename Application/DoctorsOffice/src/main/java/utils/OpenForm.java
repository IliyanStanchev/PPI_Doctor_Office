package utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OpenForm {

    public static FXMLLoader openNewForm(String fxmlFileName, String formTitle) {
        FXMLLoader fxmlLoad = null;
        try {
            fxmlLoad = new FXMLLoader(
                    new OpenForm().getClass().getResource(fxmlFileName));

            Parent root = fxmlLoad.load();
            Stage stage = new Stage();
            stage.setTitle(formTitle);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't load new window. ");
        }
        return fxmlLoad;
    }


    public static FXMLLoader openNewFormOnTop(String fxmlFileName, String formTitle) {
        FXMLLoader fxmlLoad = null;
        try {
            fxmlLoad = new FXMLLoader(
                    new OpenForm().getClass().getResource(fxmlFileName));

            Parent root = fxmlLoad.load();
            Stage stage = new Stage();
            stage.setTitle(formTitle);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setAlwaysOnTop(true);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't load new window. ");
        }
        return fxmlLoad;
    }
}
