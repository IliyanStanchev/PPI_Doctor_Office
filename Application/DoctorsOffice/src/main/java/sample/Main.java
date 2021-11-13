package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import utils.OpenForm;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        DatabaseFiller databaseFiller = new DatabaseFiller();
        databaseFiller.fillDatabase();

        //OpenForm.openNewForm("/Hall.fxml", "Choose seat");
        OpenForm.openNewForm("/Login.fxml", "Login page");

    }
}
