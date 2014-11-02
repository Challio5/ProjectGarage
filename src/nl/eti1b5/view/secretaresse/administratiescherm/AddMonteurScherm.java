package nl.eti1b5.view.secretaresse.administratiescherm;

import nl.eti1b5.controller.MonteurToevoegControl;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddMonteurScherm extends VBox{
	
	private Stage newStage;
	
	private HBox naamBox;
	private Label naamLabel;
	private TextField naamField;
	private HBox adresBox;
	private Label adresLabel;
	private TextField adresField;
	private HBox postcodeBox;
	private Label postcodeLabel;
	private TextField postcodeField;
	private HBox woonplaatsBox;
	private Label woonplaatsLabel;
	private TextField woonplaatsField;
	private HBox telnrBox;
	private Label telnrLabel;
	private TextField telnrField;
	private HBox wwBox;
	private Label wwLabel;
	private PasswordField wwField;
	private HBox specialiteitBox;
	private Label specialiteitLabel;
	private TextField specialiteitField;
	private ComboBox<String> specialiteitCombo;
	private Button submit;
		
	public AddMonteurScherm(){
		naamBox = new HBox();
		naamLabel = new Label("Naam: ");
		naamLabel.minWidth(200);
		naamField = new TextField();
		naamBox.getChildren().addAll(naamLabel, naamField);
		
		adresBox = new HBox();
		adresLabel = new Label("Adres: ");
		adresLabel.minWidth(200);
		adresField = new TextField();
		adresBox.getChildren().addAll(adresLabel, adresField);
		
		postcodeBox = new HBox();
		postcodeLabel = new Label("Postcode: ");
		postcodeLabel.minWidth(200);
		postcodeField = new TextField();
		postcodeBox.getChildren().addAll(postcodeLabel, postcodeField);
		
		woonplaatsBox = new HBox();
		woonplaatsLabel = new Label("Woonplaats: ");
		woonplaatsLabel.minWidth(200);
		woonplaatsField = new TextField();
		woonplaatsBox.getChildren().addAll(woonplaatsLabel, woonplaatsField);
		
		telnrBox = new HBox();
		telnrLabel = new Label("Telefoonnummer: ");
		telnrLabel.minWidth(200);
		telnrField = new TextField();
		telnrBox.getChildren().addAll(telnrLabel, telnrField);
		
		wwBox = new HBox();
		wwLabel = new Label("Wachtwoord: ");
		wwLabel.minWidth(200);
		wwField = new PasswordField();
		wwBox.getChildren().addAll(wwLabel, wwField);
		
		specialiteitBox = new HBox();
		specialiteitLabel = new Label("Specialiteit: ");
		specialiteitLabel.minWidth(200);
		specialiteitCombo = new ComboBox<String>();
		specialiteitCombo.getItems().addAll("Overig", "Motor", "Carroserie", "Banden");
		specialiteitCombo.getSelectionModel().selectFirst();
		specialiteitCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals("Overig")){
				specialiteitField.setEditable(true);
			} else {
				specialiteitField.setEditable(false);
			}
		});
		specialiteitField = new TextField();
		specialiteitBox.getChildren().addAll(specialiteitLabel, specialiteitCombo, specialiteitField);
		
		submit = new Button("Toevoegen");
		submit.setOnAction(new MonteurToevoegControl(this));
				
		this.getChildren().addAll(naamBox, adresBox, postcodeBox, woonplaatsBox, telnrBox, wwBox, specialiteitBox, submit);
		
		init();
	}
	
	public void init(){
		newStage = new Stage();
		newStage.setTitle("Klant toevoegen");
		Scene stageScene = new Scene(this);
		newStage.setScene(stageScene);
		newStage.show();
	}
	
	public void setSpecialiteitListener() {
		specialiteitCombo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue.equals("Overig")){
				specialiteitField.setEditable(false);
			} else {
				specialiteitField.setEditable(false);
			}
		});
	}

	public String getNaam() {
		return naamField.getText();
	}
	
	public String getAdres(){
		return adresField.getText();
	}
	
	public String getPostcode(){
		return postcodeField.getText();
	}
	
	public String getWoonplaats(){
		return woonplaatsField.getText();
	}
	
	public String getTelefoonNummer(){
		return telnrField.getText();
	}
	
	public String getWachtwoord(){
		return wwField.getText();
	}
	
	public String getSpecialiteit(){
		String result = "";
		if(specialiteitCombo.getValue().equals("Overig")){
			result = specialiteitField.getText();
		} else {
			result = specialiteitCombo.getValue();
		}
		return result;
	}

	public void close() {
		newStage.close();		
	}

}
