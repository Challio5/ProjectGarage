package nl.eti1b5.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nl.eti1b5.database.dao.AutoDao;
import nl.eti1b5.model.Auto;
import nl.eti1b5.view.MainLoader;

public class ViewControl {
	
	MainLoader app;
	ObservableList<Auto> oListAuto;
	AutoDao autoDao;
	
	public ViewControl(MainLoader app){
		this.app = app;
	}
	
	public void updateTable(){
		autoDao = new AutoDao();
		ObservableList<Auto> oListAuto = FXCollections.observableArrayList(autoDao.getAutos());
	}

}
