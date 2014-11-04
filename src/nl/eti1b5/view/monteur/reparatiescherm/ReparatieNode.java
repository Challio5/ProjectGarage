package nl.eti1b5.view.monteur.reparatiescherm;

import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import nl.eti1b5.database.dao.OmschrijvingDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.secretaresse.reparatiescherm.OmschrijvingsPopup;

// Begin en eindtijden opgeven?
public class ReparatieNode extends TableView<Reparatie>{
	
	private ReparatieDao reparatieDao;
	
	private Stage popupStage;
	
	private TableColumn<Reparatie, Integer> reparatieNummerKolom;
	private TableColumn<Reparatie, String> kentekenKolom;
	private TableColumn<Reparatie, Integer> omschrijvingKolom;
	private TableColumn<Reparatie, Boolean> reparatieStatusKolom;
	
	public ReparatieNode(){
		this.setEditable(true);
		
		reparatieDao = new ReparatieDao();
		
		popupStage = new Stage();
		
		reparatieNummerKolom = new TableColumn<Reparatie, Integer>("Reparatie nummer:");
		reparatieNummerKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("reparatieNummer"));
		// Niet editable want is een primary key in de database voor een reparatie
		
		kentekenKolom = new TableColumn<Reparatie, String>("Kenteken:");
		kentekenKolom.setCellValueFactory(new PropertyValueFactory<Reparatie,String>("kenteken"));
		kentekenKolom.setCellFactory(TextFieldTableCell.<Reparatie>forTableColumn());
		// Niet editable want monteur heeft de rechten niet

		omschrijvingKolom = new TableColumn<Reparatie, Integer>("Omschrijving");
		omschrijvingKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Integer>("naam"));
		omschrijvingKolom.setCellFactory(new OmschrijvingsNummerCallback());
		// Niet editable want is een primary key in de database voor een omschrijving
		
		reparatieStatusKolom = new TableColumn<Reparatie, Boolean>("Reparatie Status");
		reparatieStatusKolom.setCellValueFactory(new PropertyValueFactory<Reparatie, Boolean>("reparatieStatus"));
		reparatieStatusKolom.setCellFactory(new ReparatieStatusCallback());
		// Monteur kan reparatie aanpassen nadat de hele reparatie voltooid is
		
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
	
	private class ReparatieStatusCallback implements Callback<TableColumn<Reparatie, Boolean>, TableCell<Reparatie, Boolean>>{

		@Override
		public TableCell<Reparatie, Boolean> call(
				TableColumn<Reparatie, Boolean> column) {
			
			TableCell<Reparatie, Boolean> cell = new TableCell<Reparatie, Boolean>() {

				@Override
				protected void updateItem(Boolean status, boolean empty) {
					super.updateItem(status, empty);
					
					if(empty) {
						this.setText(null);
						this.setGraphic(null);
					}
					else {
						this.setText(String.valueOf(status));
						this.setGraphic(null);
					}
				}
				
			};
			
			// Flipt de boolean waarde van true naar false of omgekeerd bij een muisklik
			cell.setOnMouseClicked(e -> {
				if(!cell.isEmpty()) {
					// De nieuwe waarde na de muisklik
					boolean newValue = !cell.getItem();
					
					// Past de de statuswaarde aan in de table
					cell.startEdit();
					cell.commitEdit(newValue);
					
					// Schrijft de aangepaste reparatie weg naar de database
					Reparatie reparatie = (Reparatie) cell.getTableRow().getItem();
					reparatie.setReparatieStatus(newValue);
					reparatieDao.addReparatie(reparatie);
				}
			});
			
			return cell;
		}
	}
}
