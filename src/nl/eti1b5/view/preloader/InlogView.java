package nl.eti1b5.view.preloader;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

/**
 * GUI klasse, inlogscherm
 * View met de inlognode, knop en functiekiezer, om in te loggen in het programma
 * 
 * @author ETI2vb3
 * @since 24 sep. 2014
 * @version 1.0
 */

public class InlogView extends VBox{
	// Combobox voor het selecteren van de functie
	private ComboBox<String> job;
	
	// Inlognode met het tekst- en wachtwoordveld
	private InlogNode inlog;
	
	// Knop voor het submitten van de inloggevens
	private Button submit;
	
	// Label voor het weergeven van een foutmelding
	private Label foutMelding;
	
	/**
	 * Constructor voor het initialiseren en toevoegen van de verschillende onderdelen
	 * Voegt de class-selector '.start' toe voor indentificatie met de stylesheet
	 */
	public InlogView() {
		this.getStyleClass().add("start");
		this.setMinSize(400, 400);
		this.setSpacing(50);
		
		job = new ComboBox<>();
		job.getItems().addAll("Monteur", "Secretaresse");
		job.getSelectionModel().selectFirst();
		
		inlog = new InlogNode();
		Reflection reflection = new Reflection();
		inlog.setEffect(reflection);
		
		submit = new Button("Submit");
		
		foutMelding = new Label();
		foutMelding.setId("Foutmelding");
		
		this.getChildren().addAll(job, inlog, submit, foutMelding);
	}
	
	/**
	 * Setter voor het toevoegen van een actionlistener aan de submitknop
	 * @param event Het actionevent wat plaats vindt op het moment de knop wordt ingedrukt
	 */
	public void setSubmitListener(EventHandler<ActionEvent> event) {
		submit.setOnAction(event);
	}
	
	/**
	 * Setter voor het toevoegen van een Keyevent aan de view
	 * @param event Keyevent voor het afhandelen van een toetsdruk
	 */
	public void setEnterListener(EventHandler<KeyEvent> event) {
		this.setOnKeyPressed(event);
	}
	
	/**
	 * Getter voor opvragen van de inlognode met de inlogvelden
	 * @return De inlognode met de inlogvelden
	 */
	public InlogNode getInlogNode(){
		return inlog;
	}
	
	/**
	 * Getter voor het opvragen van de combobox voor het selecteren van een monteur of secretaresse
	 * @return De combobox voor het selecteren van monteur of secretaresse
	 */
	public ComboBox<String> getJob(){
		return job;
	}
	
	/**
	 * Getter voor het opvragen van het label voor het weergeven van foutmeldingen
	 * @return Label voor foutmeldingen
	 */
	public Label getFoutMelding(){
		return foutMelding;
	}
}
