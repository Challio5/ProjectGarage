package nl.eti1b5.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * Klasse voor het opslaan van de gegevens van een klant
 * Heeft persoon als superklasse die de algemene gegevens van een persoon bevat
 * Voegt een klantnummer toe en de lijst met auto's van deze klant
 * Slaat ook de gegevens van een klanttuple uit de database op
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Klant extends Persoon {

	private IntegerProperty klantNr;
	private List<Auto> autoLijst;

	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Klant() {
		super();
		this.klantNr = new SimpleIntegerProperty();
		this.autoLijst = new ArrayList<>();
	}
	
	/**
	 * Constructor voor het initialiseren van de gegevens van een klant vanuit de database
	 * @param klantNr Het klantnummer van de klant
	 * @param naam De naam van de klant
	 * @param adres Het adres van de klant
	 * @param postcode De postcode van de klant
	 * @param plaats De woonplaats van de klant
	 * @param telNr Het telefoonnummer van de klant
	 */
	public Klant(int klantNr, String naam, String adres, String postcode, String plaats, String telNr, ArrayList<Auto> autoLijst) {
		super(naam, adres, postcode, plaats, telNr);
		
		this.klantNr = new SimpleIntegerProperty(klantNr);
		this.autoLijst = autoLijst;
	}

	/**
	 * Getter voor het opvragen van het klantnummer van de klant
	 * @return Het klantnummer van de klant
	 */
	public int getKlantnr() {
		return klantNr.get();
	}

	/**
	 * Getter voor het opvragen van de lijst met auto's van de klant
	 * @return De lijst met auto's
	 */
	public List<Auto> getAutoLijst() {
		return autoLijst;
	}
	
	/**
	 * Setter voor het aanpassen van het klantnummer van de klant 
	 * @param klantnr Het klantnummer van de klant
	 */
	public void setKlantnr(int klantnr) {
		this.klantNr.set(klantnr);
	}

	/**
	 * Setter voor het aanpassen van de lijst met auto's van de klant
	 * @param autoLijst De lijst met auto's
	 */
	public void setAutoLijst(ArrayList<Auto> autoLijst) {
		this.autoLijst = autoLijst;
	}
	
	/**
	 * Property die de klantnummergegevens bevat
	 * @return Property met de klantnummergegevens
	 */
	public IntegerProperty klantNrProperty() {
		return klantNr;
	}
	
	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		String autoString = autoLijst.stream()
		.map(Object::toString)
		.collect(Collectors.joining(", "));
		for(Auto auto : autoLijst) autoString += auto + ", ";
		return "Klant [klantNr=" + klantNr.get() + " " + super.toString() + ", autoLijst=" + autoString + "]";
	}
}
