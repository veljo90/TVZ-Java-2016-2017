package hr.java.vjezbe.entitet;

/**
 * Predstavlja abstraktni entitet osobe sa imenom i prezimenom
 * @author Velimir
 *
 */
public abstract class Osoba {

	private String ime;
	private String prezime;

	/**
	 * Inicijalizira podatak o imenu i prezimenu osobe
	 * @param ime podatak o imenu osobe
	 * @param prezime podatak o prezimenu osobe
	 */
	public Osoba(String ime, String prezime) {
		super();
		this.ime = ime;
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
}
