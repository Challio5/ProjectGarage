package nl.eti1b5.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse voor het opslaan van de gegevens van de voorraad
 * Bevat de naam, prijs en aantallen van de materialen
 * Slaat ook de gegevens van een voorraadtuple uit de database op
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Voorraad {

	private IntegerProperty materiaalnr;
	private StringProperty naam;
	private DoubleProperty prijs;
	private IntegerProperty aantal;

	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Voorraad() {}
	
	/**
	 * Constructor voor het initialiseren van de gegevens van de voorraad vanuit de database
	 * @param materiaalnr Het nummer van het materiaal
	 * @param naam De naam van het materiaal
	 * @param prijs De prijs van het materiaal
	 * @param aantal Het aantal van het materiaal
	 */
	public Voorraad(int materiaalnr, String naam, double prijs, int aantal) {
		this.naam = new SimpleStringProperty(naam);
		this.materiaalnr = new SimpleIntegerProperty(materiaalnr);
		this.prijs = new SimpleDoubleProperty(prijs);
		this.aantal = new SimpleIntegerProperty(aantal);
	}

	/**
	 * Getter voor het opvragen van het nummer van het materiaal
	 * @return Het nummer van het materiaal
	 */
	public int getMateriaalnr() {
		return materiaalnr.get();
	}

	/**
	 * Getter voor het opvragen van de naam van het materiaal
	 * @return De naam van het materiaal
	 */
	public String getNaam() {
		return naam.get();
	}
	
	/**
	 * Getter voor het opvragen van de prijs van het materiaal
	 * @return De prijs van het materiaal
	 */
	public double getPrijs() {
		return prijs.get();
	}

	/**
	 * Getter voor het opvragen van het aantal van het materiaal
	 * @return Het aantal van het materiaal
	 */
	public int getAantal() {
		return aantal.get();
	}

	/**
	 * Setter voor het aanpassen het nummer van het materiaal
	 * @param materiaalnr Het nummer van het materiaal
	 */
	public void setMateriaalnr(int materiaalnr) {
		this.materiaalnr.set(materiaalnr);
	}
	
	/**
	 * Setter voor het aanpassen van de naam van het materiaal
	 * @param naam De naam van het materiaal
	 */
	public void setNaam(String naam) {
		this.naam.set(naam);
	}
	
	/**
	 * Setter voor het aanpassen de prijs van het materiaal
	 * @param prijs De prijs van het materiaal
	 */
	public void setPrijs(double prijs) {
		this.prijs.set(prijs);
	}

	/**
	 * Setter voor het aanpassen het aantal van het materiaal
	 * @param aantal Het aantal van het materiaal
	 */
	public void setAantal(int aantal) {
		this.aantal.set(aantal);
	}

	/**
	 * Property die de materiaalnummergegevens bevat
	 * @return Property met de materiaalnummergegevens
	 */
	public IntegerProperty materiaalNrProperty() {
		return materiaalnr;
	}
	
	/**
	 * Property die de naamgegevens bevat
	 * @return Property met de naamgegevens
	 */
	public StringProperty naamProperty() {
		return naam;
	}
	
	/**
	 * Property die de prijsgegevens bevat
	 * @return Property met de prijsgegevens
	 */
	public DoubleProperty prijsProperty() {
		return prijs;
	}
	
	/**
	 * Property die de aantalgegevens bevat
	 * @return Property met de aantalgegevens
	 */
	public IntegerProperty aantalProperty() {
		return aantal;
	}

	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		return "Voorraad [materiaalnr=" + materiaalnr.get() + ", naam=" + naam.get()
				+ ", prijs=" + prijs.get() + ", aantal=" + aantal.get() + "]";
	}
}
