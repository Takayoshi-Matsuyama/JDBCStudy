package tech.tkys.db;

import java.sql.*;

/**
 * PostgreSQL JDBC Driver
 * https://jdbc.postgresql.org/download.html
 */
public class Main {

    public static void main(String[] args) {
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
}
