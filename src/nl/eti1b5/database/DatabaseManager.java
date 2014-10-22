package nl.eti1b5.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Singleton klasse die de connectie met de database beheert
 * Creëert instantie van zichzelf die op te vragen is met de bijbehorende methode
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 7 okt. 2014
 */

public class DatabaseManager {
	
	// De connectie met de database
	private Connection connection;
	
	/**
	 * Private constructor voor Singletonklasse
	 */
	private DatabaseManager() {}
	
	/**
	 * Methode om de eigen instantie van de klasse op te vragen
	 * Haalt de instantie op van de geneste holder klasse
	 * @return Referentie naar de databasemanager
	 */
	public static DatabaseManager getInstance() {
		return DatabaseManagerHolder.INSTANCE;
	}
	
	/**
	 * Getter voor het opvragen van de connectie naar de datbase
	 * @return De connectie met de database
	 */
	public Connection getConnection() {
		if(connection == null) {
			try {
				createConnection();
			} catch (IOException e) {
				System.err.println("Kan de juiste properties niet laden");
				e.printStackTrace();
			} catch (SQLException e) {
				System.err.println("De gebruikersnaam, wachtwoord of ");
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	/**
	 * Methode die de verbinding opzet met de database
	 * @throws IOException De propertyfile kan niet geopend worden
	 * @throws SQLException De verbinding met de database kan niet tot stand komen
	 */
	public void createConnection() throws IOException, SQLException {
		Properties databaseProperties = new Properties();
		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("props.properties");
		databaseProperties.load(stream);
		
		String driver = databaseProperties.getProperty("jdbc.drivers");
		String url = databaseProperties.getProperty("jdbc.url");
		String user = databaseProperties.getProperty("jdbc.username");
		String password = databaseProperties.getProperty("jdbc.password");
		
		System.setProperty("jdbc.driver", driver);
		connection = DriverManager.getConnection(url, user, password);
	}
	
	/**
	 * Sluit de verbinding met de database
	 */
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				System.err.println("Kan de verbinding niet sluiten");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Geneste holder klasse, schermt de instantie van de databasemanager af van de buitenwereld
	 * Wordt gebruikt om de enige beschikbare instantie van de databasemanager mee op te halen
	 * 
	 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
	 * @since 22 okt. 2014
	 */
    private static class DatabaseManagerHolder {
        private static final DatabaseManager INSTANCE = new DatabaseManager();
    }
}
