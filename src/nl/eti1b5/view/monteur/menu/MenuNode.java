package nl.eti1b5.view.monteur.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuNode extends MenuBar {
	
	private Menu file;
	private MenuItem restart;
	private MenuItem exit;
	
	public MenuNode(){
		this.getStyleClass().add("menu");
		
		file = new Menu("File");
	
		restart = new MenuItem("Restart");
		exit = new MenuItem("Exit");
		
		file.getItems().addAll(restart, exit);

		this.getMenus().addAll(file);
	}
	
	public void restartSetOnAction(EventHandler<ActionEvent> event) {
		restart.setOnAction(event);
	}
	
	public void exitSetOnAction(EventHandler<ActionEvent> event) {
		exit.setOnAction(event);
	}
	
}
