package nl.eti1b5.view.secretaresse.planningsscherm;

import nl.eti1b5.model.Reparatie;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ReparatiePopup extends GridPane {
	
	// De verschillende attributen met labels
	private Label reparatieNummerLabel;
	private TextField reparatieNummer;
	
	private Label kentekenLabel;
	private TextField kenteken;
	
	private Label omschrijvingLabel;
	private TextField omschrijving;
	
	private Label beginTijdLabel;
	private TextField beginTijd;
	
	private Label eindTijdLabel;
	private TextField eindTijd;
	
	private Label reparatieStatusLabel;
	private TextField reparatieStatus;
	
	private Label betaalStatusLabel;
	private TextField betaalStatus;
	
	private Label materialenLijstLabel;
	private TextField materialenLijst;
	
	public ReparatiePopup(Reparatie reparatie) {
		reparatieNummerLabel = new Label("ReparatieNummer");
		reparatieNummer = new TextField();
		reparatieNummer.setEditable(false);
		reparatieNummer.setText(String.valueOf(reparatie.getReparatieNummer()));
		
		kentekenLabel = new Label("Kenteken");
		kenteken = new TextField();
		kenteken.setEditable(false);
		kenteken.setText(reparatie.getKenteken());
		
		omschrijvingLabel = new Label("Omschrijving");
		omschrijving = new TextField();
		omschrijving.setEditable(false);
		//omschrijving.setText(reparatie.getOmschrijving().getNaam());
		
		beginTijdLabel = new Label("Begintijd");
		beginTijd = new TextField();
		beginTijd.setEditable(false);
		//beginTijd.setText(reparatie.getBeginTijd().toString());
		
		eindTijdLabel = new Label("Eindtijd");
		eindTijd = new TextField();
		eindTijd.setEditable(false);
		//eindTijd.setText(reparatie.getEindTijd().toString());
		
		reparatieStatusLabel = new Label("Reparatiestatus");
		reparatieStatus = new TextField();
		reparatieStatus.setEditable(false);
		reparatieStatus.setText(String.valueOf(reparatie.getReparatieStatus()));
		
		betaalStatusLabel = new Label("Betaalstatus");
		betaalStatus = new TextField();
		betaalStatus.setEditable(false);
		betaalStatus.setText(String.valueOf(reparatie.getBetaalStatus()));
		
		materialenLijstLabel = new Label("Materialen");
		materialenLijst = new TextField();
		materialenLijst.setEditable(false);
		materialenLijst.setText(reparatie.getMaterialenLijst().toString());
		
		this.add(reparatieNummerLabel, 0, 0);
		this.add(reparatieNummer, 1, 0);
		
		this.add(kentekenLabel, 0, 1);
		this.add(kenteken, 1, 1);
		
		this.add(omschrijvingLabel, 0, 2);
		this.add(omschrijving, 1, 2);
		
		this.add(beginTijdLabel, 0, 3);
		this.add(beginTijd, 1, 3);
		
		this.add(eindTijdLabel, 0, 4);
		this.add(eindTijd, 1, 4);
		
		this.add(reparatieStatusLabel, 0, 5);
		this.add(reparatieStatus, 1, 5);
		
		this.add(betaalStatusLabel, 0, 6);
		this.add(betaalStatus, 1, 6);
		
		this.add(materialenLijstLabel, 0, 7);
		this.add(materialenLijst, 1, 7);
	}
}
