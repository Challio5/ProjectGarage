package nl.eti1b5.model.converter;

import java.util.HashMap;
import java.util.Map;

import javafx.util.StringConverter;
import nl.eti1b5.model.Klant;

public class KlantConverter extends StringConverter<Klant> {

	private Map<String, Klant> klantenMap = new HashMap<>();
	
	@Override
	public Klant fromString(String naam) {
		return klantenMap.get(naam);
	}

	@Override
	public String toString(Klant klant) {
		klantenMap.put(klant.getNaam(), klant);
		return klant.getNaam();
	}

}
