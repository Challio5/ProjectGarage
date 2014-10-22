package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Monteur;

/**
 * Data access object wat de monteurs tabel beheert in de database
 * Haalt de gegevens van een monteur op en schrijft ze weg naar de database
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class MonteurDao {
	// Databasemanager met de connectie naar de database
	private DatabaseManager manager;
	
	/**
	 * Constructor voor het ophalen van de singleton instantie van de databasemanager
	 */
	public MonteurDao() {
		manager = DatabaseManager.getInstance();
	}
	
	/**
	 * Constructor voor het meegegeven van de singleton instantie van de databasemanager
	 * @param manager Singleton instantie van de databasemanager
	 */
	public MonteurDao(DatabaseManager manager) {
		this.manager = manager;
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
				
				ArrayList<Integer> ReparatieLijst = new ArrayList<>();
				
				// Query die de bijbehorende reparaties uit de koppetabel haalt
				String ReparatiesQuery = "select Reparatienr from Planning"
						+ " where werknemernr = ?";
				PreparedStatement ReparatieStatement = connection.prepareStatement(ReparatiesQuery);
				ReparatieStatement.setInt(1, werknemerNummer);
				ResultSet ReparatieSet = ReparatieStatement.executeQuery();
				
				while(ReparatieSet.next()) {
					int Reparatie = Integer.parseInt(ReparatieSet.getString("Reparatienr"));
					ReparatieLijst.add(Reparatie);
				}
				
				monteurLijst.add(new Monteur(werknemerNummer, naam, woonplaats, adres, postcode, telNr,
								 wachtwoord, specialiteit, beschikbaarheidsLijst, ReparatieLijst));
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
	
	public Monteur getMonteur(int werknemernr) {
		Monteur monteur = null;
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel reparatie haalt
			String monteurQuery = "select * from monteur where werknemernr = ?";
			PreparedStatement monteurStatement = connection.prepareStatement(monteurQuery);
			monteurStatement.setInt(1, werknemernr);
			
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
				ArrayList<Integer> reparatieLijst = new ArrayList<>();
				
				monteur = new Monteur(werknemerNummer, naam, woonplaats, adres, postcode, telNr,
						 wachtwoord, specialiteit, beschikbaarheidsLijst, reparatieLijst);
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		return monteur;
	}
}
