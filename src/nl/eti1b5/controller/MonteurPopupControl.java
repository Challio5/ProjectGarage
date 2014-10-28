package nl.eti1b5.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.view.monteur.reparatiescherm.ReparatiePopup;

public class MonteurPopupControl  implements EventHandler<ActionEvent>, ChangeListener<Boolean>{
	
	private boolean checkbox;
	private Reparatie reparatie;
	private Stage newStage;
	private MonteurViewControl monteurViewControl;
	
	public MonteurPopupControl(Reparatie reparatie, MonteurViewControl monteurViewControl){
		this.reparatie = reparatie;
		this.monteurViewControl = monteurViewControl;
	}
	
	public void showReparatiePopup(){
		checkbox = reparatie.getReparatieStatus();
		ReparatiePopup popup = new ReparatiePopup(reparatie, this);
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
		reparatie.setReparatieStatus(checkbox);
		ReparatieDao reparatieDao = new ReparatieDao();
		reparatieDao.wijzigReparatie(reparatie);
		monteurViewControl.UpdateTabel();
		System.out.println(checkbox);
		newStage.close();
	}
}
