package nl.eti1b5.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Secretaresse;
import nl.eti1b5.view.MainLoader;
import nl.eti1b5.view.monteur.reparatiescherm.MonteurScherm;
import nl.eti1b5.view.preloader.InlogPreloader;
import nl.eti1b5.view.preloader.InlogView;
import nl.eti1b5.view.secretaresse.planningsscherm.AddKlantScherm;
import nl.eti1b5.view.secretaresse.reparatiescherm.ReparatieView;


public class InlogControl {
	
	private MainLoader app;
	private InlogPreloader inlogPre;
	private InlogView inlogView;
	private int counter;
	private MonteurDao monteurDao;
	private Secretaresse secretaresse;
	
	public InlogControl(InlogPreloader inlogPre, MainLoader app){
		this.inlogPre = inlogPre;
		this.app = app;
		inlogView = inlogPre.getInlogView();
		inlogView.setSubmitListener(new SubmitHandler());
		inlogView.setEnterListener(new EnterHandler());
		counter = 1;
		monteurDao = new MonteurDao();
		secretaresse = new Secretaresse("Mercedes", "Rektum", "SjaakAfhaak 1", "3451DD", "09005544",
				1, "Welkom", "secretaresse", 999, 1);
	}
	
	
	/**
	 * methode handle
	 * gaat na of de counter aftelt.
	 * wanneer er geen job is ingevuld komt hiervan een melding.
	 * wanneer er geen of foutieve inlog gegevens worden toegevoegd komt hiervan een melding.
	 * wanneer de inlog gegevens correct zijn wordt het desbetreffende scherm geopent passend bij de job.
	 **/
	
	public class SubmitHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent arg0) {
			ObservableList<Monteur> oListMonteurs = FXCollections.observableArrayList(monteurDao.getMonteurs());
			Boolean ingevuld = true;
			Boolean gevonden = false;
			String job = inlogView.getJob().getValue();
			String naam = inlogView.getInlogNode().getName().getText();
			String wachtwoord = inlogView.getInlogNode().getPassword().getText();
			if(job == null){
				showJobFout();
				ingevuld = false;
			} 
			else if(counter < 3){
				if (naam.equals("")){
					showPassNaam();
					ingevuld = false;
				} 
				else if (wachtwoord.equals("")){
					showPassNaam();
					ingevuld = false;
				} 
				else if(ingevuld){
					// App is null op het moment deze nog niet uitgeladen is
					if(inlogView.getJob().getValue().equals("Monteur")){
						for(Monteur monteur : oListMonteurs ){
							if(naam.equals(monteur.getNaam()) && wachtwoord.equals(monteur.getWachtwoord())){
								if(app != null) {
									// Hide de preloader en showt het hoofdprogramma
									inlogPre.getStage().hide();
									showMonteur();
								}
								app.setIngelogd(monteur);
								gevonden = true;
							}
						}
					}
					else if(inlogView.getJob().getValue().equals("Secretaresse")){
						if(naam.equals(secretaresse.getNaam()) && wachtwoord.equals(secretaresse.getWachtwoord())){
							if(app != null) {
								// Hide de preloader en showt het hoofdprogramma
								inlogPre.getStage().hide();
								showPlanning();
							}
							//app.setIngelogd(monteur);
							gevonden = true;
						}
					}
				}
				if(!gevonden && ingevuld){
					showPassNaam();
				}
			}
			else {
				inlogView.getInlogNode().setLock();
				inlogView.getFoutMelding().setText("U heeft een incorrecte \nusername/password combinatie ingevuld!\nu heeft nog " + (3-counter) + " kansen!");
			}
		}
	}
	
	public class EnterHandler implements EventHandler<KeyEvent>{
		@Override
		public void handle(KeyEvent e) {
			if(e.getCode() == KeyCode.ENTER) {
				ObservableList<Monteur> oListMonteurs = FXCollections.observableArrayList(monteurDao.getMonteurs());
				Boolean ingevuld = true;
				Boolean gevonden = false;
				String job = inlogView.getJob().getValue();
				String naam = inlogView.getInlogNode().getName().getText();
				String wachtwoord = inlogView.getInlogNode().getPassword().getText();
				if(job == null){
					showJobFout();
					ingevuld = false;
				} 
				else if(counter < 3){
					if (naam.equals("")){
						showPassNaam();
						ingevuld = false;
					} 
					else if (wachtwoord.equals("")){
						showPassNaam();
						ingevuld = false;
					} 
					else if(ingevuld){
						// App is null op het moment deze nog niet uitgeladen is
						if(inlogView.getJob().getValue().equals("Monteur")){
							for(Monteur monteur : oListMonteurs ){
								if(naam.equals(monteur.getNaam()) && wachtwoord.equals(monteur.getWachtwoord())){
									if(app != null) {
										// Hide de preloader en showt het hoofdprogramma
										inlogPre.getStage().hide();
										showMonteur();
									}
									app.setIngelogd(monteur);
									gevonden = true;
								}
							}
						}
						else if(inlogView.getJob().getValue().equals("Secretaresse")){
							if(naam.equals(secretaresse.getNaam()) && wachtwoord.equals(secretaresse.getWachtwoord())){
								if(app != null) {
									// Hide de preloader en showt het hoofdprogramma
									inlogPre.getStage().hide();
									showPlanning();
								}
								//app.setIngelogd(monteur);
								gevonden = true;
							}
						}
					}
					if(!gevonden && ingevuld){
						showPassNaam();
					}
				}
				else {
					inlogView.getInlogNode().setLock();
					inlogView.getFoutMelding().setText("U heeft een incorrecte \nusername/password combinatie ingevuld!\nu heeft nog " + (3-counter) + " kansen!");
				}
			}
		}
	}
	
	//foutmelding wanneer er geen job gekozen is
	public void showJobFout() {
		inlogView.getFoutMelding().setText("U heeft geen job gekozen");
	}
	
	//foutmelding wanneer verkeerde inlog gegevens worden ingevuld
	public void showPassNaam(){
		inlogView.getFoutMelding().setText("U heeft een incorrecte \nusername/password combinatie ingevuld!\nu heeft nog " + (3-counter) + " kansen!");
		//counter voor het aantal kansen van inloggen
		counter++;
	}
	
	//monteur scherm wanneer als monteur correct ingelogd
	public void showMonteur() {
		MonteurScherm monteurScherm = new MonteurScherm();
		MonteurViewControl view = new MonteurViewControl(monteurScherm, app);
		Scene scene = new Scene(monteurScherm);
		
		// Stylesheet
		String stylesheet = this.getClass().getResource("/menu.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		app.getStage().setScene(scene);
		app.getStage().setTitle("Monteur");
		app.getStage().show();
	}
	
	//reparatie scherm wanneer correct ingelogd
	public void showReparatie(){

		Scene scene = new Scene(new ReparatieView());
		
		// Stylesheet
		String stylesheet = this.getClass().getResource("/menu.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		app.getStage().setScene(scene);
		app.getStage().setTitle("Reparatie");
		app.getStage().show();
	}
	
	//plan scherm wanneer correct ingelogd
	public void showPlanning(){

		Scene scene = new Scene(new AddKlantScherm());
		
		// Stylesheet
		String stylesheet = this.getClass().getResource("/menu.css").toString();
		scene.getStylesheets().add(stylesheet);
		
		app.getStage().setScene(scene);
		app.getStage().setTitle("Planning");
		app.getStage().show();
	}
}
