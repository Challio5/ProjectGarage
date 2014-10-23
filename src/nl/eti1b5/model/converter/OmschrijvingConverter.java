package nl.eti1b5.model.converter;

import java.util.HashMap;
import java.util.Map;

import nl.eti1b5.model.Omschrijving;
import javafx.util.StringConverter;

public class OmschrijvingConverter extends StringConverter<Omschrijving> {
	
	private Map<String, Omschrijving> omschrijvingsMap = new HashMap<>();
	
	@Override
	public Omschrijving fromString(String naam) {
		return omschrijvingsMap.get(naam);
	}

	@Override
	public String toString(Omschrijving monteur) {
		omschrijvingsMap.put(monteur.getNaam(), monteur);
		return monteur.getNaam();
	}

}
