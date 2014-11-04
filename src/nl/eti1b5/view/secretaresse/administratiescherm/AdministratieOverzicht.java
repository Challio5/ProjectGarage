package nl.eti1b5.view.secretaresse.administratiescherm;

import nl.eti1b5.database.dao.KlantDao;
import nl.eti1b5.database.dao.MonteurDao;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

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

	// Getters voor het opvragen van de nodes
	public KlantenTabel getKlantenTabel() {
		return klantenTabel;
	}

	public MonteursTabel getMonteursTabel() {
		return monteursTabel;
	}
	
	public void update(){
		// Voegt de klanten toe aan de tabel
		klantenTabel.getItems().clear();
		monteursTabel.getItems().clear();
		klantenTabel.getItems().addAll(new KlantDao().getKlanten());
		monteursTabel.getItems().addAll(new MonteurDao().getMonteurs());
	}
	
	public Button getKlantToevoegButton() {
		return klantToevoegButton;
	}

	public Button getMonteurToevoegButton() {
		return monteurToevoegButton;
	}

	// Listener voor de geselecteerde waarde in de combobox
	public void setAdministratieKiezerListener(ChangeListener<String> listener) {
		administratieKiezer.getSelectionModel().selectedItemProperty().addListener(listener);
	}
}
