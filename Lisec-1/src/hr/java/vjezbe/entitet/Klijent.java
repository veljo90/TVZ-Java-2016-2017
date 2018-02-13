package hr.java.vjezbe.entitet;

import java.time.LocalDate;

public class Klijent {

	private String oib;
	private String prezime;
	private String ime;
	private String brojTelefona;
	private String eMail;
	private LocalDate datumRodjenja;
	
	public Klijent(String oib, String prezime, String ime, String brojTelefona, String eMail, LocalDate datumRodjenja) {
		super();
		this.oib = oib;
		this.prezime = prezime;
		this.ime = ime;
		this.brojTelefona = brojTelefona;
		this.eMail = eMail;
		this.datumRodjenja = datumRodjenja;
	}

	public String getOib() {
		return oib;
	}

	public void setOib(String oib) {
		this.oib = oib;
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

	public String getBrojTelefona() {
		return brojTelefona;
	}

	public void setBrojTelefona(String brojTelefona) {
		this.brojTelefona = brojTelefona;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
	
}
