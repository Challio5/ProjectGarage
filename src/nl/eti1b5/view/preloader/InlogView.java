package nl.eti1b5.view.preloader;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.VBox;

/**
 * GUI klasse, inlogscherm
 * View met de inlognode, knop en functiekiezer, om in te loggen in het programma
 * 
 * @author Rob Bonhof
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
		
		inlog = new InlogNode();
		Reflection reflection = new Reflection();
		inlog.setEffect(reflection);
		
		submit = new Button("Submit");
		
		this.getChildren().addAll(job, inlog, submit);
	}
	
	/**
	 * Setter voor het toevoegen van een actionlistener aan de submitknop
	 * @param event Het actionevent wat plaats vindt op het moment de knop wordt ingedrukt
	 */
	public void setSubmitListener(EventHandler<ActionEvent> event) {
		submit.setOnAction(event);
	}
	
	public InlogNode getInlogNode(){
		return inlog;
	}
	
	public ComboBox<String> getJob(){
		return job;
	}
}
