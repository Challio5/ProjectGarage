package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Auto;
import nl.eti1b5.model.Klant;

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
	
	public void addKlant(Klant klant) {
		// Checkt of de klant al een klantnummer heeft
		// Als dit zo is dan bestaat hij al in de database en dient hij aangepast te worden
		if(klant.getKlantnr() != 0) {
			this.addExistingKlant(klant);
		}
		else {
			this.addNewKlant(klant);
		}
	}
	
	private void addNewKlant(Klant klant) {
		// Zet de verbinding op met de database
		Connection connection = manager.getConnection();
		
		// De sql string met de juiste waarden voor de database
		String insertString = "insert into klant "
				+ "naam, adres, postcode, woonplaats, telnr "
				+ "values "
				+ "(?, ?, ?, ?, ?)";
		
		try {
			// Het statement met de juiste sql string
			PreparedStatement insertStatement = connection.prepareStatement(insertString);
			
			// Meldt de attributen van de planning aan bij het statement
			insertStatement.setString(1, klant.getNaam());
			insertStatement.setString(2, klant.getAdres());
			insertStatement.setString(3, klant.getPostcode());
			insertStatement.setString(4, klant.getWoonplaats());
			insertStatement.setString(5, klant.getTelNr());
			
			// Voert het statement uit
			insertStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();	
	}
	
	private void addExistingKlant(Klant klant) {
		// Zet de verbinding op met de database
		Connection connection = manager.getConnection();
		
		// De sql string met de juiste waarden voor de database
		String insertString = "update klant "
				+ "set naam=?, adres=?, postcode=?, woonplaats=?, TelNr=? "
				+ "where klantnr=?";
		
		try {
			// Het statement met de juiste sql string
			PreparedStatement insertStatement = connection.prepareStatement(insertString);
			
			// Meldt de attributen van de planning aan bij het statement
			insertStatement.setString(1, klant.getNaam());
			insertStatement.setString(2, klant.getAdres());
			insertStatement.setString(3, klant.getPostcode());
			insertStatement.setString(4, klant.getWoonplaats());
			insertStatement.setString(5, klant.getTelNr());
			insertStatement.setInt(6, klant.getKlantnr());
			
			// Voert het statement uit
			insertStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();	
	}
}
