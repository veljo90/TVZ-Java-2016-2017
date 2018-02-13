package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Predstavlja entitet prodaje artikla koji je definiran klijentom, artiklom, opisom, 
 * datumom, cijenom i statusom usluge
 * 
 * @author Velimir
 *
 */
public class ProdajaArtikla extends Usluga implements Robna {

	private Artikl artikl;

	@Override
	public BigDecimal prodaja(int brojArtikala) {
		super.setObavljena(true);
		super.setNaplacena(true);
		return super.getCijena().multiply(new BigDecimal(brojArtikala));
	}

	/**
	 * Inicijalizira podatak o klijentu, vrsti, opisu, datumu i cijeni usluge, 
	 * statusu usluge (obavljena i naplaæena) i artiklu
	 * @param klijent podatak o klijentu koji kupuje artikl
	 * @param vrstaUsluge podatak o vrsti usluge
	 * @param opisUsluge podatak o opisu usluge
	 * @param datumUsluge podatak o datumu usluge
	 * @param cijenaUsluge podatak o cijeni usluge
	 * @param obavljena podatak da li je usluga obavljena
	 * @param naplacena podatak da li je usluga naplaæena
	 * @param artikl podatak o artiklu
	 */
	public ProdajaArtikla(Klijent klijent, String vrstaUsluge, String opisUsluge, LocalDate datumUsluge,
			BigDecimal cijenaUsluge, Boolean obavljena, Boolean naplacena, Artikl artikl) {
		super(klijent, vrstaUsluge, opisUsluge, datumUsluge, cijenaUsluge, obavljena, naplacena);
		this.artikl = artikl;
	}
	
	/**
	 * Inicijalizira podatak o klijentu, vrsti, opisu, datumu i cijeni usluge, te artiklu
	 * @param klijent podatak o klijentu koji kupuje artikl
	 * @param vrstaUsluge podatak o vrsti usluge
	 * @param opisUsluge podatak o opisu usluge
	 * @param datumUsluge podatak o datumu usluge
	 * @param cijenaUsluge podatak o cijeni usluge
	 * @param artikl podatak o artiklu
	 */
	public ProdajaArtikla(Klijent klijent, String vrstaUsluge, String opisUsluge, LocalDate datumUsluge,
			BigDecimal cijenaUsluge, Artikl artikl) {
		super(klijent, vrstaUsluge, opisUsluge, datumUsluge, cijenaUsluge);
		this.artikl = artikl;
	}

	public Artikl getArtikl() {
		return artikl;
	}

	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}

}
