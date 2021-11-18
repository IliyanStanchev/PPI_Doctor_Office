package utils;

import javax.swing.*;
import java.io.File;

public class FileChooser {

    public static boolean chooseFile(){

        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory( new File(System.getProperty("user.home") ));


        //int result = fileChooser.showOpenDialog( parent );

        return true;
    }
}
