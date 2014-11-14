package nl.eti1b5.view.monteur.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
/**
 * Dit is de klasse die de menubar voor het monteursscherm weergeeft.
 * @author Groep 5
 * @version 1.0
 *
 */
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
		restart.setOnAction(e -> { 
			System.exit(0);
		});
	}
	
	public void exitSetOnAction(EventHandler<ActionEvent> event) {
		exit.setOnAction(e -> { 
			System.exit(0);
		});
	}
	
}
