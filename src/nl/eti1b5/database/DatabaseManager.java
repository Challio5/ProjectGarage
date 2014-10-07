package nl.eti1b5.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {
	private static DatabaseManager manager;
	
	private Connection connection;
	
	private DatabaseManager() throws IOException, SQLException {
		Properties databaseProperties = new Properties();
		InputStream stream = this.getClass().getResourceAsStream("./props.properties");
		databaseProperties.load(stream);
		
		String driver = databaseProperties.getProperty("drivers");
		String url = databaseProperties.getProperty("url");
		String user = databaseProperties.getProperty("username");
		String password = databaseProperties.getProperty("password");

		System.setProperty("jdbc.driver", driver);
		connection = DriverManager.getConnection(url, user, password);
	}
	
	public static DatabaseManager getInstance() {
		if(manager == null) {
			try {
				manager = new DatabaseManager();
			} catch (IOException e) {
				System.err.println("Kan de juiste properties niet laden");
				e.printStackTrace();
			} catch (SQLException e) {
				System.err.println("De gebruikersnaam, wachtwoord of ");
				e.printStackTrace();
			}
		}
		return manager;
	}
	
	public Connection getConnection() {
		return connection;
	}
	
	public void close() {
		if(manager != null) {
			try {
				connection.close();
				manager = null;
			} catch (SQLException e) {
				System.err.println("Kan de verbinding niet sluiten");
				e.printStackTrace();
			}
		}
	}
}
