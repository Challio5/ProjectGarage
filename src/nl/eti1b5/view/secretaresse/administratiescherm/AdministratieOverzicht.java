package nl.eti1b5.view.secretaresse.administratiescherm;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import nl.eti1b5.database.dao.KlantDao;
import nl.eti1b5.database.dao.MonteurDao;

/**
 * Gui klasse met tabel en combobox voor het weergeven van alle klanten en monteurs
 * Aan de hand van de combobox wordt of de tabel met monteurs met die met klanten weergegeven
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */

public class AdministratieOverzicht extends VBox{

	// De tabellen met het klanten- en monteursoverzicht
	private KlantenTabel klantenTabel;
	private MonteursTabel monteursTabel;
	
	// HBox voor de refresh butten en de administratieKiezer
	private HBox hBox;
		
	// Combobox voor het kiezen van de adminstratie van klanten of monteurs
	private ComboBox<String> administratieKiezer;
	
	//Button voor het verversen van de lijst
	private Button refresh;
	
	// Button voor het toevoegen van een klant of monteur
	private Button klantToevoegButton;
	private Button monteurToevoegButton;
	
	/**
	 * Constructor voor het initialiseren van de klanten- en monteurs tabel inclusief kolommen
	 * Maakt ook een combobox aan voor het selecteren van de juiste tabel
	 */
	public AdministratieOverzicht() {
		
		// De tabellen met het klanten- en monteursoverzicht
		klantenTabel = new KlantenTabel();
		monteursTabel = new MonteursTabel();
		
		// Combobox voor het kiezen van de adminstratie van klanten of monteurs
		administratieKiezer = new ComboBox<>();
		administratieKiezer.getItems().add("Klanten");
		administratieKiezer.getItems().add("Monteurs");
		administratieKiezer.getSelectionModel().selectFirst();
		
		refresh = new Button("Verversen");
		refresh.setOnAction(e -> {
			update();
		});
		
		hBox = new HBox();
		hBox.getChildren().addAll(administratieKiezer, refresh);
		// Button voor het toevoegen van klanten ofwel monteurs
		klantToevoegButton = new Button("Klant toevoegen");
		klantToevoegButton.setOnAction(e -> {
			new AddKlantScherm();
		});
		monteurToevoegButton = new Button("Monteur toevoegen");
		monteurToevoegButton.setOnAction(e -> {
			new AddMonteurScherm();
		});
		
		update();
		
		// Voegt de onderdelen toe aan het klantenoverzicht
		this.getChildren().add(hBox);
		this.getChildren().add(klantenTabel);
		this.getChildren().add(klantToevoegButton);
	}

	/**
	 * Getter voor het opvragen van de tabel met klanten
	 * @return De tabel met klanten
	 */
	public KlantenTabel getKlantenTabel() {
		return klantenTabel;
	}

	/**
	 * Getter voor het opvragen van de tabel met monteurs
	 * @return De tabel met monteurs
	 */
	public MonteursTabel getMonteursTabel() {
		return monteursTabel;
	}
	
	/**
	 * Methode om de data in de tabel mee te updaten
	 */
	public void update(){
		// Voegt de klanten toe aan de tabel
		klantenTabel.getItems().clear();
		monteursTabel.getItems().clear();
		klantenTabel.getItems().addAll(new KlantDao().getKlanten());
		monteursTabel.getItems().addAll(new MonteurDao().getMonteurs());
	}
	
	/**
	 * Getter voor het opvragen van de klanttoevoegknop
	 * @return De klanttoevoegknop
	 */
	public Button getKlantToevoegButton() {
		return klantToevoegButton;
	}

	/**
	 * Getter voor het opvragen van de monteurtoevoegknop
	 * @return De monteurtoevoegknop
	 */
	public Button getMonteurToevoegButton() {
		return monteurToevoegButton;
	}

	/**
	 * Voegt een Changelistener toe aan de administratiekiezer
	 * Luistert naar een verandering van de geselecteerde waarde in de combobox
	 * @param listener De changelistener voor het afhandelen van veranderingen in de geselecteerde waarde
	 */
	public void setAdministratieKiezerListener(ChangeListener<String> listener) {
		administratieKiezer.getSelectionModel().selectedItemProperty().addListener(listener);
	}
}
