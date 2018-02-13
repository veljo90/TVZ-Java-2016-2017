package hr.java.vjezbe.javafx;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Zaposlenik;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ZaposleniciController {

	private List<Zaposlenik> listaZaposlenika;

	@FXML
	private TextField zaposleniciFilterTextField;

	@FXML
	private TableView<Zaposlenik> zaposleniciTableView;

	@FXML
	private TableColumn<Zaposlenik, String> korisnickoImeColumn;

	@FXML
	private TableColumn<Zaposlenik, String> prezimeColumn;

	@FXML
	private TableColumn<Zaposlenik, String> imeColumn;

	@FXML
	private TableColumn<Zaposlenik, String> sifraColumn;

	@FXML
	public void initialize() {
		korisnickoImeColumn.setCellValueFactory(new PropertyValueFactory<Zaposlenik, String>("korisnickoIme"));
		prezimeColumn.setCellValueFactory(new PropertyValueFactory<Zaposlenik, String>("prezime"));
		imeColumn.setCellValueFactory(new PropertyValueFactory<Zaposlenik, String>("ime"));
		sifraColumn.setCellValueFactory(new PropertyValueFactory<Zaposlenik, String>("sifra"));

		listaZaposlenika = Main.ucitajZaposlenike(Main.PUTANJA_ZAPOSLENICI);
		ispuniTablicu();
	}

	public void ispuniTablicu() {
		List<Zaposlenik> filtriraniZaposlenici = new ArrayList<>();
		if (zaposleniciFilterTextField.getText().isEmpty() == false) {
			filtriraniZaposlenici = listaZaposlenika.stream()
					.filter(p -> p.getPrezime().toLowerCase().contains(zaposleniciFilterTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		} else {
			filtriraniZaposlenici = listaZaposlenika;
		}
		ObservableList<Zaposlenik> listaZaposlenikaObservable = FXCollections
				.observableArrayList(filtriraniZaposlenici);
		zaposleniciTableView.setItems(listaZaposlenikaObservable);
	}
}
