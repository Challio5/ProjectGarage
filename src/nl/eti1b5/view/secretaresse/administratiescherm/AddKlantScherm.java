package nl.eti1b5.view.secretaresse.administratiescherm;

import java.util.ArrayList;

import nl.eti1b5.controller.KlantToevoegControl;
import nl.eti1b5.model.Auto;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

	public void init(){
		newStage = new Stage();
		newStage.setTitle("Klant toevoegen");
		Scene stageScene = new Scene(this);
		newStage.setScene(stageScene);
		newStage.show();
	}
	
	public String getNaam(){
		return addNaam.getText();
	}
	
	public String getAdres(){
		return addAdres.getText();
	}
	
	public String getPostcode(){
		return addPostcode.getText();
	}
	
	public String getWoonplaats(){
		return addWoonplaats.getText();
	}
	
	public String getTelefoonNummer(){
		return addTelefoonNummer.getText();
	}
	
	public ArrayList<Auto> getAutos(){
		return klantNode.getAutos();
	}
}
