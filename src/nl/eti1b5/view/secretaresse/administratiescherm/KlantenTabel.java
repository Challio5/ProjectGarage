package nl.eti1b5.view.secretaresse.administratiescherm;

import nl.eti1b5.database.dao.KlantDao;
import nl.eti1b5.model.Klant;
import javafx.event.EventHandler;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

public class KlantenTabel extends TableView<Klant> {
	
	// Tabelkolommen voor het weergeven van de attributen van klanten
	private TableColumn<Klant, Integer> klantnummerKolom;
	private TableColumn<Klant, String> klantNaamKolom;
	private TableColumn<Klant, String> klantAdresKolom;
	private TableColumn<Klant, String> klantPostcodeKolom;
	private TableColumn<Klant, String> klantPlaatsKolom;
	private TableColumn<Klant, String> klantTelefoonnummerKolom;
	
	public KlantenTabel() {
		// Bewerkbaar voor het aanpassen van de data
		this.setEditable(true);
		
		// Tabelkolommen voor het weergeven van de attributen
		klantnummerKolom = new TableColumn<>("Klantnummer");
		klantnummerKolom.setCellValueFactory(new PropertyValueFactory<>("klantNr"));
		// Niet editable aangezien primary key in database
		
		klantNaamKolom = new TableColumn<>("Naam");
		klantNaamKolom.setCellValueFactory(new PropertyValueFactory<>("naam"));
		klantNaamKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
		
		klantAdresKolom = new TableColumn<>("Adres");
		klantAdresKolom.setCellValueFactory(new PropertyValueFactory<>("adres"));
		klantAdresKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
		
		klantPostcodeKolom = new TableColumn<>("Postcode");
		klantPostcodeKolom.setCellValueFactory(new PropertyValueFactory<>("postcode"));
		klantPostcodeKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
		
		klantPlaatsKolom = new TableColumn<>("Plaats");
		klantPlaatsKolom.setCellValueFactory(new PropertyValueFactory<>("woonplaats"));
		klantPlaatsKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
		
		klantTelefoonnummerKolom = new TableColumn<>("Telefoonnummer");
		klantTelefoonnummerKolom.setCellValueFactory(new PropertyValueFactory<>("telNr"));
		klantTelefoonnummerKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
	
		// Voegt de kolommen toe aan de klanttabel
		this.getColumns().add(klantnummerKolom);
		this.getColumns().add(klantNaamKolom);
		this.getColumns().add(klantAdresKolom);
		this.getColumns().add(klantPostcodeKolom);
		this.getColumns().add(klantPlaatsKolom);
		this.getColumns().add(klantTelefoonnummerKolom);
		
		// Voegt de gegevens uit de database toe aan de tabel
		this.getItems().addAll(new KlantDao().getKlanten());
	}
	
	// Callback voor factuurpopup
	public void setKlantnummerKolomCellFactory(Callback<TableColumn<Klant, Integer>, TableCell<Klant, Integer>> callback) {
		klantnummerKolom.setCellFactory(callback);
	}
	
	// CellEditEvent handlers voor de klant	
	public void setKlantNaamKolomCellEditEvent(EventHandler<CellEditEvent<Klant, String>> e) {
		klantNaamKolom.setOnEditCommit(e);
	}
	
	public void setKlantAdresKolomKolomCellEditEvent(EventHandler<CellEditEvent<Klant, String>> e) {
		klantAdresKolom.setOnEditCommit(e);
	}
	
	public void setKlantPostcodeKolomCellEditEvent(EventHandler<CellEditEvent<Klant, String>> e) {
		klantPostcodeKolom.setOnEditCommit(e);
	}
	
	public void setKlantPlaatsKolomCellEditEvent(EventHandler<CellEditEvent<Klant, String>> e) {
		klantPlaatsKolom.setOnEditCommit(e);
	}
	
	public void setKlantTelefoonnummerKolomCellEditEvent(EventHandler<CellEditEvent<Klant, String>> e) {
		klantTelefoonnummerKolom.setOnEditCommit(e);
	}
}
