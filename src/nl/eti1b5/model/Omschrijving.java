package nl.eti1b5.model;

/**
 * Klasse voor het opslaan van een reparatieomschrijving
 * Kan gebruikt worden voor het maken van een nieuwe reparatieomschrijving met naam en tijd
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Omschrijving {
	private String naam;
	private double duur;
	
	/**
	 * Constructor voor het initialiseren van de gegevens van een omschrijving van een reparatie
	 * @param naam De naam van de reparatie
	 * @param duur De duur van de reparatie
	 */
	public Omschrijving(String naam, double duur) {
		this.naam = naam;
		this.duur = duur;
	}

	/**
	 * Getter voor het opvragen van de naam van de reparatie
	 * @return De naam van de reparatie
	 */
	public String getNaam() {
		return naam;
	}

	/**
	 * Getter voor het opvragen van de duur van de reparatie
	 * @return De duur van de reparatie
	 */
	public double getDuur() {
		return duur;
	}

	/**
	 * Setter voor het aanpassen van de naam van de reparatie
	 * @param naam De naam van de reparatie
	 */
	public void setNaam(String naam) {
		this.naam = naam;
	}

	/**
	 * Setter voor het aanpassen van de duur van de reparatie
	 * @param tijd De duur van de reparatie
	 */
	public void setTijd(double duur) {
		this.duur = duur;
	}

	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		return naam;
	}
}
