package nl.eti1b5.view.secretaresse.planningsscherm;

import java.sql.Timestamp;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import nl.eti1b5.database.dao.PlanningDao;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Planning;
import nl.eti1b5.model.Reparatie;

public class PlanningsOverzicht extends VBox{

	// Tabel die de attributen van de de planning weergeeft
	private TableView<Planning> planningsTabel;
	
	// Kolommen voor de verschillende attributen van de planning
	private TableColumn<Planning, Timestamp> beginTijdKolom;
	private TableColumn<Planning, Timestamp> eindTijdKolom;
	private TableColumn<Planning, Monteur> monteurKolom;
	private TableColumn<Planning, Reparatie> reparatieKolom;
	
	// Knop voor het open van het planscherm
	private Button planKnop;
	
	public PlanningsOverzicht() {
		planningsTabel = new TableView<>();
		
		beginTijdKolom = new TableColumn<>("Begintijd");
		beginTijdKolom.setCellValueFactory(new PropertyValueFactory<>("beginTijd"));
		
		eindTijdKolom = new TableColumn<>("Eindtijd");
		eindTijdKolom.setCellValueFactory(new PropertyValueFactory<>("eindTijd"));
		
		monteurKolom = new TableColumn<>("Monteur");
		monteurKolom.setCellValueFactory(new PropertyValueFactory<>("werknemer"));
		
		reparatieKolom = new TableColumn<>("Reparatie");
		reparatieKolom.setCellValueFactory(new PropertyValueFactory<>("reparatie"));
		
		planningsTabel.getColumns().add(beginTijdKolom);
		planningsTabel.getColumns().add(eindTijdKolom);
		planningsTabel.getColumns().add(monteurKolom);
		planningsTabel.getColumns().add(reparatieKolom);
		
		planningsTabel.getItems().addAll(new PlanningDao().getPlanning());
		
		planKnop = new Button("plan");
		
		this.getChildren().add(planningsTabel);
		this.getChildren().add(planKnop);
	}
	
	public void setMonteurKolomCallback(Callback<TableColumn<Planning, Monteur>, TableCell<Planning, Monteur>> callback) {
		monteurKolom.setCellFactory(callback);
	}
	
	public void setReparatieKolomCallback(Callback<TableColumn<Planning, Reparatie>, TableCell<Planning, Reparatie>> callback) {
		reparatieKolom.setCellFactory(callback);
	}
	
	public void setPlanKnopActionListener(EventHandler<ActionEvent> e) {
		planKnop.setOnAction(e);
	}
}
