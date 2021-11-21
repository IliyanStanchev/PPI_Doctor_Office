package utils;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
}
