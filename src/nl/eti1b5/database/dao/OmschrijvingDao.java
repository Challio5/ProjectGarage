package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Omschrijving;

public class OmschrijvingDao {
	
	// De databaseManager die de connectie naar de database beheert
	private DatabaseManager manager;
	
	public OmschrijvingDao() {
		manager = DatabaseManager.getInstance();
	}
	
	public ArrayList<Omschrijving> getOmschrijvingen() {
		ArrayList<Omschrijving> omschrijvingenLijst = new ArrayList<>();
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel monteur haalt
			String omschrijvingsQuery = "select * from omschrijving";
			PreparedStatement omschrijvingsStatement = connection.prepareStatement(omschrijvingsQuery);
			ResultSet omschrijvingsSet = omschrijvingsStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(omschrijvingsSet.next()) {
				// De gegevens van een rij
				String naam = omschrijvingsSet.getString("Naam");
				Time duur = omschrijvingsSet.getTime("Duur");
				
				omschrijvingenLijst.add(new Omschrijving(naam, duur));
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
		
		return omschrijvingenLijst;
	}
}
