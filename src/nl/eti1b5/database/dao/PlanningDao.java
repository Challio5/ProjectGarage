package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Planning;
import nl.eti1b5.model.Reparatie;

/**
 * Data access object wat de planning tabel beheert in de database
 * Haalt de gegevens van een planning op en schrijft ze weg naar de database
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class PlanningDao {
	// Databasemanager met de connectie naar de database
	private DatabaseManager manager;
	
	/**
	 * Constructor voor het ophalen van de singleton instantie van de databasemanager
	 */
	public PlanningDao() {
		manager = DatabaseManager.getInstance();
	}
	
	/**
	 * Constructor voor het meegegeven van de singleton instantie van de databasemanager
	 * @param manager Singleton instantie van de databasemanager
	 */
	public PlanningDao(DatabaseManager manager) {
		this.manager = manager;
	}
	
	/**
	 * Methode voor het opvragen van de volledige planning inclusief bijbehorende monteur en reparatie
	 * @return De volledige planning van de garage
	 */
	public ArrayList<Planning> getPlanning() {
		ArrayList<Planning> planningsLijst = new ArrayList<>();
		Connection connection = manager.getConnection();
		
		String planningsQuery = "select * from planning";
		
		try {
			PreparedStatement planningsStatement = connection.prepareStatement(planningsQuery);
			ResultSet planningsSet = planningsStatement.executeQuery();
			
			while(planningsSet.next()) {
				// Gegevens van planning
				Date begintijd = planningsSet.getDate("begintijd");
				Date eindtijd = planningsSet.getDate("eindtijd");
				int werknemernr = planningsSet.getInt("werknemernr");
				int reparatienr = planningsSet.getInt("reparatienr");
				
				// Gebruikt andere daos voor de gegevens
				MonteurDao monteurDao = new MonteurDao(manager);
				Monteur monteur = monteurDao.getMonteur(werknemernr);
				ReparatieDao reparatieDao = new ReparatieDao(manager);
				Reparatie reparatie = reparatieDao.getReparatie(reparatienr);
				
				planningsLijst.add(new Planning(begintijd, eindtijd, monteur, reparatie));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return planningsLijst;
	}
}
