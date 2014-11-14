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

/**
 * Gui klasse voor het weergeven van de tabel met planningen en knop
 * Met de knop kan een planning aan de tabel en database toegevoeg worden
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */

public class PlanningsOverzicht extends VBox{

	// Knop
	private Button ververs;
	
	// Tabel die de attributen van de de planning weergeeft
	private TableView<Planning> planningsTabel;
	
	// Kolommen voor de verschillende attributen van de planning
	private TableColumn<Planning, Timestamp> beginTijdKolom;
	private TableColumn<Planning, Timestamp> eindTijdKolom;
	private TableColumn<Planning, Monteur> monteurKolom;
	private TableColumn<Planning, Reparatie> reparatieKolom;
	
	// Knop voor het open van het planscherm
	private Button planKnop;
	
	/**
	 * Constructor voor het initialiseren van planningstabel met kolommen
	 * Maakt voor elk attribuut van planning een kolom aan en voegt deze toe
	 * Maakt ook een planningsknop aan voor het weergeven van een popup voor het toevoegen van een planning
	 */
	public PlanningsOverzicht() {
		ververs = new Button("ververs");
		
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
		
		this.getChildren().add(ververs);
		this.getChildren().add(planningsTabel);
		this.getChildren().add(planKnop);
	}
	
	public void setVervers(EventHandler<ActionEvent> e) {
		ververs.setOnAction(e);
	}
	
	public TableView<Planning> getPlanningTabel() {
		return planningsTabel;
	}
	
	/**
	 * Setter voor het toevoegen van een cellfactory aan de monteurskolom
	 * @param callback Factory die de tablecell produceert
	 */
	public void setMonteurKolomCallback(Callback<TableColumn<Planning, Monteur>, TableCell<Planning, Monteur>> callback) {
		monteurKolom.setCellFactory(callback);
	}
	
	/**
	 * Setter voor het toevoegen van een cellfactory aan de reparatiekolom
	 * @param callback Factory die de tablecell produceert
	 */
	public void setReparatieKolomCallback(Callback<TableColumn<Planning, Reparatie>, TableCell<Planning, Reparatie>> callback) {
		reparatieKolom.setCellFactory(callback);
	}
	
	/**
	 * Voegt een actionlistener toe aan de planningsknop
	 * @param e Actionevent die een druk op de knop afhandeld
	 */
	public void setPlanKnopActionListener(EventHandler<ActionEvent> e) {
		planKnop.setOnAction(e);
	}
}
