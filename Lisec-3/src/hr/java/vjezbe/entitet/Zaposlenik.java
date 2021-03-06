package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet zaposlenika koji je definiran imenom, prezimenom, korisničkim imenom i šifrom zaposlenika
 * 
 * @author Velimir
 *
 */
public class Zaposlenik extends Osoba{

	private String korisnickoIme;
	private String sifra;
	
	/**
	 * Inicijalizira podatak o imenu, prezimenu, korisničkom imenu i šifri zaposlenika
	 * 
	 * @param ime podatak o imenu zaposlenika
	 * @param prezime podatak o prezimenu zaposlenika
	 * @param korisnickoIme podatak o korisničkom imenu zaposlenika
	 * @param sifra podatak o šifri zaposlenika
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
