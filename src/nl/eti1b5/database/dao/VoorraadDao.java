package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Voorraad;

/**
 * Data access object wat de voorraad tabel beheert in de database
 * Haalt de gegevens van de voorraad op en schrijft ze weg naar de database
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class VoorraadDao {
	// Databasemanager met de connectie naar de database
	private DatabaseManager manager;
	
	/**
	 * Constructor voor het ophalen van de singleton instantie van de databasemanager
	 */
	public VoorraadDao() {
		manager = DatabaseManager.getInstance();
	}
	
	/**
	 * Constructor voor het meegegeven van de singleton instantie van de databasemanager
	 * @param manager Singleton instantie van de databasemanager
	 */
	public VoorraadDao(DatabaseManager manager) {
		this.manager = manager;
	}
	
	public ArrayList<Voorraad> getVoorraad() {
		// Lijst met de resultaten van de query
		ArrayList<Voorraad> voorraadLijst = new ArrayList<>();
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel voorraad haalt
			String voorraadQuery = "select * from voorraad";
			PreparedStatement voorraadStatement = connection.prepareStatement(voorraadQuery);
			ResultSet voorraadSet = voorraadStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(voorraadSet.next()) {
				// De gegevens van een rij
				int materiaalnr = voorraadSet.getInt("materiaalnr");
				String naam = voorraadSet.getString("naam");
				double prijs = voorraadSet.getDouble("prijs");
				int aantal = voorraadSet.getInt("aantal");
		
				voorraadLijst.add(new Voorraad(materiaalnr, naam, prijs, aantal));
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
		
		// Geeft de lijst met voorraad terug
		return voorraadLijst;
	}
}

