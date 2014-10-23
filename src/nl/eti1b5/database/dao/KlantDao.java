package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Auto;
import nl.eti1b5.model.Klant;
import nl.eti1b5.model.Monteur;

public class KlantDao {
	
	// De databaseManager die de connectie naar de database beheert
	private DatabaseManager manager;
	
	public KlantDao() {
		manager = DatabaseManager.getInstance();
	}
	
	public ArrayList<Klant> getKlanten() {
		// De lijst waar de klanten uit de database in komen
		ArrayList<Klant> klantenLijst = new ArrayList<>();
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel monteur haalt
			String klantQuery = "select * from klant";
			PreparedStatement klantStatement = connection.prepareStatement(klantQuery);
			ResultSet klantSet = klantStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(klantSet.next()) {
				// De gegevens van een rij
				int klantNummer = klantSet.getInt("Klantnr");
				String naam = klantSet.getString("Naam");
				String adres = klantSet.getString("Adres");
				String postcode = klantSet.getString("Postcode");
				String woonplaats = klantSet.getString("Woonplaats");
				String telNr = klantSet.getString("Telnr");
				
				ArrayList<Auto> autoLijst = new ArrayList<>();
				
				// Query die de bijbehorende auto's uit de database haalt
				String autoQuery = "select * from auto"
						+ " where klantnr = ?";
				PreparedStatement autoStatement = connection.prepareStatement(autoQuery);
				autoStatement.setInt(1, klantNummer);
				ResultSet autoSet = autoStatement.executeQuery();
				
				while(autoSet.next()) {
					int klantnummer = autoSet.getInt("klantnr");
					String kenteken = autoSet.getString("kenteken");
					String merk = autoSet.getString("merk");
					String model = autoSet.getString("model");
					int verzekeringsNummer = autoSet.getInt("verzekeringsnr");
					autoLijst.add(new Auto(kenteken, klantnummer, merk, model, verzekeringsNummer));
				}
				
				klantenLijst.add(new Klant(klantNummer, naam, adres, postcode, woonplaats, telNr, autoLijst));
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
		
		return klantenLijst;
	}
}
