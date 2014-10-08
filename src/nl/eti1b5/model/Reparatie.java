package nl.eti1b5.model;

import java.sql.Date;
import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reparatie {

	private IntegerProperty reparatieNummer;
	private IntegerProperty klantNummer;
	private IntegerProperty werknemerNummer;
	private StringProperty kenteken;
	private StringProperty reparatie;
	private ObjectProperty<Date> beginTijd;
	private ObjectProperty<Date> eindTijd;
	private ArrayList<Voorraad> materialenLijst;
	private StringProperty status;

	public Reparatie(int reparatieNummer, int klantNummer, int werknemerNummer, String kenteken, 
			String reparatie, Date beginTijd, Date eindTijd, ArrayList<Voorraad> materialenLijst, 
			String status) {
		this.reparatieNummer = new SimpleIntegerProperty(reparatieNummer);
		this.klantNummer = new SimpleIntegerProperty(klantNummer);
		this.werknemerNummer = new SimpleIntegerProperty(werknemerNummer);
		this.kenteken = new SimpleStringProperty(kenteken);
		this.reparatie = new SimpleStringProperty(reparatie);
		this.beginTijd = new SimpleObjectProperty<>(beginTijd);
		this.eindTijd = new SimpleObjectProperty<>(eindTijd);
		this.materialenLijst = materialenLijst;
		this.status = new SimpleStringProperty(status);
	}

	// Getters
	public int getReparatieNummer() {
		return reparatieNummer.get();
	}

	public int getKlantNummer() {
		return klantNummer.get();
	}
	
	public int getWerknemerNummer() {
		return werknemerNummer.get();
	}
	
	public String getKenteken() {
		return kenteken.get();
	}
	
	public String getReparatie() {
		return reparatie.get();
	}
	
	public Date getBeginTijd() {
		return beginTijd.get();
	}
	
	public Date getEindTijd() {
		return eindTijd.get();
	}
	
	public ArrayList<Voorraad> getMaterialenLijst() {
		return materialenLijst;
	}
	
	public String getStatus() {
		return status.get();
	}
	
	// Setters
	public void setReparatieNummer(int reparatieNummer) {
		this.reparatieNummer.set(reparatieNummer);
	}

	public void setKlantNummer(int klantNummer) {
		this.klantNummer.set(klantNummer);
	}
	
	public void setWerknemerNummer(int werknemerNummer) {
		this.werknemerNummer.set(werknemerNummer);
	}
	
	public void setKenteken(String kentekenNummer) {
		this.kenteken.set(kentekenNummer);
	}
	
	public void setReparatie(String reparatie) {
		this.reparatie.set(reparatie);
	}
	
	public void setBeginTijd(Date beginTijd) {
		this.setBeginTijd(beginTijd);
	}
	
	public void setEindTijd(Date eindTijd) {
		this.eindTijd.set(eindTijd);
	}
	
	public void setMaterialenLijst(ArrayList<Voorraad> materialenLijst) {
		this.materialenLijst = materialenLijst;
	}
	
	public void setStatus(String status) {
		this.status.set(status);
	}
	
	// Properties
	public IntegerProperty reparatieNummerProperty() {
		return this.reparatieNummer;
	}
	
	public IntegerProperty klantNummerProperty() {
		return this.klantNummer;
	}
	
	public IntegerProperty werknemerNummerProperty() {
		return this.werknemerNummer;
	}
	
	public StringProperty kentekenProperty() {
		return this.kenteken;
	}
	
	public StringProperty reparatieProperty() {
		return this.reparatie;
	}
	
	public ObjectProperty<Date> beginTijdProperty() {
		return this.beginTijd;
	}
	
	public ObjectProperty<Date> eindTijdProperty() {
		return this.eindTijd;
	}
	
	public StringProperty statusProperty() {
		return this.status;
	}
}
