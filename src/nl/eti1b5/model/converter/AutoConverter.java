package nl.eti1b5.model.converter;

import java.util.HashMap;
import java.util.Map;

import nl.eti1b5.model.Auto;
import javafx.util.StringConverter;

public class AutoConverter extends StringConverter<Auto> {

	Map<String, Auto> autoMap = new HashMap<>();
	
	@Override
	public Auto fromString(String merkModel) {
		return autoMap.get(merkModel);
	}

	@Override
	public String toString(Auto auto) {
		String display = auto.getMerk() + " " + auto.getModel();
		autoMap.put(display, auto);
		return display;
	}

}
