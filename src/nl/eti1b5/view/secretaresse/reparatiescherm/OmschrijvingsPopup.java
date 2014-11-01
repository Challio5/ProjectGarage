package nl.eti1b5.view.secretaresse.reparatiescherm;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import nl.eti1b5.model.Omschrijving;

public class OmschrijvingsPopup extends GridPane{
	private Label naamLabel;
	private TextField naam;
	
	private Label duurLabel;
	private TextField duur;
	
	public OmschrijvingsPopup(Omschrijving omschrijving) {
		this.setPadding(new Insets(10));
		
		this.setHgap(10);
		this.setVgap(10);
		
		naamLabel = new Label("Naam");
		naam = new TextField();
		naam.setEditable(false);
		naam.setText(omschrijving.getNaam());
		
		duurLabel = new Label("Duur");
		duur = new TextField();
		duur.setEditable(false);
		duur.setText(omschrijving.getDuur().toString());
		
		this.add(naamLabel, 0, 0);
		this.add(naam, 1, 0);
		
		this.add(duurLabel, 0, 1);
		this.add(duur, 1, 1);
	}
}
