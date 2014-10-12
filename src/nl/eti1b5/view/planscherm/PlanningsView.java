package nl.eti1b5.view.planscherm;

import java.sql.Date;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Omschrijving;

public class PlanningsView extends GridPane{
	private ReparatieDao dao;
	
	private Label reparatieLabel;
	private ComboBox<Omschrijving> reparatieSelectie;
	private CheckBox checkOverigeReparatie;
	private TextField overigeReparatie;
	
	private Label dagLabel;
	private ComboBox<Date> dag;
	
	private Label tijdLabel;
	private ComboBox<String> begintTijd;
	private ComboBox<String> eindTijd;
	
	private Label monteurLabel;
	private ComboBox<String> monteur;
	
	private Button submit;
	
	public PlanningsView() {
		this.setPrefSize(400, 400);
		this.setPadding(new Insets(10));
		this.setHgap(10);
		this.setVgap(10);
		
		reparatieLabel = new Label("Reparatie");
		reparatieSelectie = new ComboBox<>();
		checkOverigeReparatie = new CheckBox();
		overigeReparatie = new TextField();
		overigeReparatie.setDisable(true);
		
		checkOverigeReparatie.selectedProperty().addListener((check, oldValue, newValue) -> {
			if(newValue.booleanValue()) {
				overigeReparatie.setDisable(false);
				reparatieSelectie.setDisable(true);
			}
			else {
				overigeReparatie.setDisable(true);
				reparatieSelectie.setDisable(false);
			}
		});
		
		// Voeg reparaties toe aan de combobox
		reparatieSelectie.getItems().add(new Omschrijving("Banden", 1.5));
		reparatieSelectie.getItems().add(new Omschrijving("Lakschade", 1.0));
		reparatieSelectie.getItems().add(new Omschrijving("Grote Beurt", 2.5));
		
		// Listener voor het selecteren van een reparatie in de combobox
		reparatieSelectie.getSelectionModel().selectedItemProperty().addListener((reparatie, oldValue, newValue) -> {
			double tijd = newValue.getTijd();
			//dao.getPlanning()
		}); 
		
		dagLabel = new Label("Datum");
		dag = new ComboBox<>();
		
		tijdLabel = new Label("Tijd");
		begintTijd = new ComboBox<>();
		eindTijd = new ComboBox<>();
		
		monteurLabel = new Label("Monteur");
		monteur = new ComboBox<>();
		
		submit = new Button("Submit");
		submit.setOnAction(click -> {
			// voeg toe aan database
			
		});
		
		this.add(reparatieLabel, 0, 0);
		this.add(reparatieSelectie, 1, 0);
		this.add(checkOverigeReparatie, 2, 0);
		this.add(overigeReparatie, 3, 0);
		
		this.add(dagLabel, 0, 1);
		this.add(dag, 1, 1);
		
		this.add(tijdLabel, 0, 2);
		this.add(begintTijd, 1, 2);
		this.add(eindTijd, 2, 2);
		
		this.add(monteurLabel, 0, 3);
		this.add(monteur, 1, 3);
		
		this.add(submit, 0, 4);
	}
}
