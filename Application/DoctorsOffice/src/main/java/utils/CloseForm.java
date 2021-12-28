package utils;

import javafx.event.Event;
import javafx.scene.Node;
import javafx.stage.Stage;

public class CloseForm {

    public static void closeForm(Event event) {

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
