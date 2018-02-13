package hr.java.vjezbe.entitet;

import java.time.LocalDate;

public class Klijent extends Osoba{

	private String oib;
	private String brojTelefona;
	private String eMail;
	private LocalDate datumRodjenja;
	

	public Klijent(String ime, String prezime, String oib, String brojTelefona, String eMail, LocalDate datumRodjenja) {
		super(ime, prezime);
		this.oib = oib;
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
