package nl.eti1b5.model;

public class Voorraad {

	private int naam;
	private int materiaalnr;
	private double prijs;
	private int aantal;
	private Reparatie reparatie;

	public Voorraad(int naam, int materiaalnr, double prijs, int aantal,
			Reparatie reparatie) {
		this.naam = naam;
		this.materiaalnr = materiaalnr;
		this.prijs = prijs;
		this.aantal = aantal;
		this.reparatie = reparatie;
	}

	public int getNaam() {
		return naam;
	}

	public void setNaam(int naam) {
		this.naam = naam;
	}

	public int getMateriaalnr() {
		return materiaalnr;
	}

	public void setMateriaalnr(int materiaalnr) {
		this.materiaalnr = materiaalnr;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public Reparatie getReparatie() {
		return reparatie;
	}

	public void setReparatie(Reparatie reparatie) {
		this.reparatie = reparatie;
	}
}
