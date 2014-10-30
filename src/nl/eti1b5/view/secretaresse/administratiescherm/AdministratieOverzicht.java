package nl.eti1b5.view.secretaresse.administratiescherm;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;
import nl.eti1b5.database.dao.KlantDao;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.model.Klant;
import nl.eti1b5.model.Monteur;

public class AdministratieOverzicht extends VBox{
	
	// Dao voor het ophalen en wegschrijven van de klanten en monteurs
	private KlantDao klantDao;
	private MonteurDao monteurDao;
	
	// Combobox voor het kiezen van de adminstratie van klanten of monteurs
	private ComboBox<String> administratieKiezer;
	
	// Tabel voor het weergeven van de klanten en monteur
	private TableView<Klant> klantenTabel;
	private TableView<Monteur> monteurTabel;
	
	// Tabelkolommen voor het weergeven van de attributen van klanten
	private TableColumn<Klant, Integer> klantnummerKolom;
	private TableColumn<Klant, String> klantNaamKolom;
	private TableColumn<Klant, String> klantAdresKolom;
	private TableColumn<Klant, String> klantPostcodeKolom;
	private TableColumn<Klant, String> klantPlaatsKolom;
	private TableColumn<Klant, String> klantTelefoonnummerKolom;
	
	// Tabelkolommen voor het weergeven van de attributen van monteurs
	private TableColumn<Monteur, Integer> monteurnummerKolom;
	private TableColumn<Monteur, String> monteurNaamKolom;
	private TableColumn<Monteur, String> monteurAdresKolom;
	private TableColumn<Monteur, String> monteurPostcodeKolom;
	private TableColumn<Monteur, String> monteurPlaatsKolom;
	private TableColumn<Monteur, String> monteurTelefoonnummerKolom;
	private TableColumn<Monteur, String> monteurWachtwoordKolom;
	private TableColumn<Monteur, String> monteurSpecialiteitKolom;	
	
	public AdministratieOverzicht() {
		// Dao voor het ophalen en wegschrijven van de klanten
		klantDao = new KlantDao();
		monteurDao = new MonteurDao();
		
		// Combobox voor het kiezen van de adminstratie van klanten of monteurs
		administratieKiezer = new ComboBox<>();
		administratieKiezer.getItems().add("Klanten");
		administratieKiezer.getItems().add("Monteurs");
		administratieKiezer.getSelectionModel().selectFirst();
		this.setAdministratieKiezerListener();
		
		// Tabellen voor het weergeven van de klanten en monteurs
		// Maakt ze editable voor bewerken van de weergegeven data
		klantenTabel = new TableView<>();
		klantenTabel.setEditable(true);
		monteurTabel = new TableView<>();
		monteurTabel.setEditable(true);
		
		// Tabelkolommen voor het weergeven van de attributen
		monteurnummerKolom = new TableColumn<>("Monteurnummer");
		monteurnummerKolom.setCellValueFactory(new PropertyValueFactory<>("werknemerNr"));
		// Niet editable aangezien primary key in database
		
		monteurNaamKolom = new TableColumn<>("Naam");
		monteurNaamKolom.setCellValueFactory(new PropertyValueFactory<>("naam"));
		monteurNaamKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		monteurNaamKolom.setOnEditCommit(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setNaam(e.getNewValue());
		});
		
		monteurAdresKolom = new TableColumn<>("Adres");
		monteurAdresKolom.setCellValueFactory(new PropertyValueFactory<>("adres"));
		monteurAdresKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		monteurAdresKolom.setOnEditCommit(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setAdres(e.getNewValue());
		});
		
		monteurPostcodeKolom = new TableColumn<>("Postcode");
		monteurPostcodeKolom.setCellValueFactory(new PropertyValueFactory<>("postcode"));
		monteurPostcodeKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		monteurPostcodeKolom.setOnEditCommit(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setPostcode(e.getNewValue());
		});
		
		monteurPlaatsKolom = new TableColumn<>("Plaats");
		monteurPlaatsKolom.setCellValueFactory(new PropertyValueFactory<>("woonplaats"));
		monteurPlaatsKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		monteurPlaatsKolom.setOnEditCommit(e -> {
			Monteur monteur = e.getRowValue();
			monteur.setwoonplaats(e.getNewValue());
		});
		
		monteurTelefoonnummerKolom = new TableColumn<>("Telefoonnummer");
		monteurTelefoonnummerKolom.setCellValueFactory(new PropertyValueFactory<>("telNr"));
		monteurTelefoonnummerKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		monteurTelefoonnummerKolom.setOnEditCommit(e -> {
			Monteur monteur = e.getRowValue();
		});
		
		monteurWachtwoordKolom = new TableColumn<>("Wachtwoord");
		monteurWachtwoordKolom.setCellValueFactory(new PropertyValueFactory<>("wachtwoord"));
		monteurWachtwoordKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		monteurWachtwoordKolom.setOnEditCommit(e -> {
			Monteur monteur = e.getRowValue();
		});
		
		monteurSpecialiteitKolom = new TableColumn<>("Specialiteit");
		monteurSpecialiteitKolom.setCellValueFactory(new PropertyValueFactory<>("specialiteit"));
		monteurSpecialiteitKolom.setCellFactory(TextFieldTableCell.<Monteur>forTableColumn());
		monteurSpecialiteitKolom.setOnEditCommit(e -> {
			Monteur monteur = e.getRowValue();
		});
		
		// Tabelkolommen voor het weergeven van de attributen
		klantnummerKolom = new TableColumn<>("Klantnummer");
		klantnummerKolom.setCellValueFactory(new PropertyValueFactory<>("klantNr"));
		// Niet editable aangezien primary key in database
		
		klantNaamKolom = new TableColumn<>("Naam");
		klantNaamKolom.setCellValueFactory(new PropertyValueFactory<>("naam"));
		klantNaamKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
		klantNaamKolom.setOnEditCommit(e -> {
			Klant klant = e.getRowValue();
			klant.setNaam(e.getNewValue());
		});
		
		klantAdresKolom = new TableColumn<>("Adres");
		klantAdresKolom.setCellValueFactory(new PropertyValueFactory<>("adres"));
		klantAdresKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
		klantAdresKolom.setOnEditCommit(e -> {
			Klant klant = e.getRowValue();
			klant.setAdres(e.getNewValue());
		});
		
		klantPostcodeKolom = new TableColumn<>("Postcode");
		klantPostcodeKolom.setCellValueFactory(new PropertyValueFactory<>("postcode"));
		klantPostcodeKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
		klantPostcodeKolom.setOnEditCommit(e -> {
			Klant klant = e.getRowValue();
			klant.setPostcode(e.getNewValue());
		});
		
		klantPlaatsKolom = new TableColumn<>("Plaats");
		klantPlaatsKolom.setCellValueFactory(new PropertyValueFactory<>("woonplaats"));
		klantPlaatsKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
		klantPlaatsKolom.setOnEditCommit(e -> {
			Klant klant = e.getRowValue();
			klant.setwoonplaats(e.getNewValue());
		});
		
		klantTelefoonnummerKolom = new TableColumn<>("Telefoonnummer");
		klantTelefoonnummerKolom.setCellValueFactory(new PropertyValueFactory<>("telNr"));
		klantTelefoonnummerKolom.setCellFactory(TextFieldTableCell.<Klant>forTableColumn());
		klantTelefoonnummerKolom.setOnEditCommit(e -> {
			Klant klant = e.getRowValue();
			klant.setTelNr(e.getNewValue());
		});
		
		// Voegt de kolommen toe aan de klanttabel
		klantenTabel.getColumns().add(klantnummerKolom);
		klantenTabel.getColumns().add(klantNaamKolom);
		klantenTabel.getColumns().add(klantAdresKolom);
		klantenTabel.getColumns().add(klantPostcodeKolom);
		klantenTabel.getColumns().add(klantPlaatsKolom);
		klantenTabel.getColumns().add(klantTelefoonnummerKolom);
		
		// Voegt de kolommen toe aan de monteurtabel
		monteurTabel.getColumns().add(monteurnummerKolom);
		monteurTabel.getColumns().add(monteurNaamKolom);
		monteurTabel.getColumns().add(monteurAdresKolom);
		monteurTabel.getColumns().add(monteurPostcodeKolom);
		monteurTabel.getColumns().add(monteurPlaatsKolom);
		monteurTabel.getColumns().add(monteurTelefoonnummerKolom);
		monteurTabel.getColumns().add(monteurWachtwoordKolom);
		monteurTabel.getColumns().add(monteurSpecialiteitKolom);
		
		// Voegt de klanten toe aan de tabel
		klantenTabel.getItems().addAll(klantDao.getKlanten());
		monteurTabel.getItems().addAll(monteurDao.getMonteurs());
		
		// Voegt de onderdelen toe aan het klantenoverzicht
		this.getChildren().add(administratieKiezer);
		this.getChildren().add(klantenTabel);
	}
	
	// Listener voor het detecteren van een verandering in de administratieKiezer
	// Laat op basis van de selectie het juiste overzicht zien
	public void setAdministratieKiezerListener() {
		administratieKiezer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			switch(newValue) {
			case "Klanten":
				this.getChildren().remove(monteurTabel);
				this.getChildren().add(klantenTabel);
				break;
			case "Monteurs":
				this.getChildren().remove(klantenTabel);
				this.getChildren().add(monteurTabel);
				break;
			}
		});
	}
}
