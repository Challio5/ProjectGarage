package nl.eti1b5.controller;

import nl.eti1b5.view.main.MainLoader;
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
		// Opent de applicatie
		LauncherImpl.launchApplication(MainLoader.class, InlogPreloader.class, args);
	}
}
