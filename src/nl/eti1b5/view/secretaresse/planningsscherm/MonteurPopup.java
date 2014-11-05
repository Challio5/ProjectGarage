package nl.eti1b5.view.secretaresse.planningsscherm;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.eti1b5.model.Monteur;

/**
 * GUI Popup klasse voor het weergeven van de details van een monteur
 * Wordt aangemaakt op het moment de gebruiker de details wil weten
 * Attributen van een bepaalde monteur worden dan weergegeven
 * Verschijnt bovenop het hoofdscherm
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */

public class MonteurPopup extends GridPane{
	// Label en tekstveld voor het werknemernummer van de monteur
	private Label werknemerNrLabel;
	private TextField werknemerNr;
	
	// Label en tekstveld voor de naam van de monteur
	private Label naamLabel;
	private TextField naam;
	
	// Label en tekstveld voor het adres van de monteur
	private Label adresLabel;
	private TextField adres;

	// Label en tekstveld voor de postcode van de monteur
	private Label postcodeLabel;
	private TextField postcode;
	
	// Label en tekstveld voor de woonplaats van de monteur
	private Label woonplaatsLabel;
	private TextField woonplaats;
	
	// Label en tekstveld voor het telefoonnummer van de monteur
	private Label telNrLabel;
	private TextField telNr;
	
	// Label en tekstveld voor het wachtwoord van de monteur
	private Label wachtwoordLabel;
	private TextField wachtwoord;
	
	// Label en tekstveld voor de specialiteit van de monteur
	private Label specialiteitLabel;
	private TextField specialiteit;
	
	// Label en tekstveld voor de beschikbaarheid van de monteur
	private Label beschikbaarheidsLijstLabel;
	private TextField beschikbaarheidsLijst;
	
	// Label en tekstveld voor de reparaties van de monteur
	private Label reparatieLijstLabel;
	private TextField reparatieLijst;
	
	/**
	 * Constructor voor het initialiseren van de labels en tekstvelden
	 * Labels en tekstvelden komen overeen met
	 * @param monteur De weer te geven monteur
	 */
	public MonteurPopup(Monteur monteur) {
		this.setPadding(new Insets(10));
		
		this.setHgap(10);
		this.setVgap(10);
		
		werknemerNrLabel = new Label("Monteurnummer");
		werknemerNr = new TextField();
		werknemerNr.setEditable(false);
		werknemerNr.setText(String.valueOf(monteur.getWerknemerNr()));
		
		naamLabel = new Label("Naam");
		naam = new TextField();
		naam.setEditable(false);
		naam.setText(monteur.getNaam());
		
		adresLabel = new Label("Adres");
		adres = new TextField();
		adres.setEditable(false);
		adres.setText(monteur.getAdres());
		
		postcodeLabel = new Label("Postcode");
		postcode = new TextField();
		postcode.setEditable(false);
		postcode.setText(monteur.getPostcode());
		
		woonplaatsLabel = new Label("Woonplaats");
		woonplaats = new TextField();
		woonplaats.setEditable(false);
		woonplaats.setText(monteur.getWoonplaats());
		
		telNrLabel = new Label("Telefoonnummer");
		telNr = new TextField();
		telNr.setEditable(false);
		telNr.setText(monteur.getTelNr());
		
		wachtwoordLabel = new Label("Wachtwoord");
		wachtwoord = new TextField();
		wachtwoord.setEditable(false);
		wachtwoord.setText(monteur.getWachtwoord());
		
		specialiteitLabel = new Label("Specialiteit");
		specialiteit = new TextField();
		specialiteit.setEditable(false);
		specialiteit.setText(monteur.getSpecialiteit());
		
		beschikbaarheidsLijstLabel = new Label("Beschikbaarheid");
		beschikbaarheidsLijst = new TextField();
		beschikbaarheidsLijst.setEditable(false);
		beschikbaarheidsLijst.setText(monteur.getBeschikbaarheid().toString());
		
		reparatieLijstLabel = new Label("Reparaties");
		reparatieLijst = new TextField();
		reparatieLijst.setEditable(false);
		reparatieLijst.setText(monteur.getReparaties().toString());
		
		this.add(werknemerNrLabel, 0, 0);
		this.add(werknemerNr, 1, 0);
		
		this.add(naamLabel, 0, 1);
		this.add(naam, 1, 1);
		
		this.add(adresLabel, 0, 2);
		this.add(adres, 1, 2);
		
		this.add(postcodeLabel, 0, 3);
		this.add(postcode, 1, 3);
		
		this.add(woonplaatsLabel, 0, 4);
		this.add(woonplaats, 1, 4);
		
		this.add(telNrLabel, 0, 5);
		this.add(telNr, 1, 5);
		
		this.add(wachtwoordLabel, 0, 6);
		this.add(wachtwoord, 1, 6);
		
		this.add(specialiteitLabel, 0, 7);
		this.add(specialiteit, 1, 7);
		
		this.add(beschikbaarheidsLijstLabel, 0, 8);
		this.add(beschikbaarheidsLijst, 1, 8);
		
		this.add(reparatieLijstLabel, 0, 9);
		this.add(reparatieLijst, 1, 9);
	}
}
