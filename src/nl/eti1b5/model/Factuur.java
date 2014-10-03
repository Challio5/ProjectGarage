package nl.eti1b5.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Factuur {

	private IntegerProperty factuurNr;
	private IntegerProperty prijs;
	private ObjectProperty<Date> datum;

	public Factuur(int factuurNr, int prijs, Date datum) {
		this.factuurNr = new SimpleIntegerProperty(factuurNr);
		this.prijs = new SimpleIntegerProperty(prijs);
		this.datum = new SimpleObjectProperty<>(datum);
	}

	public int getFactuurNr() {
		return factuurNr.get();
	}

	public int getPrijs() {
		return prijs.get();
	}

	public Date getDatum() {
		return datum.get();
	}

	public void setFactuurNr(int factuurNr) {
		this.factuurNr.set(factuurNr);
	}

	public void setPrijs(int prijs) {
		this.prijs.set(prijs);
	}

	public void setDatum(Date datum) {
		this.datum.set(datum);
	}
	
	public IntegerProperty factuurNrProperty() {
		return factuurNr;
	}
	
	public IntegerProperty prijsProperty() {
		return prijs;
	}
	
	public ObjectProperty<Date> datumProperty() {
		return datum;
	}
}
