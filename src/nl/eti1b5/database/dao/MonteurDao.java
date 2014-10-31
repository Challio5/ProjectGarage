package nl.eti1b5.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;

import nl.eti1b5.database.DatabaseManager;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Planning;

/**
 * Data access object wat de monteurs tabel beheert in de database
 * Haalt de gegevens van een monteur op en schrijft ze weg naar de database
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class MonteurDao {
	// Databasemanager met de connectie naar de database
	private DatabaseManager manager;
	
	/**
	 * Constructor voor het ophalen van de singleton instantie van de databasemanager
	 */
	public MonteurDao() {
		manager = DatabaseManager.getInstance();
	}
	
	/**
	 * Constructor voor het meegegeven van de singleton instantie van de databasemanager
	 * @param manager Singleton instantie van de databasemanager
	 */
	public MonteurDao(DatabaseManager manager) {
		this.manager = manager;
	}
	
	public ArrayList<Monteur> getMonteurs() {
		// Lijst met de resultaten van de query
		ArrayList<Monteur> monteurLijst = new ArrayList<>();
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel monteur haalt
			String monteurQuery = "select * from monteur";
			PreparedStatement monteurStatement = connection.prepareStatement(monteurQuery);
			ResultSet monteurSet = monteurStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(monteurSet.next()) {
				// De gegevens van een rij
				int werknemerNummer = monteurSet.getInt("Werknemernr");
				String naam = monteurSet.getString("Naam");
				String adres = monteurSet.getString("Adres");
				String postcode = monteurSet.getString("Postcode");
				String woonplaats = monteurSet.getString("Woonplaats");
				String telNr = monteurSet.getString("Telnr");
				String wachtwoord = monteurSet.getString("Wachtwoord");
				String specialiteit = monteurSet.getString("Specialiteit");
				
				ArrayList<String> beschikbaarheidsLijst = new ArrayList<>();
				
				// Query die de bijbehorende beschikbaarheid uit de koppetabel haalt
				String beschikbaarheidQuery = "select beschikbaarheid from monteurbeschikbaarheid"
						+ " where werknemernr = ?";
				PreparedStatement beschikbaarheidStatement = connection.prepareStatement(beschikbaarheidQuery);
				beschikbaarheidStatement.setInt(1, werknemerNummer);
				ResultSet beschikbaarheidSet = beschikbaarheidStatement.executeQuery();
				
				while(beschikbaarheidSet.next()) {
					String beschikbaarheid = beschikbaarheidSet.getString("beschikbaarheid");
					beschikbaarheidsLijst.add(beschikbaarheid);
				}
				
				ArrayList<Integer> ReparatieLijst = new ArrayList<>();
				
				// Query die de bijbehorende reparaties uit de koppetabel haalt
				String ReparatiesQuery = "select Reparatienr from Planning"
						+ " where werknemernr = ?";
				PreparedStatement ReparatieStatement = connection.prepareStatement(ReparatiesQuery);
				ReparatieStatement.setInt(1, werknemerNummer);
				ResultSet ReparatieSet = ReparatieStatement.executeQuery();
				
				while(ReparatieSet.next()) {
					int Reparatie = Integer.parseInt(ReparatieSet.getString("Reparatienr"));
					ReparatieLijst.add(Reparatie);
				}
				
				monteurLijst.add(new Monteur(werknemerNummer, naam, adres, postcode, woonplaats, telNr,
								 wachtwoord, specialiteit, beschikbaarheidsLijst, ReparatieLijst));
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();
		
		// Geeft de lijst met monteurs terug
		return monteurLijst;
	}
	
	public Monteur getMonteur(int werknemernr) {
		Monteur monteur = null;
		
		// De connectie met de database op
		Connection connection = manager.getConnection();
		
		// De te execturen sql querys
		try {
			// Query die alle gegevens uit de tabel reparatie haalt
			String monteurQuery = "select * from monteur where werknemernr = ?";
			PreparedStatement monteurStatement = connection.prepareStatement(monteurQuery);
			monteurStatement.setInt(1, werknemernr);
			
			ResultSet monteurSet = monteurStatement.executeQuery();
			
			// Zolang er nog gegevens in de tabel staan
			while(monteurSet.next()) {
				// De gegevens van een rij
				int werknemerNummer = monteurSet.getInt("Werknemernr");
				String naam = monteurSet.getString("Naam");
				String adres = monteurSet.getString("Adres");
				String postcode = monteurSet.getString("Postcode");
				String woonplaats = monteurSet.getString("Woonplaats");
				String telNr = monteurSet.getString("Telnr");
				String wachtwoord = monteurSet.getString("Wachtwoord");
				String specialiteit = monteurSet.getString("Specialiteit");
				
				ArrayList<String> beschikbaarheidsLijst = new ArrayList<>();
				ArrayList<Integer> reparatieLijst = new ArrayList<>();
				
				monteur = new Monteur(werknemerNummer, naam, adres, postcode, woonplaats, telNr,
						 wachtwoord, specialiteit, beschikbaarheidsLijst, reparatieLijst);
			}
		} catch (SQLException e) {
			System.err.println("Kan het statement niet uitvoeren");
			e.printStackTrace();
		}
		return monteur;
	}

	public ArrayList<Monteur> getMonteurs(LocalDateTime datumTijd) {
		// De lijst met resultaten
		ArrayList<Monteur> monteursLijst = new ArrayList<>();
		
		// De lijst met ingeplande monteurs voor die datum
		PlanningDao planningDao = new PlanningDao(manager);
		ArrayList<Planning> planningsLijst = planningDao.getPlanning(datumTijd);
		ArrayList<Integer> werknemernummerLijst = new ArrayList<>();
		for(Planning planning : planningsLijst) {
			werknemernummerLijst.add(planning.getWerknemer().getWerknemerNr());
		}
		
		// String voor het omrekeken van de datum naar een beschikbaarheidscode
		String dagcode = "";
		int dagdeelcode = 0;
		
		// Array met ochtend en middag uren
		int[] ochtend = { 9, 10, 11, 12 };
		int[] middag = { 13, 14, 15, 16, 17 };

		// Checkt de dag en zet deze om naar een beschikbaarheidscode
		// Gebruikt de eerste twee letters van de dag in het engels
		DayOfWeek dag = datumTijd.getDayOfWeek();
		
		switch (dag) {
		case MONDAY:
			dagcode = "MO";
			break;
		case TUESDAY:
			dagcode = "TU";
			break;
		case WEDNESDAY:
			dagcode = "WE";
			break;
		case THURSDAY:
			dagcode = "TH";
			break;
		case FRIDAY:
			dagcode = "FR";
			break;
		case SATURDAY:
			dagcode = "SA";
			break;
		case SUNDAY:
			dagcode = "SU";
			break;
		}
		
		// Checkt het uur en kijkt of het match met de ochtend of middag
		// Bij de ochtend wordt een 1 toegevoegd, bij de middag een 2
		int uur = datumTijd.getHour();

		for (int ochtenduur : ochtend) {
			if (uur == ochtenduur) {
				dagdeelcode = 1;
			}
		}

		for (int middaguur : middag) {
			if (uur == middaguur) {
				dagdeelcode = 2;
			}
		}
		
		Connection connection = manager.getConnection();

		try {
			// Sql statement voor het checken van de beschikbaarheid
			// Koppelt op basis van de beschikbaarheid er een monteur aan vast
			String planningsString = "select * from monteurbeschikbaarheid "
					+ "natural join monteur "
					+ "where beschikbaarheid = ? or beschikbaarheid = ? ";
			PreparedStatement planningStatement = connection.prepareStatement(planningsString);
			planningStatement.setString(1, dagcode + dagdeelcode);
			planningStatement.setString(2, dagcode + 3);
			
			ResultSet monteurSet = planningStatement.executeQuery();
			while(monteurSet.next()) {
				// De gegevens van de tuple
				int werknemerNummer = monteurSet.getInt("Werknemernr");
				String naam = monteurSet.getString("Naam");
				String adres = monteurSet.getString("Adres");
				String postcode = monteurSet.getString("Postcode");
				String woonplaats = monteurSet.getString("Woonplaats");
				String telNr = monteurSet.getString("Telnr");
				String wachtwoord = monteurSet.getString("Wachtwoord");
				String specialiteit = monteurSet.getString("Specialiteit");
				
				// De monteur met de juiste gegevens
				Monteur monteur = new Monteur();
				monteur.setWerknemerNr(werknemerNummer);
				monteur.setNaam(naam);
				monteur.setAdres(adres);
				monteur.setPostcode(postcode);
				monteur.setwoonplaats(woonplaats);
				monteur.setTelNr(telNr);
				monteur.setWachtwoord(wachtwoord);
				monteur.setSpecialiteit(specialiteit);
				
				// Er wordt gecheckt of de monteur al op de planning staat
				// Als dit niet zo is wordt hij aan de lijst toegevoegd
				if(!werknemernummerLijst.contains(werknemerNummer)) {
					monteursLijst.add(monteur);
				}
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return monteursLijst;
	}
	
	public void addMonteur(Monteur monteur) {
		// Checkt of de monteur al een werknemernummer heeft
		// Als dit zo is dan bestaat hij al in de database en dient hij aangepast te worden
		if(monteur.getWerknemerNr() != 0) {
			this.addExistingMonteur(monteur);
		}
		else {
			this.addNewMonteur(monteur);
		}
	}
	
	private void addNewMonteur(Monteur monteur) {
		// Zet de verbinding op met de database
		Connection connection = manager.getConnection();
		
		// De sql string met de juiste waarden voor de database
		String insertString = "insert into monteur "
				+ "(naam, adres, postcode, woonplaats, TelNr, Wachtwoord, specialiteit) "
				+ "values "
				+ "(?, ?, ?, ?, ?, ?, ?)";
		
		try {
			// Het statement met de juiste sql string
			PreparedStatement insertStatement = connection.prepareStatement(insertString);
			
			// Meldt de attributen van de planning aan bij het statement
			insertStatement.setString(1, monteur.getNaam());
			insertStatement.setString(2, monteur.getAdres());
			insertStatement.setString(3, monteur.getPostcode());
			insertStatement.setString(4, monteur.getWoonplaats());
			insertStatement.setString(5, monteur.getTelNr());
			insertStatement.setString(6, monteur.getWachtwoord());
			insertStatement.setString(7, monteur.getSpecialiteit());
			
			// Voert het statement uit
			insertStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();	
	}
	
	private void addExistingMonteur(Monteur monteur) {
		// Zet de verbinding op met de database
		Connection connection = manager.getConnection();
		
		// De sql string met de juiste waarden voor de database
		String insertString = "update monteur "
				+ "set naam=?, adres=?, postcode=?, woonplaats=?, TelNr=?, Wachtwoord=?, specialiteit=? "
				+ "where werknemernr=?";
		
		try {
			// Het statement met de juiste sql string
			PreparedStatement insertStatement = connection.prepareStatement(insertString);
			
			// Meldt de attributen van de planning aan bij het statement
			insertStatement.setString(1, monteur.getNaam());
			insertStatement.setString(2, monteur.getAdres());
			insertStatement.setString(3, monteur.getPostcode());
			insertStatement.setString(4, monteur.getWoonplaats());
			insertStatement.setString(5, monteur.getTelNr());
			insertStatement.setString(6, monteur.getWachtwoord());
			insertStatement.setString(7, monteur.getSpecialiteit());
			insertStatement.setInt(8, monteur.getWerknemerNr());
			
			// Voert het statement uit
			insertStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Sluit de verbinding met de database
		manager.closeConnection();	
	}
}
