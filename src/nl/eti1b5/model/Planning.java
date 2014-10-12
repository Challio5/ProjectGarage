package nl.eti1b5.model;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Planning {
	private ObjectProperty<Date> beginTijd;
	private ObjectProperty<Date> eindTijd;
	private IntegerProperty werknemernr;
	private IntegerProperty reparatienr;
	
	public Planning(Date beginTijd, Date eindTijd, int werknemernr, int reparatienr) {
		this.beginTijd = new SimpleObjectProperty<>(beginTijd);
		this.eindTijd = new SimpleObjectProperty<>(eindTijd);
		this.werknemernr = new SimpleIntegerProperty(werknemernr);
		this.reparatienr = new SimpleIntegerProperty(reparatienr);
	}

	// Getters
	public Date getBeginTijd() {
		return beginTijd.get();
	}

	public Date getEindTijd() {
		return eindTijd.get();
	}

	public int getWerknemernr() {
		return werknemernr.get();
	}

	public int getReparatienr() {
		return reparatienr.get();
	}

	// Setters
	public void setBeginTijd(Date beginTijd) {
		this.beginTijd = new SimpleObjectProperty<>(beginTijd);
	}

	public void setEindTijd(Date eindTijd) {
		this.eindTijd = new SimpleObjectProperty<>(eindTijd);
	}

	public void setWerknemernr(int werknemernr) {
		this.werknemernr = new SimpleIntegerProperty(werknemernr);
	}

	public void setReparatienr(int reparatienr) {
		this.reparatienr = new SimpleIntegerProperty(reparatienr);
	}

	// Properties
	public ObjectProperty<Date> getBeginTijdProperty() {
		return beginTijd;
	}

	public ObjectProperty<Date> getEindTijdProperty() {
		return eindTijd;
	}

	public IntegerProperty getWerknemernrProperty() {
		return werknemernr;
	}

	public IntegerProperty getReparatienrProperty() {
		return reparatienr;
	}
}
