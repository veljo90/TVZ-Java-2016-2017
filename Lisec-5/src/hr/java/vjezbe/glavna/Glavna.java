package hr.java.vjezbe.glavna;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.KategorijaArtikla;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.Komunikacija;
import hr.java.vjezbe.entitet.LjudskiResursi;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Osoba;
import hr.java.vjezbe.entitet.ProdajaArtikla;
import hr.java.vjezbe.entitet.Tvrtka;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.entitet.VrstaKomunikacije;
import hr.java.vjezbe.entitet.VrstaUsluge;
import hr.java.vjezbe.entitet.Zaposlenik;
import hr.java.vjezbe.iznimke.AlarmIstekaoException;
import hr.java.vjezbe.iznimke.AlarmPredIstekomException;
import hr.java.vjezbe.sortiranje.KomunikacijaSorter;;

public class Glavna {

	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {

		Scanner unos = new Scanner(System.in);
		BigDecimal ukupnaCijena = new BigDecimal(0);

		System.out.println("UNOS PODATAKA");
		logger.info("Zapoèet unos podataka");
		List<Klijent> klijenti = new ArrayList<Klijent>();
		klijenti = unesiKlijente(unos);
		List<Zaposlenik> zaposlenici = new ArrayList<Zaposlenik>();
		zaposlenici = unesiZaposlenike(unos);

		LjudskiResursi<Osoba> osobe = new LjudskiResursi<>();

		for (Klijent klijent : klijenti) {
			osobe.add(klijent);
		}

		for (Zaposlenik zaposlenik : zaposlenici) {
			osobe.add(zaposlenik);
		}

		// osobe.addAll(klijenti);
		// osobe.addAll(zaposlenici);
		// osobe.sort((p1, p2) -> p1.getPrezime().compareTo(p2.getPrezime()));

		ispisiOsobe(osobe.getSortedList());

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
					usluga.getDatum(), usluga.getCijena(), tvrtka.getArtikli().get(odabraniArtikl), brojArtikala);

			String opisAlarma = "Povratna informacija za obavljenu uslugu: " + usluga.getOpis();
			LocalDateTime vrijemeAlarma = LocalDateTime.now().plusMinutes(1);

			Alarm alarm = new Alarm(usluga.getKlijent(), opisAlarma, vrijemeAlarma, true);
			logger.info("Kreiran alarm za uslugu");

			prodajeArtikala.add(prodajaArtikla);
			alarmi.add(alarm);

			prodajaArtikla.prodaja(brojArtikala);
			//ukupnaCijena = ukupnaCijena.add(prodajaArtikla.prodaja(brojArtikala));
		}

		ukupnaCijena = prodajeArtikala.stream()
								.map(p -> p.getCijena().multiply(new BigDecimal(p.getBrojArtikala())))
								.reduce(BigDecimal.ZERO, BigDecimal::add);
		
		tvrtka.setAlarmi(alarmi);
		logger.info("Postavljeni alarmi za cijelu tvrtku");

		System.out.println("Ukupna cijena svih prodanih proizvoda je " + ukupnaCijena + " kn");
		logger.info("Ukupna cijena svih prodanih proizvoda: " + ukupnaCijena + " kn");

		List<Komunikacija> komunikacije = new ArrayList<Komunikacija>();

		while (true) {

			Boolean alarmAktivan = false;

			for (Alarm alarm : tvrtka.getAlarmi()) {
				try {
					provjeriAlarm(alarm);
				} catch (AlarmPredIstekomException e) {
					System.out.println(e.getMessage());
					logger.error("Alarm pred istekom", e);
					alarmAktivan = true;

					while (true) {
						System.out.println("Želite li obaviti komunikaciju s klijentom (DA/NE)?");
						System.out.print("UNOS >> ");
						String zeliKomunikaciju = unos.nextLine().toUpperCase();
						if (zeliKomunikaciju.equals("DA")) {

							logger.info("Korisnik želi obaviti komunikaciju sa klijentom");
							System.out.println("Odaberite zaposlenika koji æe obaviti komunikaciju:");
							Zaposlenik zaposlenik = odabirZaposlenika(tvrtka, unos);
							System.out.println("Odaberite vrstu komunikacije:");
							VrstaKomunikacije vrsta = odabirKomunikacije(unos);

							Komunikacija komunikacija = new Komunikacija(alarm.getKlijent(), zaposlenik, vrsta,
									"Povratna informacija o obavljenoj usluzi");
							komunikacije.add(komunikacija);
							logger.info("Obavljena komunikacija sa klijentom");
							break;

						} else if (zeliKomunikaciju.equals("NE")) {
							logger.info("Korisnik ne želi obaviti komunikaciju sa klijentom");
							break;
						} else {
							System.out.println("Nepravilan unos, ponovite");
							logger.error("Na pitanje želi li obaviti komunikaciju s klijentom korisnik unosi: "
									+ zeliKomunikaciju);
						}
					}

				} catch (AlarmIstekaoException ex) {
					System.out.println(ex.getMessage());
					logger.error("Alarm je istekao", ex);
				}
			}

			if (alarmAktivan == false) {
				tvrtka.setKomunikacija(komunikacije);
				break;
			}

			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				logger.error("Prekinuto spavanje niti", e);
			}
		}

		long seed = System.nanoTime();
		Collections.shuffle(tvrtka.getKomunikacija(), new Random(seed));

		System.out.println("Ispis svih komunikacija:");
		ispisiKomunikacije(tvrtka);
		System.out.println();

		long pocetakSortiranjaBezLambdi = System.currentTimeMillis();
		Collections.sort(tvrtka.getKomunikacija(), new KomunikacijaSorter());
		long krajSortiranjaBezLambdi = System.currentTimeMillis();

		System.out.println("Ispis sortiranih komunikacija:");
		ispisiKomunikacije(tvrtka);

		System.out.println(
				"Vrijeme sortiranja bez lambdi: " + (krajSortiranjaBezLambdi - pocetakSortiranjaBezLambdi) + " ms");

		Comparator<Komunikacija> c = (p1, p2) -> p1.getVrijeme().compareTo(p2.getVrijeme());
		c = c.thenComparing((p1, p2) -> p1.getKlijent().getPrezime().compareTo(p2.getKlijent().getPrezime()));
		long pocetakSortiranjaSaLambdama = System.currentTimeMillis();
		tvrtka.getKomunikacija().stream().sorted(c);
		long krajSortiranjaSaLambdama = System.currentTimeMillis();
		System.out.println(
				"Vrijeme sortiranja sa lambdama: " + (krajSortiranjaSaLambdama - pocetakSortiranjaSaLambdama) + " ms");
		unos.close();
	}

	private static void ispisiKomunikacije(MaloprodajnaTvrtka tvrtka) {
		int redniBroj = 1;

		for (Komunikacija komunikacija : tvrtka.getKomunikacija()) {
			System.out.println("Podaci za " + redniBroj++ + ". komunikaciju:");
			System.out.println("Prezime i ime klijenta: " + komunikacija.getKlijent().getPrezime() + " "
					+ komunikacija.getKlijent().getIme());
			System.out.println("Prezime i ime zaposlenika: " + komunikacija.getZaposlenik().getPrezime() + " "
					+ komunikacija.getZaposlenik().getIme());
			System.out.println("Vrsta komunikacije: " + komunikacija.getVrsta());
			System.out.println("Sadržaj komunikacije: " + komunikacija.getSadrzaj());
			System.out.println("Vrijeme komunikacije: "
					+ komunikacija.getVrijeme().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")));
		}

	}

	private static VrstaKomunikacije odabirKomunikacije(Scanner unos) {

		System.out.println("Odaberite vrstu komunikacije:");
		for (int i = 0; i < VrstaKomunikacije.values().length - 1; i++) {
			System.out.println((i + 1) + ". " + VrstaKomunikacije.values()[i]);
		}
		int redniBrojKomunikacije;

		while (true) {
			System.out.print("ODABIR >> ");
			try {
				redniBrojKomunikacije = unos.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Neispravan unos!");
				logger.error("Neispravan unos vrste komunikacije", e);
			} finally {
				unos.nextLine();
			}
		}

		if (redniBrojKomunikacije >= 1 && redniBrojKomunikacije < VrstaKomunikacije.values().length) {
			return VrstaKomunikacije.values()[redniBrojKomunikacije - 1];
		} else {
			return VrstaKomunikacije.OSTALO;
		}
	}

	private static Zaposlenik odabirZaposlenika(MaloprodajnaTvrtka tvrtka, Scanner unos) {

		int odabraniZaposlenik;

		ispisiZaposlenike(tvrtka);

		while (true) {
			System.out.print("ODABIR >> ");
			try {
				odabraniZaposlenik = unos.nextInt() - 1;
				if (odabraniZaposlenik >= 0 && odabraniZaposlenik < tvrtka.getZaposlenici().size()) {
					Zaposlenik zaposlenik = tvrtka.getZaposlenici().get(odabraniZaposlenik);
					logger.info("Odabran zaposlenik: " + zaposlenik.getIme() + " " + zaposlenik.getPrezime());
					return zaposlenik;
				} else {
					System.out.println("Morate unijeti broj izmeðu 1 i " + tvrtka.getZaposlenici().size());
					logger.error("Izabran nepostojeæi zaposlenik " + odabraniZaposlenik);
				}
			} catch (InputMismatchException e) {
				System.out.println("Nepravilan unos, ponovite");
				logger.error("Nije unesen pravilan broj", e);
			} finally {
				unos.nextLine();
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

		VrstaUsluge vrstaUsluge = null;
		for (int i = 0; i < VrstaUsluge.values().length - 1; i++) {
			System.out.println((i + 1) + ". " + VrstaUsluge.values()[i]);
		}

		int redniBrUsluge;

		while (true) {
			System.out.print("ODABIR >> ");
			try {
				redniBrUsluge = unos.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Neispravan unos, ponovite");
				logger.error("Neisprava unos usluge", e);
			} finally {
				unos.nextLine();
			}
		}

		if (redniBrUsluge >= 1 && redniBrUsluge < VrstaUsluge.values().length) {
			vrstaUsluge = VrstaUsluge.values()[redniBrUsluge - 1];
		} else {
			vrstaUsluge = VrstaUsluge.OSTALO;
		}
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

			KategorijaArtikla kategorija = null;

			for (int j = 0; j < KategorijaArtikla.values().length - 1; j++) {
				System.out.println((j + 1) + ". " + KategorijaArtikla.values()[j]);
			}

			int redniBrKategorije;

			while (true) {
				System.out.println("ODABIR >> ");
				try {
					redniBrKategorije = unos.nextInt();
					break;
				} catch (InputMismatchException e) {
					System.out.println("Neispravan unos kategorija, ponovite");
					logger.error("Neispravan unos kategorije artikla", e);
				} finally {
					unos.nextLine();
				}
			}
			if (redniBrKategorije >= 1 && redniBrKategorije < KategorijaArtikla.values().length) {
				kategorija = KategorijaArtikla.values()[redniBrKategorije - 1];
			} else {
				kategorija = KategorijaArtikla.OSTALO;
			}

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

		osobe.forEach(o -> {
			System.out.println("Prezime i ime: " + o.getPrezime() + " " + o.getIme());
			if (o instanceof Klijent) {
				System.out.println("Osoba je klijent.");
				logger.info("Osoba je klijent");
			} else if (o instanceof Zaposlenik) {
				System.out.println("Osoba je zaposlenik.");
				logger.info("Osoba je zaposlenik");
			}
		});

//		for (Osoba osoba : osobe) {
//			System.out.println("Prezime i ime: " + osoba.getPrezime() + " " + osoba.getIme());
//			logger.info("Ispisana osoba: " + osoba.getPrezime() + " " + osoba.getIme());
//			if (osoba instanceof Klijent) {
//				System.out.println("Osoba je klijent.");
//				logger.info("Osoba je klijent");
//			} else if (osoba instanceof Zaposlenik) {
//				System.out.println("Osoba je zaposlenik.");
//				logger.info("Osoba je zaposlenik");
//			}
//		}

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
			System.out.println("Unesite e-mail klijenta:");
			String eMail = unos.nextLine();

			LocalDate datumRodjenja = null;
			String datumRodjenjaString = null;
			Boolean ponoviUnos = true;

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
		for (int i = 0; i < Tvrtka.BROJ_ZAPOSLENIKA; i++) {
			System.out.printf("UNOS %d. ZAPOSLENIKA:\n", i + 1);
			logger.info("Zapoèet unos " + (i + 1) + ". zaposlenika");
			System.out.println("Unesite korisnièko ime zaposlenika:");
			String korisnickoIme = unos.nextLine();
			System.out.println("Unesite prezime zaposlenika:");
			String prezime = unos.nextLine();
			System.out.println("Unesite ime zaposlenika:");
			String ime = unos.nextLine();
			System.out.println("Unesite šifru zaposlenika:");
			String sifra = unos.nextLine();

			zaposlenici.add(new Zaposlenik(ime, prezime, korisnickoIme, sifra));
			logger.info("Unesen zaposlenik: " + prezime + " " + ime);
		}
		return zaposlenici;
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

	private static void ispisiZaposlenike(MaloprodajnaTvrtka tvrtka) {

		//int redniBroj = 1;
		final AtomicInteger redniBroj = new AtomicInteger(1);
		
		tvrtka.getZaposlenici().forEach(z -> {
			System.out.printf("%d. ZAPOSLENIK:\n", redniBroj.getAndIncrement());
			System.out.println("PREZIME: " + z.getPrezime());
			System.out.println("IME: " + z.getIme());
			System.out.println("KORISNIÈKO IME: " + z.getKorisnickoIme());
		});

//		for (Zaposlenik zaposlenik : tvrtka.getZaposlenici()) {
//
//			System.out.printf("%d. ZAPOSLENIK:\n", redniBroj++);
//			System.out.println("PREZIME: " + zaposlenik.getPrezime());
//			System.out.println("IME: " + zaposlenik.getIme());
//			System.out.println("KORISNIÈKO IME: " + zaposlenik.getKorisnickoIme());
//		}
	}

	/**
	 * Ispisuje artikle tvrtke
	 * 
	 * @param tvrtka
	 *            podatak o tvrtki za koju treba ispisati artikle
	 */
	private static void ispisiArtikle(MaloprodajnaTvrtka tvrtka) {
		//int redniBroj = 1;
		final AtomicInteger redniBroj = new AtomicInteger(1);
		
		tvrtka.getArtikli().forEach(a -> { 
			System.out.printf("%d. ARTIKL:\n", redniBroj.getAndIncrement());
			System.out.println("Naziv artikla: " + a.getNaziv());
			System.out.println("Kategorija artikla: " + a.getKategorija());
		}
		);

//		for (Artikl artikl : tvrtka.getArtikli()) {
//
//			System.out.printf("%d. ARTIKL:\n", redniBroj++);
//			System.out.println("Naziv artikla: " + artikl.getNaziv());
//			System.out.println("Kategorija artikla: " + artikl.getKategorija());
//		}
	}
}
