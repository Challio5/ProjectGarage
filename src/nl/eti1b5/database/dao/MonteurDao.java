package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Monteur;

public class MonteurDao {
	private DatabaseManager manager;
	
	public MonteurDao() {
		manager = DatabaseManager.getInstance();
	}
	
	public ArrayList<Monteur> getMonteurs() {
		// Lijst met de resultaten van de query
		ArrayList<Monteur> monteurLijst = new ArrayList<>();
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel monteur haalt
			String monteurQuery = "select * from monteur";
			PreparedStatement monteurStatement = connection.prepareStatement(monteurQuery);
			ResultSet monteurSet = monteurStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(monteurSet.next()) {
				// De gegevens van een rij
				int werknemerNummer = monteurSet.getInt("Werknemernr");
				String naam = monteurSet.getString("Naam");
				String adres = monteurSet.getString("Adres");
				String postcode = monteurSet.getString("Postcode");
				String woonplaats = monteurSet.getString("Woonplaats");
				String telNr = monteurSet.getString("Telnr");
				String wachtwoord = monteurSet.getString("Wachtwoord");
				String specialiteit = monteurSet.getString("Specialiteit");
				
				ArrayList<String> beschikbaarheidsLijst = new ArrayList<>();
				
				// Query die de bijbehorende beschikbaarheid uit de koppetabel haalt
				String beschikbaarheidQuery = "select beschikbaarheid from monteurbeschikbaarheid"
						+ " where werknemernr = ?";
				PreparedStatement beschikbaarheidStatement = connection.prepareStatement(beschikbaarheidQuery);
				beschikbaarheidStatement.setInt(1, werknemerNummer);
				ResultSet beschikbaarheidSet = beschikbaarheidStatement.executeQuery();
				
				while(beschikbaarheidSet.next()) {
					String beschikbaarheid = beschikbaarheidSet.getString("beschikbaarheid");
					beschikbaarheidsLijst.add(beschikbaarheid);
				}
				
				monteurLijst.add(new Monteur(werknemerNummer, naam, woonplaats, adres, postcode, telNr,
								 wachtwoord, specialiteit, beschikbaarheidsLijst));
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
		
		// Geeft de lijst met monteurs terug
		return monteurLijst;
	}
}