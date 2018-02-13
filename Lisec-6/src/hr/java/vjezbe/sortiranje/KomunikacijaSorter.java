package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Komunikacija;

public class KomunikacijaSorter implements Comparator<Komunikacija> {

	@Override
	public int compare(Komunikacija arg0, Komunikacija arg1) {
		if(arg0.getVrijeme().isBefore(arg1.getVrijeme())){
			return -1;
		}else if (arg0.getVrijeme().isAfter(arg1.getVrijeme())){
			return 1;
		}
		else{
			return arg0.getKlijent().getPrezime().compareToIgnoreCase(arg1.getKlijent().getPrezime());
		}
	}

}
