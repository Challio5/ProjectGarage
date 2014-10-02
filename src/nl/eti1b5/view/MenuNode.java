package nl.eti1b5.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenuNode extends MenuBar {
	
	private Menu file;
	private Menu keuzeMenu;
	private MenuItem reparatieMenu;
	private MenuItem administratieMenu;
	private MenuItem planningMenu;
	private MenuItem exit;
	
	public MenuNode(){
		this.getStyleClass().add("menu");
		
		keuzeMenu = new Menu("Keuzemenu's");
		file = new Menu("File");
	
		reparatieMenu = new MenuItem("Reparaties");
		administratieMenu = new MenuItem("Administratie");
		planningMenu = new MenuItem("Planning");
		exit = new MenuItem("Exit");
		
		file.getItems().addAll(exit);
		keuzeMenu.getItems().addAll(reparatieMenu, administratieMenu, planningMenu);

		this.getMenus().addAll(file, keuzeMenu);
	}
	

}
