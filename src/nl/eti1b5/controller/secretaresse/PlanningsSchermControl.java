package nl.eti1b5.controller.secretaresse;

import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;
import nl.eti1b5.controller.secretaresse.popup.PlanningPopupControl;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Planning;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.secretaresse.planningsscherm.MonteurPopup;
import nl.eti1b5.view.secretaresse.planningsscherm.PlanningsOverzicht;
import nl.eti1b5.view.secretaresse.planningsscherm.ReparatiePopup;

public class PlanningsSchermControl {
	
	// View waar de klasse controller van is
	private PlanningsOverzicht view;
	
	// Stage voor weergeven van popups
	private Stage popupStage;
	
	public PlanningsSchermControl(PlanningsOverzicht view) {
		this.view = view;
		
		popupStage = new Stage();
		
		view.setMonteurKolomCallback(new MonteurKolomCallback());
		view.setReparatieKolomCallback(new ReparatieKolomCallback());
		this.addPlanKnopActionListener();
	}
	
	private void addPlanKnopActionListener() {
		view.setPlanKnopActionListener(e -> {
			new PlanningPopupControl();
		});
	}
	
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
