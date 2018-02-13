package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * predstavlja robnu uslugu
 * @author Velimir
 *
 */
public interface Robna {

	/**
	 * Obavlja prodaju artikla
	 * @param brojArtikala podatak o broju artikala koji se prodaje
	 * @return podatak o cijeni koja je naplaæena
	 */
	public BigDecimal prodaja(int brojArtikala);
	
}
