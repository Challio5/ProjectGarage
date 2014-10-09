package nl.eti1b5.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Persoon {

	private StringProperty naam;
	private StringProperty woonplaats;
	private StringProperty adres;
	private StringProperty postcode;
	private StringProperty telNr;

	public Persoon(String naam, String woonplaats, String adres, String postcode, String telNr) {
		this.naam = new SimpleStringProperty(naam);
		this.woonplaats = new SimpleStringProperty(woonplaats);
		this.adres = new SimpleStringProperty(adres);
		this.postcode = new SimpleStringProperty(postcode);
		this.telNr = new SimpleStringProperty(telNr);
	}

	public String getNaam() {
		return naam.get();
	}

	public String getWoonplaats() {
		return woonplaats.get();
	}
	
	public String getAdres() {
		return adres.get();
	}
	
	public String getPostcode() {
		return postcode.get();
	}
	
	public String getTelNr() {
		return telNr.get();
	}
	
	public void setNaam(String naam) {
		this.naam.set(naam);
	}
	
	public void setwoonplaats(String woonplaats) {
		this.woonplaats.set(woonplaats);
	}
	
	public void setAdres(String adres) {
		this.adres.set(adres);
	}
	
	public void setPostcode(String postcode){
		this.postcode.set(postcode);
	}
	
	public void setTelNr(String telNr) {
		this.telNr.set(telNr);
	}
	
	public StringProperty naamProperty() {
		return naam;
	}
	
	public StringProperty woonplaatsProperty() {
		return woonplaats;
	}

	public StringProperty adresProperty() {
		return adres;
	}
	
	public StringProperty postcodeProperty() {
		return postcode;
	}
	
	public StringProperty telNrProperty() {
		return telNr;
	}

	@Override
	public String toString() {
		return "Persoon [naam=" + naam.get() + ", woonplaats=" + woonplaats.get() + ", adres="
				+ adres.get() + ", postcode=" + postcode.get() + ", telNr=" + telNr.get() + "]";
	}
}
