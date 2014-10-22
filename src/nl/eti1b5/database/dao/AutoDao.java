package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Auto;

/**
 * Data access object wat de auto tabel beheert in de database
 * Haalt de gegevens van een auto op en schrijft ze weg naar de database
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class AutoDao {
	// Databasemanager met de connectie naar de database
	private DatabaseManager manager;

	/**
	 * Constructor voor het ophalen van de singleton instantie van de databasemanager
	 */
	public AutoDao() {
		manager = DatabaseManager.getInstance();
	}
	
	/**
	 * Constructor voor het meegegeven van de singleton instantie van de databasemanager
	 * @param manager Singleton instantie van de databasemanager
	 */
	public AutoDao(DatabaseManager manager) {
		this.manager = manager;
	}

	public ArrayList<Auto> getAutos() {
		// Lijst met de resultaten van de query
		ArrayList<Auto> autoLijst = new ArrayList<>();

		// De connectie met de database op
		Connection connection = manager.getConnection();

		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel auto haalt
			String autoQuery = "select * from auto";
			PreparedStatement autoStatement = connection
					.prepareStatement(autoQuery);
			ResultSet autoSet = autoStatement.executeQuery();

			// Zolang er nog gegevens in de tabel staan
			while (autoSet.next()) {
				// De gegevens van een rij
				String kenteken = autoSet.getString("kenteken");
				int klantnummer = autoSet.getInt("klantnr");
				String merk = autoSet.getString("merk");
				String model = autoSet.getString("model");
				int verzekeringsNummer = autoSet.getInt("verzekeringsnr");

				autoLijst.add(new Auto(kenteken, klantnummer, merk, model,
						verzekeringsNummer));
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}

		// Sluit de verbinding met de database
		manager.closeConnection();

		// Geeft de lijst met auto's terug
		return autoLijst;
	}
}
