package nl.eti1b5.view;

import nl.eti1b5.view.monteursoverzicht.MonteurView;
import nl.eti1b5.view.reparatiesoverzicht.ReparatieView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GUI klasse, hoofdprogramma
 * Hoofdprogramma voor interactie met de gebruiker
 * Opent nadat de preloader gesloten is en de juiste inloggegevens zijn ingevoerd
 * 
 * @author Rob Bonhof
 * @since 24 sep. 2014
 * @version 1.0
 */

public class MainLoader extends Application {
	
	// De stage
	private Stage stage;
	
	/**
	 * Start methode wordt aangeroepen door de mainmethode voor het starten van het hoofdprogramma
	 */
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
	}

	/**
	 * Getter voor het opvragen van de stage van het hoofdprogramma
	 * @return De stage van het hoofdprogramma
	 */
	public Stage getStage() {
		return stage;
	}
}
