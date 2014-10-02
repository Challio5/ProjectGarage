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
	/*
	 * 
	 *  Dient nergens meer voor
	 *  
		Scene scene = new Scene(new MonteurView());
		
		// Stylesheet
		String stylesheet = this.getClass().getResource("/menu.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		stage.setScene(scene);
		stage.setTitle("Garage Management");
		*/
	}

	/**
	 * Getter voor het opvragen van de stage van het hoofdprogramma
	 * @return De stage van het hoofdprogramma
	 */
	public Stage getStage() {
		return stage;
	}
	

	public void showReparatie(){

		Scene scene = new Scene(new ReparatieView());
		
		// Stylesheet
		String stylesheet = this.getClass().getResource("/menu.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		stage.setScene(scene);
		stage.setTitle("Reparatie");
		stage.show();
		
	}

	public void showMonteur() {
		Scene scene = new Scene(new MonteurView());
		
		// Stylesheet
		String stylesheet = this.getClass().getResource("/menu.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		stage.setScene(scene);
		stage.setTitle("Monteur");
		stage.show();
	}

	public void showJobFout() {
		Stage newStage = new Stage();
		VBox comp = new VBox();
		Label fout = new Label("U heeft geen job gekozen");
		comp.getChildren().add(fout);
		Scene stageScene = new Scene(comp,300,300);
		newStage.setScene(stageScene);
		newStage.show();		
	}

	public void showPassNaam() {
		Stage newStage = new Stage();
		VBox comp = new VBox();
		Label fout = new Label("U heeft een incorrecte username/password combinatie ingevuld");
		comp.getChildren().add(fout);
		Scene stageScene = new Scene(comp,300,300);
		newStage.setScene(stageScene);
		newStage.show();
		
	}

}
