package nl.eti1b5.model;

import java.util.Date;

public class Factuur {

	private int factuurNr;
	private int prijs;
	private Date datum;

	public Factuur(int factuurNr, int prijs, Date datum) {
		this.factuurNr = factuurNr;
		this.prijs = prijs;
		this.datum = datum;
	}

	public int getFactuurNr() {
		return factuurNr;
	}

	public void setFactuurNr(int factuurNr) {
		this.factuurNr = factuurNr;
	}

	public int getPrijs() {
		return prijs;
	}

	public void setPrijs(int prijs) {
		this.prijs = prijs;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}
}
