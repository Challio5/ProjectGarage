package nl.eti1b5.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Klasse voor het opslaan van de gegevens van een auto
 * Bevat de het kenteken, merk en model samen met de eigenaar en verzekeringsnummer
 * Slaat ook de gegevens van een autotuple uit de database op
 * Maakt gebruik van de properties waardoor deze observeerbaar zijn
 * 
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 22 okt. 2014
 */

public class Auto {

	private StringProperty kenteken;
	private IntegerProperty klantnr;
	private StringProperty merk;
	private StringProperty model;
	private IntegerProperty verzekeringsnummer;

	/**
	 * Default constructor voor het meegegeven van de default waarden aan attributen
	 * Worden later aangepast aan de invoer vanuit de GUI
	 */
	public Auto() {}
	
	/**
	 * Constructor voor het initialiseren van de gegevens van een auto van uit de database
	 * @param kenteken Het kenteken van de auto
	 * @param klantnr Het klantnummer van de auto
	 * @param merk Het merk van de auto
	 * @param model Het model van de auto
	 * @param verzekeringsnummer Het verzekeringsnummer van de auto
	 */
	public Auto(String kenteken, int klantnr, String merk, String model, int verzekeringsnummer) {
		this.merk = new SimpleStringProperty(merk);
		this.klantnr = new SimpleIntegerProperty(klantnr);
		this.model = new SimpleStringProperty(model);
		this.kenteken = new SimpleStringProperty(kenteken);
		this.verzekeringsnummer = new SimpleIntegerProperty(verzekeringsnummer);
	}

	/**
	 * Getter voor het opvragen van het kenteken van de auto
	 * @return Het kenteken van de auto
	 */
	public String getKenteken() {
		return kenteken.get();
	}

	/**
	 * Getter voor het opvragen van het klantnr van de auto
	 * @return Het klantnummer van de auto
	 */
	public int getKlantnr() {
		return klantnr.get();
	}
	
	/**
	 * Getter voor het opvragen van het merk van de auto
	 * @return Het merk van de auto
	 */
	public String getMerk() {
		return merk.get();
	}

	/**
	 * Getter voor het opvragen van het model van de auto
	 * @return Het model van de auto
	 */
	public String getModel() {
		return model.get();
	}
	
	/**
	 * Getter voor het opvragen van het verzekeringsnummer van de auto
	 * @return Het verzekeringsnummer van de auto
	 */
	public int getVerzekeringsnummer() {
		return verzekeringsnummer.get();
	}
	
	/**
	 * Setter voor het aanpassen van het kenteken van de auto
	 * @param kenteken Het kenteken van de auto
	 */
	public void setKenteken(String kenteken) {
		this.kenteken.set(kenteken);
	}
	
	/**
	 * Setter voor het aanpassen van het klantnummer van de eigenaar van de auto
	 * @param klantnr Het klantnummer van de eigenaar van de auto
	 */
	public void setKlantnr(int klantnr) {
		this.klantnr.set(klantnr);
	}
	
	/**
	 * Setter voor het aanpassen van het merk van de auto
	 * @param merk Het merk van de auto
	 */
	public void setMerk(String merk) {
		this.merk.set(merk);
	}

	/**
	 * Setter voor het aanpassen van het model van de auto
	 * @param model Het model van de auto
	 */
	public void setModel(String model) {
		this.model.set(model);
	}

	/**
	 * Setter voor het aanpassen van het verzekeringsnummer van de auto
	 * @param verzekeringsnummer Het verzekeringsnummer van de auto
	 */
	public void setVerzekeringsnummer(int verzekeringsnummer) {
		this.verzekeringsnummer.set(verzekeringsnummer);
	}
	
	/**
	 * Property die de kentekengegevens bevat
	 * @return Property met de kentekengegevens
	 */
	public StringProperty kentekenProperty() {
		return kenteken;
	}
	
	/**
	 * Property die de klantgegevens bevat
	 * @return Property met de klantgegevens
	 */
	public IntegerProperty klantnrProperty() {
		return klantnr;
	}
	
	/**
	 * Property die de merkgegevens bevat
	 * @return Property met de merkgegevens
	 */
	public StringProperty merkProperty() {
		return merk;
	}
	
	/**
	 * Property die de modelgegevens bevat
	 * @return Property met de modelgegevens
	 */
	public StringProperty modelProperty() {
		return model;
	}
	
	/**
	 * Property die het verzekeringsnummer bevat
	 * @return Property met het verzekeringsnummers
	 */
	public IntegerProperty verzekeringsnummerProperty() {
		return verzekeringsnummer;
	}

	/**
	 * Methode om de huidige waarden van de attributen terug te geven
	 * @return De string met de huidige waarden van de attributen
	 */
	@Override
	public String toString() {
		return "Auto [kenteken=" + kenteken.get() + ", klantnr=" + klantnr.get()
				+ ", merk=" + merk.get() + ", model=" + model.get()
				+ ", verzekeringsnummer=" + verzekeringsnummer.get() + "]";
	}
}
