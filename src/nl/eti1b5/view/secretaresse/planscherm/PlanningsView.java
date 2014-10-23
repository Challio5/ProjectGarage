package nl.eti1b5.view.secretaresse.planscherm;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.database.dao.PlanningDao;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.MonteurConverter;
import nl.eti1b5.model.Omschrijving;
import nl.eti1b5.model.Planning;

public class PlanningsView extends GridPane{
	private PlanningDao planningDao;
	private MonteurDao monteurDao;
	
	private Label reparatieLabel;
	private ComboBox<Omschrijving> reparatieSelectie;
	private CheckBox checkOverigeReparatie;
	private TextField overigeReparatie;
	
	private Label dagLabel;
	private DatePicker datumPicker;
	
	private Label tijdLabel;
	private ComboBox<String> begintTijd;
	private ComboBox<String> eindTijd;
	
	private Label monteurLabel;
	private ComboBox<Monteur> monteurKiezer;
	
	private Button submit;
	
	public PlanningsView() {
		planningDao = new PlanningDao();
		monteurDao = new MonteurDao();
		
		this.setPrefSize(600, 400);
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
		reparatieSelectie.getItems().add(new Omschrijving("Carosserie", 1.0));
		reparatieSelectie.getItems().add(new Omschrijving("Motor", 2.5));
		
		dagLabel = new Label("Datum");
		datumPicker = new DatePicker();
		
		tijdLabel = new Label("Tijd");
		begintTijd = new ComboBox<>();
		eindTijd = new ComboBox<>();
		
		monteurLabel = new Label("Monteur");
		monteurKiezer = new ComboBox<>();
		monteurKiezer.setConverter(new MonteurConverter());
		ArrayList<Monteur> monteurs = monteurDao.getMonteurs();
		for(Monteur monteur : monteurs) {
			monteurKiezer.getItems().add(monteur);
		}
		
		submit = new Button("Submit");
		submit.setOnAction(click -> {
			// voeg toe aan database
			
		});
		
		this.add(reparatieLabel, 0, 0);
		this.add(reparatieSelectie, 1, 0);
		this.add(checkOverigeReparatie, 2, 0);
		this.add(overigeReparatie, 3, 0);
		
		this.add(dagLabel, 0, 1);
		this.add(datumPicker, 1, 1);
		
		this.add(tijdLabel, 0, 2);
		this.add(begintTijd, 1, 2);
		this.add(eindTijd, 2, 2);
		
		this.add(monteurLabel, 0, 3);
		this.add(monteurKiezer, 1, 3);
		
		this.add(submit, 0, 4);
		
		// Listener voor het selecteren van een reparatie in de combobox
		reparatieSelectie.getSelectionModel().selectedItemProperty().addListener((reparatie, oldValue, newValue) -> {
			String naam = newValue.getNaam();
			double tijd = newValue.getDuur();
			ArrayList<Planning> planningsLijst = planningDao.getPlanning();
			LocalDate datum = LocalDate.now();
			for(Planning planning : planningsLijst) {
				if(planning.getBeginTijd().toLocalDate().isEqual(datum)) {
					datum = datum.plusDays(1);
				}
			}
			datumPicker.setValue(datum);
			ArrayList<Monteur> monteursLijst = monteurDao.getMonteurs();
			for(Monteur monteur : monteursLijst) {
				if(monteur.getSpecialiteit().equals(naam)) {
					ArrayList<String> beschikbaarheidsLijst = new ArrayList<>(monteur.getBeschikbaarheid());
					String datumcode = datum.getDayOfWeek().toString().substring(0, 2);
					for(String beschikbaarheidsCode : beschikbaarheidsLijst) {
						beschikbaarheidsCode = beschikbaarheidsCode.substring(0, 2);
						System.out.println(monteur.getNaam() + " beschikbaar op: " + beschikbaarheidsCode + " datumcode vandaag: " + datumcode);
						if(beschikbaarheidsCode.equals(datumcode)) {
							monteurKiezer.getSelectionModel().select(monteur);
						}
					}
				}
			}
		}); 
	}
}
