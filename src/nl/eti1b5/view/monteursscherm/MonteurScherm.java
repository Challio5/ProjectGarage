package nl.eti1b5.view.monteursscherm;

import nl.eti1b5.view.MenuNode;
import javafx.beans.value.ChangeListener;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;

public class MonteurScherm extends BorderPane{
	
	private MenuNode menuNode;
	private ReparatieNode reparatieNode;
	private CheckBox checkBox;
	
	public MonteurScherm(){
		
		menuNode = new MenuNode();
		reparatieNode= new ReparatieNode();
		checkBox = new CheckBox();
		checkBox.setText("Eigen Reparaties");
		
		
		this.setTop(menuNode);
		this.setCenter(checkBox);
		this.setBottom(reparatieNode);
	}
	
	public void addCheckBoxListener(ChangeListener<Boolean> Listener){
		checkBox.selectedProperty().addListener(Listener);
	}
	
	public ReparatieNode getReparatieNode(){
		return reparatieNode;
	}
}
