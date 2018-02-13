package hr.java.vjezbe.iznimke;

/**
 * Javlja se u metodi provjeriAlarm ako je alarm pred istekom
 * 
 * @author Velimir
 *
 */
public class AlarmPredIstekomException extends Exception{

	private static final long serialVersionUID = 1835628531001502867L;

	public AlarmPredIstekomException(){
		super("Alarm pred istekom!");
	}
	
	public AlarmPredIstekomException(String poruka){
		super(poruka);
	}
	
	public AlarmPredIstekomException(Throwable uzrok){
		super(uzrok);
	}
	
	public AlarmPredIstekomException(String poruka, Throwable uzrok){
		super(poruka, uzrok);
	}
}
