package nl.eti1b5.view.preloader;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 * GUI klasse, inlognode
 * Node met een tekst- en wachtwoordveld voor het invoeren van inloggevens
 * 
 * @author ETI2vb3
 * @since 24 sep. 2014
 * @version 1.0
 */

public class InlogNode extends GridPane {

	// Label en tekstveld voor de gebruikersnaam
	private Label namelabel;
	private TextField name;
	
	// Label en wachtwoordveld voor het wachtwoord
	private Label passwordlabel;
	private PasswordField password;
	
	/**
	 * Constructor voor het initialiseren en toevoegen van de verschillende onderdelen
	 * Voegt de class-selector '.login' toe voor identificatie met de stylesheet
	 */
	public InlogNode() {
		this.getStyleClass().add("login");
		
		namelabel = new Label("Naam");
		name = new TextField();
		
		passwordlabel = new Label("Wachtwoord");
		password = new PasswordField();
		
		this.add(namelabel, 0, 0);
		this.add(name, 1, 0);
		
		this.add(passwordlabel, 0, 1);
		this.add(password, 1, 1);
	}
	
	/**
	 * Getter voor het opvragen van het tekstveld voor het intypen van de gebruikersnaam
	 * @return Het tekstveld voor de gebruikersnaam
	 */
	public TextField getName(){
		return name;
	}
	
	/**
	 * Getter voor het opvragen van het tekst voor het intypen van het wachtwoord
	 * @return Het tekstveld voor het wachtwoord
	 */
	public TextField getPassword(){
		return password;
	}
	
	/**
	 * Methode om de tekstvelden voor de gebruikersnaam en wachtwoord uit te schakelen
	 */
	public void setLock(){
		name.setDisable(true);
		password.setDisable(true);
	}
	
}
