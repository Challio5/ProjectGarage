package nl.eti1b5.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.eti1b5.database.dao.MateriaalDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Materiaal;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.monteur.reparatiescherm.ReparatiePopup;

public class MonteurPopupControl  implements EventHandler<ActionEvent>, ChangeListener<Boolean>{
	
	private boolean checkbox;
	private Reparatie reparatie;
	private Stage newStage;
	private MonteurViewControl monteurViewControl;
	private ReparatiePopup popup;
	
	public MonteurPopupControl(Reparatie reparatie, MonteurViewControl monteurViewControl){
		this.reparatie = reparatie;
		this.monteurViewControl = monteurViewControl;
	}
	
	public void showReparatiePopup(){
		this.popup = new ReparatiePopup(reparatie);
		popup.addButtonListener(this);
		newStage = new Stage();
		String nummer = Integer.toString(reparatie.getReparatieNummer());
		newStage.setTitle("Reparatie: " + nummer);
		Scene stageScene = new Scene(popup);
		newStage.setScene(stageScene);
		newStage.show();
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1,
			Boolean arg2) {
		checkbox = !checkbox;
	}

	@Override
	public void handle(ActionEvent arg0) {
		reparatie.setReparatieStatus(popup.getReparatieStatus().isSelected());
		LocalDate beginDate = popup.getBeginDatum().getValue();
		LocalTime beginTime = LocalTime.of((int)popup.getBeginUur().getValue(), (int)popup.getBeginKwartier().getValue());
		LocalDateTime beginDateTime = LocalDateTime.of(beginDate, beginTime);
		Timestamp begin = Timestamp.valueOf(beginDateTime);
		reparatie.setBeginTijd(begin);
		
		LocalDate eindDate = popup.getEindDatum().getValue();
		LocalTime eindTime = LocalTime.of((int)popup.getEindUur().getValue(), (int)popup.getEindKwartier().getValue());
		LocalDateTime eindDateTime = LocalDateTime.of(eindDate, eindTime);
		Timestamp eind = Timestamp.valueOf(eindDateTime);
		reparatie.setEindTijd(eind);
		
		ReparatieDao reparatieDao = new ReparatieDao();
		reparatieDao.wijzigReparatie(reparatie);
		monteurViewControl.UpdateTabel();
		System.out.println(reparatie.toString());
		newStage.close();
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
