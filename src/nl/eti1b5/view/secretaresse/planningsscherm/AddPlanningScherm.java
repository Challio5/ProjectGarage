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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
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
		
		// Listener die schakelt tussen het zelf invoeren van een reparatie of een geselecteerde reparatie
		this.setOverigeReparatieListener();
		
		// Listener die een nieuwe reparatieomschrijving toevoegd
		this.setOmschrijvingListener();
		
		// Combobox voor het kiezen van een reparatieomschrijving
		omschrijvingsLabel = new Label("Reparatie");
		omschrijvingsKiezer = new ComboBox<>();
		omschrijvingsKiezer.setConverter(new OmschrijvingConverter());
		omschrijvingsKiezer.setItems(FXCollections.observableArrayList(omschrijvingDao.getOmschrijvingen()));
		omschrijvingsDuur = new TextField();
		omschrijvingsDuur.setEditable(false);
		
		// Listener die op basis van de ingevoerde gegevens dag en evt. gespecialiseerde monteur kiest
		this.setPreselectionListener();
	
		// Datepicker voor het kiezen van een datum voor de reparatie
		dagLabel = new Label("Datum");
		datumPicker = new DatePicker();
		datumPicker.setDayCellFactory(new DatumPickerCallback());
		
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
		this.setSubmitListener();
		
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
	}
	
	public void setOverigeReparatieListener() {
		// Listener voor het kiezen of zelf toevoegen van een reparatieomschrijving
		checkOverigeReparatie.selectedProperty().addListener((check, oldValue, newValue) -> {
			if(newValue.booleanValue()) {
				// Zet de nodes in de GUI aan voor het toevoegen van een reparatieomschrijving
				overigeReparatie.setDisable(false);
				urenOverigeReparatieKiezer.setDisable(false);
				minutenOverigeReparatieKiezer.setDisable(false);
				overigeReparatieKnop.setDisable(false);
				
				omschrijvingsKiezer.setDisable(true);
			}
			else {
				// Zet de nodes in de GUI uit voor het toevoegen van een reparatieomschrijving
				overigeReparatie.setDisable(true);
				urenOverigeReparatieKiezer.setDisable(true);
				minutenOverigeReparatieKiezer.setDisable(true);
				overigeReparatieKnop.setDisable(true);
				
				omschrijvingsKiezer.setDisable(false);
			}
		});
	}
	
	public void setOmschrijvingListener() {
		// Listener voor het toevoegen van een overige reparatie
		overigeReparatieKnop.setOnAction(e -> {
			// De ingevoerde gegevens uit de GUI
			String reparatieNaam = overigeReparatie.getText();
			int reparatieDuurUren = urenOverigeReparatieKiezer.getSelectionModel().getSelectedItem();
			int reparatieDuurMinuten = minutenOverigeReparatieKiezer.getSelectionModel().getSelectedItem();
			
			// Omrekenen van de uren en minuten naar sqlformaat
			Time reparatieDuur = Time.valueOf(LocalTime.of(reparatieDuurUren, reparatieDuurMinuten));
			
			// Maakt met de gegevens een omschrijving aan en voegt deze toe aan de database
			Omschrijving omschrijving = new Omschrijving(reparatieNaam, reparatieDuur);
			omschrijvingDao.addOmschrijving(omschrijving);
			
			// Update de GUI met deze nieuwe omschrijving, vinkt de selectie uit en selecteert de nieuwe omschrijving
			omschrijvingsKiezer.getItems().add(omschrijving);
			omschrijvingsKiezer.getSelectionModel().select(omschrijving);
			checkOverigeReparatie.setSelected(false);
		});
	}
	
	public void setPreselectionListener() {
		// Listener voor het selecteren van een reparatie in de combobox
		omschrijvingsKiezer.getSelectionModel().selectedItemProperty().addListener((omschrijving, oldValue, newValue) -> {
			// Haalt de gegevens op van de geselecteerde omschrijving
			String naam = newValue.getNaam();
			Time tijd = newValue.getDuur();
			
			// Geeft de duur weer in de GUI
			omschrijvingsDuur.setText(tijd.toString());
			
			// Vraagt de lijst met monteurs op en checkt of deze beschikbaar is op de datum
			// Als de monteur de juiste specialisatie bezit voor de reparatie wordt deze automatisch geselecteerd
			ArrayList<Monteur> monteursLijst = monteurDao.getMonteurs();
			for(Monteur monteur : monteursLijst) {
				if(monteur.getSpecialiteit().equals(naam)) {
					ArrayList<String> beschikbaarheidsLijst = new ArrayList<>(monteur.getBeschikbaarheid());
					String datumcode = datumPicker.getValue().getDayOfWeek().toString().substring(0, 2);
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
	
	public void setSubmitListener() {
		// Listener voor een druk op de submit knop
		submit.setOnAction(click -> {
			// Vraagt de datum op voor de te plannen reparatie
			LocalDate datum = datumPicker.getValue();

			// Vraagt de tijd op voor de te plannen reparatie
			int uren = urenKiezer.getSelectionModel().getSelectedItem();
			int minuten = minutenKiezer.getSelectionModel().getSelectedItem();
			
			// Vraagt de duur op van de reparatie
			int urenOmschrijving = urenOverigeReparatieKiezer
					.getSelectionModel().getSelectedItem();
			int minutenOmschrijving = minutenOverigeReparatieKiezer
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
	
	// Callback handler voor de datumpicker
	private class DatumPickerCallback implements Callback<DatePicker, DateCell> {
		// De huidige planning 
		ArrayList<Planning> planningsLijst;
		
		// Constructor
		public DatumPickerCallback() {
			planningsLijst = planningDao.getPlanning();
		}
		
		@Override
		public DateCell call(DatePicker datePicker) {
			
			DateCell dateCell = new DateCell() {
				@Override
				public void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty);
					for(Planning planning : planningsLijst) {
						if(item.isBefore(LocalDate.now()) || item.isEqual(planning.getBeginTijd().toLocalDateTime().toLocalDate())) {
							this.setDisable(true);
						}
					}
				}
			};
			
			return dateCell;
		}
	}
}
