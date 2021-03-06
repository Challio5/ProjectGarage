package nl.eti1b5.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.view.secretaresse.administratiescherm.AddMonteurScherm;
/**
 * De klasse MonteurToevoegControl
 * Deze klasse dient als controller voor het toevoegen van een Monteur, de addMonteur Klasse,
 * @author Groep 3
 * @version 1.0
 *
 */
public class MonteurToevoegControl implements EventHandler<ActionEvent> {
	
	private AddMonteurScherm addMonteurScherm;
	private Monteur newMonteur;

	/**
	 * Constructor die een addMonteurScherm mee krijgt en deze aan de variabele toevoegd.
	 * Ook wordt er een lege monteur gemaakt.
	 * @param addMonteurScherm Het scherm wat deze controller beheert
	 */
	public MonteurToevoegControl(AddMonteurScherm addMonteurScherm) {
		this.addMonteurScherm = addMonteurScherm;
		newMonteur = new Monteur();
	}

	/**
	 * de handler voor de addMonteurButton,
	 * Voegt alle waarden toe aan de lege monteur die vervolgens wordt toegevoegd aan de database.
	 */
	@Override
	public void handle(ActionEvent arg0) {
		newMonteur.setNaam(addMonteurScherm.getNaam());
		newMonteur.setAdres(addMonteurScherm.getAdres());
		newMonteur.setPostcode(addMonteurScherm.getPostcode());
		newMonteur.setWoonplaats(addMonteurScherm.getWoonplaats());
		newMonteur.setTelNr(addMonteurScherm.getTelefoonNummer());
		newMonteur.setWachtwoord(addMonteurScherm.getWachtwoord());
		newMonteur.setSpecialiteit(addMonteurScherm.getSpecialiteit());
		
		MonteurDao monteurdao = new MonteurDao();
		monteurdao.addMonteur(newMonteur);
		addMonteurScherm.close();		
	}

}
