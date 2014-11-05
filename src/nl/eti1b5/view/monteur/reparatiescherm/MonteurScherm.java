package nl.eti1b5.view.monteur.reparatiescherm;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import nl.eti1b5.view.monteur.menu.MenuNode;
/**
 * Deze klasse geeft een monteurscherm weer, op dit scherm worden de reparaties voor de monteurs weergegeven.
 * @author Groep 5
 * @version 1.0
 */
public class MonteurScherm extends BorderPane{
	
	private MenuNode menuNode;
	private ReparatieNode reparatieNode;
	private CheckBox checkBox;
	
	/**
	 * De constructor van het monteurscherm, 
	 * het monteurscherm bestaat uit een menuNode, reparatieNode en een checkbox.
	 */
	public MonteurScherm(){
		
		menuNode = new MenuNode();
		reparatieNode= new ReparatieNode();
		checkBox = new CheckBox();
		checkBox.setText("Eigen Reparaties");
		
		
		this.setTop(menuNode);
		this.setCenter(checkBox);
		this.setBottom(reparatieNode);
	}
	
	/**
	 * Methode voor het toevoegen van een listener aan de checkbox
	 * @param Listener De listener
	 */
	public void addCheckBoxListener(ChangeListener<Boolean> Listener){
		checkBox.selectedProperty().addListener(Listener);
	}
	
	/**
	 * Methode voor het toevoegen van een listener aan reparatienode
	 * @param handler de EventHandler
	 */
	public void addMouseHandler(EventHandler<MouseEvent> handler){
		reparatieNode.setOnMouseClicked(handler);
	}
	
	/**
	 * Methode voor het opvragen van de reparatieNode
	 * @return de reparatieNode
	 */
	public ReparatieNode getReparatieNode(){
		return reparatieNode;
	}

}
