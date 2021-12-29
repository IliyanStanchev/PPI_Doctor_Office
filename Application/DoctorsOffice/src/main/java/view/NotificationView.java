package view;

import entities.Notification;
import javafx.scene.control.Alert;

import java.time.format.DateTimeFormatter;

public class NotificationView {

    private int             id;
    private String          formattedNotificationTimestamp;
    private String          message;
    private Alert.AlertType notificationType;
    private int             reservedHourID;

    public NotificationView( Notification notification ) {

        final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-yyyy-MM HH:mm:ss");

        this.id                             = notification.getId();
        this.formattedNotificationTimestamp = notification.getNotificationTimestamp().format( dateTimeFormatter );
        this.message                        = notification.getDescription();
        this.notificationType               = notification.getNotificationType();
        this.reservedHourID                 = notification.getReservedHourID();
    }

    public int getReservedHourID() {
        return reservedHourID;
    }

    public void setReservedHourID(int reservedHourID) {
        this.reservedHourID = reservedHourID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormattedNotificationTimestamp() {
        return formattedNotificationTimestamp;
    }

    public void setFormattedNotificationTimestamp(String formattedNotificationTimestamp) {
        this.formattedNotificationTimestamp = formattedNotificationTimestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Alert.AlertType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Alert.AlertType notificationType) {
        this.notificationType = notificationType;
    }
}
