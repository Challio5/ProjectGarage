package nl.eti1b5.view.secretaresse.administratiescherm;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.eti1b5.controller.KlantToevoegControl;
import nl.eti1b5.model.Auto;
/**
 * Klasse die de popup weergeeft om een klant toe te voegen of aan een bestaande klant een auto toe te voegen.
 * @author Groep 5
 * @version 1.0
 */
public class AddKlantScherm extends BorderPane{
	
	private Stage newStage;
	private Button submit;
	private VBox labels;
	private VBox textFields;
	
	private Label naam;
	private Label adres;
	private Label postcode;
	private Label woonplaats;
	private Label telefoonNummer;
	
	private TextField addNaam;
	private TextField addAdres;
	private TextField addPostcode;
	private TextField addWoonplaats;
	private TextField addTelefoonNummer;
	
	private AddKlantNode klantNode;

	/**
	 * De constructor van deze klasse,
	 * De klasse bestaat uit een aantal labels en fields en een Node voor de auto's
	 */
	public AddKlantScherm(){
		labels = new VBox();
		labels.setPadding(new Insets(4));
		labels.setSpacing(8);
		
		naam = new Label("Naam: ");
		adres = new Label("Adres: ");
		postcode = new Label("Postcode: ");
		woonplaats = new Label("Woonplaats");
		telefoonNummer = new Label("Telefoonnummer: ");
		
		labels.getChildren().addAll(naam, adres, postcode, woonplaats, telefoonNummer);
		
		textFields = new VBox();
		textFields.setPadding(new Insets(4));
		
		addNaam = new TextField();
		addAdres = new TextField();
		addPostcode = new TextField();
		addWoonplaats = new TextField();
		addTelefoonNummer = new TextField();
		
		textFields.getChildren().addAll(addNaam, addAdres, addPostcode, addWoonplaats, addTelefoonNummer);
		
		klantNode = new AddKlantNode();
		
		submit = new Button("Toevoegen");
		submit.setOnAction(new KlantToevoegControl(this));
		
		this.setTop(submit);
		this.setLeft(labels);
		this.setRight(textFields);
		this.setBottom(klantNode);
		init();
	}
	/**
	 * Methode voor het initialiseren van een nieuwe Stage
	 */
	public void init(){
		newStage = new Stage();
		newStage.setTitle("Klant toevoegen");
		Scene stageScene = new Scene(this);
		newStage.setScene(stageScene);
		newStage.show();
	}

	/**
	 * Methode voor het opvragen van de naam
	 * @return De naam
	 */
	public String getNaam(){
		return addNaam.getText();
	}
	
	/**
	 * Methode voor het opvragen van het adres
	 * @return het adres
	 */
	public String getAdres(){
		return addAdres.getText();
	}
	
	/**
	 * Methode voor het opvragen van de postcode
	 * @return de postcode
	 */
	public String getPostcode(){
		return addPostcode.getText();
	}
	
	/**
	 * Methode voor het opvragen van de woonplaats
	 * @return de woonplaats
	 */
	public String getWoonplaats(){
		return addWoonplaats.getText();
	}
	
	/**
	 * Methode voor het opvragen van het telefoonnummer 
	 * @return de telefoon
	 */
	public String getTelefoonNummer(){
		return addTelefoonNummer.getText();
	}
	
	/**
	 * Methode voor het opvragen van de lijst met auto's
	 * @return de lijst met auto's
	 */
	public ArrayList<Auto> getAutos(){
		return klantNode.getAutos();
	}

	/**
	 * Methode voor het sluiten van de stage
	 */
	public void close() {
		newStage.close();
	}
}
