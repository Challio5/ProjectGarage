package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
				Timestamp begintijd = planningsSet.getTimestamp("begintijd");
				Timestamp eindtijd = planningsSet.getTimestamp("eindtijd");
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
		
		manager.closeConnection();
		
		return planningsLijst;
	}
	
	/**
	 * Methode voor het opvragen van een geplande reparatie op basis van de tijd
	 * @param beginTijd De tijd waarop de reparaties gepland zijn
	 * @return De volledige planning van de garage
	 */
	public ArrayList<Planning> getPlanning(LocalDateTime datumTijd) {
		ArrayList<Planning> planningsLijst = new ArrayList<>();
		Connection connection = manager.getConnection();
		
		String planningsQuery = "select * from planning "
				+ "where ? between begintijd and eindtijd";
		
		try {
			PreparedStatement planningsStatement = connection.prepareStatement(planningsQuery);
			planningsStatement.setTimestamp(1, Timestamp.valueOf(datumTijd));
			ResultSet planningsSet = planningsStatement.executeQuery();
			
			while(planningsSet.next()) {
				// Gegevens van planning
				Timestamp begintijd = planningsSet.getTimestamp("begintijd");
				Timestamp eindtijd = planningsSet.getTimestamp("eindtijd");
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
		
		manager.closeConnection();
		
		return planningsLijst;
	}
	
	/**
	 * Methode voor het opvragen van een geplande reparatie op basis van de tijd
	 * @param beginTijd De tijd waarop de reparaties gepland zijn
	 * @return De volledige planning van de garage
	 */
	public ArrayList<Planning> getPlanning(LocalDate datum) {
		ArrayList<Planning> planningsLijst = new ArrayList<>();
		Connection connection = manager.getConnection();
		
		String planningsQuery = "select * from planning "
				+ "where date(begintijd) = ? or date(eindtijd) = ?";
		
		try {
			PreparedStatement planningsStatement = connection.prepareStatement(planningsQuery);
			planningsStatement.setDate(1, Date.valueOf(datum));
			planningsStatement.setDate(2, Date.valueOf(datum));
			ResultSet planningsSet = planningsStatement.executeQuery();
			
			while(planningsSet.next()) {
				// Gegevens van planning
				Timestamp begintijd = planningsSet.getTimestamp("begintijd");
				Timestamp eindtijd = planningsSet.getTimestamp("eindtijd");
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
		
		manager.closeConnection();
		
		return planningsLijst;
	}
	
	/**
	 * Methode voor het wegschrijven van een planningsobject naar de database
	 * @param planning Het weg te schrijven planningsobject
	 */
	public void addPlanning(Planning planning) {
		// Zet de verbinding op met de database
		Connection connection = manager.getConnection();
		
		// De sql string met de juiste waarden voor de database
		String insertString = "insert into planning "
				+ "(begintijd, eindtijd, werknemernr, reparatienr) "
				+ "values"
				+ "(?, ?, ?, ?)";
		
		try {
			// Het statement met de juiste sql string
			PreparedStatement insertStatement = connection.prepareStatement(insertString);
			
			// Meldt de attributen van de planning aan bij het statement
			insertStatement.setTimestamp(1, planning.getBeginTijd());
			insertStatement.setTimestamp(2, planning.getEindTijd());
			insertStatement.setInt(3, planning.getWerknemer().getWerknemerNr());
			insertStatement.setInt(4, planning.getReparatie().getReparatieNummer());
			
			// Voert het statement uit
			insertStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();	
	}
}
