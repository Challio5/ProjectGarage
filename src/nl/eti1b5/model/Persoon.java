package nl.eti1b5.model;

public class Persoon {

	private String naam;
	private String plaats;
	private String adres;
	private String telNr;

	public Persoon(String naam, String plaats, String adres, String telNr) {
		this.naam = naam;
		this.plaats = plaats;
		this.adres = adres;
		this.telNr = telNr;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getPlaats() {
		return plaats;
	}

	public void setPlaats(String plaats) {
		this.plaats = plaats;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getTelNr() {
		return telNr;
	}

	public void setTelNr(String telNr) {
		this.telNr = telNr;
	}
}
