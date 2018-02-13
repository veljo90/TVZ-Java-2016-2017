package hr.java.vjezbe.javafx;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hr.java.vjezbe.entitet.Alarm;
import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.KategorijaArtikla;
import hr.java.vjezbe.entitet.Klijent;
import hr.java.vjezbe.entitet.MaloprodajnaTvrtka;
import hr.java.vjezbe.entitet.Zaposlenik;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static final String PUTANJA_KLIJENTI = "dat/klijenti.txt";
	public static final String PUTANJA_ZAPOSLENICI = "dat/zaposlenici.txt";
	public static final String PUTANJA_TVRTKA = "dat/tvrtka.txt";
	public static final String PUTANJA_ARTIKLI = "dat/artikli.txt";
	public static final String PUTANJA_ALARMI = "dat/alarmi.txt";

	private static BorderPane root;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			root = (BorderPane) FXMLLoader.load(getClass().getResource("PocetniEkran.fxml"));
			root.setMinWidth(600);
			root.setMinHeight(400);
			Scene scene = new Scene(root, 800, 600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setMinWidth(600);
			primaryStage.setMinHeight(400);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setCenterPane(BorderPane centerPane){
		root.setCenter(centerPane);
	}

	public static void main(String[] args) {
		launch(args);
	}

	static List<Klijent> ucitajKlijente(String path) {
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(path).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error("Došlo je do pogreške u èitanju datoteke!", e);
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

	static List<Zaposlenik> ucitajZaposlenike(String path) {
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(path).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error("Došlo je do pogreške u èitanju datoteke!", e);
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

	static MaloprodajnaTvrtka ucitajTvrtku(String path) {
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(path).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error("Došlo je do pogreške u èitanju datoteke!", e);
		}

		String naziv = listaStringova.get(0);
		String oib = listaStringova.get(1);
		//System.out.println("Uèitavanje artikala...");
		List<Artikl> listaArtikala = ucitajArtikle(PUTANJA_ARTIKLI);

		return new MaloprodajnaTvrtka(naziv, oib, listaArtikala);
	}

	static List<Artikl> ucitajArtikle(String path) {
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(path).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error("Došlo je do pogreške u èitanju datoteke!", e);
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

	static List<Alarm> ucitajAlarme(String path) {
		List<String> listaStringova = new ArrayList<>();
		try (Stream<String> stream = Files.lines(new File(path).toPath(), Charset.forName("Cp1250"))) {
			listaStringova = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
			// logger.error("Došlo je do pogreške u èitanju datoteke!", e);
		}

		List<Alarm> listaAlarma = new ArrayList<>();
		for (int i = 0; i < listaStringova.size(); i += 9) {

			String oib = listaStringova.get(i);
			String prezime = listaStringova.get(i + 1);
			String ime = listaStringova.get(i + 2);
			String brTelefona = listaStringova.get(i + 3);
			String email = listaStringova.get(i + 4);
			String datumRodjenjaString = listaStringova.get(i + 5);

			LocalDate datumRodjenja = LocalDate.parse(datumRodjenjaString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
			Klijent klijent = new Klijent(ime, prezime, oib, brTelefona, email, datumRodjenja);

			String opis = listaStringova.get(i + 6);
			String vrijemeString = listaStringova.get(i + 7);
			String statusString = listaStringova.get(i + 8);

			LocalDateTime vrijeme = LocalDateTime.parse(vrijemeString,
					DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss"));
			Boolean status = null;
			if (statusString.equals("true")) {
				status = true;
			} else {
				status = false;
			}
			Alarm alarm = new Alarm(klijent, opis, vrijeme, status);

			listaAlarma.add(alarm);
		}
		return listaAlarma;
	}
}
