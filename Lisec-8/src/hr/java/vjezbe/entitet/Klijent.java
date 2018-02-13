package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Predstavlja entitet klijenta koji je definiran imenom, prezimenom, OIB-om, brojem telefona, e-mailom 
 * i datumom roðenja
 * 
 * @author Velimir
 */
public class Klijent extends Osoba implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -790150017738710707L;
	
	private String oib;
	private String brojTelefona;
	private String eMail;
	private LocalDate datumRodjenja;
	

	/**
	 * Inicijalizira podatak o imenu, prezimenu, OIB-u, broju telefona, e-mailu i datumu roðenja klijenta
	 * @param ime podatak o imenu klijenta
	 * @param prezime podatak o prezimenu klijenta
	 * @param oib podatak o OIB-u klijenta
	 * @param brojTelefona podatak o broju telefona klijenta
	 * @param eMail podatak o e-mailu klijenta
	 * @param datumRodjenja podatak o datumu roðenja klijenta
	 */
	public Klijent(String ime, String prezime, String oib, String brojTelefona, String eMail, LocalDate datumRodjenja) {
		super(ime, prezime);
		this.oib = oib;
		this.brojTelefona = brojTelefona;
		this.eMail = eMail;
		this.datumRodjenja = datumRodjenja;
	}
	
	@Override
	public String toString(){
		return super.getPrezime() + " " + super.getIme();
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

	public String getEmail() {
		return eMail;
	}

	public void setEmail(String eMail) {
		this.eMail = eMail;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	
	
}
