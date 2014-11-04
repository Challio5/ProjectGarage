package nl.eti1b5.view.secretaresse.administratiescherm;

import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.model.Monteur;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class MonteursTabel extends TableView<Monteur> {
	
	// Tabelkolommen voor het weergeven van de attributen van monteurs
	private TableColumn<Monteur, Integer> monteurnummerKolom;
	private TableColumn<Monteur, String> monteurNaamKolom;
	private TableColumn<Monteur, String> monteurAdresKolom;
	private TableColumn<Monteur, String> monteurPostcodeKolom;
	private TableColumn<Monteur, String> monteurPlaatsKolom;
	private TableColumn<Monteur, String> monteurTelefoonnummerKolom;
	private TableColumn<Monteur, String> monteurWachtwoordKolom;
	private TableColumn<Monteur, String> monteurSpecialiteitKolom;
	
	public MonteursTabel() {
		// Bewerkbaar voor het aanpassen van de data
		this.setEditable(true);
		
		// Tabelkolommen voor het weergeven van de attributen
		monteurnummerKolom = new TableColumn<>("Monteurnummer");
		monteurnummerKolom.setCellValueFactory(new PropertyValueFactory<>("werknemerNr"));
		// Niet editable aangezien primary key in database
		
		monteurNaamKolom = new TableColumn<>("Naam");
		monteurNaamKolom.setCellValueFactory(new PropertyValueFactory<>("naam"));
		monteurNaamKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		
		monteurAdresKolom = new TableColumn<>("Adres");
		monteurAdresKolom.setCellValueFactory(new PropertyValueFactory<>("adres"));
		monteurAdresKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		
		monteurPostcodeKolom = new TableColumn<>("Postcode");
		monteurPostcodeKolom.setCellValueFactory(new PropertyValueFactory<>("postcode"));
		monteurPostcodeKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		
		monteurPlaatsKolom = new TableColumn<>("Plaats");
		monteurPlaatsKolom.setCellValueFactory(new PropertyValueFactory<>("woonplaats"));
		monteurPlaatsKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		
		monteurTelefoonnummerKolom = new TableColumn<>("Telefoonnummer");
		monteurTelefoonnummerKolom.setCellValueFactory(new PropertyValueFactory<>("telNr"));
		monteurTelefoonnummerKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		
		monteurWachtwoordKolom = new TableColumn<>("Wachtwoord");
		monteurWachtwoordKolom.setCellValueFactory(new PropertyValueFactory<>("wachtwoord"));
		monteurWachtwoordKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		
		monteurSpecialiteitKolom = new TableColumn<>("Specialiteit");
		monteurSpecialiteitKolom.setCellValueFactory(new PropertyValueFactory<>("specialiteit"));
		monteurSpecialiteitKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
	
		// Voegt de kolommen toe aan de monteurtabel
		this.getColumns().add(monteurnummerKolom);
		this.getColumns().add(monteurNaamKolom);
		this.getColumns().add(monteurAdresKolom);
		this.getColumns().add(monteurPostcodeKolom);
		this.getColumns().add(monteurPlaatsKolom);
		this.getColumns().add(monteurTelefoonnummerKolom);
		this.getColumns().add(monteurWachtwoordKolom);
		this.getColumns().add(monteurSpecialiteitKolom);
		
		// Voegt de gegevens uit de database toe aan de tabel
		this.getItems().addAll(new MonteurDao().getMonteurs());
	}
	
	// CellEditEvent handlers voor de monteur	
	public void setMonteurNaamKolomCellEditEvent(EventHandler<CellEditEvent<Monteur, String>> e) {
		monteurNaamKolom.setOnEditCommit(e);
	}

	public void setMonteurAdresKolomCellEditEvent(EventHandler<CellEditEvent<Monteur, String>> e) {
		monteurAdresKolom.setOnEditCommit(e);
	}

	public void setMonteurPostcodeKolomCellEditEvent(EventHandler<CellEditEvent<Monteur, String>> e) {
		monteurPostcodeKolom.setOnEditCommit(e);
	}
	
	public void setMonteurPlaatsKolomCellEditEvent(EventHandler<CellEditEvent<Monteur, String>> e) {
		monteurPlaatsKolom.setOnEditCommit(e);
	}
	
	public void setMonteurTelefoonnummerKolomCellEditEvent(EventHandler<CellEditEvent<Monteur, String>> e) {
		monteurTelefoonnummerKolom.setOnEditCommit(e);
	}
	
	public void setMonteurWachtwoordKolom(EventHandler<CellEditEvent<Monteur, String>> e) {
		monteurWachtwoordKolom.setOnEditCommit(e);
	}
	
	public void setMonteurSpecialiteitKolomCellEditEvent(EventHandler<CellEditEvent<Monteur, String>> e) {
		monteurSpecialiteitKolom.setOnEditCommit(e);
	}
}
