package nl.eti1b5.controller.secretaresse;

import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import nl.eti1b5.controller.secretaresse.popup.PlanningPopupControl;
import nl.eti1b5.database.dao.PlanningDao;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Planning;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.secretaresse.planningsscherm.MonteurPopup;
import nl.eti1b5.view.secretaresse.planningsscherm.PlanningsOverzicht;
import nl.eti1b5.view.secretaresse.planningsscherm.ReparatiePopup;

/**
 * Controller die het planningsscherm beheert en zijn events afhandelt
 * Geeft de view weer en geeft deze het juiste model mee
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */
public class PlanningsSchermControl {
	
	// View waar de klasse controller van is
	private PlanningsOverzicht view;
	
	// Stage voor weergeven van popups
	private Stage popupStage;
	
	/**
	 * Constructor voor het toevoegen van events aan de view
	 * @param view Planningsoverzicht wat deze controller beheert
	 */
	public PlanningsSchermControl(PlanningsOverzicht view) {
		this.view = view;
		
		popupStage = new Stage();
		
		view.setMonteurKolomCallback(new MonteurKolomCallback());
		view.setReparatieKolomCallback(new ReparatieKolomCallback());
		this.addPlanKnopActionListener();
		this.update();
	}
	
	private void update() {
		view.setVervers(e -> {
			view.getPlanningTabel().getItems().setAll(new PlanningDao().getPlanning());
		});
	}
	
	/**
	 * Voegt een actionlistener toe aan de planknop
	 * Laat het scherm zien voor toevoegen van een planning
	 */
	private void addPlanKnopActionListener() {
		view.setPlanKnopActionListener(e -> {
			new PlanningPopupControl();
		});
	}
	
	/**
	 * Cellfactory voor het genereren van tabelcellen voor de monteurkolom
	 * Voegt een mouseclickevent toe aan de cellen voor het weergegeven van een popup met de details
	 * @author ETI2vb2
	 * @since 5 nov. 2014
	 */
	private class MonteurKolomCallback implements Callback<TableColumn<Planning, Monteur>, TableCell<Planning, Monteur>> {

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
					popupStage.setScene(new Scene(new MonteurPopup(cell.getItem())));
					popupStage.show();
				}
			});
				
			return cell;
		}
	}
	
	/**
	 * Cellfactory voor het genereren van tabelcellen voor de reparatiekolom
	 * Voegt een mouseclickevent toe aan de cellen voor het weergegeven van een popup met de details
	 * @author ETI2vb2
	 * @since 5 nov. 2014
	 */
	private class ReparatieKolomCallback implements
			Callback<TableColumn<Planning, Reparatie>, TableCell<Planning, Reparatie>> {

		@Override
		public TableCell<Planning, Reparatie> call(
				TableColumn<Planning, Reparatie> kolom) {
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
				if (!cell.isEmpty()) {
					popupStage.setScene(new Scene(new ReparatiePopup(cell
							.getItem())));
					popupStage.show();
				}
			});

			return cell;
		}
	}
}
