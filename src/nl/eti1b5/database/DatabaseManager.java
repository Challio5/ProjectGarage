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
	
	public static DatabaseManager getInstance() throws IOException, SQLException {
		if(manager == null) {
			manager = new DatabaseManager();
		}
		return manager;
	}
}
