package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProdajaArtikla extends Usluga implements Robna {

	private Artikl artikl;

	@Override
	public BigDecimal prodaja(int brojArtikala) {
		super.setObavljena(true);
		super.setNaplacena(true);
		return super.getCijena().multiply(new BigDecimal(brojArtikala));
	}

	public ProdajaArtikla(Klijent klijent, String vrstaUsluge, String opisUsluge, LocalDate datumUsluge,
			BigDecimal cijenaUsluge, Boolean obavljena, Boolean naplacena, Artikl artikl) {
		super(klijent, vrstaUsluge, opisUsluge, datumUsluge, cijenaUsluge, obavljena, naplacena);
		this.artikl = artikl;
	}
	
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
