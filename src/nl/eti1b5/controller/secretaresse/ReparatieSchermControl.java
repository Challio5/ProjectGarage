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

public class ReparatieSchermControl {
	
	// View waar de klasse controller van is
	private ReparatieOverzicht view;
	
	// Stage voor weergeven van popups
	private Stage popupStage;
	
	// ReparatieDao voor het wegschrijven en ophalen van de data
	private ReparatieDao reparatieDao;
	
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
	
	// Kenteken cellfactory en editcommit
	private void kentekenCallback() {
		view.setKentekenKolomCellFactory(ComboBoxTableCell.<Reparatie, String>forTableColumn(FXCollections.observableArrayList(new AutoDao().getKentekens())));
	}
	
	private void kentekenEditCommit() {
		view.setKentekenOnEditComit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setKenteken(e.getNewValue());
			reparatieDao.addReparatie(reparatie);
		});
	}
	
	// ReparatieStatus cellfactory en editcommit
	private void betaalStatusCallback() {
		view.setBetaalStatusCellFactory(CheckBoxTableCell.<Reparatie>forTableColumn(view.getBetaalStatusKolom()));
	}
	
	private void betaalStatusEditCommit() {
		view.setBetaalStatusOnEditComit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setBetaalStatus(e.getNewValue());
			reparatieDao.addReparatie(reparatie);
		});
	}
	
	// BetaalStatus cellfactory en editcommit
	private void reparatieStatusCallback() {
		view.setReparatieStatusCellFactory(CheckBoxTableCell.<Reparatie>forTableColumn(view.getReparatieStatusKolom()));
	}
	
	private void reparatieStatusEditCommit() {
		view.setReparatieStatusOnEditComit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setReparatieStatus(e.getNewValue());
			reparatieDao.addReparatie(reparatie);
		});
	}
	
	// Omschrijvingsnummer cellfactory
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
