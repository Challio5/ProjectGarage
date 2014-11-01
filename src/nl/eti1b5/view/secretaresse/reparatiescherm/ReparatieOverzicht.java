package nl.eti1b5.view.secretaresse.reparatiescherm;

import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.BooleanStringConverter;

import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.database.dao.OmschrijvingDao;
import nl.eti1b5.model.Reparatie;


public class ReparatieOverzicht extends TableView<Reparatie>{
	
	// Stage voor weergeven van popups
	private Stage popupStage;
	
	// ReparatieDao voor het wegschrijven en ophalen van de data
	private ReparatieDao reparatieDao;
	
	// Alle kolommen voor het weergeven van de attributen van reparatie
	private TableColumn<Reparatie, Integer> reparatieNummerKolom;
	private TableColumn<Reparatie, String> kentekenKolom;
	private TableColumn<Reparatie, Integer> omschrijvingsNummerKolom;
	private TableColumn<Reparatie, Timestamp> begintijdKolom;
	private TableColumn<Reparatie, Timestamp> eindtijdKolom;	
	private TableColumn<Reparatie, Boolean> reparatieStatusKolom;
	private TableColumn<Reparatie, Boolean> betaalStatusKolom;
	// Materialen ???
	
	public ReparatieOverzicht(){
		this.setEditable(true);
		
		popupStage = new Stage();
		
		reparatieDao = new ReparatieDao();
		
		reparatieNummerKolom = new TableColumn<Reparatie, Integer>("Reparatie nummer");
		reparatieNummerKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("reparatieNummer"));
		// Niet editable want primary key in database van reparatie
		
		kentekenKolom = new TableColumn<Reparatie, String>("Kenteken");
		kentekenKolom.setCellValueFactory(new PropertyValueFactory<Reparatie,String>("kenteken"));
		kentekenKolom.setCellFactory(TextFieldTableCell.<Reparatie>forTableColumn());
		kentekenKolom.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setKenteken(e.getNewValue());
			reparatieDao.addReparatie(reparatie);
		});
		
		omschrijvingsNummerKolom = new TableColumn<Reparatie, Integer>("Omschrijving");
		omschrijvingsNummerKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("omschrijvingsNummer"));
		omschrijvingsNummerKolom.setCellFactory(new OmschrijvingsNummerCallback());
		// Niet editable want primary key in database van omschrijvingsnummer
		
		begintijdKolom = new TableColumn<Reparatie, Timestamp>("Begintijd");
		begintijdKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Timestamp>("beginTijd"));
		/*begintijdKolom.setCellFactory(TextFieldTableCell.<Reparatie>forTableColumn(new Timestampconveter()));
		begintijdKolom.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setBeginTijd(e.getNewValue());
		});*/
		
		eindtijdKolom = new TableColumn<Reparatie, Timestamp>("Eindtijd");
		eindtijdKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Timestamp>("eindTijd"));
		/*eindtijdKolom.setCellFactory(TextFieldTableCell.<Reparatie>forTableColumn(new Timestampconveter()));
		eindtijdKolom.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setEindTijd(e.getNewValue());
		});*/
		
		reparatieStatusKolom = new TableColumn<Reparatie, Boolean>("Reparatie Status");
		reparatieStatusKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Boolean>("reparatieStatus"));
		reparatieStatusKolom.setCellFactory(TextFieldTableCell.<Reparatie, Boolean>forTableColumn(new BooleanStringConverter()));
		reparatieStatusKolom.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setReparatieStatus(e.getNewValue());
			reparatieDao.addReparatie(reparatie);
		});
		
		betaalStatusKolom = new TableColumn<Reparatie, Boolean>("Betaal Status");
		betaalStatusKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Boolean>("betaalStatus"));
		betaalStatusKolom.setCellFactory(TextFieldTableCell.<Reparatie, Boolean>forTableColumn(new BooleanStringConverter()));
		betaalStatusKolom.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setBetaalStatus(e.getNewValue());
			reparatieDao.addReparatie(reparatie);
		});
		
		this.getColumns().add(reparatieNummerKolom);
		this.getColumns().add(kentekenKolom);
		this.getColumns().add(omschrijvingsNummerKolom);
		this.getColumns().add(begintijdKolom);
		this.getColumns().add(eindtijdKolom);
		this.getColumns().add(reparatieStatusKolom);
		this.getColumns().add(betaalStatusKolom);
		
		this.setItems(FXCollections.observableArrayList(reparatieDao.getReparaties()));
	}
	
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
