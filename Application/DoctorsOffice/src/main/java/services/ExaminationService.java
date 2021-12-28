package services;

import dao.implementation.ExaminationDAO;
import dao.implementation.ReservedHourDAO;
import entities.Examination;
import entities.ReservedHour;
import utils.FileManager;
import utils.FileNameGenerator;

import java.io.File;
import java.io.IOException;

public class ExaminationService {

    private final ExaminationDAO examinationDAO = new ExaminationDAO();
    private final ReservedHourDAO reservedHourDAO = new ReservedHourDAO();

    public Examination getExaminationByReservedHourID(int reservedHourId) {

        return examinationDAO.getExaminationByReservedHourID(reservedHourId);
    }

    public Examination saveExamination(int reservedHourId, File notesFile, String description, boolean shareWithPatient) {

        final String notesFileName = FileNameGenerator.generateCurrentTimeStampName(FileManager.examinationNoteFileNameStarter);

        String notesPath = "";

        try {
            notesPath = FileManager.copyFileToDirectory(notesFile, FileManager.examinationNoteDirectory, notesFileName);
        } catch (IOException e) {
            return null;
        }

        ReservedHour reservedHour = reservedHourDAO.findById(reservedHourId);

        if (reservedHour == null)
            return null;

        Examination examination = examinationDAO.getExaminationByReservedHourID(reservedHourId);

        if (examination == null)
            examination = new Examination();

        examination.setReservedHour(reservedHour);
        examination.setDocumentPath(notesPath);
        examination.setDescription(description);
        examination.setSharedWithPatient(shareWithPatient);

        Examination savedExamination = examinationDAO.saveOrUpdate(examination);

        return savedExamination;
    }
}
