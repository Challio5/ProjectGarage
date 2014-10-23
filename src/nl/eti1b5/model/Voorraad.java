package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;

public class Voorraad {
	
	private Materiaal materiaal;
	private IntegerProperty aantal;
	
	public Voorraad(Materiaal materiaal, IntegerProperty aantal){
		this.materiaal = materiaal;
		this.aantal = aantal;
	}
	
	public Materiaal getMateriaal(){
		return materiaal;
	}
	
	public void setMateriaal(Materiaal materiaal){
		this.materiaal = materiaal;
	}
	
	public IntegerProperty getAantal(){
		return aantal;
	}
	
	public void setAantal(IntegerProperty aantal){
		this.aantal = aantal;
	}
}
