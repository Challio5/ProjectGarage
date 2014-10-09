package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Voorraad;

public class VoorraadDao {

	private DatabaseManager manager;
	
	public VoorraadDao() {
		manager = DatabaseManager.getInstance();
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

