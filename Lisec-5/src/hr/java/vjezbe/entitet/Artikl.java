package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet artikla sa nazivom i kategorijom artikla
 * 
 * @author Velimir
 *
 */
public class Artikl {

	private String naziv;
	private KategorijaArtikla kategorija;
	
	/**
	 * Inicijalizira podatak o nazivu i kategoriji artikla
	 * @param naziv podatak o nazivu artikla
	 * @param kategorija podatak o kategoriji artikla
	 */
	public Artikl(String naziv, KategorijaArtikla kategorija) {
		super();
		this.naziv = naziv;
		this.kategorija = kategorija;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public KategorijaArtikla getKategorija() {
		return kategorija;
	}

	public void setKategorija(KategorijaArtikla kategorija) {
		this.kategorija = kategorija;
	}
	
}
