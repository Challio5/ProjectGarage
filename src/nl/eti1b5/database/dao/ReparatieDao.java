package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Reparatie;

public class ReparatieDao {
	private DatabaseManager manager;
	
	public ReparatieDao() {
		manager = DatabaseManager.getInstance();
	}
	
	public ArrayList<Reparatie> getReparatie(int reparatieNummer) {
		ArrayList<Reparatie> reparatieLijst = new ArrayList<>();
		
		Connection connection = manager.getConnection();
		String query = "select ? from garage";
		
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, reparatieNummer);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reparatieLijst;
	}
}
