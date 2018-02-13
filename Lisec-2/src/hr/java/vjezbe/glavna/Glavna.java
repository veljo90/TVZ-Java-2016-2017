package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Osoba;
import hr.java.vjezbe.entitet.ProdajaArtikla;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.entitet.Zaposlenik;;

public class Glavna {

	public static void main(String[] args) {

		Scanner unos = new Scanner(System.in);
		BigDecimal ukupnaCijena = new BigDecimal(0);
		int brojArtikala = 0;

		System.out.println("UNOS PODATAKA");

		List<Klijent> klijenti = new ArrayList<Klijent>();
		klijenti = unesiKlijente(unos);
		List<Zaposlenik> zaposlenici = new ArrayList<Zaposlenik>();
		zaposlenici = unesiZaposlenike(unos);

		Comparator<Zaposlenik> c = (p1, p2) -> p1.getPrezime().compareTo(p2.getPrezime());
		c = c.thenComparing((p1, p2) -> p1.getIme().compareTo(p2.getIme()));

		zaposlenici.sort(c);
		for (Zaposlenik zaposlenik : zaposlenici) {

			System.out.println("Prezime zaposlenika: " + zaposlenik.getPrezime());
			System.out.println("Ime zaposlenika: " + zaposlenik.getIme());
		}
		

		List<Osoba> osobe = new ArrayList<Osoba>();
		osobe.addAll(klijenti);
		osobe.addAll(zaposlenici);
		osobe.sort((p1, p2) -> p1.getPrezime().compareTo(p2.getPrezime()));

		ispisiOsobe(osobe);

		MaloprodajnaTvrtka tvrtka = unesiTvrtku(unos);
		tvrtka.setKlijenti(klijenti);
		tvrtka.setZaposlenici(zaposlenici);

		System.out.println("Unesite broj usluga koji želite izvršiti:");
		int brUsluga = unos.nextInt();
		unos.nextLine();

		List<ProdajaArtikla> prodajeArtikala = new ArrayList<ProdajaArtikla>();

		for (int i = 0; i < brUsluga; i++) {

			System.out.printf("UNESITE %d. USLUGU:\n", i + 1);
			System.out.println("ODABERITE REDNI BROJ KLIJENTA:");

			ispisiKlijente(tvrtka);
			System.out.println("ODABIR:");

			int odabraniKlijent = unos.nextInt() - 1;
			unos.nextLine();

			Usluga usluga = unesiUslugu(tvrtka.getKlijenti().get(odabraniKlijent), unos);

			System.out.println("ODABIR ARTIKLA:");
			ispisiArtikle(tvrtka);
			System.out.println("ODABIR:");
			int odabraniArtikl = unos.nextInt() - 1;
			unos.nextLine();

			System.out.println("Unesite broj artikala koje želite prodati:");
			brojArtikala = unos.nextInt();
			unos.nextLine();

			ProdajaArtikla prodajaArtikla = new ProdajaArtikla(usluga.getKlijent(), usluga.getVrsta(), usluga.getOpis(),
					usluga.getDatum(), usluga.getCijena(), tvrtka.getArtikli().get(odabraniArtikl));

			prodajeArtikala.add(prodajaArtikla);

			ukupnaCijena = ukupnaCijena.add(prodajaArtikla.prodaja(brojArtikala));

		}

		System.out.println("Ukupna cijena svih prodanih proizvoda je " + ukupnaCijena + " kn");

		unos.close();

	}

	private static Usluga unesiUslugu(Klijent klijent, Scanner unos) {
		System.out.println("Vrsta usluge:");
		String vrstaUsluge = unos.nextLine();
		System.out.println("Opis usluge:");
		String opisUsluge = unos.nextLine();
		System.out.println("Cijena usluge:");
		BigDecimal cijenaUsluge = unos.nextBigDecimal();
		unos.nextLine();

		LocalDate datumUsluge = LocalDate.now();

		return new Usluga(klijent, vrstaUsluge, opisUsluge, datumUsluge, cijenaUsluge);
	}

	private static MaloprodajnaTvrtka unesiTvrtku(Scanner unos) {

		List<Artikl> artikli = new ArrayList<Artikl>(MaloprodajnaTvrtka.BROJ_ARTIKALA);

		System.out.println("UNOS TVRTKE:");
		System.out.println("Unesite naziv tvrtke:");
		String nazivTvrtke = unos.nextLine();
		System.out.println("Unesite oib tvrtke:");
		String oib = unos.nextLine();

		for (int i = 0; i < MaloprodajnaTvrtka.BROJ_ARTIKALA; i++) {
			System.out.printf("Unesite %d. artikl:\n", i + 1);
			System.out.printf("Unesite naziv %d. artikla:\n", i + 1);
			String nazivArtikla = unos.nextLine();
			System.out.printf("Unesite kategoriju %d. artikla:\n", i + 1);
			String kategorija = unos.nextLine();
			artikli.add(new Artikl(nazivArtikla, kategorija));
		}

		return new MaloprodajnaTvrtka(nazivTvrtke, oib, artikli);
	}

	private static void ispisiOsobe(List<Osoba> osobe) {
		System.out.println("Klijenti i zaposlenici tvrtke:");
		for (Osoba osoba : osobe) {
			System.out.println("Prezime i ime: " + osoba.getPrezime() + " " + osoba.getIme());
			if (osoba instanceof Klijent) {
				System.out.println("Osoba je klijent.");
			} else if (osoba instanceof Zaposlenik) {
				System.out.println("Osoba je zaposlenik.");
			}
		}

	}

	private static List<Klijent> unesiKlijente(Scanner unos) {

		List<Klijent> klijenti = new ArrayList<Klijent>();
		for (int i = 0; i < Tvrtka.BROJ_KLIJENATA; i++) {
			System.out.printf("UNOS %d. KLIJENTA:\n", i + 1);
			System.out.println("Unesite OIB klijenta:");
			String oib = unos.nextLine();
			System.out.println("Unesite prezime klijenta:");
			String prezime = unos.nextLine();
			System.out.println("Unesite ime klijenta:");
			String ime = unos.nextLine();
			System.out.println("Unesite broj telefona klijenta:");
			String brojTelefona = unos.nextLine();
			System.out.println("Unesite e-mail klijenta:");
			String eMail = unos.nextLine();
			System.out.println("Unesite datum roðenja klijenta (dd.mm.yyyy):");
			String datumRodjenjaString = unos.nextLine();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaString, format);

			klijenti.add(new Klijent(ime, prezime, oib, brojTelefona, eMail, datumRodjenja));
		}
		return klijenti;
	}

	private static List<Zaposlenik> unesiZaposlenike(Scanner unos) {

		List<Zaposlenik> zaposlenici = new ArrayList<Zaposlenik>();
		int i = 1;
		while (true) {
			System.out.printf("UNOS %d. ZAPOSLENIKA:\n", i++);
			System.out.println("Unesite korisnièko ime zaposlenika:");
			String korisnickoIme = unos.nextLine();
			if (korisnickoIme.equals(""))
				break;
			System.out.println("Unesite prezime zaposlenika:");
			String prezime = unos.nextLine();
			System.out.println("Unesite ime zaposlenika:");
			String ime = unos.nextLine();
			System.out.println("Unesite šifru zaposlenika:");
			String sifra = unos.nextLine();

			zaposlenici.add(new Zaposlenik(ime, prezime, korisnickoIme, sifra));
		}
		return zaposlenici;
	}

	private static void ispisiKlijente(MaloprodajnaTvrtka tvrtka) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		int redniBroj = 1;

		try {
			for (Klijent klijent : tvrtka.getKlijenti()) {

				System.out.printf("%d. KLIJENT:\n", redniBroj++);

				System.out.println("OIB klijenta: " + klijent.getOib());
				System.out.println("Prezime klijenta: " + klijent.getPrezime());
				System.out.println("Ime klijenta: " + klijent.getIme());
				System.out.println("Broj telefona klijenta: " + klijent.getBrojTelefona());
				System.out.println("E-mail klijenta: " + klijent.geteMail());
				System.out.println("Datum roðenja klijenta (dd.mm.yyyy): " + klijent.getDatumRodjenja().format(format));
			}
		} catch (Exception e) {
		}
	}

	private static void ispisiArtikle(MaloprodajnaTvrtka tvrtka) {
		int redniBroj = 1;

		try {
			for (Artikl artikl : tvrtka.getArtikli()) {

				System.out.printf("%d. ARTIKL:\n", redniBroj++);
				System.out.println("Naziv artikla: " + artikl.getNaziv());
				System.out.println("Kategorija artikla: " + artikl.getKategorija());
			}
		} catch (Exception e) {
		}

	}
}
