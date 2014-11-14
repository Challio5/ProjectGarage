package nl.eti1b5.controller.secretaresse;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import nl.eti1b5.database.dao.AutoDao;
import nl.eti1b5.database.dao.OmschrijvingDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.secretaresse.reparatiescherm.OmschrijvingsPopup;
import nl.eti1b5.view.secretaresse.reparatiescherm.ReparatieOverzicht;

/**
 * Controller voor het afhandelen van events van het reparatiescherm
 * Krijgt het scherm binnen via de hoofdcontroller en laat deze zien
 * 
 * @author ETI2vb2
 * @since 5 nov. 2014
 */
public class ReparatieSchermControl {
	
	// View waar de klasse controller van is
	private ReparatieOverzicht view;
	
	// Model met de data voor de view
	private ObservableList<Reparatie> reparatieLijst;
	
	// Stage voor weergeven van popups
	private Stage popupStage;
	
	// ReparatieDao voor het wegschrijven en ophalen van de data
	private ReparatieDao reparatieDao;
	
	/**
	 * Constructor voor toevoegen van events aan het reparatiescherm
	 * Voegt ook cellfactorys toe voor het genereren van tabelcellen
	 * @param view Het reparatiescherm die de controller beheert
	 */
	public ReparatieSchermControl(ReparatieOverzicht view) {
		this.view = view;
		
		popupStage = new Stage();
		
		reparatieDao = new ReparatieDao();
		reparatieLijst = FXCollections.observableList(reparatieDao.getReparaties(), new ReparatieLijstCallback());
		this.reparatieLijstChangeListener();
		
		view.getItems().addAll(reparatieLijst);
		
		this.kentekenCallback();
		this.kentekenEditCommit();
		
		this.reparatieStatusCallback();
		this.betaalStatusCallback();
		
		view.setOmschrijvingsNummerCellFactory(new OmschrijvingsNummerCallback());
	}
	
	/**
	 * ListChangeListener voor het luisteren van veranderingen aan en in de reparatielijst
	 * Schrijft de veranderingen weg naar de database
	 */
	private void reparatieLijstChangeListener() {
		reparatieLijst.addListener(new ListChangeListener<Reparatie>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Reparatie> change) {
				ObservableList<? extends Reparatie> reparatieLijst = change.getList();
				for(Reparatie reparatie : reparatieLijst) {
					reparatieDao.addReparatie(reparatie);
				}
			}
			
		});
	}
	
	/**
	 * Voegt een comboboxcellfactory toe aan de kentekenkolom
	 * Vult de kentekenkolom met comboboxen
	 */
	private void kentekenCallback() {
		view.setKentekenKolomCellFactory(ComboBoxTableCell.<Reparatie, String>forTableColumn(FXCollections.observableArrayList(new AutoDao().getKentekens())));
	}
	
	/**
	 * Handelt de celleditevents af van het kentekenkolom
	 * Wordt getriggerd als de cel wordt aangepast en schrijft de aanpassing weg in de database
	 */
	private void kentekenEditCommit() {
		view.setKentekenOnEditComit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setKenteken(e.getNewValue());
		});
	}
	
	/**
	 * Voegt een checkboxcellfactory toe aan de betaalstatuskolom
	 * Vult de betaalstatuskolom met checkboxes
	 */
	private void betaalStatusCallback() {
		view.setBetaalStatusCellFactory(CheckBoxTableCell.<Reparatie>forTableColumn(view.getBetaalStatusKolom()));
	}

	/**
	 * Voegt een checkboxcellfactory toe aan de reparatiestatuskolom
	 * Vult de reparatiestatuskolom met checkboxes
	 */
	private void reparatieStatusCallback() {
		view.setReparatieStatusCellFactory(CheckBoxTableCell.<Reparatie>forTableColumn(view.getReparatieStatusKolom()));
	}
	
	/**
	 * Cellfactory voor het generegen van tabelcellen voor het omschrijvingsnummerkolom
	 * Voegt een muisklik event toe aan cellen voor het weergeven van de details van de omschrijving
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
	 * Factory voor het genereren van een extractor wat elk element in de lijst observable maakt
	 * Als er aanpassingen aan de lijst plaats vinden wordt de listchangelistener aangeroepen
	 * 
	 * @author ETI2vb3
	 * @since 12 nov. 2014
	 */
	private class ReparatieLijstCallback implements Callback<Reparatie, Observable[]> {
        
		@Override
        public Observable[] call(Reparatie reparatie) {
            Observable[] observables = new Observable[7];
            
            observables[0] = reparatie.reparatieNummerProperty();
            observables[1] = reparatie.kentekenProperty();
            observables[2] = reparatie.omschrijvingsNummerProperty();
            observables[3] = reparatie.beginTijdProperty();
            observables[4] = reparatie.eindTijdProperty();
            observables[5] = reparatie.reparatieStatusProperty();
            observables[6] = reparatie.betaalStatusProperty();
            
            return observables;
        }
	}
}
