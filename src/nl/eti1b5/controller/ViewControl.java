package nl.eti1b5.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.MainLoader;
import nl.eti1b5.view.monteursscherm.MonteurScherm;

public class ViewControl implements ChangeListener<Boolean>{
	
	MainLoader app;
	MonteurScherm monteurScherm;
	ReparatieDao reparatieDao;
	MonteurDao monteurDao;
	Boolean eigenReparaties;
	
	public ViewControl(MonteurScherm monteurScherm, MainLoader app){
		this.monteurScherm = monteurScherm;
		monteurScherm.addCheckBoxListener(this);
		reparatieDao = new ReparatieDao();
		eigenReparaties = false;
		this.app = app;
		UpdateTabel();
	}
	
	public void UpdateTabel(){
		if(eigenReparaties){
			ObservableList<Reparatie> oListEigenReparatie = FXCollections.observableArrayList(reparatieDao.eigenReparaties(app.getIngelogd().getWerknemerNr()));
			monteurScherm.getReparatieNode().setItems(oListEigenReparatie);
		} else {
			ObservableList<Reparatie> oListReparatie = FXCollections.observableArrayList(reparatieDao.getReparaties());
			monteurScherm.getReparatieNode().setItems(oListReparatie);
		}
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1,
			Boolean arg2) {
		eigenReparaties = !eigenReparaties;
		UpdateTabel();		
	}	
	
}

