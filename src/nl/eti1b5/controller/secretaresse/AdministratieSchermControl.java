package nl.eti1b5.controller.secretaresse;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import nl.eti1b5.controller.secretaresse.popup.FactuurPopupControl;
import nl.eti1b5.database.dao.KlantDao;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.model.Klant;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.view.secretaresse.administratiescherm.AdministratieOverzicht;

/**
 * Controller voor het afhandelen van events van het administratiescherm
 * Maakt het scherm en model en geeft deze mee aan het scherm
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */
public class AdministratieSchermControl {
	
	// View waar klasse controller van is
	private AdministratieOverzicht view;
	
	// Dao's voor het ophalen en wegschrijven van data naar de database
	private MonteurDao monteurDao;
	private KlantDao klantDao;
	
	// Lijst met monteurs en klanten voor de tabellen
	private ObservableList<Monteur> monteursLijst;
	private ObservableList<Klant> klantenLijst;
	
	/**
	 * Constructor voor het aanmaken van het model en view
	 * Voegt de juiste events toe aan de view
	 * @param view Het administratiescherm wat deze controller beheert
	 */
	public AdministratieSchermControl(AdministratieOverzicht view) {
		// View waar klasse controller van is
		this.view = view;
		
		// De monteur- en klantdao voor ophalen en wegschrijven van gegevens naar de database
		monteurDao = new MonteurDao();
		klantDao = new KlantDao();
		
		// Lijst met monteurs en klanten voor de tabellen
		monteursLijst = FXCollections.observableList(monteurDao.getMonteurs(), new MonteursLijstCallback());
		klantenLijst = FXCollections.observableList(klantDao.getKlanten(), new KlantenLijstCallback());
		
		// Voegt changelisteners toe aan de lijsten voor detecteren van veranderingen
		this.monteursLijstChangeListener();
		this.klantenLijstChangeListener();
		
		// Voegt de lijst met monteurs aan klanten toe aan de view
		view.getMonteursTabel().setItems(monteursLijst);
		view.getKlantenTabel().setItems(klantenLijst);
		
		// Handelt de CellEditEvents van de monteurs en klanten af
		this.setMonteurCellEditEvents();
		this.setKlantCellEditEvents();
		
		// Listener voor het selecteren van de klant- of monteurstabel
		this.setAdministratieKiezerListener();
		
		// Voegt de callback toe aan de klantnummerkolom
		view.getKlantenTabel().setKlantnummerKolomCellFactory(new KlantNummerCallback());
	}
	
	/**
	 * ListChangeListener voor het luisteren van veranderingen aan en in de monteurlijst
	 * Schrijft de veranderingen weg naar de database
	 */
	private void monteursLijstChangeListener() {
		monteursLijst.addListener(new ListChangeListener<Monteur>() {

			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Monteur> change) {
				for(Monteur monteur : change.getList()) {
					monteurDao.addMonteur(monteur);
				}
			}
			
		});
	}
	
	/**
	 * ListChangeListener voor het luisteren van veranderingen aan en in de klantenlijst
	 * Schrijft de veranderingen weg naar de database
	 */
	private void klantenLijstChangeListener() {
		klantenLijst.addListener(new ListChangeListener<Klant>() {

			@Override
			public void onChanged(
					javafx.collections.ListChangeListener.Change<? extends Klant> change) {
				for(Klant klant : change.getList()) {
					klantDao.addKlant(klant);
				}
			}
			
		});
	}
	
	/**
	 * Methode die de celleditevents aan de monteurkolommen toevoegd
	 * Slaat de wijzigingen op in zowel model als de doa
	 */
	private void setMonteurCellEditEvents() {
		view.getMonteursTabel().setMonteurNaamKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setNaam(e.getNewValue());
		});
		
		view.getMonteursTabel().setMonteurAdresKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setAdres(e.getNewValue());
		});
		
		view.getMonteursTabel().setMonteurPostcodeKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setPostcode(e.getNewValue());
		});
		
		view.getMonteursTabel().setMonteurPlaatsKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setWoonplaats(e.getNewValue());
		});
		
		view.getMonteursTabel().setMonteurTelefoonnummerKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setTelNr(e.getNewValue());
		});
		
		view.getMonteursTabel().setMonteurWachtwoordKolom(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setWachtwoord(e.getNewValue());
		});
		
		view.getMonteursTabel().setMonteurSpecialiteitKolomCellEditEvent(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setSpecialiteit(e.getNewValue());
		});
	}
	
	/**
	 * Methode die de celleditevents aan de klantkolommen toevoegd
	 * Slaat de wijzigingen op in zowel model als de doa
	 */
	private void setKlantCellEditEvents() {
		view.getKlantenTabel().setKlantNaamKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setNaam(e.getNewValue());
		});
		
		view.getKlantenTabel().setKlantAdresKolomKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setAdres(e.getNewValue());
		});
		
		view.getKlantenTabel().setKlantPostcodeKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setPostcode(e.getNewValue());
		});
		
		view.getKlantenTabel().setKlantPlaatsKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setWoonplaats(e.getNewValue());
		});
		
		view.getKlantenTabel().setKlantTelefoonnummerKolomCellEditEvent(e -> {
			Klant klant = e.getRowValue();
			klant.setTelNr(e.getNewValue());
		});
	}
	
	/**
	 * Listener voor het detecteren van een verandering in de administratieKiezer
	 * Laat op basis van de selectie het juiste overzicht zien
	 */
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
	
	/**
	 * Cellfactory voor het genereren van tabelcellen voor de klantnummerkolom
	 * Voegt een mouseclick listener toe aan de cellen voor het laten zien van de openstaande reparaties van de aangeklikte klant
	 * @author ETI2vb3
	 * @since 5 nov. 2014
	 */
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
	
	/**
	 * Factory voor het genereren van een extractor wat elk element in de lijst observable maakt
	 * Als er aanpassingen aan de lijst plaats vinden wordt de listchangelistener aangeroepen
	 * 
	 * @author ETI2vb3
	 * @since 12 nov. 2014
	 */
	private class MonteursLijstCallback implements Callback<Monteur, Observable[]> {
        
		@Override
        public Observable[] call(Monteur monteur) {
            Observable[] observables = new Observable[8];
            
            observables[0] = monteur.werknemerNrProperty();
            observables[1] = monteur.naamProperty();
            observables[2] = monteur.adresProperty();
            observables[3] = monteur.postcodeProperty();
            observables[4] = monteur.woonplaatsProperty();
            observables[5] = monteur.telNrProperty();
            observables[6] = monteur.wachtwoordProperty();
            observables[7] = monteur.specialiteitProperty();
            
            return observables;
        }
	}
	
	/**
	 * Factory voor het genereren van een extractor wat elk element in de lijst observable maakt
	 * Als er aanpassingen aan de lijst plaats vinden wordt de listchangelistener aangeroepen
	 * 
	 * @author ETI2vb3
	 * @since 12 nov. 2014
	 */
	private class KlantenLijstCallback implements Callback<Klant, Observable[]> {
        
		@Override
        public Observable[] call(Klant klant) {
            Observable[] observables = new Observable[6];
            
            observables[0] = klant.klantNrProperty();
            observables[1] = klant.naamProperty();
            observables[2] = klant.adresProperty();
            observables[3] = klant.postcodeProperty();
            observables[4] = klant.woonplaatsProperty();
            observables[5] = klant.telNrProperty();
            
            return observables;
        }
	}
}
