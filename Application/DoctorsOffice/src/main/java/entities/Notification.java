package entities;

import javafx.scene.control.Alert;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity( name = "NOTIFICATIONS")
public class Notification implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @OneToOne
    private User user;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "NOTIFICATION_TIMESTAMP")
    private LocalDateTime notificationTimestamp;

    @Column(name = "NOTIFICATION_TYPE")
    private Alert.AlertType notificationType;

    @Column(name = "RESERVED_HOUR_ID")
    private int reservedHourID;

    @Column(name = "SEEN")
    private boolean seen;

    public Notification() {

    }

    public Notification(User user, String description, Alert.AlertType alertType, int reservedHourID ) {

        this.user                   = user;
        this.description            = description;
        this.notificationTimestamp  = LocalDateTime.now();
        this.notificationType       = alertType;
        this.reservedHourID         = reservedHourID;
        this.seen                   = false;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public int getReservedHourID() {
        return reservedHourID;
    }

    public void setReservedHourID(int reservedHourID) {
        this.reservedHourID = reservedHourID;
    }

    public Alert.AlertType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(Alert.AlertType notificationType) {
        this.notificationType = notificationType;
    }

    public LocalDateTime getNotificationTimestamp() {

        return notificationTimestamp;
    }

    public void setNotificationTimestamp(LocalDateTime notificationTimestamp) {
        this.notificationTimestamp = notificationTimestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return id == that.id && Objects.equals(user, that.user) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, description);
    }
}
