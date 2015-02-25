package nl.eti1b5.view.secretaresse.administratiescherm;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import nl.eti1b5.model.Auto;

/**
 * Deze klasse geeft een Node in het popup scherm van de addklant weer. 
 * Hier kunnen de auto's worden toegevoegd aan de klant.
 * @author Groep 5
 * @version 1.0
 */
public class AddKlantNode extends BorderPane{

	private Button add;
	
	private TableView<Auto> autos;
	private TableColumn<Auto, String> autoMerk;
	private TableColumn<Auto, String> autoModel;
	private TableColumn<Auto, String> autoKenteken;
	
	private HBox addAuto;
	private TextField autoKentekenField;
	private TextField autoMerkField;
	private TextField autoModelField;
	private TextField autoVerzekeringsNummerField;
	
	private ArrayList<Auto> autoLijst;

	private ObservableList<Auto> oListAuto;
	
	/**
	 * Dit is de constructor van deze klasse, de klasse bestaat uit een tableView van een aantal auto's
	 * Een knop om een auto toe te voegen, een veld om het kenteken in the vullen, een veld om het merk in te vullen
	 * een veld om het model in te vullen en een veld om the verzekeringsnummer in te vullen.
	 */
	public AddKlantNode(){
		autos = new TableView<Auto>();
		
		autoKenteken = new TableColumn<>("Kenteken");
		autoKenteken.setCellValueFactory(new PropertyValueFactory<Auto, String>("kenteken"));
		autoKenteken.setMinWidth(150);
		
		autoMerk = new TableColumn<>("Merk");
		autoMerk.setCellValueFactory(new PropertyValueFactory<Auto, String>("merk"));
		autoMerk.setMinWidth(150);
		
		autoModel = new TableColumn<>("Model");
		autoModel.setCellValueFactory(new PropertyValueFactory<Auto, String>("model"));
		autoModel.setMinWidth(150);
		
		autos.getColumns().add(autoKenteken); 
		autos.getColumns().add(autoMerk); 
		autos.getColumns().add(autoModel);
		
		addAuto = new HBox();
		autoKentekenField = new TextField("Kenteken");
		autoKentekenField.setEditable(true);
		autoMerkField = new TextField("Merk");
		autoMerkField.setEditable(true);
		autoModelField = new TextField("Model");
		autoModelField.setEditable(true);
		autoVerzekeringsNummerField = new TextField("Verzekeringsnummer");
		autoVerzekeringsNummerField.setEditable(true);
		
		add = new Button("Voeg Toe");
		add.setOnAction(e -> {
			Auto auto = new Auto();
			auto.setKenteken(autoKentekenField.getText());
			auto.setMerk(autoMerkField.getText());
			auto.setModel(autoModelField.getText());
			auto.setVerzekeringsnummer(Integer.parseInt(autoVerzekeringsNummerField.getText()));
			autoLijst.add(auto);
			autos.setItems(FXCollections.observableArrayList(autoLijst));
		});
		
		autoLijst = new ArrayList<Auto>();
		oListAuto = FXCollections.observableArrayList(autoLijst);
		autos.setItems(oListAuto);
				
		addAuto.getChildren().addAll(autoKentekenField, autoMerkField, autoModelField, autoVerzekeringsNummerField, add);
		
		this.setTop(autos);
		this.setBottom(addAuto);
	}
	
	/**
	 * Getter voor het opvragen van de toegevoegde auto's
	 * @return de lijst met auto's 
	 */
	public ArrayList<Auto> getAutos(){
		return autoLijst;
	}
	
	
	
	

}
