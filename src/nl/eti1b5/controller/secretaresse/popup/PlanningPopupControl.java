package nl.eti1b5.controller.secretaresse.popup;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ListIterator;

import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import javafx.util.Callback;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.database.dao.OmschrijvingDao;
import nl.eti1b5.database.dao.PlanningDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Auto;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Omschrijving;
import nl.eti1b5.model.Planning;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.secretaresse.planningsscherm.PlanningPopup;

/**
 * Controller voor het aansturen van de planningpopup
 * Handelt de events af die vanuit de planningpopup worden getriggerd
 * Bevat ook cellfactorys voor het genereren van tabelcellen
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */

public class PlanningPopupControl {
	
	// View waar de klasse controller van is
	private PlanningPopup planningPopup;
	
	// Stage voor het weergeven van popups
	private Stage popupStage;
	
	// Dao's voor het ophalen en wegschrijven van data naar de database
	private OmschrijvingDao omschrijvingDao;
	private MonteurDao monteurDao;
	private ReparatieDao reparatieDao;
	private PlanningDao planningDao;
	
	/**
	 * Constructor voor het aanmaken van de planningpopup
	 * Voegt events toe aan deze planningpopup en geeft deze het model mee
	 */
	public PlanningPopupControl() {
		// Stage voor het weergeven van popups
		popupStage = new Stage();
		
		// Dao's voor het ophalen en wegschrijven van data naar de database
		omschrijvingDao = new OmschrijvingDao();
		monteurDao = new MonteurDao();
		reparatieDao = new ReparatieDao();
		planningDao = new PlanningDao();
		
		// De nieuwe popup om te laten zien
		planningPopup = new PlanningPopup();
		popupStage.setScene(new Scene(planningPopup));
		popupStage.setTitle("Planning");
		popupStage.show();
		
		// Event Handlers
		this.setOverigeReparatieListener();
		this.setOmschrijvingListener();
		this.setPreselectionListener();
		this.setDateListener();
		this.setSubmitListener();
	}
	
	/**
	 * Listener voor de checkbox van de planningpopup
	 * Schakelt het gedeelte voor het toevoegen van een reparatieomschrijving aan of uit
	 */
	private void setOverigeReparatieListener() {
		// Listener voor het kiezen of zelf toevoegen van een reparatieomschrijving
		planningPopup.setCheckOverigeReparatieChangeListener((check, oldValue, newValue) -> {
			if(newValue.booleanValue()) {
				planningPopup.getOverigeReparatie().setDisable(false);
				planningPopup.getUrenOverigeReparatieKiezer().setDisable(false);
				planningPopup.getMinutenOverigeReparatieKiezer().setDisable(false);
				planningPopup.getOverigeReparatieKnop().setDisable(false);
				
				planningPopup.getOmschrijvingsKiezer().setDisable(true);
			}
			else {
				planningPopup.getOverigeReparatie().setDisable(true);
				planningPopup.getUrenOverigeReparatieKiezer().setDisable(true);
				planningPopup.getMinutenOverigeReparatieKiezer().setDisable(true);
				planningPopup.getOverigeReparatieKnop().setDisable(true);
				
				planningPopup.getOmschrijvingsKiezer().setDisable(false);
			}
		});
	}
	
	/**
	 * Listener voor toevoegen van een nieuwe reparatieomschrijving
	 * Vraagt de gegevens van de GUI op en schrijft ze weg in de DAO
	 */
	private void setOmschrijvingListener() {
		// Listener voor het toevoegen van een overige reparatie
		planningPopup.setOverigeReparatieActionEvent((e -> {
			// De ingevoerde gegevens uit de GUI
			String reparatieNaam = planningPopup.getOverigeReparatie().getText();
			int reparatieDuurUren = planningPopup.getUrenOverigeReparatieKiezer().getSelectionModel().getSelectedItem();
			int reparatieDuurMinuten = planningPopup.getMinutenOverigeReparatieKiezer().getSelectionModel().getSelectedItem();
			
			// Omrekenen van de uren en minuten naar sqlformaat
			Time reparatieDuur = Time.valueOf(LocalTime.of(reparatieDuurUren, reparatieDuurMinuten));
			
			// Maakt met de gegevens een omschrijving aan en voegt deze toe aan de database
			Omschrijving omschrijving = new Omschrijving(reparatieNaam, reparatieDuur);
			omschrijvingDao.addOmschrijving(omschrijving);
			
			// Geeft de nieuwe toegevoegde omschrijving weer in het console
			planningPopup.getConsoleText().setText(omschrijving.toString());
			
			// Update de GUI met deze nieuwe omschrijving, vinkt de selectie uit en selecteert de nieuwe omschrijving
			planningPopup.getOmschrijvingsKiezer().getItems().add(omschrijving);
			planningPopup.getOmschrijvingsKiezer().getSelectionModel().select(omschrijving);
			planningPopup.getCheckOverigeReparatie().setSelected(false);
		}));
	}
	
	/**
	 * Methode die op basis van de ingevoerde gegevens een voorstel doet voor een datum en gespecialiseerde monteur
	 */
	private void setPreselectionListener() {
		// Listener voor het selecteren van een reparatie in de combobox
		planningPopup.setOmschrijvingsKiezerChangeListener((omschrijving, oldValue, newValue) -> {
			// Haalt de gegevens op van de geselecteerde omschrijving
			String naam = newValue.getNaam();
			Time tijd = newValue.getDuur();
			
			// Geeft de duur weer in de GUI
			planningPopup.getOmschrijvingsDuur().setText(tijd.toString());
			
			// Vraagt de lijst met monteurs op en checkt of deze beschikbaar is op de datum
			// Als de monteur de juiste specialisatie bezit voor de reparatie wordt deze automatisch geselecteerd
			ArrayList<Monteur> monteursLijst = monteurDao.getMonteurs();
			for(Monteur monteur : monteursLijst) {
				if(monteur.getSpecialiteit().equals(naam)) {
					ArrayList<String> beschikbaarheidsLijst = new ArrayList<>(monteur.getBeschikbaarheid());
					String datumcode = planningPopup.getDatumPicker().getValue().getDayOfWeek().toString().substring(0, 2);
					for(String beschikbaarheidsCode : beschikbaarheidsLijst) {
						beschikbaarheidsCode = beschikbaarheidsCode.substring(0, 2);
						System.out.println(monteur.getNaam() + " beschikbaar op: " + beschikbaarheidsCode + " datumcode vandaag: " + datumcode);
						if(beschikbaarheidsCode.equals(datumcode)) {
							planningPopup.getMonteurKiezer().getSelectionModel().select(monteur);
						}
					}
				}
			}
			
			// Maakt het tweede deel van het planningsscherm beschikbaar
			planningPopup.getDatumPicker().setDayCellFactory(new DatumPickerCallback());
			planningPopup.getDatumPicker().setDisable(false);
			planningPopup.getUrenKiezer().setDisable(false);
			planningPopup.getMinutenKiezer().setDisable(false);
			planningPopup.getMonteurKiezer().setDisable(false);
		}); 
	}
	
	/**
	 * Methode die op basis van de datum en tijd de juiste monteurs in de combobox zet
	 * Er worden alleen monteurs weergegeven die voor deze reparatie beschikbaar zijn
	 */
	private void setDateListener() {
		planningPopup.setDatumPickerValueListener((date, oldValue, newValue) -> {
			for(int uur : planningPopup.getUrenKiezer().getItems()) {
				for(int minuten : planningPopup.getMinutenKiezer().getItems()) {
					// De tijd combinatie die gecheckt wordt
					LocalTime beginTijd = LocalTime.of(uur, minuten);
					int extraUren = Integer.parseInt(planningPopup.getOmschrijvingsDuur().getText().substring(0, 2));
					int extraMinuten = Integer.parseInt(planningPopup.getOmschrijvingsDuur().getText().substring(3, 5));
					LocalTime eindTijd = beginTijd.plusHours(extraUren);
					eindTijd = eindTijd.plusMinutes(extraMinuten);
					
					// Lijst met beschikbare monteurs op de begin- en eindtijd van de reparatie
					ArrayList<Monteur> monteursLijstBeginTijd = monteurDao.getMonteurs(LocalDateTime.of(newValue, beginTijd));
					ArrayList<Monteur> monteursLijstEindTijd = monteurDao.getMonteurs(LocalDateTime.of(newValue, eindTijd));
					
					// Checkt of de monteur ook in de lijst met eindtijden zit 
					// Verwijdert de monteur waneer dit niet het geval is
					ListIterator<Monteur> iterator = monteursLijstBeginTijd.listIterator();
					while(iterator.hasNext()) {
						Monteur monteur = iterator.next();
						if(monteursLijstEindTijd.contains(monteur)) {
							iterator.remove();
						}
					}
					
					// Als er geen monteurs beschikbaar zijn voor het tijdstip wordt deze niet beschikbaar gemaakt
					if(monteursLijstBeginTijd.isEmpty()) {
						planningPopup.getConsoleText().appendText("\n Tijd is niet beschikbaar: " + beginTijd);
					}
					else {
						for(Monteur monteur : monteursLijstBeginTijd) {
							planningPopup.getConsoleText().appendText("\n Tijd is wel beschikbaar: " + beginTijd + "Monteur " + monteur);
						}	
					}
				}
			}
		});
	}
	
	/**
	 * Methode die actionevent toevoegd aan de submitknop in de planningpopup
	 * Vraagt alle ingevulde gegevens uit de popup weg en schrijft deze weg in de database
	 */
	private void setSubmitListener() {
		// Listener voor een druk op de submit knop
		planningPopup.setSubmitActionEvent(click -> {
			// Vraagt de datum op voor de te plannen reparatie
			LocalDate datum = planningPopup.getDatumPicker().getValue();

			// Vraagt de tijd op voor de te plannen reparatie
			int uren = planningPopup.getUrenKiezer().getSelectionModel().getSelectedItem();
			int minuten = planningPopup.getMinutenKiezer().getSelectionModel().getSelectedItem();
			
			// Vraagt de duur op van de reparatie
			int urenOmschrijving = planningPopup.getUrenOverigeReparatieKiezer()
					.getSelectionModel().getSelectedItem();
			int minutenOmschrijving = planningPopup.getMinutenOverigeReparatieKiezer()
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
			Monteur monteur = planningPopup.getMonteurKiezer().getSelectionModel()
					.getSelectedItem();

			// De in te plannen reparatie
			Reparatie reparatie = new Reparatie();

			// Het kenteken van de auto van klant
			Auto auto = planningPopup.getAutoKiezer().getSelectionModel().getSelectedItem();
			reparatie.setKenteken(auto.getKenteken());

			// De omschrijving van de reparatie
			if (planningPopup.getCheckOverigeReparatie().isSelected()) {

				LocalTime tijdOmschrijving = LocalTime.of(urenOmschrijving,
						minutenOmschrijving);
				Time sqlTijd = Time.valueOf(tijdOmschrijving);
			} else {
				// Voeg omschrijving toe aan database en
				omschrijvingDao.addOmschrijving(new Omschrijving());
			}

			// Voegt de reparatie toe aan de database
			reparatieDao.addReparatie(reparatie);

			// Voegt de planning toe voor de reparatie aan de database
			Planning planning = new Planning(sqlbegintijd, sqleindtijd, monteur,
					reparatie);
			planningDao.addPlanning(planning);
		});
	}
	
	/**
	 * Cellfactory voor het genereren van datecellen voor de datumpicker
	 * Zet de data uit die niet beschikbaar zijn voor reparatie
	 * Past de kleur van de cellen aan de beschikbaarheid van de datum
	 * @author ETI2vb3
	 * @since 5 nov. 2014
	 */
	private class DatumPickerCallback implements Callback<DatePicker, DateCell> {

		@Override
		public DateCell call(DatePicker datePicker) {
			
			DateCell dateCell = new DateCell() {
				@Override
				public void updateItem(LocalDate item, boolean empty) {
					super.updateItem(item, empty);
					
					// Zet alle data in de verleden tijd op rood
					if(item.isBefore(LocalDate.now())) {
						this.setDisable(true);
						this.setStyle("-fx-background-color:red");
					}
					// Zet alle overige data op groen
					else {
						this.setStyle("-fx-background-color:green");
					}
					
					// Zet de data uit de planning op oranje
					if(item.isEqual(LocalDate.now())) {
						this.setDisable(true);
						this.setStyle("-fx-background-color:orange");
					}
				}
			};
			
			return dateCell;
		}
	}
}
