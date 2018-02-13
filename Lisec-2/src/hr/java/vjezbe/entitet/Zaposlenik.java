package hr.java.vjezbe.entitet;

public class Zaposlenik extends Osoba{

	private String korisnickoIme;
	private String sifra;
	
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
