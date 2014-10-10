package nl.eti1b5.view.reparatiesoverzicht;

import javafx.scene.layout.BorderPane;
import nl.eti1b5.view.MenuNode;

public class ReparatieView extends BorderPane{
	
	private MenuNode menuNode;
	private ReparatieNode reparatieNode;
	
	public ReparatieView(){
		menuNode = new MenuNode();
		reparatieNode = new ReparatieNode();
		
		this.setTop(menuNode);
		this.setCenter(reparatieNode);
	}
		
		

}
