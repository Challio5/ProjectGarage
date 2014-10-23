package nl.eti1b5.model;

import java.sql.Timestamp;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * Klasse voor het opslaan van de gegevens van een planningsitem
 * Bevat de geplande eind- en begintijden samen met de gekoppelde werknemer en reparatie
 * Slaat ook de gegevens van een planningstuple uit de database op
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Planning {
	private ObjectProperty<Timestamp> beginTijd;
	private ObjectProperty<Timestamp> eindTijd;
	private ObjectProperty<Monteur> werknemer;
	private ObjectProperty<Reparatie> reparatie;
	
	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Planning() {}
	
	/**
	 * Constructor voor het initialiseren van de gegevens van een planning vanuit de database
	 * @param beginTijd De begintijd van de geplande reparatie
	 * @param eindTijd De eindtijd van de geplande reparatie
	 * @param werknemer De monteur voor de geplande reparatie
	 * @param reparatie De gekoppelde reparatie aan de planning
	 */
	public Planning(Timestamp beginTijd, Timestamp eindTijd, Monteur werknemer, Reparatie reparatie) {
		this.beginTijd = new SimpleObjectProperty<>(beginTijd);
		this.eindTijd = new SimpleObjectProperty<>(eindTijd);
		this.werknemer = new SimpleObjectProperty<>(werknemer);
		this.reparatie = new SimpleObjectProperty<>(reparatie);
	}

	/**
	 * Getter voor het opvragen van de begintijd van de planning
	 * @return De begintijd van de planning
	 */
	public Timestamp getBeginTijd() {
		return beginTijd.get();
	}

	/**
	 * Getter voor het opvragen van de eindtijd van de planning
	 * @return De eindtijd van de planning
	 */
	public Timestamp getEindTijd() {
		return eindTijd.get();
	}

	/**
	 * Getter voor het opvragen van de monteur van de geplande reparatie
	 * @return De monteur van de geplande reparatie
	 */
	public Monteur getWerknemer() {
		return werknemer.get();
	}

	/**
	 * Getter voor het opvragen van de gekoppelde reparatie van de planning
	 * @return De gekoppelde reparatie van de planning
	 */
	public Reparatie getReparatie() {
		return reparatie.get();
	}

	/**
	 * Setter voor het aanpassen van de begintijd van de planning
	 * @param beginTijd De begintijd van de planning
	 */
	public void setBeginTijd(Timestamp beginTijd) {
		this.beginTijd.set(beginTijd);
	}

	/**
	 * Setter voor het aanpassen van de eindtijd van de planning
	 * @param eindTijd De eindtijd van de planning
	 */
	public void setEindTijd(Timestamp eindTijd) {
		this.eindTijd.set(eindTijd);
	}

	/**
	 * Setter voor het aanpassen de geplande monteur voor de reparatie
	 * @param werknemer De geplande monteur voor de reparatie
	 */
	public void setWerknemer(Monteur werknemer) {
		this.werknemer.set(werknemer);
	}

	/**
	 * Setter voor het aanpassen de gekoppelde reparatie van de planning
	 * @param reparatie De gekoppelde reparatie van de planning
	 */
	public void setReparatie(Reparatie reparatie) {
		this.reparatie.set(reparatie);
	}

	/**
	 * Property die de begintijdgegevens bevat
	 * @return Property met de begintijdgegevens
	 */
	public ObjectProperty<Timestamp> getBeginTijdProperty() {
		return beginTijd;
	}

	/**
	 * Property die de eindtijdgegevens bevat
	 * @return Property met de eindtijdgegevens
	 */
	public ObjectProperty<Timestamp> getEindTijdProperty() {
		return eindTijd;
	}

	/**
	 * Property die de monteurgegevens bevat
	 * @return Property met de monteurgegevens
	 */
	public ObjectProperty<Monteur> getWerknemerProperty() {
		return werknemer;
	}

	/**
	 * Property die de reperatiegegevens bevat
	 * @return Property met de reparatiegegevens
	 */
	public ObjectProperty<Reparatie> getReparatieProperty() {
		return reparatie;
	}

	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		return "Planning [beginTijd=" + beginTijd.get() + ", eindTijd=" + eindTijd.get()
				+ ", werknemer=" + werknemer.get() + ", reparatie=" + reparatie.get() + "]";
	}
}
