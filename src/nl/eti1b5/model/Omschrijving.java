package nl.eti1b5.model;

public class Omschrijving {
	private String naam;
	private double tijd;
	
	public Omschrijving(String naam, double tijd) {
		this.naam = naam;
		this.tijd = tijd;
	}

	// Getters
	public String getNaam() {
		return naam;
	}

	public double getTijd() {
		return tijd;
	}

	// Setters
	public void setNaam(String naam) {
		this.naam = naam;
	}

	public void setTijd(double tijd) {
		this.tijd = tijd;
	}

	@Override
	public String toString() {
		return naam;
	}
}
