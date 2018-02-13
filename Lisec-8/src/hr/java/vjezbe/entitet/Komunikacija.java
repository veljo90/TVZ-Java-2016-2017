package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Predstavlja entitet komunikacije koja se održala izmeðu klijenta i zaposlenika, zajedno sa vrstom, 
 * sadržajem i vremenom održavanja komunikacije
 * 
 * @author Velimir
 *
 */
public class Komunikacija implements Serializable{

	private static final long serialVersionUID = 3802895767674089366L;
	
	private Klijent klijent;
	private Zaposlenik zaposlenik;
	private VrstaKomunikacije vrsta;
	private String sadrzaj;
	private LocalDateTime vrijeme;
	
	/**
	 * Inicijalizira podatak o klijentu, zaposleniku, vrsti i sadržaju komunikacije
	 * @param klijent podatak o klijentu koji je sudjelovao u komunikaciji
	 * @param zaposlenik podatak o zaposleniku koji je sudjelovao u komunikaciji
	 * @param vrsta podatak o vrsti komunikacije
	 * @param sadrzaj podatak o sadžaju komunikacije
	 */
	public Komunikacija(Klijent klijent, Zaposlenik zaposlenik, VrstaKomunikacije vrsta, String sadrzaj) {
		super();
		this.klijent = klijent;
		this.zaposlenik = zaposlenik;
		this.vrsta = vrsta;
		this.sadrzaj = sadrzaj;
		this.vrijeme = LocalDateTime.now();
	}
	
	/**
	 * Inicijalizira podatak o klijentu, zaposleniku i vrstikomunikacije
	 * @param klijent podatak o klijentu koji je sudjelovao u komunikaciji
	 * @param zaposlenik podatak o zaposleniku koji je sudjelovao u komunikaciji
	 * @param vrsta podatak o vrsti komunikacije
	 */
	public Komunikacija(Klijent klijent, Zaposlenik zaposlenik, VrstaKomunikacije vrsta) {
		super();
		this.klijent = klijent;
		this.zaposlenik = zaposlenik;
		this.vrsta = vrsta;
		this.vrijeme = LocalDateTime.now();
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	public Zaposlenik getZaposlenik() {
		return zaposlenik;
	}

	public void setZaposlenik(Zaposlenik zaposlenik) {
		this.zaposlenik = zaposlenik;
	}

	public VrstaKomunikacije getVrsta() {
		return vrsta;
	}

	public void setVrsta(VrstaKomunikacije vrsta) {
		this.vrsta = vrsta;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public LocalDateTime getVrijeme() {
		return vrijeme;
	}
}
