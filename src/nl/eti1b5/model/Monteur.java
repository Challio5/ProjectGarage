package nl.eti1b5.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse voor het opslaan van de gegevens van een monteur
 * Heeft gebruiker als superklasse die de algemene gegevens van een gebruiker bevat
 * Voegt een specialiteit toe en de lijsten met beschikbaarheidscodes en reparaties
 * Slaat ook de gegevens van een monteurtuple uit de database op
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Monteur extends Gebruiker {

	private StringProperty specialiteit;
	private List<String> beschikbaarheidsLijst;
	private List<Integer> reparatieLijst;

	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Monteur() {
		super();
		this.specialiteit = new SimpleStringProperty();
		this.beschikbaarheidsLijst = new ArrayList<>();
		this.reparatieLijst = new ArrayList<>();
	}
	
	/**
	 * Constructor voor het initialiseren van de gegevens van een monteur vanuit de database
	 * @param werknemerNr Het werknemer van de monteur
	 * @param naam De naam van de monteur
	 * @param woonplaats De woonplaats van de monteur
	 * @param adres Het adres van de monteur
	 * @param postcode De postcode van de monteur
	 * @param telNr Het telefoonnummer van de monteur
	 * @param wachtwoord Het wachtwoord van de monteur
	 * @param specialiteit De specialiteit van de monteur
	 * @param beschikbaarheidsLijst De lijst met de beschikbaarheid van de monteur
	 * @param reparatieLijst De lijst met de reparaties van de monteur
	 */
	public Monteur(int werknemerNr, String naam, String adres, String postcode, String woonplaats, String telNr,
				   String wachtwoord, String specialiteit, ArrayList<String> beschikbaarheidsLijst, ArrayList<Integer> reparatieLijst) {
		super(werknemerNr, naam, adres, postcode, woonplaats, telNr, wachtwoord);
		
		this.specialiteit = new SimpleStringProperty(specialiteit);
		this.beschikbaarheidsLijst = beschikbaarheidsLijst;
		this.reparatieLijst = reparatieLijst;
	}

	/**
	 * Getter voor het opvragen van de specialiteit van de monteur
	 * @return De specialiteit van de monteur
	 */
	public String getSpecialiteit() {
		return specialiteit.get();
	}
	
	/**
	 * Getter voor het opvragen van de lijst met beschikbaarheidscodes van de monteur
	 * @return De lijst met beschikbaarheidscodes van de monteur
	 */
	public List<String> getBeschikbaarheid() {
		return beschikbaarheidsLijst;
	}
	
	/**
	 * Getter voor het opvragen van de lijst met reparaties van de monteur
	 * @return De lijst met reparaties van de monteur
	 */
	public List<Integer> getReparaties() {
		return reparatieLijst;
	}
	
	/**
	 * Setter voor het aanpassen van de specialiteit van de monteur
	 * @param specialiteit De specialiteit van de monteur
	 */
	public void setSpecialiteit(String specialiteit) {
		this.specialiteit.set(specialiteit);
	}
	
	/**
	 * Setter voor het aanpassen van de lijst met beschikbaarheidscodes van de monteur
	 * @param beschikbaarheidsLijst De lijst met beschikbaarheidscodes van de monteur
	 */
	public void setBeschikbaarheid(ArrayList<String> beschikbaarheidsLijst) {
		this.beschikbaarheidsLijst = beschikbaarheidsLijst;
	}

	/**
	 * Setter voor het aanpassen van de lijst met reparaties van de monteur
	 * @param reparatieLijst De lijst met reparaties van de monteur
	 */
	public void setReparaties(ArrayList<Integer> reparatieLijst) {
		this.reparatieLijst = reparatieLijst;
	}
	
	/**
	 * Property die de specialiteitgegevens bevat
	 * @return Property met de specialiteitsgegevens
	 */
	public StringProperty specialiteitProperty() {
		return specialiteit;
	}
	
	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		String beschikbaarheidsString = "";
		for(String beschikbaarheid : beschikbaarheidsLijst) beschikbaarheidsString += beschikbaarheid + ", ";
		String reparatieString = "";
		for(int reparatie : reparatieLijst)
			reparatieString += reparatie + ", ";
		return "Gebruiker [naam=" + getNaam() + ", woonplaats=" + getWoonplaats() + ", adres=" + getAdres() + ", postcode=" + getPostcode()
						   + ", telNr=" + getTelNr() + ", wachtwoord=" + getWachtwoord() + ", werknemerNr=" + getWerknemerNr()
						   + ", specialiteit=" + specialiteit.get() + ", beschikbaarheid=" + beschikbaarheidsString + ", Reparaties="+ reparatieString+ "]";
	}

}
