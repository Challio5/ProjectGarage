package nl.eti1b5.controller;

import nl.eti1b5.view.MainLoader;
import nl.eti1b5.view.monteursoverzicht.MonteurView;
import nl.eti1b5.view.preloader.InlogPreloader;
import nl.eti1b5.view.preloader.InlogView;
import nl.eti1b5.view.reparatiesoverzicht.ReparatieView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InlogControl implements EventHandler{
	
	private MainLoader app;
	private InlogPreloader inlogPre;
	private InlogView inlogView;
	private int counter;
	
	public InlogControl(InlogPreloader inlogPre, MainLoader app){
		this.inlogPre = inlogPre;
		this.app = app;
		inlogView = inlogPre.getInlogView();
		counter = 0;
	}
	
	@Override
	public void handle(Event arg0) {
		if(counter < 3){
			if(inlogView.getJob().getValue() == null){
				showJobFout();
			} else if (inlogView.getInlogNode().getName().getText().equals("")){
				showPassNaam();
			} else if (inlogView.getInlogNode().getPassword().getText().equals("")){
				showPassNaam();
			} else if(inlogView.getInlogNode().getPassword().getText().equals("Jow") && inlogPre.getInlogView().getInlogNode().getName().getText().equals("Jow")){
			// App is null op het moment deze nog niet uitgeladen is
				if(inlogView.getJob().getValue() == null){
					showJobFout();
				} else if(inlogView.getJob().getValue().equals("Monteur")){
					if(app != null) {
						// Hide de preloader en showt het hoofdprogramma
						inlogPre.getStage().hide();
						showMonteur();
					}
				} else if(inlogView.getJob().getValue().equals("Secretaresse")){
					if(app != null) {
						// Hide de preloader en showt het hoofdprogramma
						inlogPre.getStage().hide();
						showReparatie();
					}
				}
			} else {
				showPassNaam();
			}
		} else {
			inlogView.getInlogNode().setLock();
		}
	}
	

	public void showJobFout() {
		inlogView.getFoutMelding().setText("U heeft geen job gekozen");
	}
	
	public void showPassNaam(){
		inlogView.getFoutMelding().setText("U heeft een incorrecte username/password combinatie ingevuld");
		counter++;
	}

	public void showMonteur() {
		Scene scene = new Scene(new MonteurView());
		
		// Stylesheet
		String stylesheet = this.getClass().getResource("/menu.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		app.getStage().setScene(scene);
		app.getStage().setTitle("Monteur");
		app.getStage().show();
	}
	
	public void showReparatie(){

		Scene scene = new Scene(new ReparatieView());
		
		// Stylesheet
		String stylesheet = this.getClass().getResource("/menu.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		app.getStage().setScene(scene);
		app.getStage().setTitle("Reparatie");
		app.getStage().show();
		
	}
}
