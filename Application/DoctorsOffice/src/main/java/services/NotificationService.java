package services;

import dao.implementation.NotificationDAO;
import dao.implementation.ReservedHourDAO;
import dao.implementation.UserDAO;
import entities.Examination;
import entities.Notification;
import entities.ReservedHour;
import entities.User;
import javafx.scene.control.Alert;
import view.ReservedHourView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class NotificationService {

    private final NotificationDAO notificationDAO = new NotificationDAO();
    private final ReservedHourDAO reservedHourDAO = new ReservedHourDAO();
    private final UserDAO         userDAO         = new UserDAO();

    public void addExaminationNotification(Examination examination) {

        final String message = "Dr. " + examination.getReservedHour().getExaminationHour().getDoctor().getUser().getFullName()
                + " added examination notes. Do you want to see them now?";

        Notification notification = new Notification( examination.getReservedHour().getPatient(), message, Alert.AlertType.CONFIRMATION, examination.getReservedHour().getId() );

        notificationDAO.saveOrUpdate( notification );
    }


    public void addCanceledReservedHourNotification(ReservedHourView reservedHourView) {

        final String message = "Your examination with Dr. " + reservedHourView.getDoctorName()
                + ", Date: " + reservedHourView.getDate() + " was canceled.";

        ReservedHour reservedHour = reservedHourDAO.findById( reservedHourView.getId() );

        Notification notification = new Notification( reservedHour.getPatient(), message, Alert.AlertType.INFORMATION, 0 );

        notificationDAO.saveOrUpdate( notification );
    }

    public List<Notification> getUserNotifications( int userID, boolean showSeenNotifications ) {

        if( showSeenNotifications )
            return notificationDAO.getUserNotifications( userID );

        return notificationDAO.getNewUserNotifications( userID );
    }

    public List<Notification> getNewUserNotifications( int userID ) {

        return notificationDAO.getNewUserNotifications( userID );
    }

    public void setSeenNotification( int notificationID ) {

        Notification notification = notificationDAO.findById( notificationID );

        notification.setSeen( true );

        notificationDAO.saveOrUpdate( notification );
    }

    public void updateUserNotifications( int userID ) {

        User currentUser = userDAO.findById( userID );

        if( currentUser == null )
            return;

        for( ReservedHour reservedHour : reservedHourDAO.getPatientReservedHours( userID ) ){

            if( reservedHour.getExaminationHour().getDate().isBefore( LocalDate.now() ))
                continue;

            if( reservedHour.getExaminationHour().getDate().isAfter( LocalDate.now().plusDays( 3 ) ) )
                continue;

            final String message = "You have upcoming examination hour with Dr. " + reservedHour.getExaminationHour().getDoctor().getUser().getFullName()
                    + ", Date: " + reservedHour.getExaminationHour().getDate().toString() + " , from " + reservedHour.getExaminationHour().getStartTime().toString()
                    + " to " + reservedHour.getExaminationHour().getEndTime().toString();

            if( notificationDAO.getNotificationByReservedHourID( reservedHour.getId() ) != null )
                return;

            Notification notification = new Notification( currentUser, message, Alert.AlertType.INFORMATION, reservedHour.getId() );

            notificationDAO.saveOrUpdate( notification );
        }
    }
}
