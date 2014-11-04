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
	
	public ArrayList<String> getKentekens() {
		// Lijst met de resultaten van de query
		ArrayList<String> kentekenLijst = new ArrayList<>();

		// De connectie met de database op
		Connection connection = manager.getConnection();

		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel auto haalt
			String autoQuery = "select kenteken from auto";
			PreparedStatement autoStatement = connection
					.prepareStatement(autoQuery);
			ResultSet autoSet = autoStatement.executeQuery();

			// Zolang er nog gegevens in de tabel staan
			while (autoSet.next()) {
				// De gegevens van een rij
				String kenteken = autoSet.getString("kenteken");

				kentekenLijst.add(kenteken);
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}

		// Sluit de verbinding met de database
		manager.closeConnection();

		// Geeft de lijst met auto's terug
		return kentekenLijst;
	}
	
	public void addNewAuto(Auto auto) {
		// Zet de verbinding op met de database
		Connection connection = manager.getConnection();
		
		// De sql string met de juiste waarden voor de database
		String insertString = "insert into Auto "
				+ "(kenteken, klantnr, merk, model, verzekeringsnr)"
				+ "values "
				+ "(?, ?, ?, ?, ?)";
		
		try {
			// Het statement met de juiste sql string
			PreparedStatement insertStatement = connection.prepareStatement(insertString);
			
			// Meldt de attributen van de planning aan bij het statement
			insertStatement.setString(1, auto.getKenteken());
			insertStatement.setInt(2, auto.getKlantnr());
			insertStatement.setString(3, auto.getMerk());
			insertStatement.setString(4, auto.getModel());
			insertStatement.setInt(5, auto.getVerzekeringsnummer());
			
			// Voert het statement uit
			insertStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();	
	}
}
