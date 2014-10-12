package nl.eti1b5.model;

import java.sql.Date;
import java.util.ArrayList;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reparatie {

	private IntegerProperty reparatieNummer;
	private StringProperty kenteken;
	private ObjectProperty<Omschrijving> omschrijving;
	private ObjectProperty<Date> beginTijd;
	private ObjectProperty<Date> eindTijd;
	private BooleanProperty reparatieStatus;
	private BooleanProperty betaalStatus;
	private ArrayList<Voorraad> materialenLijst;

	public Reparatie(int reparatieNummer, String kenteken, 
			Omschrijving omschrijving, Date beginTijd, Date eindTijd,
			boolean reparatieStatus, boolean betaalStatus,
			ArrayList<Voorraad> materialenLijst) {
		this.reparatieNummer = new SimpleIntegerProperty(reparatieNummer);
		this.kenteken = new SimpleStringProperty(kenteken);
		this.omschrijving = new SimpleObjectProperty<>(omschrijving);
		this.beginTijd = new SimpleObjectProperty<>(beginTijd);
		this.eindTijd = new SimpleObjectProperty<>(eindTijd);
		this.reparatieStatus = new SimpleBooleanProperty(reparatieStatus);
		this.betaalStatus = new SimpleBooleanProperty(betaalStatus);
		this.materialenLijst = materialenLijst;
	}

	// Getters
	public int getReparatieNummer() {
		return reparatieNummer.get();
	}
	
	public String getKenteken() {
		return kenteken.get();
	}
	
	public Omschrijving getOmschrijving() {
		return omschrijving.get();
	}
	
	public Date getBeginTijd() {
		return beginTijd.get();
	}
	
	public Date getEindTijd() {
		return eindTijd.get();
	}
	
	public boolean getReparatieStatus() {
		return reparatieStatus.get();
	}
	
	public boolean getBetaalStatus() {
		return betaalStatus.get();
	}
	
	public ArrayList<Voorraad> getMaterialenLijst() {
		return materialenLijst;
	}
	
	// Setters
	public void setReparatieNummer(int reparatieNummer) {
		this.reparatieNummer.set(reparatieNummer);
	}
	
	public void setKenteken(String kentekenNummer) {
		this.kenteken.set(kentekenNummer);
	}
	
	public void setOmschrijving(Omschrijving omschrijving) {
		this.omschrijving.set(omschrijving);
	}
	
	public void setBeginTijd(Date begintTijd) {
		this.beginTijd.set(begintTijd);
	}
	
	public void setEindTijd(Date eindTijd) {
		this.eindTijd.set(eindTijd);
	}
	
	public void setMaterialenLijst(ArrayList<Voorraad> materialenLijst) {
		this.materialenLijst = materialenLijst;
	}
	
	// Properties
	public IntegerProperty reparatieNummerProperty() {
		return this.reparatieNummer;
	}
	
	public StringProperty kentekenProperty() {
		return this.kenteken;
	}
	
	public ObjectProperty<Omschrijving> omschrijvingProperty() {
		return this.omschrijving;
	}
	
	public ObjectProperty<Date> beginTijdProperty() {
		return this.beginTijd;
	}
	
	public ObjectProperty<Date> eindTijdProperty() {
		return this.eindTijd;
	}
	
	public BooleanProperty reparatieStatus() {
		return this.reparatieStatus;
	}
	
	public BooleanProperty betaalStatus() {
		return this.betaalStatus;
	}

	@Override
	public String toString() {
		String materialenString = "";
		for(Voorraad materiaal : materialenLijst) materialenString += materiaal + ", ";
		return "Reparatie [reparatieNummer=" + reparatieNummer.get() + ", kenteken="
				+ kenteken.get() + ", omschrijving=" + omschrijving.get() + ", beginTijd="
				+ beginTijd.get() + ", eindTijd=" + eindTijd.get() + ", reparatieStatus="
				+ reparatieStatus.get() + ", betaalStatus=" + betaalStatus.get()
			    + ", materialenLijst=" + materialenString + "]";
	}
}