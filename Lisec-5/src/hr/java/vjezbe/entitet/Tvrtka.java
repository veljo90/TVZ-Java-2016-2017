package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Predstavlja entitet tvrtke
 * @author Velimir
 *
 */
public class Tvrtka {

	public static final int BROJ_KLIJENATA = 2;
	public static final int BROJ_ZAPOSLENIKA = 2;

	private String naziv;
	private String oib;
	private List<Klijent> klijenti;
	private List<Zaposlenik> zaposlenici;
	private List<Komunikacija> komunikacija;
	private List<Usluga> usluge;
	private List<Alarm> alarmi;

	/**
	 * Inicijalizira podatke o nazivu i OIB-u tvrtke
	 * @param naziv podatak o nazivu tvrtke
	 * @param oib podatak o OIB-u tvrtke
	 */
	public Tvrtka(String naziv, String oib) {
		super();
		this.naziv = naziv;
		this.oib = oib;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOib() {
		return oib;
	}

	public void setOib(String oib) {
		this.oib = oib;
	}

	public List<Klijent> getKlijenti() {
		return klijenti;
	}

	public void setKlijenti(List<Klijent> klijenti) {
		this.klijenti = klijenti;
	}

	public List<Zaposlenik> getZaposlenici() {
		return zaposlenici;
	}

	public void setZaposlenici(List<Zaposlenik> zaposlenici) {
		this.zaposlenici = zaposlenici;
	}

	public List<Komunikacija> getKomunikacija() {
		return komunikacija;
	}

	public void setKomunikacija(List<Komunikacija> komunikacija) {
		this.komunikacija = komunikacija;
	}

	public List<Usluga> getUsluge() {
		return usluge;
	}

	public void setUsluge(List<Usluga> usluge) {
		this.usluge = usluge;
	}

	public List<Alarm> getAlarmi() {
		return alarmi;
	}

	public void setAlarmi(List<Alarm> alarmi) {
		this.alarmi = alarmi;
	}

}
