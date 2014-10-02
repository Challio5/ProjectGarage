package nl.eti1b5.view.monteursoverzicht;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class MonteurNode extends TableView{
	
	TableColumn monteurNummer;
	TableColumn monteurNaam;
	TableColumn gegevens;
	TableColumn reparaties;
	
	public MonteurNode(){
		monteurNummer = new TableColumn("Werknemer nummer:");
		monteurNummer.setMinWidth(150);
		monteurNaam = new TableColumn("Naam:");
		monteurNaam.setMinWidth(150);
		gegevens = new TableColumn("Gegevens:");
		gegevens.setMinWidth(150);
		reparaties = new TableColumn("Reparaties");
		reparaties.setMinWidth(150);
		
		getColumns().addAll(monteurNummer, monteurNaam, gegevens, reparaties);
		

	}
}
