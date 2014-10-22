package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Omschrijving;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.model.Voorraad;

/**
 * Data access object wat de reparatie tabel beheert in de database
 * Haalt de gegevens van een reparatie op en schrijft ze weg naar de database
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class ReparatieDao {
	// Databasemanager met de connectie naar de database
	private DatabaseManager manager;
	
	/**
	 * Constructor voor het ophalen van de singleton instantie van de databasemanager
	 */
	public ReparatieDao() {
		manager = DatabaseManager.getInstance();
	}
	
	/**
	 * Constructor voor het meegegeven van de singleton instantie van de databasemanager
	 * @param manager Singleton instantie van de databasemanager
	 */
	public ReparatieDao(DatabaseManager manager) {
		this.manager = manager;
	}
	
	public ArrayList<Reparatie> getReparaties() {
		// Lijst met de resultaten van de query
		ArrayList<Reparatie> reparatieLijst = new ArrayList<>();
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel reparatie haalt
			String reparatieQuery = "select * from reparatie";
			PreparedStatement reparatieStatement = connection.prepareStatement(reparatieQuery);
			ResultSet reparatieSet = reparatieStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(reparatieSet.next()) {
				// De gegevens van een rij
				int reparatieNummer = reparatieSet.getInt("Reparatienr");
				String kenteken = reparatieSet.getString("Kenteken");
				String omschrijving = reparatieSet.getString("Omschrijving"); 
				Date begintijd = reparatieSet.getDate("Begintijd");
				Date eindtijd = reparatieSet.getDate("EindTijd");
				boolean reparatiestatus = reparatieSet.getBoolean("Reparatiestatus");
				boolean betaalstatus = reparatieSet.getBoolean("Betaalstatus");
	
				// Query die alle materiaalnummer uit de koppeltabel haalt
				String materiaalNummersQuery = "select Materiaalnr from ReparatieVoorraad"
						+ " where Reparatienr = ?";
				PreparedStatement materiaalNummersStatement = connection.prepareStatement(materiaalNummersQuery);
				
				materiaalNummersStatement.setInt(1, reparatieNummer);
				ResultSet materiaalNummerSet = materiaalNummersStatement.executeQuery();
				
				// Slaat de materiaalnummers van een reparatie op in een lijst
				ArrayList<Integer> materiaalNummersLijst = new ArrayList<>();
				while(materiaalNummerSet.next()) {
					int materiaalNummer = materiaalNummerSet.getInt("Materiaalnr");
					materiaalNummersLijst.add(materiaalNummer);
				}
				
				// Query die aan het de hand van het materiaalnummer materialen uit de tabel haalt
				ArrayList<Voorraad> materialenLijst = new ArrayList<>();
				for(int materiaalnummer : materiaalNummersLijst) {
					String materialenQuery = "select * from Voorraad"
							+ " where Materiaalnr = ?";
					PreparedStatement materialenStatement = connection.prepareStatement(materialenQuery);
					
					materialenStatement.setInt(1, materiaalnummer);
					ResultSet materialenSet = materialenStatement.executeQuery();
					
					// Een materiaal wordt toegevoegd aan de lijst
					while(materialenSet.next()) {
						int materiaalNummer = materialenSet.getInt("Materiaalnr");
						String naam = materialenSet.getString("Naam");
						double prijs = materialenSet.getDouble("Prijs");
						int aantal = materialenSet.getInt("Aantal");
						
						materialenLijst.add(new Voorraad(materiaalNummer, naam, prijs, aantal));
					}
				}
				
				reparatieLijst.add(new Reparatie(reparatieNummer, kenteken, new Omschrijving(omschrijving, 0.0), begintijd, eindtijd, reparatiestatus, betaalstatus, materialenLijst));
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
		
		// Geeft de lijst met reparaties terug
		return reparatieLijst;
	}
	
	public ArrayList<Reparatie> eigenReparaties(int werknemerNummer) {
		// Lijst met de resultaten van de query
		ArrayList<Reparatie> reparatieLijst = new ArrayList<>();
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel reparatie haalt
			String reparatieQuery = "Select * from reparatie Inner Join planning on reparatie.reparatieNr = planning.reparatieNr where werknemernr = "+ werknemerNummer;
			PreparedStatement reparatieStatement = connection.prepareStatement(reparatieQuery);
			ResultSet reparatieSet = reparatieStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(reparatieSet.next()) {
				// De gegevens van een rij
				int reparatieNummer = reparatieSet.getInt("Reparatienr");
				String kenteken = reparatieSet.getString("Kenteken");
				String omschrijving = reparatieSet.getString("Omschrijving"); 
				Date begintijd = reparatieSet.getDate("Begintijd");
				Date eindtijd = reparatieSet.getDate("EindTijd");
				boolean reparatiestatus = reparatieSet.getBoolean("Reparatiestatus");
				boolean betaalstatus = reparatieSet.getBoolean("Betaalstatus");
	
				// Query die alle materiaalnummer uit de koppeltabel haalt
				String materiaalNummersQuery = "select Materiaalnr from ReparatieVoorraad"
						+ " where Reparatienr = ?";
				PreparedStatement materiaalNummersStatement = connection.prepareStatement(materiaalNummersQuery);
				
				materiaalNummersStatement.setInt(1, reparatieNummer);
				ResultSet materiaalNummerSet = materiaalNummersStatement.executeQuery();
				
				// Slaat de materiaalnummers van een reparatie op in een lijst
				ArrayList<Integer> materiaalNummersLijst = new ArrayList<>();
				while(materiaalNummerSet.next()) {
					int materiaalNummer = materiaalNummerSet.getInt("Materiaalnr");
					materiaalNummersLijst.add(materiaalNummer);
				}
				
				// Query die aan het de hand van het materiaalnummer materialen uit de tabel haalt
				ArrayList<Voorraad> materialenLijst = new ArrayList<>();
				for(int materiaalnummer : materiaalNummersLijst) {
					String materialenQuery = "select * from Voorraad"
							+ " where Materiaalnr = ?";
					PreparedStatement materialenStatement = connection.prepareStatement(materialenQuery);
					
					materialenStatement.setInt(1, materiaalnummer);
					ResultSet materialenSet = materialenStatement.executeQuery();
					
					// Een materiaal wordt toegevoegd aan de lijst
					while(materialenSet.next()) {
						int materiaalNummer = materialenSet.getInt("Materiaalnr");
						String naam = materialenSet.getString("Naam");
						double prijs = materialenSet.getDouble("Prijs");
						int aantal = materialenSet.getInt("Aantal");
						
						materialenLijst.add(new Voorraad(materiaalNummer, naam, prijs, aantal));
					}
				}
				
				reparatieLijst.add(new Reparatie(reparatieNummer, kenteken, new Omschrijving(omschrijving, 0.0), begintijd, eindtijd, reparatiestatus, betaalstatus, materialenLijst));
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
		
		// Geeft de lijst met reparaties terug
		return reparatieLijst;
	}

	public Reparatie getReparatie(int reparatienr) {
		Reparatie reparatie = null;
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel reparatie haalt
			String reparatieQuery = "select * from reparatie where reparatienr = ?";
			PreparedStatement reparatieStatement = connection.prepareStatement(reparatieQuery);
			reparatieStatement.setInt(1, reparatienr);
			
			ResultSet reparatieSet = reparatieStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(reparatieSet.next()) {
				// De gegevens van een rij
				int reparatieNummer = reparatieSet.getInt("Reparatienr");
				String kenteken = reparatieSet.getString("Kenteken");
				String omschrijving = reparatieSet.getString("Omschrijving"); 
				Date begintijd = reparatieSet.getDate("Begintijd");
				Date eindtijd = reparatieSet.getDate("EindTijd");
				boolean reparatiestatus = reparatieSet.getBoolean("Reparatiestatus");
				boolean betaalstatus = reparatieSet.getBoolean("Betaalstatus");
				
				ArrayList<Voorraad> materialenLijst = new ArrayList<>();
				
				reparatie = new Reparatie(reparatieNummer, kenteken, new Omschrijving(omschrijving, 0.0), begintijd, eindtijd, reparatiestatus, betaalstatus, materialenLijst);
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		return reparatie;
	}
}
