package nl.eti1b5.view.secretaresse.planningsscherm;

import java.sql.Timestamp;

import nl.eti1b5.database.dao.PlanningDao;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Planning;
import nl.eti1b5.model.Reparatie;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PlanningsOverzicht extends VBox{
	// PlanningsDao voor het ophalen en wegschrijven van planningen
	private PlanningDao planningDao;
	
	// Planningspopup stage voor inplannen reparatie en popups voor weergave attributen
	private Stage planningPopup;
	private Stage reparatiePopup;
	private Stage monteurPopup;
	
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
		planningDao = new PlanningDao();
		
		planningPopup = new Stage();
		reparatiePopup = new Stage();
		monteurPopup = new Stage();
		
		planningsTabel = new TableView<>();
		planningsTabel.getItems().addAll(planningDao.getPlanning());
		
		beginTijdKolom = new TableColumn<>("Begintijd");
		beginTijdKolom.setCellValueFactory(new PropertyValueFactory<>("beginTijd"));
		
		eindTijdKolom = new TableColumn<>("Eindtijd");
		eindTijdKolom.setCellValueFactory(new PropertyValueFactory<>("eindTijd"));
		
		monteurKolom = new TableColumn<>("Monteur");
		monteurKolom.setCellValueFactory(new PropertyValueFactory<>("werknemer"));
		this.addMonteurPopupActionListener();
		
		reparatieKolom = new TableColumn<>("Reparatie");
		reparatieKolom.setCellValueFactory(new PropertyValueFactory<>("reparatie"));
		this.addReparatiePopupActionListener();
		
		planningsTabel.getColumns().add(beginTijdKolom);
		planningsTabel.getColumns().add(eindTijdKolom);
		planningsTabel.getColumns().add(monteurKolom);
		planningsTabel.getColumns().add(reparatieKolom);
		
		planKnop = new Button("plan");
		this.addPlanKnopActionListener();
		
		this.getChildren().add(planningsTabel);
		this.getChildren().add(planKnop);
	}
	
	public void addPlanKnopActionListener() {
		planKnop.setOnAction(e -> {
			planningPopup.setScene(new Scene(new PlanningPopup()));
			planningPopup.show();
		});
	}
	
	public void addMonteurPopupActionListener() {
		monteurKolom.setCellFactory(new Callback<TableColumn<Planning, Monteur>, TableCell<Planning, Monteur>>() {

			@Override
			public TableCell<Planning, Monteur> call(TableColumn<Planning, Monteur> kolom) {
				TableCell<Planning, Monteur> cell = new TableCell<Planning, Monteur>() {

					@Override
					protected void updateItem(Monteur item, boolean empty) {
						super.updateItem(item, empty);
			            if (empty) {
			                this.setText(null);
			                this.setGraphic(null);
			            } else {
			            	this.setText(item.getNaam());
			            	this.setGraphic(null);
			            }
					}
					
				};
				
				cell.setOnMouseClicked(e -> {
					if(!cell.isEmpty()) {
						monteurPopup.setScene(new Scene(new MonteurPopup(cell.getItem())));
						monteurPopup.show();
					}
				});
				
				return cell;
			}
		
		});
	}
	
	public void addReparatiePopupActionListener() {
		reparatieKolom.setCellFactory(new Callback<TableColumn<Planning, Reparatie>, TableCell<Planning, Reparatie>>() {

			@Override
			public TableCell<Planning, Reparatie> call(TableColumn<Planning, Reparatie> kolom) {
				TableCell<Planning, Reparatie> cell = new TableCell<Planning, Reparatie>() {

					@Override
					protected void updateItem(Reparatie item, boolean empty) {
						super.updateItem(item, empty);
			            if (empty) {
			                this.setText(null);
			                this.setGraphic(null);
			            } else {
			            	this.setText(String.valueOf(item.getReparatieNummer()));
			            	this.setGraphic(null);
			            }
					}
					
				};
				
				cell.setOnMouseClicked(e -> {
					if(!cell.isEmpty()) {
						reparatiePopup.setScene(new Scene(new ReparatiePopup(cell.getItem())));
						reparatiePopup.show();
					}
				});
				
				return cell;
			}
		
		});
	}
}
