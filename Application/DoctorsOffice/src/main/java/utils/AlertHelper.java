package utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.concurrent.atomic.AtomicBoolean;

public class AlertHelper extends Alert {

    public AlertHelper() {
        super(AlertType.INFORMATION);
    }

    public AlertHelper(AlertType alertType) {
        super(alertType);
    }

    public boolean show(String title, String message) {

        AtomicBoolean isOkPressed = new AtomicBoolean(false);

        setTitle(title);
        setContentText(message);
        showAndWait().ifPresent(consumer -> {

            if (consumer == ButtonType.OK)
                isOkPressed.set(true);
        });

        return isOkPressed.get();
    }
}
