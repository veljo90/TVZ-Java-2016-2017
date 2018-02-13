package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Predstavlja enitet usluge
 * @author Velimir
 *
 */
public class Usluga {

	private Klijent klijent;
	private VrstaUsluge vrsta;
	private String opis;
	private LocalDate datum;
	private BigDecimal cijena;
	private Boolean obavljena = false;
	private Boolean naplacena = false;
	
	/**
	 * Inicijalizira podatak o klijentu, vrsti, opisu, datumu i cijeni usluge, te postavlja status obavljene 
	 * i naplaæene usluge
	 * @param klijent podatak o klijentu koji prima uslugu
	 * @param vrsta podatak o vrsti usluge
	 * @param opis podatak o opisu usluge
	 * @param datum podatak o datumu izvršenja usluge
	 * @param cijena podatak o cijeni usluge
	 * @param obavljena podatak da li je usluga obavljena
	 * @param naplacena podatak da li je usluga naplaæena
	 */
	public Usluga(Klijent klijent, VrstaUsluge vrsta, String opis, LocalDate datum, BigDecimal cijena, Boolean obavljena,
			Boolean naplacena) {
		super();
		this.klijent = klijent;
		this.vrsta = vrsta;
		this.opis = opis;
		this.datum = datum;
		this.cijena = cijena;
		this.obavljena = obavljena;
		this.naplacena = naplacena;
	}
	
	/**
	 * Inicijalizira podatak o klijentu, vrsti, opisu, datumu i cijeni usluge
	 * 
	 * @param klijent podatak o klijentu koji prima uslugu
	 * @param vrsta podatak o vrsti usluge
	 * @param opis podatak o opisu usluge
	 * @param datum podatak o datumu izvršenja usluge
	 * @param cijena podatak o cijeni usluge
	 */
	public Usluga(Klijent klijent, VrstaUsluge vrsta, String opis, LocalDate datum, BigDecimal cijena) {
		super();
		this.klijent = klijent;
		this.vrsta = vrsta;
		this.opis = opis;
		this.datum = datum;
		this.cijena = cijena;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	public VrstaUsluge getVrsta() {
		return vrsta;
	}

	public void setVrsta(VrstaUsluge vrsta) {
		this.vrsta = vrsta;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public void setDatum(LocalDate datum) {
		this.datum = datum;
	}

	public BigDecimal getCijena() {
		return cijena;
	}

	public void setCijena(BigDecimal cijena) {
		this.cijena = cijena;
	}

	public Boolean getObavljena() {
		return obavljena;
	}

	public void setObavljena(Boolean obavljena) {
		this.obavljena = obavljena;
	}

	public Boolean getNaplacena() {
		return naplacena;
	}

	public void setNaplacena(Boolean naplacena) {
		this.naplacena = naplacena;
	}
	
}
