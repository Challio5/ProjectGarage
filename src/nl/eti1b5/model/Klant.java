package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Klant extends Persoon {

	private IntegerProperty klantNr;

	public Klant(String naam, String plaats, String adres, String postcode, String telNr, int klantNr) {
		super(naam, plaats, adres, postcode, telNr);
		
		this.klantNr = new SimpleIntegerProperty(klantNr);
	}

	public int getKlantnr() {
		return klantNr.get();
	}

	public void setKlantnr(int klantnr) {
		this.klantNr.set(klantnr);
	}

	public IntegerProperty klantNrProperty() {
		return klantNr;
	}

	@Override
	public String toString() {
		return "Klant [klantNr=" + klantNr.get() + "]";
	}
}
