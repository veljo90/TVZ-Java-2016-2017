package hr.java.vjezbe.iznimke;

/**
 * Javlja se u metodi provjeriAlarm ako je alarm istekao
 * 
 * @author Velimir
 *
 */
public class AlarmIstekaoException extends RuntimeException{

	private static final long serialVersionUID = 5730177570837336955L;

	public AlarmIstekaoException(){
		super("Alarm je istekao!");
	}
	
	public AlarmIstekaoException(String poruka){
		super(poruka);
	}
	
	public AlarmIstekaoException(Throwable uzrok){
		super(uzrok);
	}
	
	public AlarmIstekaoException(String poruka, Throwable uzrok){
		super(poruka, uzrok);
	}
}
