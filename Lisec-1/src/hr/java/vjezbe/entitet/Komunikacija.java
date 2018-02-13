package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

public class Komunikacija {

	private Klijent klijent;
	private Zaposlenik zaposlenik;
	private String vrsta;
	private String sadrzaj;
	private LocalDateTime vrijeme;
	
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
