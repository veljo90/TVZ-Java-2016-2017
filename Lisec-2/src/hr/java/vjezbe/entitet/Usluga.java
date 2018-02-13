package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Usluga {

	private Klijent klijent;
	private String vrsta;
	private String opis;
	private LocalDate datum;
	private BigDecimal cijena;
	private Boolean obavljena = false;
	private Boolean naplacena = false;
	
	public Usluga(Klijent klijent, String vrsta, String opis, LocalDate datum, BigDecimal cijena, Boolean obavljena,
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
	
	public Usluga(Klijent klijent, String vrsta, String opis, LocalDate datum, BigDecimal cijena) {
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

	public String getVrsta() {
		return vrsta;
	}

	public void setVrsta(String vrsta) {
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
