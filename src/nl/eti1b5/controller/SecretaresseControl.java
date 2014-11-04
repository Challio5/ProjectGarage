package nl.eti1b5.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nl.eti1b5.controller.secretaresse.AdministratieSchermControl;
import nl.eti1b5.controller.secretaresse.PlanningsSchermControl;
import nl.eti1b5.controller.secretaresse.ReparatieSchermControl;
import nl.eti1b5.view.secretaresse.administratiescherm.AdministratieOverzicht;
import nl.eti1b5.view.secretaresse.menu.MenuNode;
import nl.eti1b5.view.secretaresse.planningsscherm.PlanningsOverzicht;
import nl.eti1b5.view.secretaresse.reparatiescherm.ReparatieOverzicht;

public class SecretaresseControl{
	
	// De hoofdstage waar het programma mee draait
	private Stage stage;
	
	// Het hoofdscherm
	private Scene scene;
	private VBox view;
	
	// Het menu van het hoofdscherm
	private MenuNode menu;
	
	// De verschillende hoofdschermen waartussen geswitcht wordt
	private AdministratieOverzicht administratieOverzicht;
	private PlanningsOverzicht planningsOverzicht;
	private ReparatieOverzicht reparatieOverzicht;
	
	public SecretaresseControl(Stage stage){
		this.stage = stage;
		
		view = new VBox();
		
		menu = new MenuNode();
		
		menu.restartSetOnAction(new RestartControl());
		menu.exitSetOnAction(new ExitControl());
		
		administratieOverzicht = new AdministratieOverzicht();
		menu.adminstratieSetOnAction(new AdministratieOverzichtControl());
		new AdministratieSchermControl(administratieOverzicht);
		
		planningsOverzicht = new PlanningsOverzicht();
		menu.planningSetOnAction(new PlanningsOverzichtControl());
		new PlanningsSchermControl(planningsOverzicht);
		
		reparatieOverzicht = new ReparatieOverzicht();
		menu.reparatieSetOnAction(new ReparatieOverzichtControl());
		new ReparatieSchermControl(reparatieOverzicht);
		
		this.init();
	}
	
	private void init() {
		view.getChildren().add(menu);
		view.getChildren().add(planningsOverzicht);
		view.setPrefSize(600, 400);
		
		scene = new Scene(view);
		
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
			view.getChildren().set(1, administratieOverzicht);
		}
	
	}
	
	private class PlanningsOverzichtControl implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			view.getChildren().set(1, planningsOverzicht);
		}
		
	}
	
	private class ReparatieOverzichtControl implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent event) {
			view.getChildren().set(1, reparatieOverzicht);
		}
		
	}
}
