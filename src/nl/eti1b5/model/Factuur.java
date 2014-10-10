package nl.eti1b5.model;

import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Factuur {

	private IntegerProperty factuurNr;
	private IntegerProperty klantNr;
	private IntegerProperty reparatieNr;
	private IntegerProperty werknemerNr;
	private StringProperty status;
	private ArrayList<Reparatie> reparatieLijst;

	public Factuur(int factuurNr, int klantNr, int reparatieNr, int werknemerNr, String status) {
		this.factuurNr = new SimpleIntegerProperty(factuurNr);
		this.klantNr = new SimpleIntegerProperty(klantNr);;
		this.reparatieNr = new SimpleIntegerProperty(reparatieNr);
		this.werknemerNr = new SimpleIntegerProperty(werknemerNr);
		this.status = new SimpleStringProperty(status);
		reparatieLijst = new ArrayList<>();
	}

	// Getters
	public int getFactuurNr() {
		return factuurNr.get();
	}

	public int getKlantNr() {
		return klantNr.get();
	}
	
	public int getReparatieNr() {
		return reparatieNr.get();
	}
	
	public int getWerknemerNr() {
		return werknemerNr.get();
	}
	
	public String getStatus() {
		return status.get();
	}
	
	public ArrayList<Reparatie> getReparatieLijst() {
		return reparatieLijst;
	}

	// Setters
	public void setFactuurNr(int factuurNr) {
		this.factuurNr.set(factuurNr);
	}

	public void setKlantNr(int klantNr) {
		this.klantNr.set(klantNr);
	}
	
	public void setReparatieNr(int reparatieNr) {
		this.reparatieNr.set(reparatieNr);
	}
	
	public void setWerknemerNr(int werknemerNr) {
		this.werknemerNr.set(werknemerNr);
	}
	
	public void setStatus(String status) {
		this.status.set(status);
	}

	public void setReparatieLijst(ArrayList<Reparatie> reparatielijst) {
		this.reparatieLijst = reparatielijst;
	}
	
	// Properties
	public IntegerProperty factuurNrProperty() {
		return factuurNr;
	}
	
	public IntegerProperty klantNrProperty() {
		return klantNr;
	}
	
	public IntegerProperty reparatieNrProperty() {
		return reparatieNr;
	}
	
	public IntegerProperty werknemerNrProperty() {
		return werknemerNr;
	}
	
	public StringProperty statusProperty() {
		return status;
	}

	@Override
	public String toString() {
		String reparatieString = "";
		for(Reparatie reparatie : reparatieLijst) reparatieString += reparatie + ", ";
		return "Factuur [factuurNr=" + factuurNr + ", klantNr=" + klantNr
				+ ", reparatieNr=" + reparatieNr + ", werknemerNr="
				+ werknemerNr + ", status=" + status + ", reparatieLijst=" + reparatieString + "]";
	}
}
