package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse voor het opslaan van de gegevens van een gebruiker van het systeem, zoals monteur en secretaresse
 * Heeft persoon als superklasse die de algemene gegevens van een persoon bevat
 * Voegt een wachtwoord en werknummer toe voor toegang tot het systeem
 * Slaat ook deels de gegevens van een monteurtuple uit de database op
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */
public class Gebruiker extends Persoon {

	private StringProperty wachtwoord;
	private IntegerProperty werknemerNr;

	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Gebruiker() {}
	
	/**
	 * Constructor voor het initialiseren van de gegevens van een persoon vanuit de database
	 * @param werknemerNr Het werknemernummer van de persoon
	 * @param naam De naam van de persoon
	 * @param plaats De plaats van de persoon
	 * @param adres Het adres van de persoon
	 * @param postcode De postcode van de persoon
	 * @param telNr De telefoonnummer van de persoon
	 * @param wachtwoord Het wachtwoord van de persoon
	 */
	public Gebruiker(int werknemerNr, String naam, String plaats, String adres, String postcode, String telNr,
					 String wachtwoord) {
		super(naam, plaats, adres, postcode, telNr);
		this.werknemerNr = new SimpleIntegerProperty(werknemerNr);
		this.wachtwoord = new SimpleStringProperty(wachtwoord);
	}

	/**
	 * Getter voor het opvragen van het wachtwoord van de gebruiker
	 * @return Het wachtwoord van de gebruiker
	 */
	public String getWachtwoord() {
		return wachtwoord.get();
	}

	/**
	 * Getter voor het opvragen van het werknemernummer van de gebruiker
	 * @return Het werknemernummer van de gebruiker
	 */
	public int getWerknemerNr() {
		return werknemerNr.get();
	}

	/**
	 * Setter voor het aanpassen van het wachtwoord van de gebruiker
	 * @param wachtwoord Het wachtwoord van de gebruiker
	 */
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord.set(wachtwoord);
	}

	/**
	 * Setter voor het aanpassen het werknemernummer van de gebruiker
	 * @param werknemerNr Het werknemernummer van de gebruiker
	 */
	public void setWerknemerNr(int werknemerNr) {
		this.werknemerNr.set(werknemerNr);
	}
	
	/**
	 * Property die de wachtwoordgegevens bevat
	 * @return Property met de wachtwoordgegevens
	 */
	public StringProperty wachtwoordProperty() {
		return wachtwoord;
	}
	
	/**
	 * Property die het werknemernummer bevat
	 * @return Property met het werknemernummer
	 */
	public IntegerProperty werknemerNrProperty() {
		return werknemerNr;
	}

	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		return "Gebruiker [wachtwoord=" + wachtwoord.get()
				+ ", werknemerNr=" + werknemerNr.get() + 
				super.toString() + "]";
	}
}
