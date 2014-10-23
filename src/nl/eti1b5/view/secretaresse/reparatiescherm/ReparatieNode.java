package nl.eti1b5.view.secretaresse.reparatiescherm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import nl.eti1b5.database.dao.AutoDao;
import nl.eti1b5.model.Auto;


public class ReparatieNode extends TableView{
	
	TableColumn reparatieNummer;
	TableColumn soort;
	TableColumn kenteken;
	TableColumn omschrijving;
	TableColumn onderdelen;
	
	public ReparatieNode(){
		reparatieNummer = new TableColumn("Repartie nummer:");
		reparatieNummer.setMinWidth(150);
		soort = new TableColumn("Soort:");
		soort.setMinWidth(150);
		kenteken = new TableColumn("Kenteken:");
		kenteken.setMinWidth(150);
		kenteken.setCellValueFactory(new PropertyValueFactory<Auto,String>("kenteken"));
		omschrijving = new TableColumn("Omschrijving");
		omschrijving.setMinWidth(150);
		onderdelen = new TableColumn("Onderdelen");
		onderdelen.setMinWidth(150);
		
		AutoDao auto = new AutoDao();
		ObservableList<Auto> oListAuto = FXCollections.observableArrayList(auto.getAutos());
		this.setItems(oListAuto);
		getColumns().addAll(reparatieNummer, soort, kenteken, omschrijving, onderdelen);
		

	}
}
