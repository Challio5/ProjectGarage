package nl.eti1b5.controller;

import nl.eti1b5.view.secretaresse.administratiescherm.AdministratieOverzicht;
import nl.eti1b5.view.secretaresse.menu.MenuNode;
import nl.eti1b5.view.secretaresse.menu.MenuView;
import nl.eti1b5.view.secretaresse.planningsscherm.PlanningsOverzicht;
import nl.eti1b5.view.secretaresse.reparatiescherm.ReparatieOverzicht;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SecretaresseControl{
	
	private Stage stage;
	
	private MenuNode menu;
	private MenuView menuView;
	
	private AdministratieOverzicht adminstratieOverzicht;
	private PlanningsOverzicht planningsOverzicht;
	private ReparatieOverzicht reparatieOverzicht;
	
	public SecretaresseControl(Stage stage){
		this.stage = stage;
		
		menu = new MenuNode();
		
		menu.restartSetOnAction(new RestartControl());
		menu.exitSetOnAction(new ExitControl());
		
		adminstratieOverzicht = new AdministratieOverzicht();
		menu.adminstratieSetOnAction(new AdministratieOverzichtControl());
		
		planningsOverzicht = new PlanningsOverzicht();
		menu.planningSetOnAction(new PlanningsOverzichtControl());
		
		reparatieOverzicht = new ReparatieOverzicht();
		menu.reparatieSetOnAction(new ReparatieOverzichtControl());
		
		menuView = new MenuView(menu, adminstratieOverzicht, planningsOverzicht, reparatieOverzicht);
		
		this.init();
	}
	
	private void init() {
		Scene scene = new Scene(menuView);
		
		// Stylesheet
		String stylesheet = this.getClass().getResource("/menu.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		stage.setScene(scene);
		stage.setTitle("Garage S.J. Oemelaar");
		stage.show();
	}

	private class RestartControl implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
	}
	
	private class ExitControl implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			System.exit(0);
		}
		
	}
	
	private class AdministratieOverzichtControl implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			menuView.showAdministratieOverzicht();
		}
	
	}
	
	private class PlanningsOverzichtControl implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			menuView.showPlanningOverzicht();
		}
		
	}
	
	private class ReparatieOverzichtControl implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			menuView.showReparatieOverzicht();
		}
		
	}
}
