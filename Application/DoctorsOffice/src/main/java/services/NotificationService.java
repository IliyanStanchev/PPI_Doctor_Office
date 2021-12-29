package services;

import dao.implementation.NotificationDAO;
import dao.implementation.ReservedHourDAO;
import entities.Examination;
import entities.Notification;
import entities.ReservedHour;
import javafx.scene.control.Alert;
import view.ReservedHourView;

import java.util.List;

public class NotificationService {

    private final NotificationDAO notificationDAO = new NotificationDAO();
    private final ReservedHourDAO reservedHourDAO = new ReservedHourDAO();

    public List<Notification> getUserNotifications( int userID ) {

        return notificationDAO.getUserNotifications( userID );
    }

    public void addExaminationNotification(Examination examination) {

        final String message = "Dr. " + examination.getReservedHour().getExaminationHour().getDoctor().getUser().getFullName()
                + " added examination notes. Do you want to see them now?";

        Notification notification = new Notification( examination.getReservedHour().getPatient(), message, Alert.AlertType.CONFIRMATION, examination.getReservedHour().getId() );

        notificationDAO.saveOrUpdate( notification );
    }


    public void addCanceledReservedHourNotification(ReservedHourView reservedHourView) {

        final String message = "Your examination with Dr. " + reservedHourView.getDoctorName()
                + ", Date: " + reservedHourView.getDate() + " was canceled by administrator.";

        ReservedHour reservedHour = reservedHourDAO.findById( reservedHourView.getId() );

        Notification notification = new Notification( reservedHour.getPatient(), message, Alert.AlertType.INFORMATION, 0 );

        notificationDAO.saveOrUpdate( notification );
    }
}
