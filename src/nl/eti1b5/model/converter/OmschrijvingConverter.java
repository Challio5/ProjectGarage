package nl.eti1b5.model.converter;

import java.util.HashMap;
import java.util.Map;

import javafx.util.StringConverter;
import nl.eti1b5.model.Omschrijving;

/**
 * Deze klasse dient als converter voor een String, zodat de string gescheiden kan worden
 * @author Groep 5
 * @version 1.0
 */
public class OmschrijvingConverter extends StringConverter<Omschrijving> {
	
	private Map<String, Omschrijving> omschrijvingsMap = new HashMap<>();
	/**
	 * Methode om het merk op te vragen
	 */
	@Override
	public Omschrijving fromString(String naam) {
		return omschrijvingsMap.get(naam);
	}
	/**
	 * toString methode
	 */
	@Override
	public String toString(Omschrijving monteur) {
		omschrijvingsMap.put(monteur.getNaam(), monteur);
		return monteur.getNaam();
	}

}
