package controllers;

import entities.Notification;
import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import services.NotificationService;
import utils.AlertHelper;
import utils.OpenForm;
import view.NotificationView;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientNotificationsController implements Initializable {

    public CheckBox showSeenNotificationsCheckBox;

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

        notificationService.setSeenNotification( notificationView.getId() );
        initTableView();
    }

    public void setCurrentUser( User user ){

        this.currentUser = user;

        initTableView();
    }

    private void initTableView() {

        notificationViewList.clear();
        notificationsTableView.getItems().clear();

        final boolean showSeenNotifications = showSeenNotificationsCheckBox.isSelected();

        for (Notification notification : notificationService.getUserNotifications(currentUser.getId(), showSeenNotifications))
            notificationViewList.add(new NotificationView(notification));

        notificationsTableView.setItems(notificationViewList);

        notificationsTableView.setRowFactory(new Callback<TableView<NotificationView>, TableRow<NotificationView>>() {

            @Override
            public TableRow<NotificationView> call(TableView<NotificationView> notificationViewTableView) {
                return new TableRow<NotificationView>() {
                    @Override
                    protected void updateItem(NotificationView notificationView, boolean empty) {
                        super.updateItem(notificationView, empty);
                        if (notificationView == null || notificationView.isSeen()) {
                            setStyle("");
                        } else {
                            setStyle("-fx-font-weight: bold");
                        }
                    }
                };
            };
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        this.notificationTimestampColumn.setCellValueFactory(new PropertyValueFactory<>("formattedNotificationTimestamp"));
        this.notificationColumn.setCellValueFactory(new PropertyValueFactory<>("message"));
    }

    public void onShowSeenMessages(ActionEvent actionEvent) {

        initTableView();
    }
}
