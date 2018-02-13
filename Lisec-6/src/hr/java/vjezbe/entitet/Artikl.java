package hr.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * Predstavlja entitet artikla sa nazivom i kategorijom artikla
 * 
 * @author Velimir
 *
 */
public class Artikl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7267440308513123716L;
	
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
