package hr.java.vjezbe.entitet;

public class Zaposlenik {

	private String korisnickoIme;
	private String prezime;
	private String ime;
	private String sifra;
	
	public Zaposlenik(String korisnickoIme, String prezime, String ime, String sifra) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.prezime = prezime;
		this.ime = ime;
		this.sifra = sifra;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
}
