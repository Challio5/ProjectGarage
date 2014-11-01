package nl.eti1b5.view.secretaresse.administratiescherm;

import nl.eti1b5.controller.KlantToevoegControl;
import nl.eti1b5.model.Auto;
import nl.eti1b5.model.Materiaal;
import nl.eti1b5.view.monteur.reparatiescherm.ReparatiePopup;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private Label auto;
	
	private TextField addNaam;
	private TextField addAdres;
	private TextField addPostcode;
	private TextField addWoonplaats;
	private TextField addTelefoonNummer;
	private Button addAuto;
	
	private TableView<Auto> autos;
	
	private TableColumn<Auto, String> autoMerk;
	private TableColumn<Auto, String> autoKenteken;
	private TableColumn<Auto, String> autoModel;

	
	public AddKlantScherm(){
		labels = new VBox();
		labels.setPadding(new Insets(4));
		labels.setSpacing(8);
		
		naam = new Label("Naam: ");
		adres = new Label("Adres: ");
		postcode = new Label("Postcode: ");
		woonplaats = new Label("Woonplaats");
		telefoonNummer = new Label("Telefoonnummer: ");
		auto = new Label("Auto: ");
		
		labels.getChildren().addAll(naam, adres, postcode, woonplaats, telefoonNummer, auto);
		
		textFields = new VBox();
		textFields.setPadding(new Insets(4));
		
		addNaam = new TextField();
		addAdres = new TextField();
		addPostcode = new TextField();
		addWoonplaats = new TextField();
		addTelefoonNummer = new TextField();
		addAuto = new Button("Toevoegen");
		
		textFields.getChildren().addAll(addNaam, addAdres, addPostcode, addWoonplaats, addTelefoonNummer, addAuto);
		
		autos = new TableView<Auto>();
		
		autoKenteken = new TableColumn<Auto, String>("Kenteken");
		autoKenteken.setMinWidth(150);
		autoMerk = new TableColumn<Auto, String>("Merk");
		autoMerk.setMinWidth(150);
		autoModel = new TableColumn<Auto, String>("Model");
		autoModel.setMinWidth(150);
		
		
		submit = new Button("Toevoegen");
		submit.setOnAction(new KlantToevoegControl(this));
		
		this.setTop(submit);
		this.setLeft(labels);
		this.setRight(textFields);
		this.setBottom(autos);
		init();
	}

	public void init(){
		newStage = new Stage();
		newStage.setTitle("Klant toevoegen");
		Scene stageScene = new Scene(this);
		newStage.setScene(stageScene);
		newStage.show();
	}
}
