package nl.eti1b5.view.secretaresse.monteursoverzicht;

import javafx.scene.layout.BorderPane;
import nl.eti1b5.view.MenuNode;

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
