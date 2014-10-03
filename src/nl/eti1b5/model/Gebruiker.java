package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Gebruiker extends Persoon {

	private IntegerProperty rechten;
	private StringProperty wachtwoord;
	private StringProperty functie;
	private IntegerProperty werknemerNr;

	public Gebruiker(String naam, String plaats, String adres, String telNr,
			int rechten, String wachtwoord, String functie, int werknemerNr) {
		super(naam, plaats, adres, telNr);
		this.rechten = new SimpleIntegerProperty(rechten);
		this.wachtwoord = new SimpleStringProperty(wachtwoord);
		this.functie = new SimpleStringProperty(functie);
		this.werknemerNr = new SimpleIntegerProperty(werknemerNr);
	}

	public int getRechten() {
		return rechten.get();
	}

	public String getWachtwoord() {
		return wachtwoord.get();
	}

	public String getFunctie() {
		return functie.get();
	}

	public int getWerknemerNr() {
		return werknemerNr.get();
	}

	public void setRechten(int rechten) {
		this.rechten.set(rechten);
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord.set(wachtwoord);
	}

	public void setFunctie(String functie) {
		this.functie.set(functie);
	}

	public void setWerknemerNr(int werknemerNr) {
		this.werknemerNr.set(werknemerNr);
	}
	
	public IntegerProperty rechtenProperty() {
		return rechten;
	}
	
	public StringProperty wachtwoordProperty() {
		return wachtwoord;
	}

	public StringProperty functieProperty() {
		return functie;
	}
	
	public IntegerProperty werknemerNrProperty() {
		return werknemerNr;
	}
}
