package nl.eti1b5.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Voorraad {

	private IntegerProperty materiaalnr;
	private StringProperty naam;
	private DoubleProperty prijs;
	private IntegerProperty aantal;

	public Voorraad(int materiaalnr, String naam, double prijs, int aantal) {
		this.naam = new SimpleStringProperty(naam);
		this.materiaalnr = new SimpleIntegerProperty(materiaalnr);
		this.prijs = new SimpleDoubleProperty(prijs);
		this.aantal = new SimpleIntegerProperty(aantal);
	}

	// Getters
	public int getMateriaalnr() {
		return materiaalnr.get();
	}

	public String getNaam() {
		return naam.get();
	}
	
	public double getPrijs() {
		return prijs.get();
	}

	public int getAantal() {
		return aantal.get();
	}

	// Setters
	public void setMateriaalnr(int materiaalnr) {
		this.materiaalnr.set(materiaalnr);
	}
	
	public void setNaam(String naam) {
		this.naam.set(naam);
	}
	
	public void setPrijs(double prijs) {
		this.prijs.set(prijs);
	}

	public void setAantal(int aantal) {
		this.aantal.set(aantal);
	}

	// Properties
	public IntegerProperty materiaalNrProperty() {
		return materiaalnr;
	}
	
	public StringProperty naamProperty() {
		return naam;
	}
	
	public DoubleProperty prijsProperty() {
		return prijs;
	}
	
	public IntegerProperty aantalProperty() {
		return aantal;
	}

	@Override
	public String toString() {
		return "Voorraad [materiaalnr=" + materiaalnr.get() + ", naam=" + naam.get()
				+ ", prijs=" + prijs.get() + ", aantal=" + aantal.get() + "]";
	}
}
