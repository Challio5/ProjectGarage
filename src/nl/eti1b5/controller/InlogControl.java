package nl.eti1b5.controller;

import nl.eti1b5.view.MainLoader;
import nl.eti1b5.view.reparatiesoverzicht.ReparatieView;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InlogControl implements EventHandler{
	
	private MainLoader app;
	private Stage inlogStage;
	
	public InlogControl(Stage stage, MainLoader app){
		inlogStage = stage;
		this.app = app;
	}
	
	@Override
	public void handle(Event arg0) {
		System.out.println("In de handle");
		// App is null op het moment deze nog niet uitgeladen is
		if(app != null) {
			System.out.println("in app ongelijk null");
			// Hide de preloader en showt het hoofdprogramma
			inlogStage.hide();
			app.showReparatie();
			app.getStage().show();
		}
	}

}
