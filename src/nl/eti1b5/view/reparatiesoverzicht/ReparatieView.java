package nl.eti1b5.view.reparatiesoverzicht;

import nl.eti1b5.view.MenuNode;
import javafx.scene.layout.BorderPane;

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
