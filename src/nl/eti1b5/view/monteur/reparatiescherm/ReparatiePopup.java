package nl.eti1b5.view.monteur.reparatiescherm;

import java.time.LocalDate;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import nl.eti1b5.database.dao.MateriaalDao;
import nl.eti1b5.database.dao.OmschrijvingDao;
import nl.eti1b5.model.Materiaal;
import nl.eti1b5.model.Reparatie;



public class ReparatiePopup extends BorderPane{
	
	private VBox labels;
	private VBox textFields;
	private CheckBox reparatieStatus;
	private Button enter;
	private TableView<Materiaal> materialen;
	private TableColumn<Materiaal, String> materiaalNaam;
	private TableColumn aantal;
	private DatePicker beginDatum;
	private ComboBox<Integer> beginUur;
	private ComboBox<Integer> beginKwartier;
	private DatePicker eindDatum;
	private ComboBox<Integer> eindUur;
	private ComboBox<Integer> eindKwartier;
	
	public ReparatiePopup(Reparatie reparatie){

		labels = new VBox();
		labels.setPadding(new Insets(4));
		labels.setSpacing(8);
		
		Label repLabel = new Label("Reparatienummer: ");
		Label kentekenLabel = new Label("Kenteken: ");
		Label omschrijvingLabel = new Label("Omschrijving: ");
		Label beginDatumLabel = new Label("Begin datum: ");
		Label beginUurLabel = new Label("Begin uur: ");
		Label beginKwartierLabel = new Label("Begin Kwartier");
		Label eindDatumLabel = new Label("Eind datum: ");
		Label eindUurLabel = new Label("Eind uur: ");
		Label eindKwartierLabel = new Label("Eind Kwartier");
		Label reparatieStatusLabel = new Label("Reparatiestatus: ");
		Label materiaalLabel = new Label("Materialen: ");
		
		labels.getChildren().addAll(repLabel, kentekenLabel, omschrijvingLabel, beginDatumLabel, beginUurLabel, beginKwartierLabel, eindDatumLabel, eindUurLabel, eindKwartierLabel, reparatieStatusLabel, materiaalLabel);
		
		textFields = new VBox();
		textFields.setPadding(new Insets(4));
		TextField repNr = new TextField(Integer.toString(reparatie.getReparatieNummer()));
		repNr.setEditable(false);
		TextField kenteken= new TextField(reparatie.getKenteken());
		kenteken.setEditable(false);
		TextField omschrijving = new TextField(new OmschrijvingDao().getOmschrijving(reparatie.getOmschrijvingsNummer()).getNaam());
		omschrijving.setEditable(false);
		
		beginDatum =  new DatePicker();
		beginDatum.setValue(LocalDate.now());
		beginUur = new ComboBox<Integer>();
		beginUur.getItems().addAll(9, 10, 11, 12, 13, 14, 15, 16, 17);
		beginKwartier = new ComboBox<Integer>();
		beginKwartier.getItems().addAll(00, 15, 30, 45);
		eindDatum =  new DatePicker();
		eindUur = new ComboBox<Integer>();
		eindUur.getItems().addAll(9, 10, 11, 12, 13, 14, 15, 16, 17);
		eindKwartier = new ComboBox<Integer>();
		eindKwartier.getItems().addAll(00, 15, 30, 45);
		reparatieStatus = new CheckBox();
		
		textFields.getChildren().addAll(repNr, kenteken, omschrijving, beginDatum, beginUur, beginKwartier, eindDatum, eindUur, eindKwartier, reparatieStatus);
		
		materialen = new TableView<Materiaal>();
		materialen.setEditable(true);
		
		materiaalNaam = new TableColumn<Materiaal, String>("Materiaal");
		materiaalNaam.setCellValueFactory(new PropertyValueFactory<Materiaal,String>("naam"));
		materiaalNaam.setMinWidth(150);
		
		aantal = new TableColumn("Aantal");
		aantal.setCellFactory(TextFieldTableCell.forTableColumn());
		aantal.setOnEditCommit(
			    new EventHandler<CellEditEvent<Materiaal, String>>() {
			        @Override
			        public void handle(CellEditEvent<Materiaal, String> t) {
			            ((Materiaal) t.getTableView().getItems().get(
			                t.getTablePosition().getRow())
			                ).convertAantal(t.getNewValue());
			        }
			    }
			);
		aantal.setMinWidth(150);
		MateriaalDao materiaalDao = new MateriaalDao();
		ObservableList<Materiaal> oListMateriaal= FXCollections.observableArrayList(materiaalDao.getMateriaal());
		materialen.setItems(oListMateriaal);
		materialen.getColumns().addAll(materiaalNaam, aantal);
		
		enter = new Button("Enter");
		
		this.setTop(enter);
		this.setLeft(labels);
		this.setCenter(textFields);
		this.setBottom(materialen);
	}
	
	public DatePicker getBeginDatum(){
		return beginDatum;
	}
	
	public ComboBox getBeginUur(){
		return beginUur;
	}
	
	public ComboBox getBeginKwartier(){
		return beginKwartier;
	}
	
	public DatePicker getEindDatum(){
		return eindDatum;
	}
	
	public ComboBox getEindUur(){
		return beginUur;
	}
	
	public ComboBox getEindKwartier(){
		return beginKwartier;
	}
	
	public CheckBox getReparatieStatus(){
		return reparatieStatus;
	}
	
	public void addButtonListener(EventHandler<ActionEvent> listener){
		enter.setOnAction(listener);
	}
	
	public TableView<Materiaal> getMaterialen(){
		return materialen;
	}
}
