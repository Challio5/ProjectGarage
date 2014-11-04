package nl.eti1b5.view.secretaresse.administratiescherm;

import javafx.beans.value.ChangeListener;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

public class AdministratieOverzicht extends VBox{

	// De tabellen met het klanten- en monteursoverzicht
	private KlantenTabel klantenTabel;
	private MonteursTabel monteursTabel;
	
	// Combobox voor het kiezen van de adminstratie van klanten of monteurs
	private ComboBox<String> administratieKiezer;
	
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
		
		// Button voor het toevoegen van klanten ofwel monteurs
		klantToevoegButton = new Button("Klant toevoegen");
		klantToevoegButton.setOnAction(e -> {
			new AddKlantScherm();
		});
		monteurToevoegButton = new Button("Monteur toevoegen");
		
		// Voegt de onderdelen toe aan het klantenoverzicht
		this.getChildren().add(administratieKiezer);
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
