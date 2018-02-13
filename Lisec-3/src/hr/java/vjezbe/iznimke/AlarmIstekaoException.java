package hr.java.vjezbe.iznimke;

/**
 * Javlja se u metodi provjeriAlarm ako je alarm istekao
 * 
 * @author Velimir
 *
 */
public class AlarmIstekaoException extends RuntimeException{

	private static final long serialVersionUID = 5730177570837336955L;

	/**
	 * Inicijalizira novu iznimku
	 */
	public AlarmIstekaoException(){
		super("Alarm je istekao!");
	}
	
	/**
	 * Inicijalizira novu iznimku sa danom porukom
	 * 
	 * @param poruka poruka iznimke
	 */
	public AlarmIstekaoException(String poruka){
		super(poruka);
	}
	
	/**
	 * Inicijalizira novu iznimku sa danim uzrokom
	 * 
	 * @param uzrok uzrok iznimke
	 */
	public AlarmIstekaoException(Throwable uzrok){
		super(uzrok);
	}
	
	/**
	 * Inicijlaizira novu iznimku sa danom porukom i uzrokom
	 * @param poruka poruka iznimke
	 * @param uzrok uzrok iznimke
	 */
	public AlarmIstekaoException(String poruka, Throwable uzrok){
		super(poruka, uzrok);
	}
}
