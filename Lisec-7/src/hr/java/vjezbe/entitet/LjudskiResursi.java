package hr.java.vjezbe.entitet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Osoba;

public class LjudskiResursi<T extends Osoba> {

	private List<T> listaLjudskihResursa = new ArrayList<T>();
	
	public T get(Integer i){
		return listaLjudskihResursa.get(i);
	}
	
	public void add(T osoba){
		listaLjudskihResursa.add(osoba);
	}
	
	public List<T> getSortedList(){
		return listaLjudskihResursa.stream()
									.sorted((o1, o2) -> o1.getPrezime().compareTo(o2.getPrezime()))
									.collect(Collectors.toList());
	}
}
