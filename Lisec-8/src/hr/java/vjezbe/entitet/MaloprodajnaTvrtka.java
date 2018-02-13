package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Predstavlja entitet maloprodajne tvrtke. Nasljeðuje klasu Tvrtka. 
 * @author Velimir
 *
 */
public class MaloprodajnaTvrtka extends Tvrtka {

	public static final int BROJ_ARTIKALA = 3;
	private List<Artikl> artikli;

	/**
	 * Inicijalizira podatak o nazivu, OIB-u i artiklima maloprodajne tvrtke
	 * @param naziv podatak o nazivu tvrtke
	 * @param oib podatak o OIB-u tvrtke
	 * @param artikli lista artikala kojima tvrtka posluje
	 */
	public MaloprodajnaTvrtka(String naziv, String oib, List<Artikl> artikli) {
		super(naziv, oib);
		this.artikli = artikli;
	}

	public List<Artikl> getArtikli() {
		return artikli;
	}

	public void setArtikli(List<Artikl> artikli) {
		this.artikli = artikli;
	}

}
