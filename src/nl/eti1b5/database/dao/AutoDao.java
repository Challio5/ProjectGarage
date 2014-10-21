package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Auto;

public class AutoDao {

	private DatabaseManager manager;

	public AutoDao() {
		manager = DatabaseManager.getInstance();
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
