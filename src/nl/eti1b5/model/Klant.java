package nl.eti1b5.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Klant extends Persoon {

	private IntegerProperty klantNr;
	private List<Auto> autoLijst;

	public Klant(int klantNr, String naam, String adres, String postcode, String plaats, String telNr) {
		super(naam, adres, postcode, plaats, telNr);
		
		this.klantNr = new SimpleIntegerProperty(klantNr);
		this.autoLijst = new ArrayList<>();
	}

	// Getters
	public int getKlantnr() {
		return klantNr.get();
	}

	public List<Auto> getAutoLijst() {
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
		String autoString = autoLijst.stream()
		.map(Object::toString)
		.collect(Collectors.joining(", "));
		for(Auto auto : autoLijst) autoString += auto + ", ";
		return "Klant [klantNr=" + klantNr.get() + ", autoLijst=" + autoString + "]";
	}
}
