package hr.java.vjezbe.entitet;

import java.time.LocalDateTime;

public class Alarm {

	private Klijent klijent;
	private String opis;
	private LocalDateTime vrijeme;
	private Boolean status;
	
	public Alarm(Klijent klijent, String opis, LocalDateTime vrijeme, Boolean status) {
		super();
		this.klijent = klijent;
		this.opis = opis;
		this.vrijeme = vrijeme;
		this.status = status;
	}

	public Klijent getKlijent() {
		return klijent;
	}

	public void setKlijent(Klijent klijent) {
		this.klijent = klijent;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public LocalDateTime getVrijeme() {
		return vrijeme;
	}

	public void setVrijeme(LocalDateTime vrijeme) {
		this.vrijeme = vrijeme;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
