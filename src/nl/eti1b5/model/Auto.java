package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Auto {

	private StringProperty kenteken;
	private IntegerProperty klantnr;
	private StringProperty merk;
	private StringProperty model;
	private IntegerProperty verzekeringsnummer;

	public Auto(String kenteken, int klantnr, String merk, String model, int verzekeringsnummer) {
		this.merk = new SimpleStringProperty(merk);
		this.klantnr = new SimpleIntegerProperty(klantnr);
		this.model = new SimpleStringProperty(model);
		this.kenteken = new SimpleStringProperty(kenteken);
		this.verzekeringsnummer = new SimpleIntegerProperty(verzekeringsnummer);
	}

	// Getters
	public String getKenteken() {
		return kenteken.get();
	}

	public int getKlantnr() {
		return klantnr.get();
	}
	
	public String getMerk() {
		return merk.get();
	}

	public String getModel() {
		return model.get();
	}
	
	public int getVerzekeringsnummer() {
		return verzekeringsnummer.get();
	}
	
	// Setters
	public void setKenteken(String kenteken) {
		this.kenteken.set(kenteken);
	}
	
	public void setKlantnr(int klantnr) {
		this.klantnr.set(klantnr);
	}
	
	public void setMerk(String merk) {
		this.merk.set(merk);
	}

	public void setModel(String model) {
		this.model.set(model);
	}

	public void setVerzekeringsnummer(int verzekeringsnummer) {
		this.verzekeringsnummer.set(verzekeringsnummer);
	}
	
	// Properties
	public StringProperty kentekenProperty() {
		return kenteken;
	}
	
	public IntegerProperty klantnrProperty() {
		return klantnr;
	}
	
	public StringProperty merkProperty() {
		return merk;
	}
	
	public StringProperty modelProperty() {
		return model;
	}
	
	public IntegerProperty verzekeringsnummerProperty() {
		return verzekeringsnummer;
	}

	@Override
	public String toString() {
		return "Auto [kenteken=" + kenteken.get() + ", klantnr=" + klantnr.get()
				+ ", merk=" + merk.get() + ", model=" + model.get()
				+ ", verzekeringsnummer=" + verzekeringsnummer.get() + "]";
	}
}
