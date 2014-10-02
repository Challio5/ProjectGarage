package nl.eti1b5.model;

public class Klant extends Persoon {

	private int klantnr;
	private String rekeningNr;

	public Klant(String naam, String plaats, String adres, String telNr,
			int klantnr, String rekeningNr) {
		super(naam, plaats, adres, telNr);
		this.klantnr = klantnr;
		this.rekeningNr = rekeningNr;
	}

	public int getKlantnr() {
		return klantnr;
	}

	public void setKlantnr(int klantnr) {
		this.klantnr = klantnr;
	}

	public String getRekeningNr() {
		return rekeningNr;
	}

	public void setRekeningNr(String rekeningNr) {
		this.rekeningNr = rekeningNr;
	}
}
