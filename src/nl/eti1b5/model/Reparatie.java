package nl.eti1b5.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse voor het opslaan van de gegevens van een reparatie
 * Bevat o.a. het kenteken van de te repareren auto, de geregistreerde uren en de status van de reparatie
 * Slaat ook de gegevens van een reparatietuple uit de database op
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Reparatie {

	private IntegerProperty reparatieNummer;
	private StringProperty kenteken;
	private IntegerProperty omschrijvingsNummer;
	private ObjectProperty<Timestamp> beginTijd;
	private ObjectProperty<Timestamp> eindTijd;
	private BooleanProperty reparatieStatus;
	private BooleanProperty betaalStatus;
	private List<Materiaal> materialenLijst;

	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Reparatie() {
		this.reparatieNummer = new SimpleIntegerProperty();
		this.kenteken = new SimpleStringProperty();
		this.omschrijvingsNummer = new SimpleIntegerProperty();
		this.beginTijd = new SimpleObjectProperty<>();
		this.eindTijd = new SimpleObjectProperty<>();
		this.reparatieStatus = new SimpleBooleanProperty();
		this.betaalStatus = new SimpleBooleanProperty();
		this.materialenLijst = new ArrayList<>();
	}
	
	/**
	 * Constructor voor initialiseren van de gegevens van een reparatie vanuit de database
	 * @param reparatieNummer Het nummer van de reparatie
	 * @param kenteken Het kenteken van de auto voor de reparatie
	 * @param omschrijvingsNummer Het omschrijvingsnummer van de reparatie
	 * @param beginTijd De begintijd van de reparatie
	 * @param eindTijd De eindtijd van de reparatie
	 * @param reparatieStatus De reparatiestatus van de reparatie
	 * @param betaalStatus De eindtijd van de reparaties
	 * @param materialenLijst De lijst met gebruikte materialen van de reparatie
	 */
	public Reparatie(int reparatieNummer, String kenteken, 
			int omschrijvingsNummer, Timestamp beginTijd, Timestamp eindTijd,
			boolean reparatieStatus, boolean betaalStatus,
			ArrayList<Materiaal> materialenLijst) {
		this.reparatieNummer = new SimpleIntegerProperty(reparatieNummer);
		this.kenteken = new SimpleStringProperty(kenteken);
		this.omschrijvingsNummer = new SimpleIntegerProperty(omschrijvingsNummer);
		this.beginTijd = new SimpleObjectProperty<>(beginTijd);
		this.eindTijd = new SimpleObjectProperty<>(eindTijd);
		this.reparatieStatus = new SimpleBooleanProperty(reparatieStatus);
		this.betaalStatus = new SimpleBooleanProperty(betaalStatus);
		this.materialenLijst = materialenLijst;
	}

	/**
	 * Getter voor het opvragen van het nummer van de reparatie
	 * @return Het nummer van de reparatie
	 */
	public int getReparatieNummer() {
		return reparatieNummer.get();
	}
	
	/**
	 * Getter voor het opvragen van het kenteken van de auto van de reparatie
	 * @return Het kenteken van de auto van de reparatie
	 */
	public String getKenteken() {
		return kenteken.get();
	}
	
	/**
	 * Getter voor het opvragen van de omschrijving van de reparatie
	 * @return De omschrijving van de reparatie
	 */
	public int getOmschrijvingsNummer() {
		return omschrijvingsNummer.get();
	}
	
	/**
	 * Getter voor het opvragen van de begintijd van de reparatie
	 * @return De begintijd van de reparatie
	 */
	public Timestamp getBeginTijd() {
		return beginTijd.get();
	}
	
	/**
	 * Getter voor het opvragen van de eindtijd van de reparatie
	 * @return De eindtijd van de reparatie
	 */
	public Timestamp getEindTijd() {
		return eindTijd.get();
	}
	
	/**
	 * Getter voor het opvragen van de reparatiestatus van de reparatie
	 * @return De reparatiestatus van de reparatie
	 */
	public boolean getReparatieStatus() {
		return reparatieStatus.get();
	}
	
	/**
	 * Getter voor het opvragen van de betaalstatus van de reparatie
	 * @return De betaalstatus van de reparatie
	 */
	public boolean getBetaalStatus() {
		return betaalStatus.get();
	}
	
	/**
	 * Getter voor het opvragen van de lijst met materialen van de reparatie
	 * @return De lijst met materialen van de reparatie
	 */
	public List<Materiaal> getMaterialenLijst() {
		return materialenLijst;
	}
	
	/**
	 * Setter voor het aanpassen van het nummer van de reparatie
	 * @param reparatieNummer Het nummer van de reparatie
	 */
	public void setReparatieNummer(int reparatieNummer) {
		this.reparatieNummer.set(reparatieNummer);
	}
	
	/**
	 * Setter voor het aanpassen van het kenteken van de auto van de reparatie
	 * @param kentekenNummer Het kenteken van de auto van de reparatie
	 */
	public void setKenteken(String kentekenNummer) {
		this.kenteken.set(kentekenNummer);
	}
	
	/**
	 * Setter voor het aanpassen van de omschrijving van de reparatie
	 * @param omschrijvingsNummer De omschrijving van de reparatie
	 */
	public void setOmschrijvingsNummer(int omschrijvingsNummer) {
		this.omschrijvingsNummer.set(omschrijvingsNummer);
	}
	
	/**
	 * Setter voor het aanpassen van de begintijd van de reparatie
	 * @param begintTijd De begintijd van de reparatie
	 */
	public void setBeginTijd(Timestamp begintTijd) {
		this.beginTijd.set(begintTijd);
	}
	
	/**
	 * Setter voor het aanpassen van de eindtijd van de reparatie
	 * @param eindTijd De eindtijd van de reparatie
	 */
	public void setEindTijd(Timestamp eindTijd) {
		this.eindTijd.set(eindTijd);
	}
	
	
	/**
	 * Setter voor het aanpassen van de betaalstatus van de reparatie
	 * @param betaalStatus De betaalstatus van de reparatie
	 */
	public void setBetaalStatus(boolean betaalStatus) {
		this.betaalStatus.set(betaalStatus);
	}
	
	/**
	 * Setter voor het aanpassen van de lijst met materialen van de reparatie
	 * @param materialenLijst De lijst met materialen van de reparatie
	 */
	public void setMaterialenLijst(ArrayList<Materiaal> materialenLijst) {
		this.materialenLijst = materialenLijst;
	}
	
	/**
	 * Setter voor de reparatieStatuas van de reparatie
	 * @param reparatieStatus de nieuwe reparatieStatus
	 */
	public void setReparatieStatus(boolean reparatieStatus){
		this.reparatieStatus.set(reparatieStatus);
	}
	
	/**
	 * Property die de reparatienummergegevens bevat
	 * @return Property met de reparatienummergegevens
	 */
	public IntegerProperty reparatieNummerProperty() {
		return this.reparatieNummer;
	}
	
	/**
	 * Property die de kentekengegevens bevat
	 * @return Property met de kentekengegevens
	 */
	public StringProperty kentekenProperty() {
		return this.kenteken;
	}
	
	/**
	 * Property die de omschrijvinggegevens bevat
	 * @return Property met de omschrijvinggegevens
	 */
	public IntegerProperty omschrijvingsNummerProperty() {
		return this.omschrijvingsNummer;
	}
	
	/**
	 * Property die de begintijdgegevens bevat
	 * @return Property met de begintijdgegevens
	 */
	public ObjectProperty<Timestamp> beginTijdProperty() {
		return this.beginTijd;
	}
	
	/**
	 * Property die de eindtijdgegevens bevat
	 * @return Property met de eindtijdgegevens
	 */
	public ObjectProperty<Timestamp> eindTijdProperty() {
		return this.eindTijd;
	}
	
	/**
	 * Property die de reparatiestatusgegeven bevat
	 * @return Property met de reparatiestatusgegeven
	 */
	public BooleanProperty reparatieStatusProperty() {
		return this.reparatieStatus;
	}
	
	/**
	 * Property die de betaalstatusgegevens bevat
	 * @return Property met de betaalstatusgegevens
	 */
	public BooleanProperty betaalStatusProperty() {
		return this.betaalStatus;
	}

	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		String materialenString = "";
		for(Materiaal materiaal : materialenLijst) materialenString += materiaal + ", ";
		return "Reparatie [reparatieNummer=" + reparatieNummer.get() + ", kenteken="
				+ kenteken.get() + ", omschrijving=" + omschrijvingsNummer.get() + ", beginTijd="
				+ beginTijd.get() + ", eindTijd=" + eindTijd.get() + ", reparatieStatus="
				+ reparatieStatus.get() + ", betaalStatus=" + betaalStatus.get()
			    + ", materialenLijst=" + materialenString + "]";
	}
}