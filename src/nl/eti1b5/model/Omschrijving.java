package nl.eti1b5.model;

import java.sql.Time;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Klasse voor het opslaan van een reparatieomschrijving
 * Kan gebruikt worden voor het maken van een nieuwe reparatieomschrijving met naam en tijd
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Omschrijving {
	private ObjectProperty<String> naam;
	private ObjectProperty<Time> duur;
	
	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Omschrijving() {
		this.naam = new SimpleObjectProperty<>();
		this.duur = new SimpleObjectProperty<>();
	}
	
	/**
	 * Constructor voor het initialiseren van de gegevens van een omschrijving van een reparatie
	 * @param naam De naam van de reparatie
	 * @param duur De duur van de reparatie
	 */
	public Omschrijving(String naam, Time duur) {
		this.naam = new SimpleObjectProperty<>(naam);
		this.duur = new SimpleObjectProperty<>(duur);
	}
	
	/**
	 * Getter voor het opvragen van de naam van de reparatie
	 * @return De naam van de reparatie
	 */
	public String getNaam() {
		return naam.get();
	}

	/**
	 * Getter voor het opvragen van de duur van de reparatie
	 * @return De duur van de reparatie
	 */
	public Time getDuur() {
		return duur.get();
	}
	
	/**
	 * Setter voor het aanpassen van de naam van de reparatie
	 * @param naam De naam van de reparatie
	 */
	public void setNaam(String naam) {
		this.naam.set(naam);
	}

	/**
	 * Setter voor het aanpassen van de duur van de reparatie
	 * @param duur De duur van de reparatie
	 */
	public void setTijd(Time duur) {
		this.duur.set(duur);
	}
	
	/**
	 * Property die de naamgegevens bevat
	 * @return Property met de naamgegevens
	 */
	public ObjectProperty<String> naamProperty() {
		return naam;
	}
	
	/**
	 * Property die de tijdgegevens bevat
	 * @return Property met de tijdgegevens
	 */
	public ObjectProperty<Time> tijdProperty() {
		return duur;
	}
	
	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		return "Omschrijving [naam=" + naam.get() + ", duur=" + duur.get() + "]";
	}
}
