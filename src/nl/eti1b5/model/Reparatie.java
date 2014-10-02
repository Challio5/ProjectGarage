package nl.eti1b5.model;

public class Reparatie {

	private int prijs;
	private int kosten;
	private Voorraad benodigdMateriaal;
	private int foto;

	public Reparatie(int prijs, int kosten, Voorraad benodigdMateriaal, int foto) {
		this.prijs = prijs;
		this.kosten = kosten;
		this.benodigdMateriaal = benodigdMateriaal;
		this.foto = foto;
	}

	public int getPrijs() {
		return prijs;
	}

	public void setPrijs(int prijs) {
		this.prijs = prijs;
	}

	public int getKosten() {
		return kosten;
	}

	public void setKosten(int kosten) {
		this.kosten = kosten;
	}

	public Voorraad getBenodigdMateriaal() {
		return benodigdMateriaal;
	}

	public void setBenodigdMateriaal(Voorraad benodigdMateriaal) {
		this.benodigdMateriaal = benodigdMateriaal;
	}

	public int getFoto() {
		return foto;
	}

	public void setFoto(int foto) {
		this.foto = foto;
	}
}
