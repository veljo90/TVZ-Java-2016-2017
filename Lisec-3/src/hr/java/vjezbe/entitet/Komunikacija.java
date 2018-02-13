package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

/**
 * Predstavlja entitet komunikacije koja se održala izmeðu klijenta i zaposlenika, zajedno sa vrstom, 
 * sadržajem i vremenom održavanja komunikacije
 * 
 * @author Velimir
 *
 */
public class Komunikacija {

	private Klijent klijent;
	private Zaposlenik zaposlenik;
	private String vrsta;
	private String sadrzaj;
	private LocalDateTime vrijeme;
	
	/**
	 * Inicijalizira podatak o klijentu, zaposleniku, vrsti, sadžaju i vremenu komunikacije
	 * @param klijent podatak o klijentu koji je sudjelovao u komunikaciji
	 * @param zaposlenik podatak o zaposleniku koji je sudjelovao u komunikaciji
	 * @param vrsta podatak o vrsti komunikacije
	 * @param sadrzaj podatak o sadžaju komunikacije
	 * @param vrijeme podatak o vremenu održavanja komunikacije
	 */
	public Komunikacija(Klijent klijent, Zaposlenik zaposlenik, String vrsta, String sadrzaj, LocalDateTime vrijeme) {
		super();
		this.klijent = klijent;
		this.zaposlenik = zaposlenik;
		this.vrsta = vrsta;
		this.sadrzaj = sadrzaj;
		this.vrijeme = vrijeme;
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

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
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

	public void setVrijeme(LocalDateTime vrijeme) {
		this.vrijeme = vrijeme;
	}
	
}
