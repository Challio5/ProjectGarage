package nl.eti1b5.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persoon {

	private StringProperty naam;
	private StringProperty plaats;
	private StringProperty adres;
	private StringProperty telNr;

	public Persoon(String naam, String plaats, String adres, String telNr) {
		this.naam = new SimpleStringProperty(naam);
		this.plaats = new SimpleStringProperty(plaats);
		this.adres = new SimpleStringProperty(adres);
		this.telNr = new SimpleStringProperty(telNr);
	}

	public String getNaam() {
		return naam.get();
	}

	public String getPlaats() {
		return plaats.get();
	}
	
	public String getAdres() {
		return adres.get();
	}
	
	public String getTelNr() {
		return telNr.get();
	}
	
	public void setNaam(String naam) {
		this.naam.set(naam);
	}
	
	public void setPlaats(String plaats) {
		this.plaats.set(plaats);
	}
	
	public void setAdres(String adres) {
		this.adres.set(adres);
	}
	
	public void setTelNr(String telNr) {
		this.telNr.set(telNr);
	}
	
	public StringProperty naamProperty() {
		return naam;
	}
	
	public StringProperty plaatsProperty() {
		return plaats;
	}

	public StringProperty adresProperty() {
		return adres;
	}
	
	public StringProperty telNrProperty() {
		return telNr;
	}
}