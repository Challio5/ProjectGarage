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

public class ReparatieDao {
	private DatabaseManager manager;
	
	public ReparatieDao() {
		manager = DatabaseManager.getInstance();
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

	
	
}
