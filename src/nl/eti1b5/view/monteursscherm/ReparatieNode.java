package nl.eti1b5.view.monteursscherm;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nl.eti1b5.model.Reparatie;


public class ReparatieNode extends TableView<Reparatie>{
	
	private TableColumn<Reparatie, Integer> reparatieNummer;
	private TableColumn<Reparatie, String> kenteken;
	private TableColumn<Reparatie, String> omschrijving;
	private TableColumn<Reparatie, Boolean> reparatieStatus;
	
	public ReparatieNode(){
		reparatieNummer = new TableColumn<Reparatie, Integer>("Reparatie nummer:");
		reparatieNummer.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("reparatieNummer"));
		reparatieNummer.setMinWidth(150);
		
		kenteken = new TableColumn<Reparatie, String>("Kenteken:");
		kenteken.setCellValueFactory(new PropertyValueFactory<Reparatie,String>("kenteken"));
		kenteken.setMinWidth(150);
		
		
		omschrijving = new TableColumn<Reparatie, String>("Omschrijving:");
		omschrijving.setCellValueFactory(new PropertyValueFactory<Reparatie,String>("omschrijving"));
		omschrijving.setMinWidth(150);
		
		reparatieStatus = new TableColumn<Reparatie, Boolean>("Reparatie Status:");
		reparatieStatus.setCellValueFactory(new PropertyValueFactory<Reparatie, Boolean>("reparatieStatus"));
		reparatieStatus.setMinWidth(150);
		
		getColumns().addAll(reparatieNummer, kenteken, omschrijving, reparatieStatus);
	}
}
