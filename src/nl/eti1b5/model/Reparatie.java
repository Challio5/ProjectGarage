package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Reparatie {

	private IntegerProperty prijs;
	private IntegerProperty kosten;
	private ObjectProperty<Voorraad> benodigdMateriaal;
	private IntegerProperty foto;

	public Reparatie(int prijs, int kosten, Voorraad benodigdMateriaal, int foto) {
		this.prijs = new SimpleIntegerProperty(prijs);
		this.kosten = new SimpleIntegerProperty(kosten);
		this.benodigdMateriaal = new SimpleObjectProperty<>(benodigdMateriaal);
		this.foto = new SimpleIntegerProperty(foto);
	}

	public int getPrijs() {
		return prijs.get();
	}

	public int getKosten() {
		return kosten.get();
	}

	public Voorraad getBenodigdMateriaal() {
		return benodigdMateriaal.get();
	}

	public int getFoto() {
		return foto.get();
	}

	public void setPrijs(int prijs) {
		this.prijs.set(prijs);
	}

	public void setKosten(int kosten) {
		this.kosten.set(kosten);
	}

	public void setBenodigdMateriaal(Voorraad benodigdMateriaal) {
		this.benodigdMateriaal.set(benodigdMateriaal);
	}

	public void setFoto(int foto) {
		this.foto.set(foto);
	}
	
	public IntegerProperty prijsProperty() {
		return prijs;
	}
	
	public IntegerProperty kostenProperty() {
		return kosten;
	}
	
	public ObjectProperty<Voorraad> benodigdMateriaalProperty() {
		return benodigdMateriaal;
	}
	
	public IntegerProperty fotoProperty() {
		return foto;
	}
}
