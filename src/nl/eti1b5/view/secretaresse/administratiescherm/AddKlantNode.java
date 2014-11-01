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

public class AddKlantNode extends BorderPane{

	private Button add;
	
	private TableView<Auto> autos;
	private TableColumn<Auto, String> autoMerk;
	private TableColumn<Auto, String> autoModel;
	private TableColumn<Auto,String> autoKenteken;
	
	private HBox addAuto;
	private TextField autoKentekenField;
	private TextField autoMerkField;
	private TextField autoModelField;
	private TextField autoVerzekeringsNummerField;
	
	private ArrayList<Auto> autoLijst;

	private ObservableList<Auto> oListAuto;
	
	public AddKlantNode(){
		autos = new TableView<Auto>();
		
		autoKenteken = new TableColumn<Auto, String>("Kenteken");
		autoKenteken.setCellValueFactory(new PropertyValueFactory<Auto, String>("kenteken"));
		autoKenteken.setMinWidth(150);
		autoMerk = new TableColumn<Auto, String>("Merk");
		autoMerk.setCellValueFactory(new PropertyValueFactory<Auto, String>("merk"));
		autoMerk.setMinWidth(150);
		autoModel = new TableColumn<Auto, String>("Model");
		autoModel.setCellValueFactory(new PropertyValueFactory<Auto, String>("model"));
		autoModel.setMinWidth(150);
		
		autos.getColumns().addAll(autoKenteken, autoMerk, autoModel);
		
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
	
	public ArrayList<Auto> getAutos(){
		return autoLijst;
	}
	
	
	
	

}
