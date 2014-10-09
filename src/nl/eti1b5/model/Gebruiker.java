package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Gebruiker extends Persoon {

	private StringProperty wachtwoord;
	private IntegerProperty werknemerNr;

	public Gebruiker(String naam, String plaats, String adres, String postcode, String telNr,
					 String wachtwoord, int werknemerNr) {
		super(naam, plaats, adres, postcode, telNr);
		this.wachtwoord = new SimpleStringProperty(wachtwoord);
		this.werknemerNr = new SimpleIntegerProperty(werknemerNr);
	}

	public String getWachtwoord() {
		return wachtwoord.get();
	}

	public int getWerknemerNr() {
		return werknemerNr.get();
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord.set(wachtwoord);
	}

	public void setWerknemerNr(int werknemerNr) {
		this.werknemerNr.set(werknemerNr);
	}
	
	public StringProperty wachtwoordProperty() {
		return wachtwoord;
	}
	
	public IntegerProperty werknemerNrProperty() {
		return werknemerNr;
	}

	@Override
	public String toString() {
		return "Gebruiker [wachtwoord=" + wachtwoord.get()
				+ ", werknemerNr=" + werknemerNr.get() + 
				super.toString() + "]";
	}
}
