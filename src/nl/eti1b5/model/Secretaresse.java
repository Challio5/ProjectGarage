package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Secretaresse extends Gebruiker {

	private IntegerProperty secretaresseNr;

	public Secretaresse(String naam, String plaats, String adres, String postcode, String telNr,
				   int rechten, String wachtwoord, String functie, int werknemerNr, int secretaresseNr) {
		super(naam, plaats, adres, postcode, telNr, wachtwoord, werknemerNr);
		
		this.secretaresseNr = new SimpleIntegerProperty(secretaresseNr);
	}

	public int getSecretaresseNr() {
		return secretaresseNr.get();
	}
	
	public void setSecretaresseNr(int secretaresseNr) {
		this.secretaresseNr.set(secretaresseNr);
	}
	
	public IntegerProperty secretaresseNr() {
		return secretaresseNr;
	}
	
	@Override
	public String toString() {
		return "Gebruiker [secretaresseNummer=" + secretaresseNr.get() + "]";
	}
}
