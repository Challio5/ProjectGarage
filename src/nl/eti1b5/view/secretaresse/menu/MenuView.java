package nl.eti1b5.view.secretaresse.menu;

import javafx.scene.layout.VBox;
import nl.eti1b5.view.secretaresse.administratiescherm.AdministratieOverzicht;
import nl.eti1b5.view.secretaresse.planningsscherm.PlanningsOverzicht;
import nl.eti1b5.view.secretaresse.reparatiescherm.ReparatieOverzicht;

public class MenuView extends VBox {
	private AdministratieOverzicht administratie;
	private PlanningsOverzicht planning;
	private ReparatieOverzicht reparatie;
	
	public MenuView(MenuNode menu, AdministratieOverzicht adminstratie,
			PlanningsOverzicht planning, ReparatieOverzicht reparatie) {
		// De voorkeurgroote van het overzicht
		this.setPrefSize(600, 400);
		
		this.administratie = adminstratie;
		this.planning = planning;
		this.reparatie = reparatie;
		
		this.getChildren().add(menu);
		this.getChildren().add(planning);
	}
	
	public void showAdministratieOverzicht() {
		this.getChildren().set(1, administratie);
	}
	
	public void showPlanningOverzicht() {
		this.getChildren().set(1, planning);
	}
	
	public void showReparatieOverzicht() {
		this.getChildren().set(1, reparatie);
	}
}
