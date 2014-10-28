package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Voorraad {
	
	private ObjectProperty<Materiaal> materiaal;
	private IntegerProperty aantal;
	
	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Voorraad() {
		this.materiaal = new SimpleObjectProperty<>();
		this.aantal = new SimpleIntegerProperty();
	}
	
	/**
	 * Constructor voor initialiseren van de gegevens van de voorraad vanuit de database
	 * @param materiaal Het materiaal wat wordt opgeslagen
	 * @param aantal Het aantal wat wordt opgeslagen
	 */
	public Voorraad(Materiaal materiaal, int aantal){
		this.materiaal = new SimpleObjectProperty<>(materiaal);
		this.aantal = new SimpleIntegerProperty(aantal);
	}
	
	/**
	 * Getter voor het opvragen van het materiaal uit de voorraad
	 * @return Het materiaal uit de voorraad
	 */
	public Materiaal getMateriaal(){
		return materiaal.get();
	}
	
	/**
	 * Setter voor het aanpassen van het materiaal uit de voorraad
	 * @param materiaal Het materiaal voor de voorraad
	 */
	public void setMateriaal(Materiaal materiaal){
		this.materiaal.set(materiaal);
	}
	
	/**
	 * Getter voor het opvragen van het aantal uit de voorraad
	 * @return Het aantal uit de voorraad
	 */
	public int getAantal(){
		return aantal.get();
	}
	
	/**
	 * Setter voor het aanpassen van het aantal uit de voorraad
	 * @param aantal Het aantal voor de voorraad
	 */
	public void setAantal(int aantal){
		this.aantal.set(aantal);
	}
	
	/**
	 * Property die de materiaalgegevens bevat
	 * @return Property met de materiaalgegevens
	 */
	public ObjectProperty<Materiaal> materiaalProperty() {
		return materiaal;
	}
	
	/**
	 * Property die de aantallen bevat
	 * @return Property met de aantallen
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
		return "Voorraad [materiaal=" + materiaal.get() + ", aantal=" + aantal.get() + "]";
	}
}
