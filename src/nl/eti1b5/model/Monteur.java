package nl.eti1b5.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Monteur extends Gebruiker {

	private StringProperty specialiteit;
	private StringProperty beschikbaarheid;

	public Monteur(String naam, String plaats, String adres, String postcode, String telNr,
				   int rechten, String wachtwoord, String functie, int werknemerNr, String specialiteit, String beschikbaarheid) {
		super(naam, plaats, adres, postcode, telNr, rechten, wachtwoord, functie, werknemerNr);
		
		this.specialiteit = new SimpleStringProperty(specialiteit);
		this.beschikbaarheid = new SimpleStringProperty(beschikbaarheid);
	}

	public String getSpecialiteit() {
		return specialiteit.get();
	}
	
	public String getBeschikbaarheid() {
		return beschikbaarheid.get();
	}
	
	public void setSpecialiteit(String specialiteit) {
		this.specialiteit.set(specialiteit);
	}
	
	public void setBeschikbaarheid(String beschikbaarheid) {
		this.beschikbaarheid.set(beschikbaarheid);
	}
	
	public StringProperty specialiteitProperty() {
		return specialiteit;
	}
	
	public StringProperty beschikbaarheidProperty() {
		return beschikbaarheid;
	}
	
	@Override
	public String toString() {
		return "Gebruiker [specialiteit=" + specialiteit.get() + ", beschikbaarheid=" + beschikbaarheid.get() + "]";
	}
}
