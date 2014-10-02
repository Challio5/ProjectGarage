package nl.eti1b5.view.monteursoverzicht;

import nl.eti1b5.view.MenuNode;
import javafx.scene.layout.BorderPane;

public class MonteurView extends BorderPane{
	
	private MenuNode menuNode;
	private MonteurNode monteurNode;
	
	public MonteurView(){
		menuNode = new MenuNode();
		monteurNode = new MonteurNode();
		
		this.setTop(menuNode);
		this.setCenter(monteurNode);
	}
		
		

}
