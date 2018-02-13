package hr.java.vjezbe.javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

public class PocetniEkranController {

	@FXML
	private MenuItem dohvatiKlijenteMenuItem;

	@FXML
	private MenuItem dohvatiZaposlenikeMenuItem;

	@FXML
	private MenuItem dohvatiArtikleMenuItem;

	@FXML
	private MenuItem dohvatiAlarmeMenuItem;

	@FXML
	public void initialize() {
	}

	public void prikaziZaposlenike() {
		try {
			BorderPane centerPane = (BorderPane) FXMLLoader.load(getClass().getResource("Zaposlenici.fxml"));
			Main.setCenterPane(centerPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prikaziAlarme() {
		try {
			BorderPane centerPane = (BorderPane) FXMLLoader.load(getClass().getResource("Alarmi.fxml"));
			Main.setCenterPane(centerPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void prikaziKlijente() {
		try {
			BorderPane centerPane = (BorderPane) FXMLLoader.load(getClass().getResource("Klijenti.fxml"));
			Main.setCenterPane(centerPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void prikaziArtikle() {
		try {
			BorderPane centerPane = (BorderPane) FXMLLoader.load(getClass().getResource("Artikli.fxml"));
			Main.setCenterPane(centerPane);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
