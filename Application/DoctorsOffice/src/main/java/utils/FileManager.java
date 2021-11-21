package utils;

import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class FileManager {

    public static final String doctorPicturesFileNameStarter       = "DoctorPicture_";
    public static final String doctorDocumentaryFileNameStarter    = "DoctorDocumentary_";

    private static final String doctorPicturesDirectory             = "src/main/resources/DoctorPicture/";
    private static final String doctorDocumentaryDirectory          = "src/main/resources/DoctorDocumentary/";

    public static File choosePictureFile(Label errorLabel) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif"));

        File file = fileChooser.showOpenDialog(new Stage());

        if (file == null)
            errorLabel.setText("No file selected");

        return file;
    }

    public static File chooseDocumentaryFile(Label errorLabel) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Registration File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.PDF", "*.pdf"));

        File file = fileChooser.showOpenDialog(new Stage());

        if (file == null)
            errorLabel.setText("No file selected");

        return file;
    }

    public static String copyFileToDocumentaryDirectory(File file, final String filename) throws IOException {

        final String fullPath = doctorDocumentaryDirectory + filename;

        Path path = Paths.get(doctorDocumentaryDirectory + filename);
        Files.copy(file.toPath(), path, StandardCopyOption.REPLACE_EXISTING);

        return fullPath;
    }

    public static String copyFileToPicturesDirectory(File file, final String filename) throws IOException {

        final String fullPath = doctorPicturesDirectory + filename + ".jpg";

        Path path = Paths.get(fullPath);
        Files.copy(file.toPath(), path, StandardCopyOption.REPLACE_EXISTING);

        return fullPath;
    }


    public static void deleteFile( String filePath ) {

        File file = new File( filePath );
        file.delete();
    }
}
