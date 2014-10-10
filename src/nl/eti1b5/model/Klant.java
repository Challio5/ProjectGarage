package nl.eti1b5.model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Klant extends Persoon {

	private IntegerProperty klantNr;
	private ArrayList<Auto> autoLijst;

	public Klant(String naam, String plaats, String adres, String postcode, String telNr, int klantNr) {
		super(naam, plaats, adres, postcode, telNr);
		
		this.klantNr = new SimpleIntegerProperty(klantNr);
		this.autoLijst = new ArrayList<>();
	}

	// Getters
	public int getKlantnr() {
		return klantNr.get();
	}

	public ArrayList<Auto> getAutoLijst() {
		return autoLijst;
	}
	
	// Setters
	public void setKlantnr(int klantnr) {
		this.klantNr.set(klantnr);
	}

	public void setAutoLijst(ArrayList<Auto> autoLijst) {
		this.autoLijst = autoLijst;
	}
	
	// Propertys
	public IntegerProperty klantNrProperty() {
		return klantNr;
	}

	@Override
	public String toString() {
		String autoString = "";
		for(Auto auto : autoLijst) autoString += auto + ", ";
		return "Klant [klantNr=" + klantNr.get() + ", autoLijst=" + autoString + "]";
	}
}
