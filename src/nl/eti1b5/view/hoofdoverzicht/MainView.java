package nl.eti1b5.view.hoofdoverzicht;

import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class MainView extends BorderPane {
	private MenuBar bar;
	private GridPane main;
	
	public MainView() {
		this.setPrefSize(600, 400);
		
		bar = new MenuBar();
		main = new Overzicht();
		
		this.setTop(bar);
		this.setCenter(main);
	}
}
