package nl.eti1b5.model.converter;

import java.util.HashMap;
import java.util.Map;

import javafx.util.StringConverter;
import nl.eti1b5.model.Monteur;

/**
 * Deze klasse dient als converter voor een String, zodat de string gescheiden kan worden
 * @author Groep 5
 * @version 1.0
 */
public class MonteurConverter extends StringConverter<Monteur> {
	
	private Map<String, Monteur> monteurMap = new HashMap<>();
	/**
	 * Methode om het merk op te vragen
	 */
	@Override
	public Monteur fromString(String naam) {
		return monteurMap.get(naam);
	}
	/**
	 * toString methode
	 */
	@Override
	public String toString(Monteur monteur) {
		monteurMap.put(monteur.getNaam(), monteur);
		return monteur.getNaam();
	}

}
