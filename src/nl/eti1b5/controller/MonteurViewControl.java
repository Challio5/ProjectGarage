package nl.eti1b5.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.main.MainLoader;
import nl.eti1b5.view.monteur.reparatiescherm.MonteurScherm;
import nl.eti1b5.view.monteur.reparatiescherm.ReparatieNode;
/**
 * De klasse MonteurViewControl,
 * Deze klasse dient als controller voor het monteursscherm,
 * Hij zorgt er voor de dat de tabel geupdate wordt en de popup geopend wordt.
 * @author Groep 3
 * @version 1.0
 *
 */
public class MonteurViewControl implements ChangeListener<Boolean>, EventHandler<MouseEvent>{
	
	MainLoader app;
	MonteurScherm monteurScherm;
	ReparatieNode reparatieNode;
	ReparatieDao reparatieDao;
	MonteurDao monteurDao;
	Boolean eigenReparaties;
	
	/**
	 * De constructor, De constructor geeft meteen de listeners mee aan het reparatiescherm.
	 * @param monteurScherm Scherm van de monteur
	 * @param app de applicatie
	 */
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
	
	/**
	 * Methode voor het updaten van de tabel
	 * Hij controleert eerst of de eigenreparaties staat aangevinkt ja of nee.
	 */
	public void UpdateTabel(){
		if(eigenReparaties){
			ObservableList<Reparatie> oListEigenReparatie = FXCollections.observableArrayList(reparatieDao.eigenToDoReparaties(app.getIngelogd().getWerknemerNr()));
			reparatieNode.setItems(oListEigenReparatie);
		} else {
			ObservableList<Reparatie> oListReparatie = FXCollections.observableArrayList(reparatieDao.getToDoReparaties());
			reparatieNode.setItems(oListReparatie);
		}
	}
	
	/**
	 * kijkt of de eigenReparaties is aangevinkt
	 */
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1,
			Boolean arg2) {
		eigenReparaties = !eigenReparaties;
		UpdateTabel();		
	}
	
	/**
	 * Kijkt of er wordt dubbelgeklikt op een reparatie om vervolgens de popup te openen.
	 */
	@Override
	public void handle(MouseEvent arg0) {
		if(arg0.getClickCount() == 2){
			MonteurPopupControl popup = new MonteurPopupControl(reparatieNode.getSelectionModel().getSelectedItem(), this);
			popup.showReparatiePopup();
			arg0.consume();
			reparatieNode.getSelectionModel().clearSelection();
		}
	}		
}
	

