package hr.java.vjezbe.iznimke;

/**
 * Javlja se u metodi provjeriEmail ako je unesen neispravan email
 * 
 * @author Velimir
 *
 */
public class NeispravanEmailException extends Exception {

	private static final long serialVersionUID = 7081323665332464146L;

	/**
	 * Inicijalizira novu iznimku
	 */
	public NeispravanEmailException() {
		super("Neispravan e-mail");
	}

	/**
	 * Inicijalizira novu iznimku sa danom porukom
	 * 
	 * @param poruka poruka iznimke
	 */
	public NeispravanEmailException(String poruka) {
		super(poruka);
	}

	/**
	 * Inicijalizira novu iznimku sa danim uzrokom
	 * 
	 * @param uzrok uzrok iznimke
	 */
	public NeispravanEmailException(Throwable uzrok) {
		super(uzrok);
	}

	/**
	 * Inicijlaizira novu iznimku sa danom porukom i uzrokom
	 * @param poruka poruka iznimke
	 * @param uzrok uzrok iznimke
	 */
	public NeispravanEmailException(String poruka, Throwable uzrok) {
		super(poruka, uzrok);
	}
}
