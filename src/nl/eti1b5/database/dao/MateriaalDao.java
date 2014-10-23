package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Materiaal;

/**
 * Data access object wat de materiaal tabel beheert in de database
 * Haalt de gegevens van de materiaal op en schrijft ze weg naar de database
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class MateriaalDao {
	// Databasemanager met de connectie naar de database
	private DatabaseManager manager;
	
	/**
	 * Constructor voor het ophalen van de singleton instantie van de databasemanager
	 */
	public MateriaalDao() {
		manager = DatabaseManager.getInstance();
	}
	
	/**
	 * Constructor voor het meegegeven van de singleton instantie van de databasemanager
	 * @param manager Singleton instantie van de databasemanager
	 */
	public MateriaalDao(DatabaseManager manager) {
		this.manager = manager;
	}
	
	public ArrayList<Materiaal> getMateriaal() {
		// Lijst met de resultaten van de query
		ArrayList<Materiaal> materiaalLijst = new ArrayList<>();
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel materiaal haalt
			String materiaalQuery = "select * from materiaal";
			PreparedStatement materiaalStatement = connection.prepareStatement(materiaalQuery);
			ResultSet materiaalSet = materiaalStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(materiaalSet.next()) {
				// De gegevens van een rij
				int materiaalnr = materiaalSet.getInt("materiaalnr");
				String naam = materiaalSet.getString("naam");
				double prijs = materiaalSet.getDouble("prijs");
		
				materiaalLijst.add(new Materiaal(materiaalnr, naam, prijs));
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
		
		// Geeft de lijst met materiaal terug
		return materiaalLijst;
	}
}

