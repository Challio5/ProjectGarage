package nl.eti1b5.view.monteur.reparatiescherm;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.IntegerStringConverter;
import nl.eti1b5.model.Omschrijving;
import nl.eti1b5.model.Reparatie;


public class ReparatieNode extends TableView<Reparatie>{
	
	private TableColumn<Reparatie, Integer> reparatieNummer;
	private TableColumn<Reparatie, String> kenteken;
	private TableColumn<Reparatie, String> omschrijving;
	private TableColumn<Reparatie, Boolean> reparatieStatus;
	
	public ReparatieNode(){
		this.setEditable(true);
		
		reparatieNummer = new TableColumn<Reparatie, Integer>("Reparatie nummer:");
		reparatieNummer.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("reparatieNummer"));
		/*
		reparatieNummer.setCellFactory(TextFieldTableCell.<Reparatie, Integer>forTableColumn(new IntegerStringConverter()));
		reparatieNummer.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setReparatieNummer(e.getNewValue());
		});
		*/
		reparatieNummer.setMinWidth(150);
		
		kenteken = new TableColumn<Reparatie, String>("Kenteken:");
		kenteken.setCellValueFactory(new PropertyValueFactory<Reparatie,String>("kenteken"));
		/*
		kenteken.setCellFactory(TextFieldTableCell.<Reparatie>forTableColumn());
		kenteken.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setKenteken(e.getNewValue());
		});
		*/
		kenteken.setMinWidth(150);
		
		
		omschrijving = new TableColumn<Reparatie, String>("Omschrijving:");
		omschrijving.setCellValueFactory(new PropertyValueFactory<Reparatie, String>("naam"));
		/*
		omschrijving.setCellFactory(TextFieldTableCell.<Reparatie, Omschrijving>forTableColumn(new OmschrijvingConverter()));
		omschrijving.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setOmschrijving(e.getNewValue());
		});
		*/
		omschrijving.setMinWidth(150);
		
		reparatieStatus = new TableColumn<Reparatie, Boolean>("Reparatie Status:");
		reparatieStatus.setCellValueFactory(new PropertyValueFactory<Reparatie, Boolean>("reparatieStatus"));
		/*
		reparatieStatus.setCellFactory(TextFieldTableCell.<Reparatie, Boolean>forTableColumn(new BooleanStringConverter()));
		reparatieStatus.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setReparatieStatus(e.getNewValue());
		});
		*/
		reparatieStatus.setMinWidth(150);
		
		this.getColumns().add(reparatieNummer);
		this.getColumns().add(kenteken);
		this.getColumns().add(omschrijving);
		this.getColumns().add(reparatieStatus);
	}
}
