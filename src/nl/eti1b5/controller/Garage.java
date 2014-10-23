package nl.eti1b5.controller;

import java.util.ArrayList;

import nl.eti1b5.database.dao.AutoDao;
import nl.eti1b5.database.dao.MonteurDao;
import nl.eti1b5.database.dao.PlanningDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.database.dao.MateriaalDao;
import nl.eti1b5.model.Auto;
import nl.eti1b5.model.Monteur;
import nl.eti1b5.model.Planning;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.model.Materiaal;
import nl.eti1b5.view.MainLoader;
import nl.eti1b5.view.preloader.InlogPreloader;

import com.sun.javafx.application.LauncherImpl;

/**
 * Controller voor interactie tussen het model en de views
 * Vanuit deze klasse wordt het programma gestart
 * 
 * @author Rob Bonhof
 * @since 24 sep. 2014
 * @version 1.0
 */

public class Garage {
	/**
	 * Main methode wordt aangeroepen door de JRE
	 * Start de JavaFX applicatie samen met de preloader
	 * @param args String array met commandline argumenten
	 */
	public static void main(String[] args) {
		/* Database Test */
		ReparatieDao dao = new ReparatieDao();
		ArrayList<Reparatie> lijst = dao.getReparaties();
		for(Reparatie reparatie : lijst) {
			System.out.println(reparatie);
		}
		
		AutoDao autoDao = new AutoDao();
		ArrayList<Auto> autoLijst = autoDao.getAutos();
		for(Auto auto : autoLijst) {
			System.out.println(auto);
		}
		
		MateriaalDao materiaalDao = new MateriaalDao();
		ArrayList<Materiaal> materiaalLijst = materiaalDao.getMateriaal();
		for(Materiaal materiaal : materiaalLijst) {
			System.out.println(materiaal);
		}
		
		MonteurDao monteurDao = new MonteurDao();
		ArrayList<Monteur> monteurLijst = monteurDao.getMonteurs();
		for(Monteur monteur : monteurLijst) {
			System.out.println(monteur);
		}
		
		PlanningDao planningDao = new PlanningDao();
		ArrayList<Planning> planningsLijst = planningDao.getPlanning();
		for(Planning planning : planningsLijst) {
			System.out.println(planning);
		}
		
		// Opent de applicatie
		LauncherImpl.launchApplication(MainLoader.class, InlogPreloader.class, args);
		
	}
}
