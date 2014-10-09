package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Auto {

	private StringProperty merk;
	private StringProperty model;
	private StringProperty kenteken;
	private IntegerProperty verzekeringsnummer;

	public Auto(String kenteken, String merk, String model, int verzekeringsnummer) {
		this.merk = new SimpleStringProperty(merk);
		this.model = new SimpleStringProperty(model);
		this.kenteken = new SimpleStringProperty(kenteken);
		this.verzekeringsnummer = new SimpleIntegerProperty(verzekeringsnummer);
	}

	// Getters
	public String getMerk() {
		return merk.get();
	}

	public String getModel() {
		return model.get();
	}
	
	public String getKenteken() {
		return kenteken.get();
	}
	
	public int getVerzekeringsnummer() {
		return verzekeringsnummer.get();
	}
	
	// Setters
	public void setMerk(String merk) {
		this.merk.set(merk);
	}

	public void setModel(String model) {
		this.model.set(model);
	}

	public void setKenteken(String kenteken) {
		this.kenteken.set(kenteken);
	}
	
	public void setVerzekeringsnummer(int verzekeringsnummer) {
		this.verzekeringsnummer.set(verzekeringsnummer);
	}
	
	// Properties
	public StringProperty merkProperty() {
		return merk;
	}
	
	public StringProperty modelProperty() {
		return model;
	}
	
	public StringProperty kentekenProperty() {
		return kenteken;
	}
	
	public IntegerProperty verzekeringsnummerProperty() {
		return verzekeringsnummer;
	}

	@Override
	public String toString() {
		return "Auto [merk=" + merk.get() + ", model=" + model.get() + ", kenteken="
				+ kenteken.get() + ", verzekeringsnummer=" + verzekeringsnummer.get() + "]";
	}
}
