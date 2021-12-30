package sample;

import dao.implementation.*;
import entities.*;
import enums.RoleEnum;
import security.BCryptPasswordEncoderService;

import java.time.LocalDate;
import java.time.LocalTime;


public class DatabaseFiller {

    public void fillDatabase() {

       SpecializationDAO specializationDAO = new SpecializationDAO();
        specializationDAO.saveOrUpdate( new Specialization("Anesthesiology") );
        specializationDAO.saveOrUpdate( new Specialization("Dermatology") );
        specializationDAO.saveOrUpdate( new Specialization("Ophthalmology") );
        specializationDAO.saveOrUpdate( new Specialization("Neurology") );
        specializationDAO.saveOrUpdate( new Specialization("Pathology") );
        specializationDAO.saveOrUpdate( new Specialization("Medical genetics") );
        specializationDAO.saveOrUpdate( new Specialization("Pediatrics") );
        specializationDAO.saveOrUpdate( new Specialization("Internal medicine") );
        specializationDAO.saveOrUpdate( new Specialization("Physical medicine and rehabilitation") );
        specializationDAO.saveOrUpdate( new Specialization("Family medicine") );
        specializationDAO.saveOrUpdate( new Specialization("Preventive medicine") );

        Role role = new Role("Patient", RoleEnum.Patient);

        RoleDAO roleDAO = new RoleDAO();

        Role admin = new Role("Administrator", RoleEnum.Administrator);
        Role doctorRole = new Role("Doctor", RoleEnum.Doctor);

        roleDAO.saveOrUpdate(admin);
        roleDAO.saveOrUpdate(doctorRole);
        roleDAO.saveOrUpdate(role);

        VisitReasonDAO visitReasonDAO = new VisitReasonDAO();

        visitReasonDAO.saveOrUpdate(new VisitReason("Primary examination"));
        visitReasonDAO.saveOrUpdate(new VisitReason("Secondary examination"));
        visitReasonDAO.saveOrUpdate(new VisitReason("Prophylactic examination"));
        visitReasonDAO.saveOrUpdate(new VisitReason("Medical procedure"));
        visitReasonDAO.saveOrUpdate(new VisitReason("Other examination"));



        ExaminationHourDAO examinationHourDAO = new ExaminationHourDAO();

        DoctorDAO doctorDAO = new DoctorDAO();

        for( Doctor doctor : doctorDAO.getAllConfirmedDoctors() ) {
            for (int j = 0; j < 3; j++) {
                for (int i = 0; i < 8; i++) {

                    examinationHourDAO.saveOrUpdate(new ExaminationHour(LocalDate.now().plusDays(j)
                            , LocalTime.of(i + 8, 20)
                            , LocalTime.of(i + 8, 40)
                            , false
                            , doctor));

                    examinationHourDAO.saveOrUpdate(new ExaminationHour(LocalDate.now().plusDays(j)
                            , LocalTime.of(i + 8, 00)
                            , LocalTime.of(i + 8, 20)
                            , false
                            , doctor));
                }
            }
        }
    }
}
