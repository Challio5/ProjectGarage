package nl.eti1b5.model;

public class Auto {

	private String merk;
	private int schade;
	private String kenteken;

	public Auto(String merk, int schade, String kenteken) {
		this.merk = merk;
		this.schade = schade;
		this.kenteken = kenteken;
	}

	public String getMerk() {
		return merk;
	}

	public void setMerk(String merk) {
		this.merk = merk;
	}

	public int getSchade() {
		return schade;
	}

	public void setSchade(int schade) {
		this.schade = schade;
	}

	public String getKenteken() {
		return kenteken;
	}

	public void setKenteken(String kenteken) {
		this.kenteken = kenteken;
	}
}
