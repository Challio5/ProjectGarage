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

public class PlanningDao {
	private DatabaseManager manager;
	
	public PlanningDao() {
		manager = DatabaseManager.getInstance();
	}
	
	public PlanningDao(DatabaseManager manager) {
		this.manager = manager;
	}
	
	public ArrayList<Planning> getPlanning() {
		ArrayList<Planning> planningsLijst = new ArrayList<>();
		Connection connection = manager.getConnection();
		
		String planningsQuery = "select * from planning";
		
		PreparedStatement planningsStatement;
		try {
			planningsStatement = connection.prepareStatement(planningsQuery);
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
