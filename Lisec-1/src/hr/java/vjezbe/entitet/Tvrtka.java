package hr.java.vjezbe.entitet;

public class Tvrtka {

	public static final int BROJ_KLIJENATA = 2;
	public static final int BROJ_ZAPOSLENIKA = 3;
	
	private String naziv;
	private String oib;
	private Klijent[] klijenti;
	private Zaposlenik[] zaposlenici;
	private Komunikacija[] komunikacija;
	private Usluga[] usluge;
	private Alarm[] alarmi;
	
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

	public Klijent[] getKlijenti() {
		return klijenti;
	}

	public void setKlijenti(Klijent[] klijenti) {
		this.klijenti = klijenti;
	}

	public Zaposlenik[] getZaposlenici() {
		return zaposlenici;
	}

	public void setZaposlenici(Zaposlenik[] zaposlenici) {
		this.zaposlenici = zaposlenici;
	}

	public Komunikacija[] getKomunikacija() {
		return komunikacija;
	}

	public void setKomunikacija(Komunikacija[] komunikacija) {
		this.komunikacija = komunikacija;
	}

	public Usluga[] getUsluge() {
		return usluge;
	}

	public void setUsluge(Usluga[] usluge) {
		this.usluge = usluge;
	}

	public Alarm[] getAlarmi() {
		return alarmi;
	}

	public void setAlarmi(Alarm[] alarmi) {
		this.alarmi = alarmi;
	}
	
}
