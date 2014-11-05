package nl.eti1b5.model.converter;

import java.util.HashMap;
import java.util.Map;

import javafx.util.StringConverter;
import nl.eti1b5.model.Klant;

/**
 * Deze klasse dient als converter voor een String, zodat de string gescheiden kan worden
 * @author Groep 5
 * @version 1.0
 */
public class KlantConverter extends StringConverter<Klant> {

	private Map<String, Klant> klantenMap = new HashMap<>();
	/**
	 * Methode om het merk op te vragen
	 */
	@Override
	public Klant fromString(String naam) {
		return klantenMap.get(naam);
	}
	/**
	 * toString methode
	 */
	@Override
	public String toString(Klant klant) {
		klantenMap.put(klant.getNaam(), klant);
		return klant.getNaam();
	}

}
