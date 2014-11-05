package nl.eti1b5.view.secretaresse.reparatiescherm;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.eti1b5.model.Omschrijving;

/**
 * Gui popup klasse voor het weergeven van de attributen van een omschrijving
 * Wordt aangemaakt op het moment de gebruiker de details wil weten van een omschrijving
 * Verschijnt bovenop het hoofdscherm
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */

public class OmschrijvingsPopup extends GridPane{
	// Tekstveld en label voor het weergeven van de naam
	private Label naamLabel;
	private TextField naam;

	// Tekstveld en label voor het weergeven van de duur
	private Label duurLabel;
	private TextField duur;
	
	/**
	 * Constructor voor het initialiseren van de nodes voor het weergeven van de details
	 * Voegt ze toen in de gridpane
	 * @param omschrijving De weer te geven omschrijving
	 */
	public OmschrijvingsPopup(Omschrijving omschrijving) {
		this.setPadding(new Insets(10));
		
		this.setHgap(10);
		this.setVgap(10);
		
		naamLabel = new Label("Naam");
		naam = new TextField();
		naam.setEditable(false);
		naam.setText(omschrijving.getNaam());
		
		duurLabel = new Label("Duur");
		duur = new TextField();
		duur.setEditable(false);
		duur.setText(omschrijving.getDuur().toString());
		
		this.add(naamLabel, 0, 0);
		this.add(naam, 1, 0);
		
		this.add(duurLabel, 0, 1);
		this.add(duur, 1, 1);
	}
}
