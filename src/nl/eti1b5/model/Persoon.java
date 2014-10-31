package nl.eti1b5.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse voor het opslaan van de algemene gegevens van gebruikers en klanten
 * Bevat de naam, adres postcode, woonplaats en telefoon van de persoon
 * Slaat ook deels de gegevens van een klanttuple en/of monteurtuple uit de database op
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Persoon {

	private StringProperty naam;
	private StringProperty adres;
	private StringProperty postcode;
	private StringProperty woonplaats;
	private StringProperty telNr;

	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Persoon() {
		this.naam = new SimpleStringProperty();
		this.adres = new SimpleStringProperty();
		this.postcode = new SimpleStringProperty();
		this.woonplaats = new SimpleStringProperty();
		this.telNr = new SimpleStringProperty();
	}
	
	/**
	 * Constructor voor het initialiseren van de gegevens van een persoon vanuit de database
	 * @param naam De naam van de persoon
	 * @param woonplaats De woonplaats van de persoon
	 * @param adres Het adres van de persoon
	 * @param postcode De postcode van de persoon
	 * @param telNr De telefoonnummer van de persoon
	 */
	public Persoon(String naam, String adres, String postcode, String woonplaats, String telNr) {
		this.naam = new SimpleStringProperty(naam);
		this.adres = new SimpleStringProperty(adres);
		this.postcode = new SimpleStringProperty(postcode);
		this.woonplaats = new SimpleStringProperty(woonplaats);
		this.telNr = new SimpleStringProperty(telNr);
	}

	/**
	 * Getter voor het opvragen van de naam van de persoon
	 * @return De naam van de persoon
	 */
	public String getNaam() {
		return naam.get();
	}

	/**
	 * Getter voor het opvragen van de woonplaats van de persoon
	 * @return De woonplaats van de persoon
	 */
	public String getWoonplaats() {
		return woonplaats.get();
	}
	
	/**
	 * Getter voor het opvragen van het adres van de persoon
	 * @return Het adres van de persoon
	 */
	public String getAdres() {
		return adres.get();
	}
	
	/**
	 * Getter voor het opvragen van de postcode van de persoon
	 * @return De postcode van de persoon
	 */
	public String getPostcode() {
		return postcode.get();
	}
	
	/**
	 * Getter voor het opvragen van het telefoonnummer van de persoon
	 * @return Het telefoonnummer van de persoon
	 */
	public String getTelNr() {
		return telNr.get();
	}
	
	/**
	 * Setter voor het aanpassen de naam van de persoon
	 * @param naam De naam van de persoon
	 */
	public void setNaam(String naam) {
		this.naam.set(naam);
	}
	
	/**
	 * Setter voor het aanpassen de woonplaats van de persoon
	 * @param woonplaats De woonplaats van de persoon
	 */
	public void setwoonplaats(String woonplaats) {
		this.woonplaats.set(woonplaats);
	}
	
	/**
	 * Setter voor het aanpassen het adres van de persoon
	 * @param adres Het adres van de persoon
	 */
	public void setAdres(String adres) {
		this.adres.set(adres);
	}
	
	/**
	 * Setter voor het aanpassen de postcode van de persoon
	 * @param postcode De postcode van de persoon
	 */
	public void setPostcode(String postcode){
		this.postcode.set(postcode);
	}
	
	/**
	 * Setter voor het aanpassen het telefoonnummer van de persoon
	 * @param telNr Het telefoonnummer van de persoon
	 */
	public void setTelNr(String telNr) {
		this.telNr.set(telNr);
	}
	
	/**
	 * Property die de naamgegevens bevat
	 * @return Property met de naamgegevens
	 */
	public StringProperty naamProperty() {
		return naam;
	}
	
	/**
	 * Property die de woonplaats gegevens bevat
	 * @return Property met de woonplaatsgegevens
	 */
	public StringProperty woonplaatsProperty() {
		return woonplaats;
	}

	/**
	 * Property die de adresgegevens bevat
	 * @return Property met de adresgegevens
	 */
	public StringProperty adresProperty() {
		return adres;
	}
	
	/**
	 * Property die de postcodegegevens bevat
	 * @return Property met de postcodegegevens
	 */
	public StringProperty postcodeProperty() {
		return postcode;
	}
	
	/**
	 * Property die de telefoonnummergegevens bevat
	 * @return Property met de telefoonnummergegevens
	 */
	public StringProperty telNrProperty() {
		return telNr;
	}

	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		return "Persoon [naam=" + naam.get() + ", woonplaats=" + woonplaats.get() + ", adres="
				+ adres.get() + ", postcode=" + postcode.get() + ", telNr=" + telNr.get() + "]";
	}
}
