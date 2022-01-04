package dao.implementation;

import dao.BaseDAO;
import entities.Notification;
import javafx.scene.control.Alert;
import manager.EntityManagerExtender;

import javax.persistence.NoResultException;
import java.util.List;

public class NotificationDAO extends BaseDAO<Notification> {

    public NotificationDAO() {

        super.setClass( Notification.class );
    }

    public List<Notification> getUserNotifications( int userID ) {

        return EntityManagerExtender.getEntityManager().createQuery("FROM NOTIFICATIONS notifications WHERE notifications.user.id =: userID order by notifications.seen, notifications.notificationTimestamp desc ")
                .setParameter("userID", userID )
                .getResultList();
    }

    public List<Notification> getNewUserNotifications(int userID) {
        return EntityManagerExtender.getEntityManager().createQuery("FROM NOTIFICATIONS notifications WHERE notifications.user.id =: userID and notifications.seen = false order by notifications.notificationTimestamp desc ")
                .setParameter("userID", userID )
                .getResultList();
    }

    public Notification getNotificationByReservedHourID( int reservedHourID ) {

        Notification notification;
        try {
            notification = (Notification) EntityManagerExtender.getEntityManager().createQuery("FROM NOTIFICATIONS notification WHERE notification.reservedHourID =: reservedHourID and notification.notificationType =: alertType")
                    .setParameter("reservedHourID", reservedHourID )
                    .setParameter("alertType", Alert.AlertType.INFORMATION )
                    .getSingleResult();

        } catch (NoResultException e) {
            notification = null;
        }

        return notification;
    }
}