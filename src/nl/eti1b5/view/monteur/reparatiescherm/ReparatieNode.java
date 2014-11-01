package nl.eti1b5.view.monteur.reparatiescherm;

import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import nl.eti1b5.database.dao.OmschrijvingDao;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.secretaresse.reparatiescherm.OmschrijvingsPopup;


public class ReparatieNode extends TableView<Reparatie>{
	
	private Stage popupStage;
	
	private TableColumn<Reparatie, Integer> reparatieNummerKolom;
	private TableColumn<Reparatie, String> kentekenKolom;
	private TableColumn<Reparatie, Integer> omschrijvingKolom;
	private TableColumn<Reparatie, Boolean> reparatieStatusKolom;
	
	public ReparatieNode(){
		this.setEditable(true);
		
		reparatieNummerKolom = new TableColumn<Reparatie, Integer>("Reparatie nummer:");
		reparatieNummerKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("reparatieNummer"));
		/*
		reparatieNummer.setCellFactory(TextFieldTableCell.<Reparatie, Integer>forTableColumn(new IntegerStringConverter()));
		reparatieNummer.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setReparatieNummer(e.getNewValue());
		});
		*/
		reparatieNummerKolom.setMinWidth(150);
		
		kentekenKolom = new TableColumn<Reparatie, String>("Kenteken:");
		kentekenKolom.setCellValueFactory(new PropertyValueFactory<Reparatie,String>("kenteken"));
		/*
		kenteken.setCellFactory(TextFieldTableCell.<Reparatie>forTableColumn());
		kenteken.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setKenteken(e.getNewValue());
		});
		*/
		kentekenKolom.setMinWidth(150);

		omschrijvingKolom = new TableColumn<Reparatie, Integer>("Omschrijving");
		omschrijvingKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("naam"));
		omschrijvingKolom.setCellFactory(new OmschrijvingsNummerCallback());
		omschrijvingKolom.setMinWidth(150);
		
		reparatieStatusKolom = new TableColumn<Reparatie, Boolean>("Reparatie Status");
		reparatieStatusKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Boolean>("reparatieStatus"));
		/*
		reparatieStatusKolom.setCellFactory(TextFieldTableCell.<Reparatie, Boolean>forTableColumn(new BooleanStringConverter()));
		reparatieStatusKolom.setOnEditCommit(e -> {
			Reparatie reparatie = e.getRowValue();
			reparatie.setReparatieStatus(e.getNewValue());
		});
		*/
		reparatieStatusKolom.setMinWidth(150);
		
		this.getColumns().add(reparatieNummerKolom);
		this.getColumns().add(kentekenKolom);
		this.getColumns().add(omschrijvingKolom);
		this.getColumns().add(reparatieStatusKolom);
	}
	
	private class OmschrijvingsNummerCallback implements Callback<TableColumn<Reparatie, Integer>, TableCell<Reparatie, Integer>>{

		@Override
		public TableCell<Reparatie, Integer> call(
				TableColumn<Reparatie, Integer> column) {
			
			TableCell<Reparatie, Integer> cell = new TableCell<Reparatie, Integer>() {

				@Override
				protected void updateItem(Integer omschrijvingsNummer, boolean empty) {
					super.updateItem(omschrijvingsNummer, empty);
					
					if(empty) {
						this.setText(null);
						this.setGraphic(null);
					}
					else {
						this.setText(String.valueOf(omschrijvingsNummer));
						this.setGraphic(null);
					}
				}
				
			};
			
			cell.setOnMouseClicked(e -> {
				if(!cell.isEmpty()) {
					popupStage.setScene(new Scene(new OmschrijvingsPopup(new OmschrijvingDao().getOmschrijving(cell.getItem()))));
					popupStage.setTitle("Reparatie");
					popupStage.show();
				}
			});
			
			return cell;
		}
	}
}
