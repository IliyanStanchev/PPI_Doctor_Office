package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import manager.EntityManagerExtender;
import utils.OpenForm;

import javax.persistence.EntityManager;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        EntityManager entityManager = EntityManagerExtender.getEntityManager();

        //DatabaseFiller databaseFiller = new DatabaseFiller();
        //databaseFiller.fillDatabase();

        OpenForm.openNewForm("/Login.fxml", "Login page");

    }
}
