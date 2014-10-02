package nl.eti1b5.model;

public class Gebruiker extends Persoon {

	private int rechten;
	private String wachtwoord;
	private String functie;
	private int werknemerNr;

	public Gebruiker(String naam, String plaats, String adres, String telNr,
			int rechten, String wachtwoord, String functie, int werknemerNr) {
		super(naam, plaats, adres, telNr);
		this.rechten = rechten;
		this.wachtwoord = wachtwoord;
		this.functie = functie;
		this.werknemerNr = werknemerNr;
	}

	public int getRechten() {
		return rechten;
	}

	public void setRechten(int rechten) {
		this.rechten = rechten;
	}

	public String getWachtwoord() {
		return wachtwoord;
	}

	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}

	public String getFunctie() {
		return functie;
	}

	public void setFunctie(String functie) {
		this.functie = functie;
	}

	public int getWerknemerNr() {
		return werknemerNr;
	}

	public void setWerknemerNr(int werknemerNr) {
		this.werknemerNr = werknemerNr;
	}
}
