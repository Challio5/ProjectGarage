package nl.eti1b5.view.secretaresse.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuNode extends MenuBar {
	
	private Menu file;
	private Menu keuzeMenu;
	private MenuItem reparatieItem;
	private MenuItem administratieItem;
	private MenuItem planningItem;
	private MenuItem restart;
	private MenuItem exit;
	
	public MenuNode(){
		this.getStyleClass().add("menu");
		
		keuzeMenu = new Menu("Keuzemenu's");
		file = new Menu("File");
		
		reparatieItem = new MenuItem("Reparaties");
		administratieItem = new MenuItem("Administratie");
		planningItem = new MenuItem("Planning");
		restart = new MenuItem("Restart");
		exit = new MenuItem("Exit");
		
		file.getItems().addAll(restart, exit);
		keuzeMenu.getItems().addAll(reparatieItem, administratieItem, planningItem);

		this.getMenus().addAll(file, keuzeMenu);
	}
	
	public void restartSetOnAction(EventHandler<ActionEvent> event) {
		restart.setOnAction(event);
	}
	
	public void exitSetOnAction(EventHandler<ActionEvent> event) {
		exit.setOnAction(event);
	}
	
	public void reparatieSetOnAction(EventHandler<ActionEvent> event) {
		reparatieItem.setOnAction(event);
	}
	
	public void adminstratieSetOnAction(EventHandler<ActionEvent> event) {
		administratieItem.setOnAction(event);
	}
	
	public void planningSetOnAction(EventHandler<ActionEvent> event) {
		planningItem.setOnAction(event);
	}
}
