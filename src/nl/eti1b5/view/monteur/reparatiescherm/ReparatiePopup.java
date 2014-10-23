package nl.eti1b5.view.monteur.reparatiescherm;

import java.util.ArrayList;

import nl.eti1b5.model.Reparatie;
import nl.eti1b5.model.Voorraad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ReparatiePopup extends BorderPane{
	
	private VBox labels;
	private VBox textFields;
	private TableView<Voorraad> materialen;
	private TableColumn<Voorraad, String> materiaalNaam;
	private TableColumn<Voorraad, String> aantal;
	
	public ReparatiePopup(String r, String k, String o, ObservableList<Voorraad> lijst){
		labels = new VBox();
		labels.setPadding(new Insets(4));
		labels.setSpacing(8);
		
		Label repLabel = new Label("Reparatienummer: ");
		Label kentekenLabel = new Label("Kenteken: ");
		Label omschrijvingLabel = new Label("Omschrijving: ");
		Label beginTijdLabel = new Label("Begintijd: ");
		Label eindTijdLabel = new Label("Eindtijd: ");
		Label reparatieStatusLabel = new Label("Reparatiestatus: ");
		Label materiaalLabel = new Label("Materialen: ");
		
		labels.getChildren().addAll(repLabel, kentekenLabel, omschrijvingLabel, beginTijdLabel, eindTijdLabel, reparatieStatusLabel, materiaalLabel);
		
		textFields = new VBox();
		textFields.setPadding(new Insets(4));
		TextField repNr = new TextField(r);
		repNr.setEditable(false);
		TextField kenteken= new TextField(k);
		kenteken.setEditable(false);
		TextField omschrijving = new TextField(o);
		omschrijving.setEditable(false);
		TextField beginTijd = new TextField("jjjj-mm-dd uu-mm-ss");
		TextField eindTijd = new TextField("jjjj-mm-dd uu-mm-ss");
		CheckBox reparatieStatus = new CheckBox();
		
		textFields.getChildren().addAll(repNr, kenteken, omschrijving, beginTijd, eindTijd, reparatieStatus);
		
		materialen = new TableView<Voorraad>();
		materialen.setEditable(true);
		materiaalNaam = new TableColumn<Voorraad, String>("Materiaal");
		materiaalNaam.setCellValueFactory(new PropertyValueFactory<Voorraad,String>("naam"));
		materiaalNaam.setMinWidth(150);
		
		aantal = new TableColumn("Aantal");
		aantal.setMinWidth(150);
		aantal.setEditable(true);
	
		materialen.setItems(lijst);
		materialen.getColumns().addAll(materiaalNaam, aantal);
		
		this.setLeft(labels);
		this.setCenter(textFields);
		this.setBottom(materialen);
	}

}
