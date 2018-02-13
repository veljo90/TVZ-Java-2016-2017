package hr.java.vjezbe.entitet;

import java.util.List;

public class MaloprodajnaTvrtka extends Tvrtka {

	public static final int BROJ_ARTIKALA = 3;
	private List<Artikl> artikli;

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
