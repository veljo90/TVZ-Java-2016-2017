package hr.java.vjezbe.iznimke;

/**
 * Javlja se u metodi provjeriZaposlenika ako je unesen dupli zaposlenik
 * 
 * @author Velimir
 *
 */
public class DupliZaposlenikException extends RuntimeException {

	private static final long serialVersionUID = -4347415189825839477L;

	/**
	 * Inicijalizira novu iznimku
	 */
	public DupliZaposlenikException() {
		super("Neispravan e-mail");
	}

	/**
	 * Inicijalizira novu iznimku sa danom porukom
	 * 
	 * @param poruka poruka iznimke
	 */
	public DupliZaposlenikException(String poruka) {
		super(poruka);
	}

	/**
	 * Inicijalizira novu iznimku sa danim uzrokom
	 * 
	 * @param uzrok uzrok iznimke
	 */
	public DupliZaposlenikException(Throwable uzrok) {
		super(uzrok);
	}

	/**
	 * Inicijlaizira novu iznimku sa danom porukom i uzrokom
	 * @param poruka poruka iznimke
	 * @param uzrok uzrok iznimke
	 */
	public DupliZaposlenikException(String poruka, Throwable uzrok) {
		super(poruka, uzrok);
	}
}
