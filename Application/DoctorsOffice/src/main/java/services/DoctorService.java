package services;

import dao.implementation.*;
import entities.*;
import enums.RoleEnum;
import security.BCryptPasswordEncoderService;
import utils.FileManager;
import utils.FileNameGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DoctorService {

    private final DoctorDAO         doctorDAO       = new DoctorDAO();
    private final UserDAO           userDAO         = new UserDAO();
    private final RoleDAO           roleDAO         = new RoleDAO();
    private final AddressDAO        addressDAO      = new AddressDAO();
    private final UserAccountDAO    userAccountDAO  = new UserAccountDAO();

    public boolean addDoctorApply(User user, File imageFile, File documentaryFile, Specialization specialization, String description, Address address) {

        final String documentaryFileName    = FileNameGenerator.generateCurrentTimeStampName(FileManager.doctorDocumentaryFileNameStarter);
        final String pictureFileName        = FileNameGenerator.generateCurrentTimeStampName(FileManager.doctorPicturesFileNameStarter);

        String documentaryPath = "";
        String picturePath = "";

        try {
            picturePath = FileManager.copyFileToPicturesDirectory(imageFile, pictureFileName);
            documentaryPath = FileManager.copyFileToDirectory(documentaryFile, FileManager.doctorDocumentaryDirectory, documentaryFileName);
        } catch (IOException e) {
            return false;
        }

        User currentUser = saveCurrentUser( user );

        if( currentUser == null ) {
            FileManager.deleteFile(picturePath);
            FileManager.deleteFile(documentaryPath);

            return false;
        }

        if( userAccountDAO.saveOrUpdate( new UserAccount( currentUser, false ) ) == null ){

            FileManager.deleteFile(picturePath);
            FileManager.deleteFile(documentaryPath);

            return false;
        }

        Address savedAddress = addressDAO.saveOrUpdate(address);

        Doctor doctor = new Doctor(user, specialization, documentaryPath, picturePath, description, savedAddress);

        if (doctorDAO.saveOrUpdate(doctor) == null) {

            FileManager.deleteFile(picturePath);
            FileManager.deleteFile(documentaryPath);

            return false;
        }

        return true;
    }

    private User saveCurrentUser( User user ) {

        Role currentRole = roleDAO.getRoleByUID(RoleEnum.Doctor);
        user.setRole(currentRole);

        BCryptPasswordEncoderService bCryptPasswordEncoderService = new BCryptPasswordEncoderService();

        user.setPassword(bCryptPasswordEncoderService.encode(user.getPassword()));

        User currentUser = userDAO.saveOrUpdate(user);

        return currentUser;
    }

    public List<Doctor> getAllConfirmedDoctors() {

        return doctorDAO.getAllConfirmedDoctors();
    }

    public Doctor getDoctorByID(int doctorId) {

        return doctorDAO.findById(doctorId);
    }

    public Doctor getDoctorByUserID(int userId) {

        return doctorDAO.getDoctorByUserID(userId);
    }

    public List<Doctor> getDoctorAppointments() {

        return doctorDAO.getDoctorAppointments();
    }

    public void approveDoctor( Doctor currentDoctor ) {

        currentDoctor.setConfirmed( true );
        doctorDAO.saveOrUpdate( currentDoctor );
    }

    public void updateDoctor(Doctor currentDoctor) {

        doctorDAO.saveOrUpdate( currentDoctor );
    }
}
