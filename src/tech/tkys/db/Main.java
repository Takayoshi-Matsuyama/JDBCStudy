package tech.tkys.db;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * PostgreSQL JDBC Driver
 * https://jdbc.postgresql.org/download.html
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

	    System.out.println("Study of JDBC (Java DataBase Connectivity)");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        AppBootStrapper appBootStrapper = new AppBootStrapper();
        appBootStrapper.startApplication(primaryStage);
    }
}
