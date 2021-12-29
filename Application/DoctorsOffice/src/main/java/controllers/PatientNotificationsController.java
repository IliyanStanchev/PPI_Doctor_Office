package controllers;

import entities.Notification;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.NotificationService;
import utils.AlertHelper;
import utils.OpenForm;
import view.NotificationView;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientNotificationsController implements Initializable {

    private User currentUser;
    private final NotificationService notificationService = new NotificationService();

    public TableView< NotificationView >            notificationsTableView;
    public TableColumn< NotificationView, String >  notificationTimestampColumn;
    public TableColumn< NotificationView, String >  notificationColumn;

    private ObservableList< NotificationView > notificationViewList = FXCollections.observableArrayList();

    @FXML
    private void onOpenNotification( MouseEvent mouseEvent ) {

        NotificationView notificationView = notificationsTableView.getSelectionModel().getSelectedItem();

        if ( notificationView == null )
            return;

        AlertHelper alertHelper = new AlertHelper( notificationView.getNotificationType() );

        if( alertHelper.show( notificationView.getNotificationType().toString(), notificationView.getMessage() )
            && notificationView.getNotificationType() == Alert.AlertType.CONFIRMATION ) {

            FXMLLoader fxmlLoader = OpenForm.openNewForm("/PatientViewNotes.fxml", "Examination details");
            PatientViewNotesController controller = fxmlLoader.getController();
            controller.setCurrentReservedHour( notificationView.getReservedHourID() );
        }
    }

    public void setCurrentUser( User user ){

        this.currentUser = user;

        for( Notification notification : notificationService.getUserNotifications( currentUser.getId() ) )
            notificationViewList.add( new NotificationView( notification ) );

        notificationsTableView.setItems( notificationViewList );
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.notificationTimestampColumn.setCellValueFactory(new PropertyValueFactory<>("formattedNotificationTimestamp"));
        this.notificationColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
    }
}
