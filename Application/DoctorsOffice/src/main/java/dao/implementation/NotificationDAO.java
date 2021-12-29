package dao.implementation;

import dao.BaseDAO;
import entities.Notification;
import manager.MyEntityManager;

import java.util.List;

public class NotificationDAO extends BaseDAO<Notification> {

    public NotificationDAO() {

        super.setClass( Notification.class );
    }

    public List<Notification> getUserNotifications(int userID) {

        return MyEntityManager.getEntityManager().createQuery("FROM NOTIFICATIONS notifications WHERE notifications.user.id =: userID order by notifications.notificationTimestamp desc ")
                .setParameter("userID", userID )
                .getResultList();
    }
}