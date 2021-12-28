package sample;

import dao.implementation.*;
import entities.*;
import enums.RoleEnum;
import security.BCryptPasswordEncoderService;

import java.time.LocalDate;
import java.time.LocalTime;


public class DatabaseFiller {

    public void fillDatabase() {

        Specialization specialization = new Specialization("Ortoped");
        Specialization specialization1 = new Specialization("Hirurg");

        SpecializationDAO specializationDAO = new SpecializationDAO();
        Specialization ortoped = specializationDAO.saveOrUpdate(specialization);
        Specialization hirurg = specializationDAO.saveOrUpdate(specialization1);

        LocalDate localDate = LocalDate.of(1999, 2, 26);

        User user = new User("ench3r@gmail.com", "sach", "123", "Iliyan", "Stanchev", "0897875640", localDate);
        BCryptPasswordEncoderService bCryptPasswordEncoderService = new BCryptPasswordEncoderService();

        user.setPassword(bCryptPasswordEncoderService.encode(user.getPassword()));
        UserDAO userDAO = new UserDAO();

        Role role = new Role("Patient", RoleEnum.Patient);

        RoleDAO roleDAO = new RoleDAO();

        Role admin = new Role("Administrator", RoleEnum.Administrator);
        Role doctor = new Role("Doctor", RoleEnum.Doctor);

        roleDAO.saveOrUpdate(admin);
        roleDAO.saveOrUpdate(doctor);

        Role newRole = roleDAO.saveOrUpdate(role);
        user.setRole(newRole);

        user = userDAO.saveOrUpdate(user);

        User doctorUser = new User("ench1r@gmail.com", "doctor", "123", "Iliyan", "Stanchev", "0897875610"
                , localDate);

        doctorUser.setRole(doctor);
        doctorUser.setPassword(bCryptPasswordEncoderService.encode(doctorUser.getPassword()));

        AddressDAO addressDAO = new AddressDAO();

        User user1 = userDAO.saveOrUpdate(doctorUser);
        Doctor doctor1 = new Doctor(user1, ortoped, "src/main/resources/DoctorDocumentary/DoctorDocumentary_20211204_1339", "src/main/resources/DoctorPicture/DoctorPicture_20211204_1339.jpg", "Mn dobur doktor", addressDAO.saveOrUpdate(new Address("Varna", "Goce Delchev")));

        user = userDAO.saveOrUpdate(user);

        User doctorUser1 = new User("ench12r@gmail.com", "doctor1", "123", "Stanimir", "Stanchev", "0897875611"
                , localDate);

        doctorUser1.setRole(doctor);
        doctorUser1.setPassword(bCryptPasswordEncoderService.encode(doctorUser.getPassword()));

        User user2 = userDAO.saveOrUpdate(doctorUser1);
        Doctor doctor2 = new Doctor(user2, hirurg, "src/main/resources/DoctorDocumentary/DoctorDocumentary_20211204_1339", "src/main/resources/DoctorPicture/DoctorPicture_20211204_1339.jpg", "Mn dobur doktor", addressDAO.saveOrUpdate(new Address("Kotel", "Luda Kamchia")));

        user = userDAO.saveOrUpdate(user);

        User doctorUser3 = new User("ench5r@gmail.com", "doctor2", "123", "Valio", "Stanchev", "0897855610"
                , localDate);

        doctorUser3.setRole(doctor);
        doctorUser3.setPassword(bCryptPasswordEncoderService.encode(doctorUser.getPassword()));

        User user3 = userDAO.saveOrUpdate(doctorUser3);
        Doctor doctor3 = new Doctor(user3, ortoped, "src/main/resources/DoctorDocumentary/DoctorDocumentary_20211204_1339", "src/main/resources/DoctorPicture/DoctorPicture_20211204_1339.jpg", "Mn dobur doktor", addressDAO.saveOrUpdate(new Address("Sliven", "Stote Voivodi")));

        DoctorDAO doctorDAO = new DoctorDAO();

        doctorDAO.saveOrUpdate(doctor1);
        doctorDAO.saveOrUpdate(doctor2);
        doctorDAO.saveOrUpdate(doctor3);

        ExaminationHourDAO examinationHourDAO = new ExaminationHourDAO();

        for (int i = 0; i < 8; i++) {

            examinationHourDAO.saveOrUpdate(new ExaminationHour(LocalDate.now()
                    , LocalTime.of(i + 8, 20)
                    , LocalTime.of(i + 8, 40)
                    , false
                    , doctor1));

            examinationHourDAO.saveOrUpdate(new ExaminationHour(LocalDate.now()
                    , LocalTime.of(i + 8, 00)
                    , LocalTime.of(i + 8, 20)
                    , false
                    , doctor1));
        }

        VisitReasonDAO visitReasonDAO = new VisitReasonDAO();

        visitReasonDAO.saveOrUpdate(new VisitReason("primary examination"));
        visitReasonDAO.saveOrUpdate(new VisitReason("secondary examination"));
        visitReasonDAO.saveOrUpdate(new VisitReason("prophylactic examination"));
        visitReasonDAO.saveOrUpdate(new VisitReason("medical procedure"));
        visitReasonDAO.saveOrUpdate(new VisitReason("other examination"));
    }
}
