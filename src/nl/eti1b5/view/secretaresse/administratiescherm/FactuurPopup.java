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

/**
 * Gui klasse voor het weergeven van de attributen van opstaande reparaties
 * Wordt aangemaakt op het moment de gebruiker op de printknop drukt voor uitdraaien van een factuur
 * Wordt weergegeven bovenop het hoofdscherm
 * 
 * @author ETI1vb3
 * @since 5 nov. 2014
 */

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
	
	/**
	 * Constructor voor het initialiseren van de tabel met kolommen van de openstaande reparaties
	 * @param klantnummer Het klantnummer voor het weergeven van de openstaande reparatie van de klant
	 */
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
	
	/**
	 * Getter voor opvragen van de tabel met openstaande reparaties
	 * @return De tabel met reparaties
	 */
	public TableView<Reparatie> getReparatieTabel() {
		return reparatieTabel;
	}

	/**
	 * Setter voor het toevoegen van een cellfactory aan de omschrijvingsnummerkolom
	 * Genereert de tabelcellen voor de kolom
	 * @param callback De factory voor het genereren van tabelcellen
	 */
	public void setOmschrijvingsNummerKolomCellFactory(Callback<TableColumn<Reparatie, Integer>, TableCell<Reparatie, Integer>> callback) {
		omschrijvingsNummerKolom.setCellFactory(callback);
	}
	
	/**
	 * Setter voor het toevoegen van een cellfactory aan de betaalstatuskolom
	 * Genereert de tabelcellen voor de kolom
	 * @param callback De factory voor het genereren van tabelcellen
	 */
	public void setBetaalStatusKolomCellFactory(Callback<TableColumn<Reparatie, Boolean>, TableCell<Reparatie, Boolean>> callback) {
		betaalStatusKolom.setCellFactory(callback);
	}
	
	/**
	 * Voegt een actionlistener toe aan de printknop
	 * Actionlistener wordt getriggerd op het moment dat er op de knop gedrukt wordt
	 * @param e Actionevent voor het afhandelen van een druk op de printknop
	 */
	public void setPrintButtonActionListener(EventHandler<ActionEvent> e) {
		printButton.setOnAction(e);
	}
}
