package nl.eti1b5.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Voorraad {

	private IntegerProperty naam;
	private IntegerProperty materiaalnr;
	private DoubleProperty prijs;
	private IntegerProperty aantal;
	private ObjectProperty<Reparatie> reparatie;

	public Voorraad(int naam, int materiaalnr, double prijs, int aantal,
			Reparatie reparatie) {
		this.naam = new SimpleIntegerProperty(naam);
		this.materiaalnr = new SimpleIntegerProperty(materiaalnr);
		this.prijs = new SimpleDoubleProperty(prijs);
		this.aantal = new SimpleIntegerProperty(aantal);
		this.reparatie = new SimpleObjectProperty<>(reparatie);
	}

	public int getNaam() {
		return naam.get();
	}

	public int getMateriaalnr() {
		return materiaalnr.get();
	}

	public double getPrijs() {
		return prijs.get();
	}

	public int getAantal() {
		return aantal.get();
	}

	public Reparatie getReparatie() {
		return reparatie.get();
	}

	public void setNaam(int naam) {
		this.naam.set(naam);
	}

	public void setMateriaalnr(int materiaalnr) {
		this.materiaalnr.set(materiaalnr);
	}

	public void setPrijs(double prijs) {
		this.prijs.set(prijs);
	}

	public void setAantal(int aantal) {
		this.aantal.set(aantal);
	}

	public void setReparatie(Reparatie reparatie) {
		this.reparatie.set(reparatie);
	}
	
	public IntegerProperty naamProperty() {
		return naam;
	}
	
	public IntegerProperty materiaalNrProperty() {
		return materiaalnr;
	}
	
	public DoubleProperty prijsProperty() {
		return prijs;
	}
	
	public IntegerProperty aantalProperty() {
		return aantal;
	}
	
	public ObjectProperty<Reparatie> reparatieProperty() {
		return reparatie;
	}
}
