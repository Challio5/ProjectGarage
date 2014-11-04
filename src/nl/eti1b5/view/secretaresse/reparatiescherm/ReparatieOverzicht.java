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


public class ReparatieOverzicht extends TableView<Reparatie>{
	
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
	
	// Cell factories voor genereren van een tablecell
	public void setKentekenKolomCellFactory(Callback<TableColumn<Reparatie, String>, TableCell<Reparatie, String>> callback) {
		kentekenKolom.setCellFactory(callback);
	}
	
	public void setOmschrijvingsNummerCellFactory(Callback<TableColumn<Reparatie, Integer>, TableCell<Reparatie, Integer>> callback) {
		omschrijvingsNummerKolom.setCellFactory(callback);
	}
	
	public void setReparatieStatusCellFactory(Callback<TableColumn<Reparatie, Boolean>, TableCell<Reparatie, Boolean>> callback) {
		reparatieStatusKolom.setCellFactory(callback);
	}
	
	public void setBetaalStatusCellFactory(Callback<TableColumn<Reparatie, Boolean>, TableCell<Reparatie, Boolean>> callback) {
		betaalStatusKolom.setCellFactory(callback);
	}
	
	// Edit commits voor het afhandelen van aanpassingen in de tabel
	public void setKentekenOnEditComit(EventHandler<CellEditEvent<Reparatie, String>> e) {
		kentekenKolom.setOnEditCommit(e);
	}
	
	public void setReparatieStatusOnEditComit(EventHandler<CellEditEvent<Reparatie, Boolean>> e) {
		reparatieStatusKolom.setOnEditCommit(e);
	}
	
	public void setBetaalStatusOnEditComit(EventHandler<CellEditEvent<Reparatie, Boolean>> e) {
		betaalStatusKolom.setOnEditCommit(e);
	}
	
	// Getters
	public TableColumn<Reparatie, Boolean> getReparatieStatusKolom() {
		return reparatieStatusKolom;
	}

	public TableColumn<Reparatie, Boolean> getBetaalStatusKolom() {
		return betaalStatusKolom;
	}
}
