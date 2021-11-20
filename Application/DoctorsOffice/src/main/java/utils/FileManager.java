package utils;

import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileManager {

    private static final String doctorPicturesDirectory = "src/main/resources/";
    private static final String doctorDocumentaryDirectory = "src/main/resources/DoctorDocumentary/";

    public static File choosePictureFile(Label errorLabel ) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));

        File file = fileChooser.showOpenDialog( new Stage() );

        if( file == null )
            errorLabel.setText( "No file selected" );

        return file;
    }

    public static File chooseDocumentaryFile(Label errorLabel ) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Registration File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF", "*.pdf"));

        File file = fileChooser.showOpenDialog( new Stage() );

        if( file == null )
            errorLabel.setText( "No file selected" );

        return file;
    }

    public static String copyFileToDocumentaryDirectory( File file, final String filename ) throws IOException {

        final String fullPath = doctorDocumentaryDirectory + filename;

        Path path = Paths.get( doctorDocumentaryDirectory + filename );
        Files.copy( file.toPath(), path, StandardCopyOption.REPLACE_EXISTING  );

        return fullPath;
    }

    public static String copyFilePicturesDirectory( File file, final String filename ) throws IOException {

        final String fullPath = doctorPicturesDirectory + filename + ".jpg";

        Path path = Paths.get( fullPath );
        Path returnedPath = Files.copy( file.toPath(), path, StandardCopyOption.REPLACE_EXISTING );

        while( returnedPath == null){

        }

        return fullPath;
    }
}
