package nl.eti1b5.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.eti1b5.database.dao.MateriaalDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.monteur.reparatiescherm.ReparatiePopup;
/**
 * De klasse MonterPopupControl dient als controller voor het popup scherm wanneer een monteur dubbelklikt op een reparatie.
 * De klasse zorgt voor een goeie afhandeling, van de popup.
 * @author Groep 3
 * @version 1.0
 *
 */
public class MonteurPopupControl  implements EventHandler<ActionEvent>, ChangeListener<Boolean>{
	
	private boolean checkbox;
	private Reparatie reparatie;
	private Stage newStage;
	private MonteurViewControl monteurViewControl;
	private ReparatiePopup popup;
	
	/**
	 * De constructor, de reparatie en de monteurview worden toegevoegd aan de variabelen.
	 * @param reparatie de reparatie die aangepast moet worden
	 * @param monteurViewControl de monteurViewControl 
	 */
	public MonteurPopupControl(Reparatie reparatie, MonteurViewControl monteurViewControl){
		this.reparatie = reparatie;
		this.monteurViewControl = monteurViewControl;
	}
	
	/**
	 * De methode die de reparatiePopup weergeeft.
	 */
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
	
	/**
	 * de methode die de verandering van de checkbox bijhoudt.
	 */
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1,
			Boolean arg2) {
		checkbox = !checkbox;
	}
	
	/**
	 * De methode die wanneer de knop wordt ingedrukt, de reparatie wegschrijft naar de database.
	 */
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
		
		MateriaalDao materiaalDao = new MateriaalDao();
		materiaalDao.wijzigMateriaal(reparatie);
	
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
