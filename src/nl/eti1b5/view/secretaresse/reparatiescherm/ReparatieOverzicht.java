package nl.eti1b5.view.secretaresse.reparatiescherm;

import java.sql.Timestamp;

import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Reparatie;

/**
 * Gui klasse voor het weergeven van alle reparaties
 * Bestaat uit een tabel met tabelkolommen voor het weergeven van de attributen
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */

public class ReparatieOverzicht extends TableView<Reparatie>{
	
	// Alle kolommen voor het weergeven van de attributen van reparatie
	private TableColumn<Reparatie, Integer> reparatieNummerKolom;
	private TableColumn<Reparatie, String> kentekenKolom;
	private TableColumn<Reparatie, Integer> omschrijvingsNummerKolom;
	private TableColumn<Reparatie, Timestamp> begintijdKolom;
	private TableColumn<Reparatie, Timestamp> eindtijdKolom;	
	private TableColumn<Reparatie, Boolean> reparatieStatusKolom;
	private TableColumn<Reparatie, Boolean> betaalStatusKolom;
	
	/**
	 * Constructor voor het initialiseren van de de tabelkolommen
	 * Voegt deze toe aan de tabel
	 */
	public ReparatieOverzicht(){
		this.setEditable(true);
		
		reparatieNummerKolom = new TableColumn<Reparatie, Integer>("Reparatie nummer");
		reparatieNummerKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("reparatieNummer"));
		// Niet editable want primary key in database van reparatie
		
		kentekenKolom = new TableColumn<Reparatie, String>("Kenteken");
		kentekenKolom.setCellValueFactory(new PropertyValueFactory<Reparatie,String>("kenteken"));
		
		omschrijvingsNummerKolom = new TableColumn<Reparatie, Integer>("Omschrijving");
		omschrijvingsNummerKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("omschrijvingsNummer"));
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
		
		betaalStatusKolom = new TableColumn<Reparatie, Boolean>("Betaal Status");
		betaalStatusKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Boolean>("betaalStatus"));
		
		this.getColumns().add(reparatieNummerKolom);
		this.getColumns().add(kentekenKolom);
		this.getColumns().add(omschrijvingsNummerKolom);
		this.getColumns().add(begintijdKolom);
		this.getColumns().add(eindtijdKolom);
		this.getColumns().add(reparatieStatusKolom);
		this.getColumns().add(betaalStatusKolom);
		
		this.getItems().addAll(new ReparatieDao().getReparaties());
	}
	
	/**
	 * Voegt een cellfactory toe aan de kentekenkolom
	 * @param callback De cellfactory voor het genereren van tablecells
	 */
	public void setKentekenKolomCellFactory(Callback<TableColumn<Reparatie, String>, TableCell<Reparatie, String>> callback) {
		kentekenKolom.setCellFactory(callback);
	}
	
	/**
	 * Voegt een cellfactory toe aan de omschrijvingsnummerkolom
	 * @param callback De cellfactory voor het genereren van tablecells
	 */
	public void setOmschrijvingsNummerCellFactory(Callback<TableColumn<Reparatie, Integer>, TableCell<Reparatie, Integer>> callback) {
		omschrijvingsNummerKolom.setCellFactory(callback);
	}
	
	/**
	 * Voegt een cellfactory toe aan de de reparatiestatuskolom
	 * @param callback De cellfactory voor het genereren van tablecells
	 */
	public void setReparatieStatusCellFactory(Callback<TableColumn<Reparatie, Boolean>, TableCell<Reparatie, Boolean>> callback) {
		reparatieStatusKolom.setCellFactory(callback);
	}
	
	/**
	 * Voegt een cellfactory toe aan de betaalstatuskolom
	 * @param callback De cellfactory voor het genereren van tablecells
	 */
	public void setBetaalStatusCellFactory(Callback<TableColumn<Reparatie, Boolean>, TableCell<Reparatie, Boolean>> callback) {
		betaalStatusKolom.setCellFactory(callback);
	}
	
	/**
	 * Voegt een celleditevent toe aan de kentekenkolom
	 * Handelt aanpassingen in de celldata af
	 * @param e Celleditevent voor een aanpassing in de inhoud van een cel
	 */
	public void setKentekenOnEditComit(EventHandler<CellEditEvent<Reparatie, String>> e) {
		kentekenKolom.setOnEditCommit(e);
	}
	
	/**
	 * Voegt een celleditevent toe aan de reparatiestatuskolom
	 * Handelt aanpassingen in de celldata af
	 * @param e Celleditevent voor een aanpassing in de inhoud van een cel
	 */
	public void setReparatieStatusOnEditComit(EventHandler<CellEditEvent<Reparatie, Boolean>> e) {
		reparatieStatusKolom.setOnEditCommit(e);
	}
	
	/**
	 * Voegt een celleditevent toe aan de betaalstatuskolom
	 * Handelt aanpassingen in de celldata af
	 * @param e Celleditevent voor een aanpassing in de inhoud van een cel
	 */
	public void setBetaalStatusOnEditComit(EventHandler<CellEditEvent<Reparatie, Boolean>> e) {
		betaalStatusKolom.setOnEditCommit(e);
	}
	
	/**
	 * Getter voor het opvragen van de reparatiestatuskolom
	 * @return De reperatiestatuskolom
	 */
	public TableColumn<Reparatie, Boolean> getReparatieStatusKolom() {
		return reparatieStatusKolom;
	}

	/**
	 * Getter voor het opvragen van de betaalstatuskolom
	 * @return De betaalstatuskolom
	 */
	public TableColumn<Reparatie, Boolean> getBetaalStatusKolom() {
		return betaalStatusKolom;
	}
}
