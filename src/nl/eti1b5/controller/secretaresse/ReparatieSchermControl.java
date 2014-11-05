package nl.eti1b5.controller.secretaresse;

import javafx.collections.FXCollections;
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
		
		this.kentekenCallback();
		this.kentekenEditCommit();
		
		this.reparatieStatusCallback();
		this.reparatieStatusEditCommit();
		
		this.betaalStatusCallback();
		this.betaalStatusEditCommit();
		
		view.setOmschrijvingsNummerCellFactory(new OmschrijvingsNummerCallback());
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
			reparatieDao.addReparatie(reparatie);
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
	 * Handelt de celleditevents af van het betaalstatuskolom
	 * Wordt getriggerd als de cel wordt aangepast en schrijft de aanpassing weg in de database
	 */
	private void betaalStatusEditCommit() {
		view.setBetaalStatusOnEditComit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setBetaalStatus(e.getNewValue());
			reparatieDao.addReparatie(reparatie);
		});
	}
	
	/**
	 * Voegt een checkboxcellfactory toe aan de reparatiestatuskolom
	 * Vult de reparatiestatuskolom met checkboxes
	 */
	private void reparatieStatusCallback() {
		view.setReparatieStatusCellFactory(CheckBoxTableCell.<Reparatie>forTableColumn(view.getReparatieStatusKolom()));
	}
	
	/**
	 * Handelt de celleditevents af van het reparatiestatuskolom
	 * Wordt getriggerd als de cel wordt aangepast en schrijft de aanpassing weg in de database
	 */
	private void reparatieStatusEditCommit() {
		view.setReparatieStatusOnEditComit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setReparatieStatus(e.getNewValue());
			reparatieDao.addReparatie(reparatie);
		});
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

}
