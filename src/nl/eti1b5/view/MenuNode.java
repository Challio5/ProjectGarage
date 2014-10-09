package nl.eti1b5.view;

import nl.eti1b5.controller.InlogControl;
import nl.eti1b5.controller.MenuControl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuNode extends MenuBar {
	
	private Menu file;
	private Menu keuzeMenu;
	private MenuItem reparatieMenu;
	private MenuItem administratieMenu;
	private MenuItem planningMenu;
	private MenuItem restart;
	private MenuItem exit;
	private MenuControl menuControl;
	
	public MenuNode(){
		this.getStyleClass().add("menu");
		
		menuControl = new MenuControl();
		
		keuzeMenu = new Menu("Keuzemenu's");
		file = new Menu("File");
		
		reparatieMenu = new MenuItem("Reparaties");
		administratieMenu = new MenuItem("Administratie");
		planningMenu = new MenuItem("Planning");
		restart = new MenuItem("Restart");
		exit = new MenuItem("Exit");
		
		restart.setOnAction(menuControl.getRestartControl());
		exit.setOnAction(menuControl.getExitControl());
		
		file.getItems().addAll(restart, exit);
		keuzeMenu.getItems().addAll(reparatieMenu, administratieMenu, planningMenu);

		this.getMenus().addAll(file, keuzeMenu);
	}
}
