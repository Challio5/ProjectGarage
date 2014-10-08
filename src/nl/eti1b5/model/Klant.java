package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Klant extends Persoon {

	private IntegerProperty klantNr;
	private StringProperty rekeningNr;

	public Klant(String naam, String plaats, String adres, String telNr,
			int klantNr, String rekeningNr) {
		super(naam, plaats, adres, telNr);
		this.klantNr = new SimpleIntegerProperty(klantNr);
		this.rekeningNr = new SimpleStringProperty(rekeningNr);
	}

	public int getKlantnr() {
		return klantNr.get();
	}

	public String getRekeningNr() {
		return rekeningNr.get();
	}

	public void setKlantnr(int klantnr) {
		this.klantNr.set(klantnr);
	}

	public void setRekeningNr(String rekeningNr) {
		this.rekeningNr.set(rekeningNr);
	}
	
	public IntegerProperty klantNrProperty() {
		return klantNr;
	}
	
	public StringProperty rekeningNrProperty() {
		return rekeningNr;
	}

	@Override
	public String toString() {
		return "Klant [klantNr=" + klantNr + ", rekeningNr=" + rekeningNr
				+ ", toString()=" + super.toString() + "]";
	}
}
