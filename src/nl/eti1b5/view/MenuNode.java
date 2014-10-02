package nl.eti1b5.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MenuNode extends MenuBar {
	
	private Menu reparatieMenu;
	private Menu administratieMenu;
	private Menu planningMenu;
	
	public MenuNode(){
		this.getStyleClass().add("menu");
		
		reparatieMenu = new Menu("Reparatie");
		administratieMenu = new Menu("Admnistratie");
		planningMenu = new Menu("Planning");
		
		this.getMenus().addAll(reparatieMenu, administratieMenu, planningMenu);
	}
	

}
