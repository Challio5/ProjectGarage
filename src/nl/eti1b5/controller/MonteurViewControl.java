package nl.eti1b5.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.database.dao.MateriaalDao;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.model.Materiaal;
import nl.eti1b5.view.MainLoader;
import nl.eti1b5.view.monteur.reparatiescherm.MonteurScherm;
import nl.eti1b5.view.monteur.reparatiescherm.ReparatieNode;
import nl.eti1b5.view.monteur.reparatiescherm.ReparatiePopup;

public class MonteurViewControl implements ChangeListener<Boolean>, EventHandler<MouseEvent>{
	
	MainLoader app;
	MonteurScherm monteurScherm;
	ReparatieNode reparatieNode;
	ReparatieDao reparatieDao;
	MonteurDao monteurDao;
	Boolean eigenReparaties;
	MonteurPopupControl popup;
	
	public MonteurViewControl(MonteurScherm monteurScherm, MainLoader app){
		this.monteurScherm = monteurScherm;
		reparatieNode = monteurScherm.getReparatieNode();
		monteurScherm.addCheckBoxListener(this);
		monteurScherm.addMouseHandler(this);
		reparatieDao = new ReparatieDao();
		eigenReparaties = false;
		this.app = app;
		UpdateTabel();
	}
	
	public void UpdateTabel(){
		if(eigenReparaties){
			ObservableList<Reparatie> oListEigenReparatie = FXCollections.observableArrayList(reparatieDao.eigenToDoReparaties(app.getIngelogd().getWerknemerNr()));
			reparatieNode.setItems(oListEigenReparatie);
		} else {
			ObservableList<Reparatie> oListReparatie = FXCollections.observableArrayList(reparatieDao.getToDoReparaties());
			reparatieNode.setItems(oListReparatie);
		}
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1,
			Boolean arg2) {
		eigenReparaties = !eigenReparaties;
		UpdateTabel();		
	}

	@Override
	public void handle(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			popup = new MonteurPopupControl(reparatieNode.getSelectionModel().getSelectedItem(), this);
			popup.showReparatiePopup();
		}
	}		
}
	

