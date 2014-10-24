package nl.eti1b5.model.converter;

import java.util.HashMap;
import java.util.Map;

import javafx.util.StringConverter;
import nl.eti1b5.model.Monteur;

public class MonteurConverter extends StringConverter<Monteur> {
	
	private Map<String, Monteur> monteurMap = new HashMap<>();
	
	@Override
	public Monteur fromString(String naam) {
		return monteurMap.get(naam);
	}

	@Override
	public String toString(Monteur monteur) {
		monteurMap.put(monteur.getNaam(), monteur);
		return monteur.getNaam();
	}

}
