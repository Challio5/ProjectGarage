package nl.eti1b5.view.preloader;

import nl.eti1b5.controller.InlogControl;
import nl.eti1b5.view.MainLoader;
import javafx.application.Preloader;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.eti1b5.view.reparatiesoverzicht.*;

/**
 * GUI klasse, preloader
 * Inlog preloader, opent voor het hoofdprogramma start
 * Laat het hoofdprogramma pas zin op het moment de juiste inloggevens zijn ingevuld
 * 
 * @author Rob Bonhof
 * @since 24 sep. 2014
 * @version 1.0
 */

public class InlogPreloader extends Preloader {

	// Het hoofdprogramma
	private MainLoader app;
	
	//De Stage
	private Stage stage;
	
	//InlogController
	private InlogControl inlogControl;
	
	//Preloader view
	private InlogView start;
	
	/**
	 * Start methode, wordt aangeroepen door de mainmethode voor het starten van de preloader
	 * Voegt buttonlistener toe voor sluiten van de preloader en starten van het hoofdprogramma
	 * Voegt ook de bijbehorende stylesheet toe voor de preloader
	 */
	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		
		// Preloader view
		start = new InlogView();
		
		// Preloader scene
		Scene scene = new Scene(start);
		
		// Preloader stylesheet
		String stylesheet = this.getClass().getResource("/inlog.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		// Preloader GUI instellingen
		stage.setScene(scene);
		stage.setTitle("Login");
		stage.show();
	}

	/**
	 * Methode die wordt aangeroepen op het moment er updates zijn over het starten van het hoofdprogramma
	 * Wacht op de notificatie dat het hoofdprogramma startklaar is, en voegt een referentie naar het hoofdprogramma toe
	 * Referentie kan gebruikt worden voor het showen/hiden van het hoofdprogramma
	 */
	@Override
	public void handleStateChangeNotification(StateChangeNotification notification) {
		super.handleStateChangeNotification(notification);
		// Voldoet op het moment het hoofdprogramma startklaar is
		if(notification.getType() == StateChangeNotification.Type.BEFORE_START) {
			app = ((MainLoader) notification.getApplication());
			// Actionlistener voor een druk op submit knop
			inlogControl = new InlogControl(this, app);
			start.setSubmitListener(inlogControl);
		}
	}
	
	public Stage getStage(){
		return stage;
	}
	
	public InlogView getInlogView(){
		return start;
	}
}
