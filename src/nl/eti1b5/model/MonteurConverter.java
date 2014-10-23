package nl.eti1b5.model;

import java.util.HashMap;
import java.util.Map;

import javafx.util.StringConverter;

public class MonteurConverter extends StringConverter<Monteur> {
	
	private Map<String, Monteur> monteurMap = new HashMap<>();
	
	@Override
	public Monteur fromString(String string) {
		return monteurMap.get(string);
	}

	@Override
	public String toString(Monteur monteur) {
		monteurMap.put(monteur.getNaam(), monteur);
		return monteur.getNaam();
	}
	
}
