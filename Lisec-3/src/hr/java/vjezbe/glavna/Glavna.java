package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Osoba;
import hr.java.vjezbe.entitet.ProdajaArtikla;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.entitet.Zaposlenik;
import hr.java.vjezbe.iznimke.AlarmIstekaoException;
import hr.java.vjezbe.iznimke.AlarmPredIstekomException;
import hr.java.vjezbe.iznimke.DupliZaposlenikException;
import hr.java.vjezbe.iznimke.NeispravanEmailException;;

/**
 * Predstavlja glavnu klasu u kojoj se izvodi program
 * 
 * @author Velimir
 *
 */
public class Glavna {

	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	/**
	 * Poèetna metoda programa
	 * 
	 * @param args argumenti iz komandne linije
	 */
	public static void main(String[] args) {

		Scanner unos = new Scanner(System.in);
		BigDecimal ukupnaCijena = new BigDecimal(0);

		System.out.println("UNOS PODATAKA");
		logger.info("Zapoèet unos podataka");
		List<Klijent> klijenti = new ArrayList<Klijent>();
		klijenti = unesiKlijente(unos);
		List<Zaposlenik> zaposlenici = new ArrayList<Zaposlenik>();
		zaposlenici = unesiZaposlenike(unos);

		List<Osoba> osobe = new ArrayList<Osoba>();
		osobe.addAll(klijenti);
		osobe.addAll(zaposlenici);
		osobe.sort((p1, p2) -> p1.getPrezime().compareTo(p2.getPrezime()));

		ispisiOsobe(osobe);

		MaloprodajnaTvrtka tvrtka = unesiTvrtku(unos);
		tvrtka.setKlijenti(klijenti);
		logger.info("Postavljeni klijenti tvrtke");
		tvrtka.setZaposlenici(zaposlenici);
		logger.info("Postavljeni zaposlenici tvrtke");

		Boolean ponoviUnos = true;
		int brUsluga = 0;

		do {
			try {
				System.out.println("Unesite broj usluga koji želite izvršiti:");
				brUsluga = unos.nextInt();
				if (brUsluga < 1) {
					System.out.println("Unijeli ste premali broj usluga, ponovite unos!");
					logger.error("Unesen je premali broj usluga: " + brUsluga + " usluga");
				} else
					ponoviUnos = false;
			} catch (InputMismatchException e) {
				System.out.println("Niste unijeli cijeli broj, ponovite unos!");
				logger.error("Unesen neispravan broj usluga", e);
			} finally {
				unos.nextLine();
			}
		} while (ponoviUnos);

		List<ProdajaArtikla> prodajeArtikala = new ArrayList<ProdajaArtikla>();
		List<Alarm> alarmi = new ArrayList<Alarm>();

		for (int i = 0; i < brUsluga; i++) {

			System.out.printf("UNESITE %d. USLUGU:\n", i + 1);
			logger.info("Unos " + (i + 1) + ". usluge");
			System.out.println("ODABERITE REDNI BROJ KLIJENTA:");

			ispisiKlijente(tvrtka);
			ponoviUnos = true;
			int odabraniKlijent = 0;

			do {
				System.out.println("ODABIR:");
				try {
					odabraniKlijent = unos.nextInt() - 1;

					if (odabraniKlijent < 0 || odabraniKlijent >= Tvrtka.BROJ_KLIJENATA) {
						System.out.println("Morate unijeti broj izmeðu 1 i " + Tvrtka.BROJ_KLIJENATA);
						logger.error("Unesen je neispravan broj klijenta: " + (odabraniKlijent + 1));
					} else
						ponoviUnos = false;

				} catch (InputMismatchException e) {
					System.out.println("Niste unijeli cijeli broj, ponovite unos!");
					logger.error("Nije unesen cijeli broj", e);
				} finally {
					unos.nextLine();
				}
			} while (ponoviUnos);

			Usluga usluga = unesiUslugu(tvrtka.getKlijenti().get(odabraniKlijent), unos);

			System.out.println("ODABIR ARTIKLA:");
			ispisiArtikle(tvrtka);

			ponoviUnos = true;
			int odabraniArtikl = 0;

			do {
				System.out.println("ODABIR:");
				try {
					odabraniArtikl = unos.nextInt() - 1;

					if (odabraniArtikl < 0 || odabraniArtikl >= MaloprodajnaTvrtka.BROJ_ARTIKALA) {
						System.out.println("Morate unijeti broj izmeðu 1 i " + MaloprodajnaTvrtka.BROJ_ARTIKALA);
						logger.error("Unesen pogrešan broj artikla: " + (odabraniArtikl + 1));
					} else
						ponoviUnos = false;

				} catch (InputMismatchException e) {
					System.out.println("Niste unijeli cijeli broj, ponovite unos!");
					logger.error("Nije unesen pravilan broj artikla", e);
				} finally {
					unos.nextLine();
				}
			} while (ponoviUnos);

			ponoviUnos = true;
			int brojArtikala = 0;

			do {
				try {
					System.out.println("Unesite broj artikala koje želite prodati:");
					brojArtikala = unos.nextInt();

					if (brojArtikala >= 0)
						ponoviUnos = false;
					else {
						System.out.println("Morate unijeti pozitivan broj, ponovite unos!");
						logger.error("Unesen je negativan broj artikala: " + brojArtikala);
					}

				} catch (InputMismatchException e) {
					System.out.println("Niste unijeli cijeli broj, ponovite unos!");
					logger.error("Nije unesen cijeli broj artikala", e);
				} finally {
					unos.nextLine();
				}
			} while (ponoviUnos);

			ProdajaArtikla prodajaArtikla = new ProdajaArtikla(usluga.getKlijent(), usluga.getVrsta(), usluga.getOpis(),
					usluga.getDatum(), usluga.getCijena(), tvrtka.getArtikli().get(odabraniArtikl));

			String opisAlarma = "Povratna informacija za obavljenu uslugu: " + usluga.getOpis();
			LocalDateTime vrijemeAlarma = LocalDateTime.now().plusMinutes(1);

			Alarm alarm = new Alarm(usluga.getKlijent(), opisAlarma, vrijemeAlarma, true);
			logger.info("Kreiran alarm za uslugu");

			prodajeArtikala.add(prodajaArtikla);
			alarmi.add(alarm);

			ukupnaCijena = ukupnaCijena.add(prodajaArtikla.prodaja(brojArtikala));

		}

		tvrtka.setAlarmi(alarmi);
		logger.info("Postavljeni alarmi za cijelu tvrtku");

		System.out.println("Ukupna cijena svih prodanih proizvoda je " + ukupnaCijena + " kn");
		logger.info("Ukupna cijena svih prodanih proizvoda: " + ukupnaCijena + " kn");

		unos.close();

		while (true) {

			Boolean alarmAktivan = false;

			for (Alarm alarm : tvrtka.getAlarmi()) {
				try {
					provjeriAlarm(alarm);
				} catch (AlarmPredIstekomException e) {
					System.out.println(e.getMessage());
					logger.error("Alarm pred istekom", e);
					alarmAktivan = true;
				} catch (AlarmIstekaoException ex) {
					System.out.println(ex.getMessage());
					logger.error("Alarm je istekao", ex);
				}
			}

			if (alarmAktivan == false)
				break;

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				logger.error("Prekinuto spavanje niti", e);
			}
		}

	}

	/**
	 * Provjerava da li je alarm pred istekom ili je istekao
	 * 
	 * @param alarm
	 *            podatak o alarmu koji se želi provjeriti
	 * @throws AlarmPredIstekomException
	 * @throws AlarmIstekaoException
	 */
	private static void provjeriAlarm(Alarm alarm) throws AlarmPredIstekomException, AlarmIstekaoException {
		if (alarm.getVrijeme().isAfter(LocalDateTime.now())) {
			throw new AlarmPredIstekomException("Za manje od 1 minute æe biti vrijeme alarma " + alarm.getOpis());
		} else if (alarm.getStatus() == true) {
			alarm.setStatus(false);
			throw new AlarmIstekaoException("Sljedeæi alarm je istekao: " + alarm.getOpis());
		}
	}

	/**
	 * Unosi podatke o usluzi za klijenta
	 * 
	 * @param klijent
	 *            podatak o klijentu koji prima uslugu
	 * @param unos
	 *            scanner koji dohvaæa podatke sa ulaza
	 * @return
	 */
	private static Usluga unesiUslugu(Klijent klijent, Scanner unos) {
		logger.info("Zapoèet unos usluge");
		System.out.println("Vrsta usluge:");
		String vrstaUsluge = unos.nextLine();
		logger.info("Odabrana vrsta usluge: " + vrstaUsluge);
		System.out.println("Opis usluge:");
		String opisUsluge = unos.nextLine();
		logger.info("Odabran opis usluge: " + opisUsluge);
		Boolean ponoviUnos = true;
		BigDecimal cijenaUsluge = null;

		do {
			try {
				System.out.println("Cijena usluge:");
				cijenaUsluge = unos.nextBigDecimal();

				if (cijenaUsluge.compareTo(BigDecimal.ZERO) <= 0) {
					System.out.println("Cijena mora biti veæa od 0, ponovite unos!");
					logger.error("Unesena neispravna cijena usluge: " + cijenaUsluge + ". Cijena mora biti veæa od 0.");
				} else
					ponoviUnos = false;
			} catch (Exception e) {
				System.out.println("Niste unijeli pravilnu cijenu, ponovite unos!");
				logger.error("Nije unesen pravilan broj: " + cijenaUsluge, e);
			} finally {
				unos.nextLine();
			}
		} while (ponoviUnos);

		logger.info("Odabrana cijena usluge: " + cijenaUsluge);
		LocalDate datumUsluge = LocalDate.now();

		return new Usluga(klijent, vrstaUsluge, opisUsluge, datumUsluge, cijenaUsluge);
	}

	/**
	 * Unosi podatke o tvrtki
	 * 
	 * @param unos
	 *            scanner koji dohvaæa podatke sa ulaza
	 * @return
	 */
	private static MaloprodajnaTvrtka unesiTvrtku(Scanner unos) {

		List<Artikl> artikli = new ArrayList<Artikl>(MaloprodajnaTvrtka.BROJ_ARTIKALA);

		logger.info("Zapoèet unos tvrtke");
		System.out.println("UNOS TVRTKE:");
		System.out.println("Unesite naziv tvrtke:");
		String nazivTvrtke = unos.nextLine();
		System.out.println("Unesite oib tvrtke:");
		String oib = unos.nextLine();
		logger.info("Unesena tvrtka: " + nazivTvrtke);

		for (int i = 0; i < MaloprodajnaTvrtka.BROJ_ARTIKALA; i++) {
			System.out.printf("Unesite %d. artikl:\n", i + 1);
			logger.info("Zapoèet unos " + (i + 1) + ". artikla");
			System.out.printf("Unesite naziv %d. artikla:\n", i + 1);
			String nazivArtikla = unos.nextLine();
			System.out.printf("Unesite kategoriju %d. artikla:\n", i + 1);
			String kategorija = unos.nextLine();
			artikli.add(new Artikl(nazivArtikla, kategorija));
			logger.info("Unesen artikl: " + nazivArtikla);
		}

		return new MaloprodajnaTvrtka(nazivTvrtke, oib, artikli);
	}

	/**
	 * Ispisuje listu osoba na monitor
	 * 
	 * @param osobe
	 *            lista osoba koje se ispisuju
	 */
	private static void ispisiOsobe(List<Osoba> osobe) {
		logger.info("Zapoèet ispis osoba");
		System.out.println("Klijenti i zaposlenici tvrtke:");
		for (Osoba osoba : osobe) {
			System.out.println("Prezime i ime: " + osoba.getPrezime() + " " + osoba.getIme());
			logger.info("Ispisana osoba: " + osoba.getPrezime() + " " + osoba.getIme());
			if (osoba instanceof Klijent) {
				System.out.println("Osoba je klijent.");
				logger.info("Osoba je klijent");
			} else if (osoba instanceof Zaposlenik) {
				System.out.println("Osoba je zaposlenik.");
				logger.info("Osoba je zaposlenik");
			}
		}

	}

	/**
	 * Unosi podatke o klijentima
	 * 
	 * @param unos
	 *            scanner koji dohvaæa podatke sa ulaza
	 * @return listu klijenata koji su uneseni
	 */
	private static List<Klijent> unesiKlijente(Scanner unos) {

		List<Klijent> klijenti = new ArrayList<Klijent>();
		for (int i = 0; i < Tvrtka.BROJ_KLIJENATA; i++) {
			System.out.printf("UNOS %d. KLIJENTA:\n", i + 1);
			logger.info("Zapoèet unos " + (i + 1) + ". klijenta");
			System.out.println("Unesite OIB klijenta:");
			String oib = unos.nextLine();
			System.out.println("Unesite prezime klijenta:");
			String prezime = unos.nextLine();
			System.out.println("Unesite ime klijenta:");
			String ime = unos.nextLine();
			System.out.println("Unesite broj telefona klijenta:");
			String brojTelefona = unos.nextLine();
			String eMail = null;
			Boolean ponoviUnos = true;
			do {
				try {
					System.out.println("Unesite e-mail klijenta:");
					eMail = unos.nextLine();
					provjeriEmail(eMail);
					ponoviUnos = false;
				} catch (NeispravanEmailException e) {
					System.out.println("Upisali ste neispravan e-mail, ponovite upis");
					logger.error("Unesen neispravan email: " + eMail, e);
				}
			} while (ponoviUnos);

			LocalDate datumRodjenja = null;
			String datumRodjenjaString = null;
			ponoviUnos = true;

			do {
				try {
					System.out.println("Unesite datum roðenja klijenta (dd.mm.yyyy):");
					datumRodjenjaString = unos.nextLine();
					DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
					datumRodjenja = LocalDate.parse(datumRodjenjaString, format);
					ponoviUnos = false;
				} catch (DateTimeParseException e) {
					System.out.println("Upisali ste pogrešan format datuma, ponovite upis");
					logger.error("Unesen neispravan format datuma: " + datumRodjenjaString, e);
				}
			} while (ponoviUnos);

			klijenti.add(new Klijent(ime, prezime, oib, brojTelefona, eMail, datumRodjenja));
			logger.info("Unesen klijent: " + prezime + " " + ime);
		}
		return klijenti;
	}

	/**
	 * Unosi podatke o zaposlenicima
	 * 
	 * @param unos
	 *            scanner koji dohvaæa podatke sa ulaza
	 * @return lista zaposlenika koji su uneseni
	 */
	private static List<Zaposlenik> unesiZaposlenike(Scanner unos) {

		List<Zaposlenik> zaposlenici = new ArrayList<Zaposlenik>();
		Zaposlenik zaposlenik = null;

		String prezime = null;
		String ime = null;

		for (int i = 0; i < Tvrtka.BROJ_ZAPOSLENIKA; i++) {
			Boolean ponoviUnos = true;
			do {
				System.out.printf("UNOS %d. ZAPOSLENIKA:\n", i + 1);
				logger.info("Zapoèet unos " + (i + 1) + ". zaposlenika");
				System.out.println("Unesite korisnièko ime zaposlenika:");
				String korisnickoIme = unos.nextLine();
				System.out.println("Unesite prezime zaposlenika:");
				prezime = unos.nextLine();
				System.out.println("Unesite ime zaposlenika:");
				ime = unos.nextLine();
				System.out.println("Unesite šifru zaposlenika:");
				String sifra = unos.nextLine();

				zaposlenik = new Zaposlenik(ime, prezime, korisnickoIme, sifra);

				try {
					provjeriZaposlenika(zaposlenik, zaposlenici);
					ponoviUnos = false;
				} catch (DupliZaposlenikException e) {
					System.out.println("Upisali ste duplog zaposlenika, ponovite unos");
					logger.error("Upisan je dupli zaposlenik", e);
				}
			} while (ponoviUnos);
			zaposlenici.add(zaposlenik);
			logger.info("Unesen zaposlenik: " + prezime + " " + ime);
		}
		return zaposlenici;
	}

	/**
	 * Provjerava da li je zaposlenik veæ unesen u listu, te ako je baca iznimku
	 * @param zaposlenik novi zaposlenik kojeg želimo provjeriti
	 * @param zaposlenici lista zapolenika u kojoj tražimo duplog zaposlenika
	 */
	private static void provjeriZaposlenika(Zaposlenik zaposlenik, List<Zaposlenik> zaposlenici) {

		for (Zaposlenik stariZaposlenik : zaposlenici) {
			if (zaposlenik.getIme().equals(stariZaposlenik.getIme())
					&& zaposlenik.getKorisnickoIme().equals(stariZaposlenik.getKorisnickoIme())
					&& zaposlenik.getPrezime().equals(stariZaposlenik.getPrezime())
					&& zaposlenik.getSifra().equals(stariZaposlenik.getSifra())) {
				throw new DupliZaposlenikException();
			}
		}
	}

	/**
	 * Ispisuje klijente tvrtke
	 * 
	 * @param tvrtka
	 *            podatak o tvrtci za koju treba ispisati klijente
	 */
	private static void ispisiKlijente(MaloprodajnaTvrtka tvrtka) {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		int redniBroj = 1;

		for (Klijent klijent : tvrtka.getKlijenti()) {

			System.out.printf("%d. KLIJENT:\n", redniBroj++);
			System.out.println("OIB klijenta: " + klijent.getOib());
			System.out.println("Prezime klijenta: " + klijent.getPrezime());
			System.out.println("Ime klijenta: " + klijent.getIme());
			System.out.println("Broj telefona klijenta: " + klijent.getBrojTelefona());
			System.out.println("E-mail klijenta: " + klijent.geteMail());
			System.out.println("Datum roðenja klijenta (dd.mm.yyyy): " + klijent.getDatumRodjenja().format(format));
		}
	}

	/**
	 * Ispisuje artikle tvrtke
	 * 
	 * @param tvrtka
	 *            podatak o tvrtki za koju treba ispisati artikle
	 */
	private static void ispisiArtikle(MaloprodajnaTvrtka tvrtka) {
		int redniBroj = 1;

		for (Artikl artikl : tvrtka.getArtikli()) {

			System.out.printf("%d. ARTIKL:\n", redniBroj++);
			System.out.println("Naziv artikla: " + artikl.getNaziv());
			System.out.println("Kategorija artikla: " + artikl.getKategorija());
		}
	}

	/**
	 * Provjerava da li je e-mail pravilno upisan, te ako nije baca iznimku
	 * @param email email kojeg provjeravamo
	 * @throws NeispravanEmailException
	 */
	private static void provjeriEmail(String email) throws NeispravanEmailException {

		String regex = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);

		if (!matcher.matches()) {
			throw new NeispravanEmailException();
		}
	}
}
