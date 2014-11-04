package nl.eti1b5.view.secretaresse.planningsscherm;

import java.time.LocalDate;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import nl.eti1b5.database.dao.KlantDao;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.database.dao.OmschrijvingDao;
import nl.eti1b5.model.Auto;
import nl.eti1b5.model.Klant;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Omschrijving;
import nl.eti1b5.model.converter.AutoConverter;
import nl.eti1b5.model.converter.KlantConverter;
import nl.eti1b5.model.converter.MonteurConverter;
import nl.eti1b5.model.converter.OmschrijvingConverter;

/**
 * GUI klasse met GridPane layout voor het toevoegen van een reparatie met planning
 * @author Projectgroep ETI2b3 2014-2015 kwartiel 1
 * @since 23 okt. 2014
 */

public class PlanningPopup extends GridPane{
	// DAOs voor communicatie met de database
	private MonteurDao monteurDao;
	private KlantDao klantDao;
	private OmschrijvingDao omschrijvingDao;
	
	// Combobox voor het kiezen van een klant met auto
	private Label klantLabel;
	private ComboBox<Klant> klantKiezer;
	private ComboBox<Auto> autoKiezer;
	
	// Checkbox en velden voor het zelf toevoegen van een reparatieomschrijving
	private Label overigeReparatieLabel;
	private Button overigeReparatieKnop;
	
	private CheckBox checkOverigeReparatie;
	private TextField overigeReparatie;
	private ComboBox<Integer> urenOverigeReparatieKiezer;
	private ComboBox<Integer> minutenOverigeReparatieKiezer;
	
	// Combobox voor het kiezen van een reparatieomschrijving
	private Label omschrijvingsLabel;
	private ComboBox<Omschrijving> omschrijvingsKiezer;
	private TextField omschrijvingsDuur;
	
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
	
	// Console voor weergave informatie
	private TitledPane console;
	private TextArea consoleText;
	
	/**
	 * Constructor voor initialiseren van de onderdelen van de GUI
	 * Voegt listeners toe voor interactieve werking met de gebruiker
	 */
	public PlanningPopup() {
		// Instellingen voor de correcte weergave van het scherm
		this.setPrefSize(600, 400);
		this.setPadding(new Insets(10));
		this.setHgap(10);
		this.setVgap(10);
		
		// DAOs voor communicatie met de database
		monteurDao = new MonteurDao();
		klantDao = new KlantDao();
		omschrijvingDao = new OmschrijvingDao();
		
		// Combobox voor het kiezen van een klant met auto
		klantLabel = new Label("Klant");
		klantKiezer = new ComboBox<>();
		klantKiezer.setConverter(new KlantConverter());
		klantKiezer.setItems(FXCollections.observableArrayList(klantDao.getKlanten()));
		
		autoKiezer = new ComboBox<>();
		autoKiezer.setConverter(new AutoConverter());
		
		// Listener voor die op basis van de klant de juiste auto's weergeeft
		klantKiezer.getSelectionModel().selectedItemProperty().addListener((klant, oldValue, newValue) -> {
			autoKiezer.setItems(FXCollections.observableArrayList(newValue.getAutoLijst()));
		});
		
		// Checkbox en velden voor het zelf toevoegen van een reparatieomschrijving
		overigeReparatieLabel = new Label("Reparatie Gegevens");
		checkOverigeReparatie = new CheckBox();
		
		overigeReparatie = new TextField();
		overigeReparatie.setDisable(true);
		
		urenOverigeReparatieKiezer = new ComboBox<>();
		urenOverigeReparatieKiezer.getItems().addAll(0, 1, 2, 3);
		urenOverigeReparatieKiezer.setDisable(true);
		
		minutenOverigeReparatieKiezer = new ComboBox<>();
		minutenOverigeReparatieKiezer.getItems().addAll(0, 15, 30, 45);
		minutenOverigeReparatieKiezer.setDisable(true);
		
		overigeReparatieKnop = new Button("Submit");
		overigeReparatieKnop.setDisable(true);
		
		// Combobox voor het kiezen van een reparatieomschrijving
		omschrijvingsLabel = new Label("Reparatie");
		omschrijvingsKiezer = new ComboBox<>();
		omschrijvingsKiezer.setConverter(new OmschrijvingConverter());
		omschrijvingsKiezer.setItems(FXCollections.observableArrayList(omschrijvingDao.getOmschrijvingen()));
		omschrijvingsDuur = new TextField();
		omschrijvingsDuur.setEditable(false);
	
		// Datepicker voor het kiezen van een datum voor de reparatie
		dagLabel = new Label("Datum");
		datumPicker = new DatePicker();
		datumPicker.setDisable(true);
		
		// Comboboxes voor het selecteren van de tijd en minuten
		tijdLabel = new Label("Tijd");
		urenKiezer = new ComboBox<>();
		urenKiezer.getItems().addAll(9, 10, 11, 12, 13, 14, 15, 16, 17);
		urenKiezer.setDisable(true);
		minutenKiezer = new ComboBox<>();
		minutenKiezer.getItems().addAll(0, 15, 30, 45);
		minutenKiezer.setDisable(true);
		
		// Combobox voor het selecteren van een monteur
		monteurLabel = new Label("Monteur");
		monteurKiezer = new ComboBox<>();
		monteurKiezer.setConverter(new MonteurConverter());
		monteurKiezer.setItems(FXCollections.observableArrayList(monteurDao.getMonteurs()));
		monteurKiezer.setDisable(true);
		
		// Submitknop voor het wegschrijven van de gegevens naar de database
		submit = new Button("Submit");
		submit.setDisable(true);
		
		// Console voor het weergeven van informatie
		console = new TitledPane();
		consoleText = new TextArea();
		console.setText("Console");
		console.setExpanded(false);
		console.setContent(consoleText);
		
		// Klant en autokiezer
		this.add(klantLabel, 0, 0);
		this.add(klantKiezer, 1, 0);
		this.add(autoKiezer, 2, 0);
		
		// Overige omschrijving
		this.add(overigeReparatieLabel, 0, 1);
		this.add(checkOverigeReparatie, 1, 1);
		
		this.add(overigeReparatie, 0, 2);
		this.add(urenOverigeReparatieKiezer, 1, 2);
		this.add(minutenOverigeReparatieKiezer, 2, 2);
		this.add(overigeReparatieKnop, 3, 2);
		
		// Omschrijvingskiezer
		this.add(omschrijvingsLabel, 0, 3);
		this.add(omschrijvingsKiezer, 1, 3);
		this.add(omschrijvingsDuur, 2, 3);
		
		// Datumkiezer
		this.add(dagLabel, 0, 4);
		this.add(datumPicker, 1, 4);
		
		// Minutenkiezer
		this.add(tijdLabel, 0, 5);
		this.add(urenKiezer, 1, 5);
		this.add(minutenKiezer, 2, 5);
		
		// Monteurkiezer
		this.add(monteurLabel, 0, 6);
		this.add(monteurKiezer, 1, 6);
		
		// Submitknop
		this.add(submit, 0, 7);
		
		// Console
		this.add(console, 0, 8, 4, 1);
	}
	
	public void enableOverigeReparatie() {
		// Zet de nodes in de GUI aan voor het toevoegen van een reparatieomschrijving
		overigeReparatie.setDisable(false);
		urenOverigeReparatieKiezer.setDisable(false);
		minutenOverigeReparatieKiezer.setDisable(false);
		overigeReparatieKnop.setDisable(false);
		
		omschrijvingsKiezer.setDisable(true);
	}
	
	public void disableOverigeReparatie() {
		// Zet de nodes in de GUI uit voor het toevoegen van een reparatieomschrijving
		overigeReparatie.setDisable(true);
		urenOverigeReparatieKiezer.setDisable(true);
		minutenOverigeReparatieKiezer.setDisable(true);
		overigeReparatieKnop.setDisable(true);
		
		omschrijvingsKiezer.setDisable(false);
	}
	
	// Event Handlers
	public void setCheckOverigeReparatieChangeListener(ChangeListener<Boolean> listener) {
		checkOverigeReparatie.selectedProperty().addListener(listener);
	}
	
	public void setOverigeReparatieActionEvent(EventHandler<ActionEvent> e) {
		overigeReparatieKnop.setOnAction(e);
	}
	
	public void setOmschrijvingsKiezerChangeListener(ChangeListener<Omschrijving> listener){
		omschrijvingsKiezer.getSelectionModel().selectedItemProperty().addListener(listener);
	}
	
	public void setDatumPickerValueListener(ChangeListener<LocalDate> listener) {
		datumPicker.valueProperty().addListener(listener);
	}
	
	public void setSubmitActionEvent(EventHandler<ActionEvent> e) {
		submit.setOnAction(e);
	}

	// Getters
	public ComboBox<Klant> getKlantKiezer() {
		return klantKiezer;
	}

	public ComboBox<Auto> getAutoKiezer() {
		return autoKiezer;
	}

	public Button getOverigeReparatieKnop() {
		return overigeReparatieKnop;
	}

	public CheckBox getCheckOverigeReparatie() {
		return checkOverigeReparatie;
	}

	public TextField getOverigeReparatie() {
		return overigeReparatie;
	}

	public ComboBox<Integer> getUrenOverigeReparatieKiezer() {
		return urenOverigeReparatieKiezer;
	}

	public ComboBox<Integer> getMinutenOverigeReparatieKiezer() {
		return minutenOverigeReparatieKiezer;
	}

	public ComboBox<Omschrijving> getOmschrijvingsKiezer() {
		return omschrijvingsKiezer;
	}

	public TextField getOmschrijvingsDuur() {
		return omschrijvingsDuur;
	}

	public DatePicker getDatumPicker() {
		return datumPicker;
	}

	public ComboBox<Integer> getUrenKiezer() {
		return urenKiezer;
	}

	public ComboBox<Integer> getMinutenKiezer() {
		return minutenKiezer;
	}

	public ComboBox<Monteur> getMonteurKiezer() {
		return monteurKiezer;
	}

	public Button getSubmit() {
		return submit;
	}

	public TextArea getConsoleText() {
		return consoleText;
	}
}
