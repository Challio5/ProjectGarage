package nl.eti1b5.model;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Monteur extends Gebruiker {

	private StringProperty specialiteit;
	private ArrayList<String> beschikbaarheidsLijst;

	public Monteur(int werknemerNr, String naam, String woonplaats, String adres, String postcode, String telNr,
				   String wachtwoord, String specialiteit, ArrayList<String> beschikbaarheidsLijst) {
		super(werknemerNr, naam, woonplaats, adres, postcode, telNr, wachtwoord);
		
		this.specialiteit = new SimpleStringProperty(specialiteit);
		this.beschikbaarheidsLijst = beschikbaarheidsLijst;
	}

	public String getSpecialiteit() {
		return specialiteit.get();
	}
	
	public ArrayList<String> getBeschikbaarheid() {
		return beschikbaarheidsLijst;
	}
	
	public void setSpecialiteit(String specialiteit) {
		this.specialiteit.set(specialiteit);
	}
	
	public void setBeschikbaarheid(ArrayList<String> beschikbaarheid) {
		this.beschikbaarheidsLijst = beschikbaarheid;
	}
	
	public StringProperty specialiteitProperty() {
		return specialiteit;
	}
	
	@Override
	public String toString() {
		String beschikbaarheidsString = "";
		for(String beschikbaarheid : beschikbaarheidsLijst) beschikbaarheidsString += beschikbaarheid + ", ";
		return "Gebruiker [naam=" + getNaam() + ", woonplaats=" + getWoonplaats() + ", adres=" + getAdres() + ", postcode=" + getPostcode()
						   + ", telNr=" + getTelNr() + ", wachtwoord=" + getWachtwoord() + ", werknemerNr=" + getWerknemerNr()
						   + ", specialiteit=" + specialiteit.get() + ", beschikbaarheid=" + beschikbaarheidsString + "]";
	}
}
