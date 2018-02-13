package hr.java.vjezbe.glavna;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.entitet.Zaposlenik;;

public class Glavna {

	public static void main(String[] args) {

		Scanner unos = new Scanner(System.in);

		System.out.println("UNOS PODATAKA");

		Klijent[] klijenti = unesiKlijente(unos);
		Zaposlenik[] zaposlenici = unesiZaposlenike(unos);
		Tvrtka tvrtka = unesiTvrtku(unos);

		tvrtka.setKlijenti(klijenti);
		tvrtka.setZaposlenici(zaposlenici);
		unos.close();

		ispisPodataka(tvrtka);
	}

	private static void ispisPodataka(Tvrtka tvrtka) {
		System.out.println("ISPIS PODATAKA");
		System.out.println();
		System.out.println("Naziv tvrtke:");
		System.out.println(tvrtka.getNaziv());
		System.out.println("OIB tvrtke:");
		System.out.println(tvrtka.getOib());

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		for (Klijent klijent : tvrtka.getKlijenti()) {

			System.out.println();

			System.out.println("OIB klijenta:");
			System.out.println(klijent.getOib());
			System.out.println("Prezime klijenta:");
			System.out.println(klijent.getPrezime());
			System.out.println("Ime klijenta:");
			System.out.println(klijent.getIme());
			System.out.println("Broj telefona klijenta:");
			System.out.println(klijent.getBrojTelefona());
			System.out.println("E-mail klijenta:");
			System.out.println(klijent.geteMail());
			System.out.println("Datum roðenja klijenta (dd.mm.yyyy):");
			System.out.println(klijent.getDatumRodjenja().format(format));
		}

		for (Zaposlenik zaposlenik : tvrtka.getZaposlenici()) {

			System.out.println();
			System.out.println("Korisnièko ime zaposlenika:");
			System.out.println(zaposlenik.getKorisnickoIme());
			System.out.println("Prezime zaposlenika");
			System.out.println(zaposlenik.getPrezime());
			System.out.println("Ime zaposlenika:");
			System.out.println(zaposlenik.getIme());
			System.out.println("Šifra zaposlenika:");
			System.out.println(zaposlenik.getSifra());
		}
	}

	private static Klijent[] unesiKlijente(Scanner unos) {

		Klijent[] klijenti = new Klijent[Tvrtka.BROJ_KLIJENATA];
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

			klijenti[i] = new Klijent(oib, prezime, ime, brojTelefona, eMail, datumRodjenja);
		}
		return klijenti;
	}

	private static Zaposlenik[] unesiZaposlenike(Scanner unos) {

		Zaposlenik[] zaposlenici = new Zaposlenik[Tvrtka.BROJ_ZAPOSLENIKA];
		Boolean ponovi = false;
		String regex = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		
		for (int i = 0; i < Tvrtka.BROJ_ZAPOSLENIKA; i++) {
			do {
				ponovi = false;
				System.out.printf("UNOS %d. ZAPOSLENIKA:\n", i + 1);
				System.out.println("Unesite korisnièko ime zaposlenika:");
				String korisnickoIme = unos.nextLine();
				System.out.println("Unesite prezime zaposlenika:");
				String prezime = unos.nextLine();
				System.out.println("Unesite ime zaposlenika:");
				String ime = unos.nextLine();

				String sifra;
				do {
					System.out.println("Unesite šifru zaposlenika:");
					sifra = unos.nextLine();

				} while (!pattern.matcher(sifra).matches());

				Zaposlenik zap = new Zaposlenik(korisnickoIme, prezime, ime, sifra);

				try {
					for (Zaposlenik zaposlenik : zaposlenici) {
						if (zaposlenik.getKorisnickoIme().equals(zap.getKorisnickoIme())) {
							System.out.println("Unijeli ste duplog zaposlenika, ponovite upis!!");
							ponovi = true;
							break;
						}

					}
				} catch (Exception e) {};

				if (ponovi == false) {
					zaposlenici[i] = zap;
				}

			} while (ponovi);

		}
		return zaposlenici;
	}

	private static Tvrtka unesiTvrtku(Scanner unos) {

		System.out.println("UNOS TVRTKE:");
		System.out.println("Unesite naziv tvrtke:");
		String naziv = unos.nextLine();
		System.out.println("Unesite oib tvrtke:");
		String oib = unos.nextLine();

		return new Tvrtka(naziv, oib);
	}

}
