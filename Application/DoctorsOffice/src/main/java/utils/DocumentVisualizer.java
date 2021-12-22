package utils;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.File;

public class DocumentVisualizer {

    public static void ShowDocument(File file, Label attachLabel ) throws Exception {
        if ( file == null ) {
            attachLabel.setText("No document attached.");
            return;
        }

        Application application = new Application() {
            @Override
            public void start(Stage stage) throws Exception {
                HostServices hostServices = getHostServices();
                hostServices.showDocument( file.getAbsolutePath());
            }
        };

        application.start( new Stage() );
    }
}
