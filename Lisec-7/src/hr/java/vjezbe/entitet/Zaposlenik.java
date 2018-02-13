package hr.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * Predstavlja entitet zaposlenika koji je definiran imenom, prezimenom, korisni�kim imenom i �ifrom zaposlenika
 * 
 * @author Velimir
 *
 */
public class Zaposlenik extends Osoba implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6601332963094223721L;
	
	private String korisnickoIme;
	private String sifra;
	
	/**
	 * Inicijalizira podatak o imenu, prezimenu, korisni�kom imenu i �ifri zaposlenika
	 * 
	 * @param ime podatak o imenu zaposlenika
	 * @param prezime podatak o prezimenu zaposlenika
	 * @param korisnickoIme podatak o korisni�kom imenu zaposlenika
	 * @param sifra podatak o �ifri zaposlenika
	 */
	public Zaposlenik(String ime, String prezime, String korisnickoIme, String sifra) {
		super(ime, prezime);
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
}
