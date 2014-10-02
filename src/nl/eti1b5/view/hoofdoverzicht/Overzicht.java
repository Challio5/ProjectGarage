package nl.eti1b5.view.hoofdoverzicht;

import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

public class Overzicht extends GridPane {
	private TableView<String> planning;
	
	private ListView<String> monteurs;
	private ListView<String> reparaties;
	
	public Overzicht() {
		planning = new TableView<>();
		planning.getColumns().add(new TableColumn<>("Tijd"));
		planning.getColumns().add(new TableColumn<>("Reparatie"));
		planning.getColumns().add(new TableColumn<>("Monteur"));
		
		monteurs = new ListView<>();
		monteurs.getItems().add("Monteur1");
		monteurs.getItems().add("Monteur2");
		
		reparaties = new ListView<>();
		reparaties.getItems().add("Reparatie1");
		reparaties.getItems().add("Reparatie2");
		
		this.add(planning, 0, 0, 2, 2);
		this.add(monteurs, 2, 0);
		this.add(reparaties, 2, 1);		
	}
}
