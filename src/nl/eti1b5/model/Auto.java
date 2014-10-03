package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Auto {

	private StringProperty merk;
	private IntegerProperty schade;
	private StringProperty kenteken;

	public Auto(String merk, int schade, String kenteken) {
		this.merk = new SimpleStringProperty(merk);
		this.schade = new SimpleIntegerProperty(schade);
		this.kenteken = new SimpleStringProperty(kenteken);
	}

	public String getMerk() {
		return merk.get();
	}

	public int getSchade() {
		return schade.get();
	}

	public String getKenteken() {
		return kenteken.get();
	}

	public void setMerk(String merk) {
		this.merk.set(merk);
	}

	public void setSchade(int schade) {
		this.schade.set(schade);
	}

	public void setKenteken(String kenteken) {
		this.kenteken.set(kenteken);
	}
	
	public StringProperty merkProperty() {
		return merk;
	}
	
	public IntegerProperty schadeProperty() {
		return schade;
	}
	
	public StringProperty kentekenProperty() {
		return kenteken;
	}
}
