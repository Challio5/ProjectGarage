package nl.eti1b5.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.view.secretaresse.administratiescherm.AddMonteurScherm;

public class MonteurToevoegControl implements EventHandler<ActionEvent> {
	
	private AddMonteurScherm addMonteurScherm;
	private Monteur newMonteur;

	public MonteurToevoegControl(AddMonteurScherm addMonteurScherm) {
		this.addMonteurScherm = addMonteurScherm;
		newMonteur = new Monteur();
	}

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
