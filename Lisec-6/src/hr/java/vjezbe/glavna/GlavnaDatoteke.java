package hr.java.vjezbe.glavna;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.KategorijaArtikla;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.Komunikacija;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.ProdajaArtikla;
import hr.java.vjezbe.entitet.Usluga;
import hr.java.vjezbe.entitet.Zaposlenik;
import hr.java.vjezbe.sortiranje.KomunikacijaSorter;

public class GlavnaDatoteke {

	public static final String PUTANJA_ARTIKLI = "dat/artikli.txt";
	public static final String PUTANJA_KLIJENTI = "dat/klijenti.txt";
	public static final String PUTANJA_TVRTKA = "dat/tvrtka.txt";
	public static final String PUTANJA_ZAPOSLENICI = "dat/zaposlenici.txt";
	public static final String PUTANJA_KOMUNIKACIJE = "dat/komunikacije.txt";
	public static final String PUTANJA_USLUGE = "dat/usluge.txt";
	public static final String PUTANJA_ALARMI = "dat/alarmi.txt";
	public static final String SERIJALIZACIJA_KOMUNIKACIJE = "dat/komunikacije.dat";
	public static final String SERIJALIZACIJA_USLUGE = "dat/usluge.dat";
	public static final String SERIJALIZACIJA_ALARMI = "dat/alarmi.dat";
	public static final String SERIJALIZACIJA_SLOVENACA = "dat/slovenci.dat";
	public static final String PUTANJA_IMENA = "dat/imena.txt";

	private static final Logger logger = LoggerFactory.getLogger(GlavnaDatoteke.class);

	public static void main(String[] args) {

		Scanner unos = new Scanner(System.in);
		
		Boolean imenaUcitana = false;
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(PUTANJA_IMENA).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
			imenaUcitana = true;
		} catch (IOException e) {
			logger.error("Došlo je do pogreške u èitanju datoteke!", e);
		}
		
		if(imenaUcitana){
			System.out.println("Suma duljine: " + listaStringova.get(0));
			System.out.println("Najkraæe ime: " + listaStringova.get(1));
			System.out.println("Najduže ime: " + listaStringova.get(2));
		}
		

		Boolean ucitaniSlovenci = false;

		List<Klijent> deserijaliziraniSlovenci = new ArrayList<>();
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SERIJALIZACIJA_SLOVENACA))) {
			while (true) {
				@SuppressWarnings("unchecked")
				List<Klijent> klijenti = (ArrayList<Klijent>) in.readObject();
				deserijaliziraniSlovenci.addAll(klijenti);
				ucitaniSlovenci = true;
			}
		} catch (IOException e) {
			logger.error("Došlo je do greške u radu s datotekom!", e);
		} catch (ClassNotFoundException ex) {
			logger.error("Greška", ex);
		}

		if (ucitaniSlovenci) {
			System.out.println("SLOVENCI:");
			for (Klijent klijent : deserijaliziraniSlovenci) {
				System.out.println(klijent.getIme() + " " + klijent.getPrezime());
			}
		}

		System.out.println("Uèitavanje klijenata...");
		List<Klijent> listaKlijenata = ucitajKlijente(PUTANJA_KLIJENTI);
		logger.info("Uèitani klijenti");
		System.out.println("Uèitavanje zaposlenika...");
		List<Zaposlenik> listaZaposlenika = ucitajZaposlenike(PUTANJA_ZAPOSLENICI);
		logger.info("Uèitani zaposlenici");
		System.out.println("Uèitavanje maloprodajne tvrtke...");
		MaloprodajnaTvrtka tvrtka = ucitajTvrtku(PUTANJA_TVRTKA);
		logger.info("Uèitana tvrtka");

		tvrtka.setZaposlenici(listaZaposlenika);
		logger.info("Postavljeni zaposlenici tvrtke");
		tvrtka.setKlijenti(listaKlijenata);
		logger.info("Postavljeni klijenti tvrtke");

		int najkraci = listaKlijenata.stream().mapToInt(k->k.getIme().length()).min().orElse(-1);
		int najduzi = listaKlijenata.stream().mapToInt(k->k.getIme().length()).max().orElse(-1);
		
		List<Klijent> najKlijenti = listaKlijenata.stream()
				.filter(s -> s.getIme().length() == najkraci || s.getIme().length() == najduzi)
				.collect(Collectors.toList());
		najKlijenti.sort((k1,k2)->{
			if(k1.getIme().length()>k2.getIme().length())
				return 1;
			else return -1;
		});
		
		String newline = System.getProperty("line.separator");
		
		File imena = new File(PUTANJA_IMENA);
		try {
			imena.createNewFile();
			Formatter output = new Formatter(PUTANJA_IMENA);
			
			output.format("%d" + newline, najkraci + najduzi);

			for (Klijent klijent : najKlijenti) {
				output.format("%s",klijent.getIme());

				if (!imena.equals(najKlijenti.get(najKlijenti.size() - 1))) {
					output.format(newline);
				}
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Došlo je do pogreške u radu s datotekom!", e);
		}
		
		List<Klijent> slovenskiKlijenti = new ArrayList<>();

		slovenskiKlijenti = listaKlijenata.stream().filter(k -> k.getBrojTelefona().startsWith("+386"))
				.collect(Collectors.toList());

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SERIJALIZACIJA_SLOVENACA))) {
			out.writeObject(slovenskiKlijenti);
		} catch (IOException ex) {
			logger.error("Došlo je do pogreške u radu s datotekom!", ex);
		}

		int brUsluga = Glavna.unesiBrojUsluga(unos);
		List<ProdajaArtikla> prodajeArtikala = new ArrayList<ProdajaArtikla>();
		List<Alarm> alarmi = new ArrayList<Alarm>();
		List<Usluga> usluge = new ArrayList<>();

		for (int i = 0; i < brUsluga; i++) {

			System.out.printf("UNESITE %d. USLUGU:\n", i + 1);
			logger.info("Unos " + (i + 1) + ". usluge");
			System.out.println("ODABERITE REDNI BROJ KLIJENTA:");

			Glavna.ispisiKlijente(tvrtka);
			Boolean ponoviUnos = true;
			int odabraniKlijent = 0;

			do {
				System.out.println("ODABIR:");
				try {
					odabraniKlijent = unos.nextInt() - 1;

					if (odabraniKlijent < 0 || odabraniKlijent >= tvrtka.getKlijenti().size()) {
						System.out.println("Morate unijeti broj izmeðu 1 i " + tvrtka.getKlijenti().size());
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

			Usluga usluga = Glavna.unesiUslugu(tvrtka.getKlijenti().get(odabraniKlijent), unos);
			usluge.add(usluga);

			System.out.println("ODABIR ARTIKLA:");
			Glavna.ispisiArtikle(tvrtka);

			ponoviUnos = true;
			int odabraniArtikl = 0;

			do {
				System.out.println("ODABIR:");
				try {
					odabraniArtikl = unos.nextInt() - 1;

					if (odabraniArtikl < 0 || odabraniArtikl >= tvrtka.getArtikli().size()) {
						System.out.println("Morate unijeti broj izmeðu 1 i " + tvrtka.getArtikli().size());
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
		}

		BigDecimal ukupnaCijena = prodajeArtikala.stream()
				.map(p -> p.getCijena().multiply(new BigDecimal(p.getBrojArtikala())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		tvrtka.setAlarmi(alarmi);
		logger.info("Postavljeni alarmi za cijelu tvrtku");
		tvrtka.setUsluge(usluge);
		logger.info("Postavljene usluge za cijelu tvrtku");

		System.out.println("Ukupna cijena svih prodanih proizvoda je " + ukupnaCijena + " kn");
		logger.info("Ukupna cijena svih prodanih proizvoda: " + ukupnaCijena + " kn");

		Glavna.unesiKomunikacije(tvrtka, unos);

		System.out.println("Ispis svih komunikacija:");
		Glavna.ispisiKomunikacije(tvrtka);
		System.out.println();

		long pocetakSortiranjaBezLambdi = System.currentTimeMillis();
		Collections.sort(tvrtka.getKomunikacija(), new KomunikacijaSorter());
		long krajSortiranjaBezLambdi = System.currentTimeMillis();

		System.out.println("Ispis sortiranih komunikacija:");
		Glavna.ispisiKomunikacije(tvrtka);

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

		File komunikacije = new File(PUTANJA_KOMUNIKACIJE);
		try {
			komunikacije.createNewFile();
			Formatter output = new Formatter(PUTANJA_KOMUNIKACIJE);

			for (Komunikacija komunikacija : tvrtka.getKomunikacija()) {
				output.format("%s %s" + newline + "%s %s" + newline + "%s" + newline + "%s" + newline + "%s",
						komunikacija.getZaposlenik().getIme(), komunikacija.getZaposlenik().getPrezime(),
						komunikacija.getKlijent().getIme(), komunikacija.getKlijent().getPrezime(),
						komunikacija.getVrsta(), komunikacija.getSadrzaj(),
						komunikacija.getVrijeme().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")));

				if (!komunikacija.equals(tvrtka.getKomunikacija().get(tvrtka.getKomunikacija().size() - 1))) {
					output.format(newline);
				}
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Došlo je do pogreške u radu s datotekom!", e);
		}

		File uslugeFile = new File(PUTANJA_USLUGE);
		try {
			uslugeFile.createNewFile();
			Formatter output = new Formatter(PUTANJA_USLUGE);

			for (Usluga usluga : tvrtka.getUsluge()) {
				output.format("%s %s" + newline + "%s" + newline + "%s" + newline + "%s" + newline + "%s",
						usluga.getKlijent().getIme(), usluga.getKlijent().getPrezime(), usluga.getVrsta(),
						usluga.getOpis(), usluga.getDatum().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")),
						usluga.getCijena());

				if (!usluga.equals(tvrtka.getUsluge().get(tvrtka.getUsluge().size() - 1))) {
					output.format(newline);
				}
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Došlo je do pogreške u radu s datotekom!", e);
		}

		File alarmiFile = new File(PUTANJA_ALARMI);
		try {
			alarmiFile.createNewFile();
			Formatter output = new Formatter(PUTANJA_ALARMI);

			for (Alarm alarm : tvrtka.getAlarmi()) {
				output.format("%s %s" + newline + "%s" + newline + "%s" + newline + "%s", alarm.getKlijent().getIme(),
						alarm.getKlijent().getPrezime(), alarm.getOpis(),
						alarm.getVrijeme().format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")),
						alarm.getStatus().toString());

				if (!alarm.equals(tvrtka.getAlarmi().get(tvrtka.getAlarmi().size() - 1))) {
					output.format(newline);
				}
			}
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Došlo je do pogreške u radu s datotekom!", e);
		}

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SERIJALIZACIJA_KOMUNIKACIJE))) {
			out.writeObject(tvrtka.getKomunikacija());
		} catch (IOException ex) {
			logger.error("Došlo je do pogreške u radu s datotekom!", ex);
		}

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SERIJALIZACIJA_USLUGE))) {
			out.writeObject(tvrtka.getUsluge());
		} catch (IOException ex) {
			logger.error("Došlo je do pogreške u radu s datotekom!", ex);
		}

		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SERIJALIZACIJA_ALARMI))) {
			out.writeObject(tvrtka.getAlarmi());
		} catch (IOException ex) {
			logger.error("Došlo je do pogreške u radu s datotekom!", ex);
		}
	}

	private static List<Artikl> ucitajArtikle(String path) {
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(path).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Došlo je do pogreške u èitanju datoteke!", e);
		}

		List<Artikl> listaArtikala = new ArrayList<>();
		for (int i = 0; i < listaStringova.size(); i += 2) {
			String naziv = listaStringova.get(i);
			KategorijaArtikla kategorija = KategorijaArtikla.valueOf(listaStringova.get(i + 1));
			Artikl artikl = new Artikl(naziv, kategorija);
			listaArtikala.add(artikl);
		}
		return listaArtikala;
	}

	private static List<Klijent> ucitajKlijente(String path) {
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(path).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Došlo je do pogreške u èitanju datoteke!", e);
		}

		List<Klijent> listaKlijenata = new ArrayList<>();
		for (int i = 0; i < listaStringova.size(); i += 6) {
			String oib = listaStringova.get(i);
			String prezime = listaStringova.get(i + 1);
			String ime = listaStringova.get(i + 2);
			String brTelefona = listaStringova.get(i + 3);
			String email = listaStringova.get(i + 4);
			String datumRodjenjaString = listaStringova.get(i + 5);

			LocalDate datumRodjenja = null;
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			datumRodjenja = LocalDate.parse(datumRodjenjaString, format);

			Klijent klijent = new Klijent(ime, prezime, oib, brTelefona, email, datumRodjenja);
			listaKlijenata.add(klijent);
		}
		return listaKlijenata;
	}

	private static List<Zaposlenik> ucitajZaposlenike(String path) {
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(path).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Došlo je do pogreške u èitanju datoteke!", e);
		}

		List<Zaposlenik> listaZaposlenika = new ArrayList<>();
		for (int i = 0; i < listaStringova.size(); i += 4) {
			String korisnickoIme = listaStringova.get(i);
			String prezime = listaStringova.get(i + 1);
			String ime = listaStringova.get(i + 2);
			String sifra = listaStringova.get(i + 3);

			Zaposlenik zaposlenik = new Zaposlenik(ime, prezime, korisnickoIme, sifra);
			listaZaposlenika.add(zaposlenik);
		}
		return listaZaposlenika;
	}

	private static MaloprodajnaTvrtka ucitajTvrtku(String path) {
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(path).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Došlo je do pogreške u èitanju datoteke!", e);
		}

		String naziv = listaStringova.get(0);
		String oib = listaStringova.get(1);
		System.out.println("Uèitavanje artikala...");
		List<Artikl> listaArtikala = ucitajArtikle(PUTANJA_ARTIKLI);

		return new MaloprodajnaTvrtka(naziv, oib, listaArtikala);
	}

	public static int unesiBrojUsluga(Scanner unos) {
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
		return brUsluga;
	}
}
