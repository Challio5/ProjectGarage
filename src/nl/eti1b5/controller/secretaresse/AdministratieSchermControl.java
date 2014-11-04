package nl.eti1b5.controller.secretaresse;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import nl.eti1b5.controller.secretaresse.popup.FactuurPopupControl;
import nl.eti1b5.database.dao.KlantDao;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.model.Klant;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.view.secretaresse.administratiescherm.AdministratieOverzicht;

public class AdministratieSchermControl {
	
	// View waar klasse controller van is
	private AdministratieOverzicht view;
	
	// Dao's voor het ophalen en wegschrijven van data naar de database
	private MonteurDao monteurDao;
	private KlantDao klantDao;
	
	public AdministratieSchermControl(AdministratieOverzicht view) {
		// View waar klasse controller van is
		this.view = view;
		
		// De monteur- en klantdao voor ophalen en wegschrijven van gegevens naar de database
		monteurDao = new MonteurDao();
		klantDao = new KlantDao();
		
		// Handelt de CellEditEvents van de monteurs en klanten af
		this.setMonteurCellEditEvents();
		this.setKlantCellEditEvents();
		
		// Listener voor het selecteren van de klant- of monteurstabel
		this.setAdministratieKiezerListener();
		
		// Voegt de callback toe aan de klantnummerkolom
		view.getKlantenTabel().setKlantnummerKolomCellFactory(new KlantNummerCallback());
	}
	
	// Handelt de CellEditEvents van de monteurs af
	private void setMonteurCellEditEvents() {
		view.getMonteursTabel().setMonteurNaamKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setNaam(e.getNewValue());
			monteurDao.addMonteur(monteur);
		});
		
		view.getMonteursTabel().setMonteurAdresKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setAdres(e.getNewValue());
			monteurDao.addMonteur(monteur);
		});
		
		view.getMonteursTabel().setMonteurPostcodeKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setPostcode(e.getNewValue());
			monteurDao.addMonteur(monteur);
		});
		
		view.getMonteursTabel().setMonteurPlaatsKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setwoonplaats(e.getNewValue());
			monteurDao.addMonteur(monteur);
		});
		
		view.getMonteursTabel().setMonteurTelefoonnummerKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setTelNr(e.getNewValue());
			monteurDao.addMonteur(monteur);
		});
		
		view.getMonteursTabel().setMonteurWachtwoordKolom(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setWachtwoord(e.getNewValue());
			monteurDao.addMonteur(monteur);
		});
		
		view.getMonteursTabel().setMonteurSpecialiteitKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setSpecialiteit(e.getNewValue());
			monteurDao.addMonteur(monteur);
		});
	}
	
	// Handelt de CellEditEvents van de klanten af
	private void setKlantCellEditEvents() {
		view.getKlantenTabel().setKlantNaamKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setNaam(e.getNewValue());
			klantDao.addKlant(klant);
		});
		
		view.getKlantenTabel().setKlantAdresKolomKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setAdres(e.getNewValue());
			klantDao.addKlant(klant);
		});
		
		view.getKlantenTabel().setKlantPostcodeKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setPostcode(e.getNewValue());
			klantDao.addKlant(klant);
		});
		
		view.getKlantenTabel().setKlantPlaatsKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setwoonplaats(e.getNewValue());
			klantDao.addKlant(klant);
		});
		
		view.getKlantenTabel().setKlantTelefoonnummerKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setTelNr(e.getNewValue());
			klantDao.addKlant(klant);
		});
	}
	
	// Listener voor het detecteren van een verandering in de administratieKiezer
	// Laat op basis van de selectie het juiste overzicht zien
	private void setAdministratieKiezerListener() {
		view.setAdministratieKiezerListener((observable, oldValue, newValue) -> {
			// Checkt de nieuwe waarde en switcht zo tussen de klant- en monteurstabel
			switch(newValue) {
			case "Klanten":
				view.getChildren().remove(view.getMonteursTabel());
				view.getChildren().remove(view.getMonteurToevoegButton());
				view.getChildren().add(view.getKlantenTabel());
				view.getChildren().add(view.getKlantToevoegButton());
				break;
			case "Monteurs":
				view.getChildren().remove(view.getKlantenTabel());
				view.getChildren().remove(view.getKlantToevoegButton());
				view.getChildren().add(view.getMonteursTabel());
				view.getChildren().add(view.getMonteurToevoegButton());
				break;
			}
		});
	}
	
	// Callback voor het aanpassen van het gedrag van de cellen in de klantnummerkolom
	// Voegt een mouseclick listener toe aan de cellen voor het laten zien van de openstaande reparaties van de aangeklikte klant
	private class KlantNummerCallback implements Callback<TableColumn<Klant, Integer>, TableCell<Klant, Integer>> {

		// Methode die wordt aangeroepen door de JRE wanneer het de kolom gaat vullen met cellen
		@Override
		public TableCell<Klant, Integer> call(TableColumn<Klant, Integer> column) {
			
			// De cell waarmee de kolom wordt gevuld
			TableCell<Klant, Integer> cell = new TableCell<Klant, Integer>() {

				// Methode die de data in de cell presenteert
				@Override
				protected void updateItem(Integer klantnummer, boolean empty) {
					super.updateItem(klantnummer, empty);
					
					// Als de cell leeg is laat hij niets zien
					if(empty) {
						this.setText(null);
						this.setGraphic(null);
					}
					// Anders de waarde van het klantnummer van de klant
					else {
						this.setText(String.valueOf(klantnummer));
						this.setGraphic(null);
					}
				}				
			};
			
			// Voegt een mouseevent toe voor het laten zien van alle openstaande reparaties
			cell.setOnMouseClicked(e -> {
				if(!cell.isEmpty()) {
					new FactuurPopupControl(cell.getItem());
				}
			});
			
			return cell;
		}
	}
}
