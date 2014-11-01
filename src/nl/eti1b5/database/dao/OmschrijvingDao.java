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
		// Lijst voor de resultaten uit de database
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
	
	public Omschrijving getOmschrijving(int omschrijvingsNummer) {
		Omschrijving omschrijving = null;
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel monteur haalt
			String omschrijvingsQuery = "select * from omschrijving "
					+ "where omschrijvingsnr = ?";
			PreparedStatement omschrijvingsStatement = connection.prepareStatement(omschrijvingsQuery);
			omschrijvingsStatement.setInt(omschrijvingsNummer, 1);
			ResultSet omschrijvingsSet = omschrijvingsStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(omschrijvingsSet.next()) {
				// De gegevens van een rij
				String naam = omschrijvingsSet.getString("Naam");
				Time duur = omschrijvingsSet.getTime("Duur");
				
				omschrijving = new Omschrijving(naam, duur);
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
		
		return omschrijving;
	}
	
	public void addOmschrijving(Omschrijving omschrijving) {
		// De connectie met de database
		Connection connection = manager.getConnection();
		
		try {
			String omschrijvingString = "insert into omschrijving "
					+ "(naam, duur) "
					+ "values "
					+ "(?, ?)";
			PreparedStatement omschrijvingStatement = connection.prepareStatement(omschrijvingString);
			omschrijvingStatement.setString(1, omschrijving.getNaam());
			omschrijvingStatement.setTime(2, omschrijving.getDuur());
			omschrijvingStatement.execute();		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
	}
}
