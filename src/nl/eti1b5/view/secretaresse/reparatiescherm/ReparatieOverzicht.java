package nl.eti1b5.view.secretaresse.reparatiescherm;

import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class ReparatieOverzicht extends BorderPane{
	
	private ReparatieNode reparatieNode;
	private CheckBox checkBox;
	
	public ReparatieOverzicht(){
		
		reparatieNode= new ReparatieNode();
		checkBox = new CheckBox();
		checkBox.setText("Eigen Reparaties");
		
		this.setCenter(checkBox);
		this.setBottom(reparatieNode);
	}
	
	public void addCheckBoxListener(ChangeListener<Boolean> Listener){
		checkBox.selectedProperty().addListener(Listener);
	}
	
	public void addMouseHandler(EventHandler<MouseEvent> handler){
		reparatieNode.setOnMouseClicked(handler);
	}
	
	public ReparatieNode getReparatieNode(){
		return reparatieNode;
	}

}
