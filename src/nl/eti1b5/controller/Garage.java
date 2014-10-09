package nl.eti1b5.controller;

import java.util.ArrayList;

import com.sun.javafx.application.LauncherImpl;

import nl.eti1b5.database.dao.AutoDao;
import nl.eti1b5.database.dao.ReparatieDao;
import nl.eti1b5.database.dao.VoorraadDao;
import nl.eti1b5.model.Auto;
import nl.eti1b5.model.Reparatie;
import nl.eti1b5.model.Voorraad;
import nl.eti1b5.view.MainLoader;
import nl.eti1b5.view.preloader.InlogPreloader;

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
		
		VoorraadDao voorraadDao = new VoorraadDao();
		ArrayList<Voorraad> voorraadLijst = voorraadDao.getVoorraad();
		for(Voorraad voorraad : voorraadLijst) {
			System.out.println(voorraad);
		}
		
		// Opent de applicatie
		LauncherImpl.launchApplication(MainLoader.class, InlogPreloader.class, args);
	}
}
