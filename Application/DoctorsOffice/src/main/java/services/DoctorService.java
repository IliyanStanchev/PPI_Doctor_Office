package services;

import dao.implementation.DoctorDAO;
import dao.implementation.UserDAO;
import entities.Doctor;
import entities.Specialization;
import entities.User;
import utils.FileManager;
import utils.FileNameGenerator;

import java.io.File;
import java.io.IOException;

public class DoctorService {

    private final DoctorDAO doctorDAO = new DoctorDAO();
    private final UserDAO   userDAO   = new UserDAO();

    public boolean addDoctorApply( User user, File imageFile, File documentaryFile, Specialization specialization, String description, String city, String address) {

        final String documentaryFileName    = FileNameGenerator.generateCurrentTimeStampName( FileManager.doctorDocumentaryFileNameStarter );
        final String pictureFileName        = FileNameGenerator.generateCurrentTimeStampName( FileManager.doctorPicturesFileNameStarter );

        String documentaryPath  = "";
        String picturePath      = "";

        try {
            picturePath      = FileManager.copyFileToPicturesDirectory( imageFile, pictureFileName );
            documentaryPath  = FileManager.copyFileToDocumentaryDirectory( documentaryFile, documentaryFileName );
        } catch (IOException e) {
            return false;
        }

        User currentUser = userDAO.saveOrUpdate( user );

        if( currentUser == null ){

            FileManager.deleteFile( picturePath );
            FileManager.deleteFile( documentaryPath );

            return false;
        }

        Doctor doctor = new Doctor( user, specialization, documentaryPath, picturePath, description, city, address );

        if( doctorDAO.saveOrUpdate( doctor ) == null ) {

            FileManager.deleteFile( picturePath );
            FileManager.deleteFile( documentaryPath );

            return false;
        }

        return true;
    }
}
