package nl.eti1b5.controller;

import nl.eti1b5.view.MainLoader;
import nl.eti1b5.view.preloader.InlogPreloader;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MenuControl{
	
	private RestartControl restartControl;
	private ExitControl exitControl;
	
	public MenuControl(){
		restartControl = new RestartControl();
		exitControl = new ExitControl();
	}
	
	public RestartControl getRestartControl(){
		return restartControl;
	}
	
	public ExitControl getExitControl(){
		return exitControl;
	}
	
	public class RestartControl implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			System.exit(0);
		}
	}
	
	public class ExitControl implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent arg0) {
			System.exit(0);
		}
		
	}
}
