package nl.eti1b5.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import nl.eti1b5.database.dao.AutoDao;
import nl.eti1b5.database.dao.KlantDao;
import nl.eti1b5.model.Auto;
import nl.eti1b5.model.Klant;
import nl.eti1b5.view.secretaresse.administratiescherm.AddKlantScherm;

/**
 * De klasse KlantToevoegControl
 * Deze klasse dient als controller voor het toevoegen van een Klant, de addKlant Klasse,
 * @author Groep 3
 * @version 1.0
 *
 */
public class KlantToevoegControl implements EventHandler<ActionEvent> {
	
	private Klant nieuweKlant;
	private AddKlantScherm addKlantScherm;
	/**
	 * Constructor die een addKlantScherm mee krijgt en deze aan de variabele toevoegd.
	 * Ook wordt er een lege Klant gemaakt.
	 * @param addKlantScherm Het scherm waar de klant wordt toegevoegd
	 */
	public KlantToevoegControl(AddKlantScherm addKlantScherm) {
		nieuweKlant = new Klant();
		this.addKlantScherm = addKlantScherm;
	}

	/**
	 * de handler voor de addMonteurButton,
	 * Voegt alle waarden toe aan de lege monteur die vervolgens wordt toegevoegd aan de database.
	 */
	@Override
	public void handle(ActionEvent arg0) {
		nieuweKlant.setNaam(addKlantScherm.getNaam());
		nieuweKlant.setAdres(addKlantScherm.getAdres());
		nieuweKlant.setPostcode(addKlantScherm.getPostcode());
		nieuweKlant.setWoonplaats(addKlantScherm.getWoonplaats());
		nieuweKlant.setTelNr(addKlantScherm.getTelefoonNummer());
		nieuweKlant.setAutoLijst(addKlantScherm.getAutos());
		KlantDao klantDao = new KlantDao();
		klantDao.addKlant(nieuweKlant);
		nieuweKlant.setKlantnr(klantDao.getKlantNr(nieuweKlant));
		AutoDao autoDao = new AutoDao();
		for(Auto auto : nieuweKlant.getAutoLijst()){
			auto.setKlantnr(nieuweKlant.getKlantnr());
			autoDao.addNewAuto(auto);
			System.out.println(auto);
		}
		System.out.println(nieuweKlant);
		addKlantScherm.close();
	}

}
