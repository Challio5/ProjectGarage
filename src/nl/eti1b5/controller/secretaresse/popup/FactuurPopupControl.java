package nl.eti1b5.controller.secretaresse.popup;

import java.io.File;
import java.io.PrintWriter;
import java.time.temporal.ChronoUnit;

import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.util.Callback;
import nl.eti1b5.database.dao.OmschrijvingDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Materiaal;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.secretaresse.administratiescherm.FactuurPopup;
import nl.eti1b5.view.secretaresse.reparatiescherm.OmschrijvingsPopup;

/**
 * Controller voor het aansturen van de factuurpopup
 * Handelt de events af die vanuit de factuurpoup worden getriggerd
 * Bevat ook cellfactorys voor het genereren van tabelcellen
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */

public class FactuurPopupControl {
	
	// View waar de klasse controller van is
	private FactuurPopup factuurPopup;
	
	// Het model met de data
	private int klantnummer;
	
	// Stage voor het weergeven van popups
	private Stage popupStage;
	
	// Dao's voor het ophalen en wegschrijven van data naar de database
	private ReparatieDao reparatieDao;
	
	/**
	 * Constructor die de events toevoegd aan de factuurpopup
	 * Maak het model aan en geeft deze mee aan de view
	 * @param klantnummer
	 */
	public FactuurPopupControl(int klantnummer) {
		// Het model met de data
		this.klantnummer = klantnummer;
		
		// Stage voor het weergeven van popups
		popupStage = new Stage();
		
		// Dao's voor het ophalen en wegschrijven van data naar de database
		reparatieDao = new ReparatieDao();
		
		// De nieuwe popup om te laten zien
		factuurPopup = new FactuurPopup(klantnummer);
		popupStage.setScene(new Scene(factuurPopup));
		popupStage.setTitle("Reparaties");
		popupStage.show();
		
		// Event handler
		factuurPopup.setOmschrijvingsNummerKolomCellFactory(new OmschrijvingsNummerCallback());
		factuurPopup.setBetaalStatusKolomCellFactory(new BetaalStatusCallback());
		this.printKnopActionEvent();
	}
	
	/**
	 * Methode die een actionlistener toevoegd aan de printknop
	 * Genereert een factuur en vraagt de gebruiker om een locatie om deze op te slaan
	 */
	private void printKnopActionEvent() {
		factuurPopup.setPrintButtonActionListener(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Factuur");
			fileChooser.setInitialFileName("factuur");
			fileChooser.getExtensionFilters().add(new ExtensionFilter("txt", "*.txt"));
			File saveFile = fileChooser.showSaveDialog(popupStage);
			
			if(saveFile != null) {
				try {
					// Print het klantnummer samen met de tabelkoppen
					PrintWriter writer = new PrintWriter(saveFile, "UTF-8");
					writer.printf("%-20s%-10d%n%n", "Klant:", klantnummer);
					writer.printf("%-20s%-40s%-10s%-10s%10s%n%n", "Artikelnummer", "Omschrijving", "Prijs", "Aantal", "Totaal");
	
					// Totaal van alle bedragen
					double totaal = 0.0;
					
					// Aantal gemaakt arbeidsuren
					double prijs = 50.0;
					double aantal = 0.0;
					
					// Berekent het aantal seconden tussen de begin- en eindtijd van een reparatie in uren
					for(Reparatie reparatie : factuurPopup.getReparatieTabel().getItems()) {
						aantal += ChronoUnit.SECONDS.between(reparatie.getBeginTijd().toLocalDateTime(), reparatie.getEindTijd().toLocalDateTime()) / 3600.0;
					}
					
					writer.printf("%-20s%-40s%-10.2f%-10.2f%10.2f%n%n", "A", "Arbeidsuren", prijs, aantal, prijs * aantal);
					totaal += prijs * aantal;
					
					for(Reparatie reparatie : factuurPopup.getReparatieTabel().getItems()) {
						// Print de reparatie met omschrijving
						String omschrijving = new OmschrijvingDao().getOmschrijving(reparatie.getOmschrijvingsNummer()).getNaam();
						writer.printf("%-20s%-40s%-10s%-10s%10s%n", "-", omschrijving, "-", "-", "-");
						
						// Print alle bijbehorende materialen van de reparatie
						for(Materiaal materiaal : reparatie.getMaterialenLijst()) {
							writer.printf("%-20d%-40s%-10.2f%-10d%10.2f%n", materiaal.getMateriaalnr(), materiaal.getNaam(), materiaal.getPrijs(), materiaal.getAantalgebruikt(), materiaal.getPrijs() * materiaal.getAantalgebruikt());
							totaal += materiaal.getPrijs() * materiaal.getAantalgebruikt();
						}
						
						// Print een lege regele voor een nieuwe reparatie
						writer.println();
					}
					
					// Print het totaal
					writer.printf("%-10s%80.2f", "Totaal", totaal);
					writer.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Cellfactory voor het generen van tabelcellen voor de omschrijvingsnummerkolom
	 * Voegt een actionlistener toe aan de cellen voor het weergeven van een omschrijvingspopup
	 * 
	 * @author ETI2vb3
	 * @since 5 nov. 2014
	 */
	private class OmschrijvingsNummerCallback implements Callback<TableColumn<Reparatie, Integer>, TableCell<Reparatie, Integer>>{

		@Override
		public TableCell<Reparatie, Integer> call(
				TableColumn<Reparatie, Integer> column) {
			
			TableCell<Reparatie, Integer> cell = new TableCell<Reparatie, Integer>() {

				@Override
				protected void updateItem(Integer omschrijvingsNummer, boolean empty) {
					super.updateItem(omschrijvingsNummer, empty);
					
					if(empty) {
						this.setText(null);
						this.setGraphic(null);
					}
					else {
						this.setText(String.valueOf(omschrijvingsNummer));
						this.setGraphic(null);
					}
				}
				
			};
			
			cell.setOnMouseClicked(e -> {
				if(!cell.isEmpty()) {
					popupStage.setScene(new Scene(new OmschrijvingsPopup(new OmschrijvingDao().getOmschrijving(cell.getItem()))));
					popupStage.setTitle("Reparatie");
					popupStage.show();
				}
			});
			
			return cell;
		}
	}
	
	/**
	 * Cellfactory voor het genereren van tabelcellen voor de betaalstatuskolom
	 * Voegt een actionlistener toe die bij een druk op de cell de waarde verandert van true naar false of vice versa
	 * 
	 * @author ETI2vb3
	 * @since 5 nov. 2014
	 */
	private class BetaalStatusCallback implements Callback<TableColumn<Reparatie, Boolean>, TableCell<Reparatie, Boolean>>{

		@Override
		public TableCell<Reparatie, Boolean> call(
				TableColumn<Reparatie, Boolean> column) {
			
			TableCell<Reparatie, Boolean> cell = new TableCell<Reparatie, Boolean>() {

				@Override
				protected void updateItem(Boolean status, boolean empty) {
					super.updateItem(status, empty);
					
					if(empty) {
						this.setText(null);
						this.setGraphic(null);
					}
					else {
						this.setText(String.valueOf(status));
						this.setGraphic(null);
					}
				}
				
			};
			
			// Flipt de boolean waarde van true naar false of omgekeerd bij een muisklik
			cell.setOnMouseClicked(e -> {
				if(!cell.isEmpty()) {
					// De nieuwe waarde na de muisklik
					boolean newValue = !cell.getItem();
					
					// Past de de statuswaarde aan in de table
					cell.startEdit();
					cell.commitEdit(newValue);
					
					// Schrijft de aangepaste reparatie weg naar de database
					Reparatie reparatie = (Reparatie) cell.getTableRow().getItem();
					reparatie.setBetaalStatus(newValue);
					reparatieDao.addReparatie(reparatie);
				}
			});
			
			return cell;
		}
	}
}
