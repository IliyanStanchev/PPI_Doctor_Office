package controllers;

import entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.RoleService;
import services.UserAccountService;
import view.UserView;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class AdminUserOverviewController implements Initializable {

    private static final String BLOCK_USER      = "Block User";
    private static final String UNBLOCK_USER    = "Unblock User";

    public Button           manageAccessButton;
    public TextField        userNameField;
    public TextField        userIdentifierField;

    public ComboBox< Role > roleComboBox;
    private ObservableList< Role > roleList = FXCollections.observableArrayList();

    public TableView< UserView > userTableView;
    public TableColumn< UserView, String > userStateColumn;
    public TableColumn< UserView, String > userIdentifierColumn;
    public TableColumn< UserView, String > userRoleColumn;
    public TableColumn< UserView, String > userNameColumn;

    private ObservableList< UserView >    userAccountList = FXCollections.observableArrayList();
    private FilteredList< UserView >      filteredData    = new FilteredList<>( userAccountList, b -> true );

    public Label resultLabel;

    public void onManageAccess( ActionEvent actionEvent ) {

        UserView userView       = userTableView.getSelectionModel().getSelectedItem();

        if( userView == null )
            return;

        UserAccountService userAccountService = new UserAccountService();

        UserAccount userAccount = userAccountService.changeUserState( userView.getId() );

        userAccountList.remove( userView );
        userAccountList.add( new UserView( userAccount ));
        userTableView.setItems( filteredData );
        userTableView.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        userStateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));
        userIdentifierColumn.setCellValueFactory(new PropertyValueFactory<>("identifier"));
        userRoleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        userNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        RoleService roleService = new RoleService();

        for ( Role role : roleService.getAllRoles() ) {

            roleList.add(role);
        }

        roleComboBox.setItems(roleList);

        InitTableView();
    }

    private void InitTableView() {

        UserAccountService userAccountService = new UserAccountService();

        for ( UserAccount userAccount : userAccountService.getAllUserAccounts() )
            userAccountList.add( new UserView( userAccount ));

        userTableView.setItems( userAccountList );
        userTableView.setItems( filteredData );

        userNameField.textProperty().addListener(obs -> {

            String userName = userNameField.getText();
            if (userName == null || userName.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate( userView -> userView.getName().toLowerCase(Locale.ROOT).contains( userName.toLowerCase(Locale.ROOT)) );
        });

        userIdentifierField.textProperty().addListener(obs -> {

            String identifier = userIdentifierField.getText();
            if ( identifier == null || identifier.length() == 0)
                filteredData.setPredicate(s -> true);
            else
                filteredData.setPredicate( userView -> userView.getIdentifier().toLowerCase(Locale.ROOT).contains( identifier.toLowerCase(Locale.ROOT)));
        });

        roleComboBox.setOnAction(actionEvent -> {

            Role role = roleComboBox.getSelectionModel().getSelectedItem();

            filteredData.setPredicate( userView ->
            {
                if (role == null)
                    return true;

                String lowerCaseFilter = role.getRoleName().toLowerCase();

                if ( userView.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1)
                    return true;

                return false;
            });
        });

        userTableView.setOnMouseClicked( new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

                UserView userView       = userTableView.getSelectionModel().getSelectedItem();

                if( userView == null )
                    return;

                manageAccessButton.setText( userView.isBlocked() ? UNBLOCK_USER : BLOCK_USER );
            }
        });
    }
}
