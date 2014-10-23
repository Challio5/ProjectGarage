package nl.eti1b5.view.secretaresse.planningsscherm;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.eti1b5.database.dao.KlantDao;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.database.dao.OmschrijvingDao;
import nl.eti1b5.database.dao.PlanningDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Auto;
import nl.eti1b5.model.Klant;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Omschrijving;
import nl.eti1b5.model.Planning;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.model.converter.AutoConverter;
import nl.eti1b5.model.converter.KlantConverter;
import nl.eti1b5.model.converter.MonteurConverter;
import nl.eti1b5.model.converter.OmschrijvingConverter;

/**
 * GUI klasse met GridPane layout voor het toevoegen van een reparatie met planning
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 23 okt. 2014
 */

public class AddPlanningScherm extends GridPane{
	// DAOs voor communicatie met de database
	private PlanningDao planningDao;
	private MonteurDao monteurDao;
	private ReparatieDao reparatieDao;
	private KlantDao klantDao;
	private OmschrijvingDao omschrijvingDao;
	
	// Combobox voor het kiezen van een klant met auto
	private Label klantLabel;
	private ComboBox<Klant> klantKiezer;
	private ComboBox<Auto> autoKiezer;
	
	// Combobox voor het kiezen van een reparatieomschrijving
	private Label omschrijvingsLabel;
	private ComboBox<Omschrijving> omschrijvingsKiezer;
	
	// Checkbox en velden voor het zelf toevoegen van een reparatieomschrijving
	private CheckBox checkOverigeReparatie;
	private TextField overigeReparatie;
	private ComboBox<Integer> urenOmschrijvingKiezer;
	private ComboBox<Integer> minutenOmschrijvingKiezer;
	
	// Datepicker voor het kiezen van een datum voor de reparatie
	private Label dagLabel;
	private DatePicker datumPicker;
	
	// Comboboxes voor het selecteren van de tijd en minuten
	private Label tijdLabel;
	private ComboBox<Integer> urenKiezer;
	private ComboBox<Integer> minutenKiezer;
	
	// Combobox voor het selecteren van een monteur
	private Label monteurLabel;
	private ComboBox<Monteur> monteurKiezer;
	
	// Submitknop voor het wegschrijven van de gegevens naar de database
	private Button submit;
	
	/**
	 * Constructor voor initialiseren van de onderdelen van de GUI
	 * Voegt listeners toe voor interactieve werking met de gebruiker
	 */
	public AddPlanningScherm() {
		// Instellingen voor de correcte weergave van het scherm
		this.setPrefSize(600, 400);
		this.setPadding(new Insets(10));
		this.setHgap(10);
		this.setVgap(10);
		
		// DAOs voor communicatie met de database
		planningDao = new PlanningDao();
		monteurDao = new MonteurDao();
		reparatieDao = new ReparatieDao();
		klantDao = new KlantDao();
		omschrijvingDao = new OmschrijvingDao();
		
		// Combobox voor het kiezen van een klant met auto
		klantLabel = new Label("Klant");
		klantKiezer = new ComboBox<>();
		klantKiezer.setConverter(new KlantConverter());
		klantKiezer.setItems(FXCollections.observableArrayList(klantDao.getKlanten()));
		
		autoKiezer = new ComboBox<>();
		autoKiezer.setConverter(new AutoConverter());
		
		// Listener voor het selecteren van een klant
		klantKiezer.getSelectionModel().selectedItemProperty().addListener((klant, oldValue, newValue) -> {
			autoKiezer.setItems(FXCollections.observableArrayList(newValue.getAutoLijst()));
		});
		
		
		// Combobox voor het kiezen van een reparatieomschrijving
		omschrijvingsLabel = new Label("Reparatie");
		omschrijvingsKiezer = new ComboBox<>();
		omschrijvingsKiezer.setConverter(new OmschrijvingConverter());
		omschrijvingsKiezer.setItems(FXCollections.observableArrayList(omschrijvingDao.getOmschrijvingen()));
		
		// Listener voor het selecteren van een reparatie in de combobox
		omschrijvingsKiezer.getSelectionModel().selectedItemProperty().addListener((reparatie, oldValue, newValue) -> {
			String naam = newValue.getNaam();
			Time tijd = newValue.getDuur();
			ArrayList<Planning> planningsLijst = planningDao.getPlanning();
			LocalDateTime datum = LocalDateTime.now();
			for(Planning planning : planningsLijst) {
				if(planning.getBeginTijd().toLocalDateTime().isEqual(datum)) {
					datum = datum.plusDays(1);
				}
			}
			datumPicker.setValue(datum.toLocalDate());
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
		
		// Checkbox en velden voor het zelf toevoegen van een reparatieomschrijving
		checkOverigeReparatie = new CheckBox();
		overigeReparatie = new TextField();
		overigeReparatie.setDisable(true);
		
		urenOmschrijvingKiezer = new ComboBox<>();
		urenOmschrijvingKiezer.getItems().addAll(0, 1, 2, 3);
		minutenOmschrijvingKiezer = new ComboBox<>();
		minutenOmschrijvingKiezer.getItems().addAll(0, 15, 30, 45);
		
		// Listener voor het kiezen of zelf toevoegen van een reparatieomschrijving
		checkOverigeReparatie.selectedProperty().addListener((check, oldValue, newValue) -> {
			if(newValue.booleanValue()) {
				overigeReparatie.setDisable(false);
				omschrijvingsKiezer.setDisable(true);
			}
			else {
				overigeReparatie.setDisable(true);
				omschrijvingsKiezer.setDisable(false);
			}
		});
		
		// Datepicker voor het kiezen van een datum voor de reparatie
		dagLabel = new Label("Datum");
		datumPicker = new DatePicker();
		
		// Comboboxes voor het selecteren van de tijd en minuten
		tijdLabel = new Label("Tijd");
		urenKiezer = new ComboBox<>();
		urenKiezer.getItems().addAll(9, 10, 11, 12, 13, 14, 15, 16, 17);
		minutenKiezer = new ComboBox<>();
		minutenKiezer.getItems().addAll(0, 15, 30, 45);
		
		// Combobox voor het selecteren van een monteur
		monteurLabel = new Label("Monteur");
		monteurKiezer = new ComboBox<>();
		monteurKiezer.setConverter(new MonteurConverter());
		monteurKiezer.setItems(FXCollections.observableArrayList(monteurDao.getMonteurs()));
		
		// Submitknop voor het wegschrijven van de gegevens naar de database
		submit = new Button("Submit");
		
		
		
		// Klant en autokiezer
		this.add(klantLabel, 0, 0);
		this.add(klantKiezer, 1, 0);
		this.add(autoKiezer, 2, 0);
		
		// Omschrijvingskiezer
		this.add(omschrijvingsLabel, 0, 1);
		this.add(omschrijvingsKiezer, 1, 1);
		
		this.add(checkOverigeReparatie, 0, 2);
		this.add(overigeReparatie, 1, 2);
		this.add(urenOmschrijvingKiezer, 2, 2);
		this.add(minutenOmschrijvingKiezer, 3, 2);
		
		// Datumkiezer
		this.add(dagLabel, 0, 3);
		this.add(datumPicker, 1, 3);
		
		// Minutenkiezer
		this.add(tijdLabel, 0, 4);
		this.add(urenKiezer, 1, 4);
		this.add(minutenKiezer, 2, 4);
		
		// Monteurkiezer
		this.add(monteurLabel, 0, 5);
		this.add(monteurKiezer, 1, 5);
		
		// Submitknop
		this.add(submit, 0, 6);
	}
	
	public void setSubmitListener() {
		// Listener voor een druk op de submit knop
		submit.setOnAction(click -> {
			// Vraagt de datum op voor de te plannen reparatie
			LocalDate datum = datumPicker.getValue();

			// Vraagt de tijd op voor de te plannen reparatie
			int uren = urenKiezer.getSelectionModel().getSelectedItem();
			int minuten = minutenKiezer.getSelectionModel().getSelectedItem();
			
			// Vraagt de duur op van de reparatie
			int urenOmschrijving = urenOmschrijvingKiezer
					.getSelectionModel().getSelectedItem();
			int minutenOmschrijving = minutenOmschrijvingKiezer
					.getSelectionModel().getSelectedItem();
			
			// Berekent de begin- en eindtijd
			LocalTime begintijd = LocalTime.of(uren, minuten);
			LocalTime eindtijd = begintijd.plusHours(urenOmschrijving);
			eindtijd = eindtijd.plusMinutes(minutenOmschrijving);

			// Voegt tijd en datum samen
			LocalDateTime beginDatumtijd = LocalDateTime.of(datum, begintijd);
			LocalDateTime eindDatumtijd = LocalDateTime.of(datum, eindtijd);

			// Zet om naar sql de begin- en eindtijd
			Timestamp sqlbegintijd = Timestamp.valueOf(beginDatumtijd);
			Timestamp sqleindtijd = Timestamp.valueOf(eindDatumtijd); 
			
			// De monteur voor de geplande reparatie
			Monteur monteur = monteurKiezer.getSelectionModel()
					.getSelectedItem();

			// De in te plannen reparatie
			Reparatie reparatie = new Reparatie();

			// Het kenteken van de auto van klant
			Auto auto = autoKiezer.getSelectionModel().getSelectedItem();
			reparatie.setKenteken(auto.getKenteken());

			// De omschrijving van de reparatie
			if (checkOverigeReparatie.isSelected()) {

				LocalTime tijdOmschrijving = LocalTime.of(urenOmschrijving,
						minutenOmschrijving);
				Time sqlTijd = Time.valueOf(tijdOmschrijving);

				// Voeg omschrijving toe aan database en
				// omschrijvingDao.addOmschrijving(new Omschrij)
				reparatie.setOmschrijving(new Omschrijving(overigeReparatie
						.getText(), sqlTijd));
			} else {
				reparatie.setOmschrijving(omschrijvingsKiezer
						.getSelectionModel().getSelectedItem());
			}

			// Voegt de reparatie toe aan de database
			reparatieDao.addReparatie(reparatie);

			// Voegt de planning toe voor de reparatie aan de database
			Planning planning = new Planning(sqlbegintijd, sqleindtijd, monteur,
					reparatie);
			planningDao.addPlanning(planning);
		});
	}
}
