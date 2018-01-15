package tech.tkys.db;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * アプリケーションを起動する（初期化とメインウィンドウの表示）
 */
public class AppBootStrapper {
    public void startApplication(Stage primaryStage) {

        // Initialize service objects.
        PostgreDBService postgreDBService = new PostgreDBService();
        ServiceContainer.registerService("PostgreDBService", postgreDBService);
        if (postgreDBService.initialize() == false) {
            System.out.println("Failed to initialize PostgreDB service.");
            return;
        }

        // Show the primary stage.
        showPrimaryStage(primaryStage);

        // Test DB connection.
        postgreDBService.testDBConnection();
    }

    private void showPrimaryStage(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("PrimaryRootPane.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
