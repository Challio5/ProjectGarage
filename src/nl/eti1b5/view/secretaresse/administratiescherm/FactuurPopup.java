package nl.eti1b5.view.secretaresse.administratiescherm;

import java.sql.Timestamp;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Reparatie;

public class FactuurPopup extends VBox{
	// Dao voor het weergeven en wegschrijven van reparaties naar de database
	private ReparatieDao reparatieDao;
	
	// Tabel voor het weergeven van de reparatie
	private TableView<Reparatie> reparatieTabel;
	
	// Kolommen voor het weergeven van de attributen van de reparaties
	private TableColumn<Reparatie, Integer> reparatieNummerKolom;
	private TableColumn<Reparatie, String> kentekenKolom;
	private TableColumn<Reparatie, Integer> omschrijvingsNummerKolom;
	private TableColumn<Reparatie, Timestamp> begintijdKolom;
	private TableColumn<Reparatie, Timestamp> eindtijdKolom;	
	private TableColumn<Reparatie, Boolean> reparatieStatusKolom;
	private TableColumn<Reparatie, Boolean> betaalStatusKolom;
	// Materialen ???
	
	// Knop voor het printen van een factuur met de gemaakte reparaties
	private Button printButton;
	
	public FactuurPopup(int klantnummer){
		// Dao voor het weergeven en wegschrijven van reparaties naar de database
		reparatieDao = new ReparatieDao();
		
		// Tabel voor het weergeven van de reparatie
		reparatieTabel = new TableView<>();
		reparatieTabel.setEditable(true);
		
		// Kolommen voor het weergeven van de attributen van de reparaties
		reparatieNummerKolom = new TableColumn<Reparatie, Integer>("Reparatie nummer");
		reparatieNummerKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("reparatieNummer"));
		
		kentekenKolom = new TableColumn<Reparatie, String>("Kenteken");
		kentekenKolom.setCellValueFactory(new PropertyValueFactory<Reparatie,String>("kenteken"));
		
		omschrijvingsNummerKolom = new TableColumn<Reparatie, Integer>("Omschrijving");
		omschrijvingsNummerKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("omschrijvingsNummer"));
		
		begintijdKolom = new TableColumn<Reparatie, Timestamp>("Begintijd");
		begintijdKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Timestamp>("beginTijd"));
		
		eindtijdKolom = new TableColumn<Reparatie, Timestamp>("Eindtijd");
		eindtijdKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Timestamp>("eindTijd"));
		
		reparatieStatusKolom = new TableColumn<Reparatie, Boolean>("Reparatie Status");
		reparatieStatusKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Boolean>("reparatieStatus"));
		
		betaalStatusKolom = new TableColumn<Reparatie, Boolean>("Betaal Status");
		betaalStatusKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Boolean>("betaalStatus"));
		
		reparatieTabel.getColumns().add(reparatieNummerKolom);
		reparatieTabel.getColumns().add(kentekenKolom);
		reparatieTabel.getColumns().add(omschrijvingsNummerKolom);
		reparatieTabel.getColumns().add(begintijdKolom);
		reparatieTabel.getColumns().add(eindtijdKolom);
		reparatieTabel.getColumns().add(reparatieStatusKolom);
		reparatieTabel.getColumns().add(betaalStatusKolom);
		
		// Reparaties van klant
		reparatieTabel.setItems(FXCollections.observableArrayList(reparatieDao.getKlantReparaties(klantnummer, false)));
		
		// Knop voor het printen van een factuur met de gemaakte reparaties
		printButton = new Button("Print");
		
		this.getChildren().add(reparatieTabel);
		this.getChildren().add(printButton);
	}
	
	public TableView<Reparatie> getReparatieTabel() {
		return reparatieTabel;
	}

	public void setOmschrijvingsNummerKolomCellFactory(Callback<TableColumn<Reparatie, Integer>, TableCell<Reparatie, Integer>> callback) {
		omschrijvingsNummerKolom.setCellFactory(callback);
	}
	
	public void setBetaalStatusKolomCellFactory(Callback<TableColumn<Reparatie, Boolean>, TableCell<Reparatie, Boolean>> callback) {
		betaalStatusKolom.setCellFactory(callback);
	}
	
	public void setPrintButtonActionListener(EventHandler<ActionEvent> e) {
		printButton.setOnAction(e);
	}
}
