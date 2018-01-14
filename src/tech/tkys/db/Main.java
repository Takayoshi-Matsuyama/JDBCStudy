package tech.tkys.db;

import java.sql.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * PostgreSQL JDBC Driver
 * https://jdbc.postgresql.org/download.html
 */
public class Main extends Application {

    public static void main(String[] args) {
        launch(args);

	    System.out.println("Study of JDBC (Java DataBase Connectivity)");

	    try {
	        Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
	        e.printStackTrace();
        }

        System.out.println("JDBC Driver for PostgreSQL is found.");

        Connection connection = null;
	    try {
	        connection = DriverManager.getConnection(
	                "jdbc:postgresql://localhost:5433/TestDB",
                    "postgres",
                    "password");
            System.out.println("Connected");

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM public.sample_table " +
                    "ORDER BY id ASC LIMIT 100");

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%d %s %d%n",
                        resultSet.getInt("id"),
                        resultSet.getString("time_stamp"),
                        resultSet.getInt("data"));
            }

            resultSet.close();
            statement.close();

        } catch (SQLException e) {
	        e.printStackTrace();
        } finally {
	        if (connection != null) {
	            try {
	                connection.close();
                    System.out.println("Closed");
                } catch (SQLException e) {
	                e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("PrimaryRootPane.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }
}
