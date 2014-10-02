package nl.eti1b5.view.reparatiesoverzicht;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ReparatieNode extends TableView{
	
	TableColumn reparatieNummer;
	TableColumn soort;
	TableColumn kenteken;
	TableColumn omschrijving;
	TableColumn onderdelen;
	
	public ReparatieNode(){
		reparatieNummer = new TableColumn("Repartie nummer:");
		reparatieNummer.setMinWidth(150);
		soort = new TableColumn("Soort:");
		soort.setMinWidth(150);
		kenteken = new TableColumn("Kenteken:");
		kenteken.setMinWidth(150);
		omschrijving = new TableColumn("Omschrijving");
		omschrijving.setMinWidth(150);
		onderdelen = new TableColumn("Onderdelen");
		onderdelen.setMinWidth(150);
		
		getColumns().addAll(reparatieNummer, soort, kenteken, omschrijving, onderdelen);
		

	}
}
