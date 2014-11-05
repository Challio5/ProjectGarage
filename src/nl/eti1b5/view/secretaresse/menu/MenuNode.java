package nl.eti1b5.view.secretaresse.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * Menubar met de onderdelen van het menu
 * Bevat twee menus voor de algemene bediening en voor het oproepen van schermen
 * 
 * @author ETI2vb3
 * @since 5 nov. 2014
 */

public class MenuNode extends MenuBar {
	
	// 2 menus voor de algemene bediening en het openen van schermen
	private Menu file;
	private Menu keuzeMenu;
	
	// File menu items
	private MenuItem restart;
	private MenuItem exit;
	
	// Keuze menu items
	private MenuItem reparatieItem;
	private MenuItem administratieItem;
	private MenuItem planningItem;
	
	/**
	 * Constructor voor het initialiseren van de menuitems
	 * Voegt de items toe aan de menus en menubar
	 */
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
	
	/**
	 * Voegt een actionevent toe aan het restart menuitem
	 * @param event Muisklik
	 */
	public void restartSetOnAction(EventHandler<ActionEvent> event) {
		restart.setOnAction(event);
	}
	
	/**
	 * Voegt een actionevent toe aan het exit menuitem
	 * @param event Muisklik
	 */
	public void exitSetOnAction(EventHandler<ActionEvent> event) {
		exit.setOnAction(event);
	}
	
	/**
	 * Voegt een actionevent toe aan het reparatiescherm menuitem
	 * @param event Muisklik
	 */
	public void reparatieSetOnAction(EventHandler<ActionEvent> event) {
		reparatieItem.setOnAction(event);
	}
	
	/**
	 * Voegt een actionevent toe aan het administratiescherm menuitem
	 * @param event Muisklik
	 */
	public void adminstratieSetOnAction(EventHandler<ActionEvent> event) {
		administratieItem.setOnAction(event);
	}
	
	/**
	 * Voegt een actionevent toe aan het planningsscherm menuitem
	 * @param event Muisklik
	 */
	public void planningSetOnAction(EventHandler<ActionEvent> event) {
		planningItem.setOnAction(event);
	}
}
