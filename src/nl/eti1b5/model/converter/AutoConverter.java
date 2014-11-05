package nl.eti1b5.model.converter;

import java.util.HashMap;
import java.util.Map;

import javafx.util.StringConverter;
import nl.eti1b5.model.Auto;

/**
 * Deze klasse dient als converter voor een String, zodat de string gescheiden kan worden
 * @author Groep 5
 * @version 1.0
 */
public class AutoConverter extends StringConverter<Auto> {

	Map<String, Auto> autoMap = new HashMap<>();
	
	/**
	 * Methode om het merk op te vragen
	 */
	@Override
	public Auto fromString(String merkModel) {
		return autoMap.get(merkModel);
	}
	
	/**
	 * toString methode
	 */
	@Override
	public String toString(Auto auto) {
		String display = auto.getMerk() + " " + auto.getModel();
		autoMap.put(display, auto);
		return display;
	}

}
