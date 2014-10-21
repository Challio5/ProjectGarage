package nl.eti1b5.model;

import java.sql.Date;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Planning {
	private ObjectProperty<Date> beginTijd;
	private ObjectProperty<Date> eindTijd;
	private ObjectProperty<Monteur> werknemer;
	private ObjectProperty<Reparatie> reparatie;
	
	public Planning(Date beginTijd, Date eindTijd, Monteur werknemer, Reparatie reparatie) {
		this.beginTijd = new SimpleObjectProperty<>(beginTijd);
		this.eindTijd = new SimpleObjectProperty<>(eindTijd);
		this.werknemer = new SimpleObjectProperty<>(werknemer);
		this.reparatie = new SimpleObjectProperty<>(reparatie);
	}

	// Getters
	public Date getBeginTijd() {
		return beginTijd.get();
	}

	public Date getEindTijd() {
		return eindTijd.get();
	}

	public Monteur getWerknemer() {
		return werknemer.get();
	}

	public Reparatie getReparatie() {
		return reparatie.get();
	}

	// Setters
	public void setBeginTijd(Date beginTijd) {
		this.beginTijd.set(beginTijd);
	}

	public void setEindTijd(Date eindTijd) {
		this.eindTijd.set(eindTijd);
	}

	public void setWerknemer(Monteur werknemer) {
		this.werknemer.set(werknemer);
	}

	public void setReparatie(Reparatie reparatie) {
		this.reparatie.set(reparatie);
	}

	// Properties
	public ObjectProperty<Date> getBeginTijdProperty() {
		return beginTijd;
	}

	public ObjectProperty<Date> getEindTijdProperty() {
		return eindTijd;
	}

	public ObjectProperty<Monteur> getWerknemerProperty() {
		return werknemer;
	}

	public ObjectProperty<Reparatie> getReparatieProperty() {
		return reparatie;
	}

	@Override
	public String toString() {
		return "Planning [beginTijd=" + beginTijd.get() + ", eindTijd=" + eindTijd.get()
				+ ", werknemer=" + werknemer.get() + ", reparatie=" + reparatie.get() + "]";
	}
}
