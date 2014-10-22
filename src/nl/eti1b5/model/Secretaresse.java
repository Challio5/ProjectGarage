package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Klasse voor het opslaan van de gegevens van een secretaresse
 * Heeft gebruiker als superklasse die de algemene gegevens van een gebruiker bevat
 * Voegt een secretaressenummer toe 
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Secretaresse extends Gebruiker {

	private IntegerProperty secretaresseNr;

	/**
	 * Constructor voor het initialiseren van de gegevens van een secretaresse
	 * @param naam De naam van de secretaresse
	 * @param plaats De woonplaats van de secretaresse
	 * @param adres Het adres van de secretaresse
	 * @param postcode De postcode van de secretaresse
	 * @param telNr Het telefoonnummer van de secretaresse
	 * @param rechten De rechten van de secretaresse
	 * @param wachtwoord Het wachtwoord van de secretaresse
	 * @param functie De functie van de secretaresse
	 * @param werknemerNr Het werknemernummer van de secretaresse
	 * @param secretaresseNr Het nummer van de secretaresse
	 */
	public Secretaresse(String naam, String plaats, String adres, String postcode, String telNr,
				   int rechten, String wachtwoord, String functie, int werknemerNr, int secretaresseNr) {
		super(werknemerNr, naam, plaats, adres, postcode, telNr, wachtwoord);
		
		this.secretaresseNr = new SimpleIntegerProperty(secretaresseNr);
	}

	/**
	 * Getter voor het opvragen van het nummer van de secretaresse
	 * @return Het nummer van de secretaresse
	 */
	public int getSecretaresseNr() {
		return secretaresseNr.get();
	}
	
	/**
	 * Setter voor het aanpassen van het nummer van de secretaresse
	 * @param secretaresseNr Het nummer van de secretaresse
	 */
	public void setSecretaresseNr(int secretaresseNr) {
		this.secretaresseNr.set(secretaresseNr);
	}
	
	/**
	 * Property die de secretaressenummergegevens bevat
	 * @return Property met de secretaressenummergegevens
	 */
	public IntegerProperty secretaresseNr() {
		return secretaresseNr;
	}
	
	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		return "Gebruiker [secretaresseNummer=" + secretaresseNr.get() + "]";
	}
}
