package hr.java.vjezbe.iznimke;

/**
 * Javlja se u metodi provjeriAlarm ako je alarm pred istekom
 * 
 * @author Velimir
 *
 */
public class AlarmPredIstekomException extends Exception{

	private static final long serialVersionUID = 1835628531001502867L;

	/**
	 * Inicijalizira novu iznimku
	 */
	public AlarmPredIstekomException(){
		super("Alarm pred istekom!");
	}
	
	/**
	 * Inicijalizira novu iznimku sa danom porukom
	 * 
	 * @param poruka poruka iznimke
	 */
	public AlarmPredIstekomException(String poruka){
		super(poruka);
	}
	
	/**
	 * Inicijalizira novu iznimku sa danim uzrokom
	 * 
	 * @param uzrok uzrok iznimke
	 */
	public AlarmPredIstekomException(Throwable uzrok){
		super(uzrok);
	}
	
	/**
	 * Inicijlaizira novu iznimku sa danom porukom i uzrokom
	 * @param poruka poruka iznimke
	 * @param uzrok uzrok iznimke
	 */
	public AlarmPredIstekomException(String poruka, Throwable uzrok){
		super(poruka, uzrok);
	}
}
