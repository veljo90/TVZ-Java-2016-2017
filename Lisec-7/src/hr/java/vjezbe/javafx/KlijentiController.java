package hr.java.vjezbe.javafx;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Klijent;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class KlijentiController {

	private List<Klijent> listaKlijenata;

	@FXML
	private TextField klijentiFilterTextField;

	@FXML
	private TableView<Klijent> klijentiTableView;

	@FXML
	private TableColumn<Klijent, String> prezimeColumn;

	@FXML
	private TableColumn<Klijent, String> imeColumn;

	@FXML
	private TableColumn<Klijent, String> oibColumn;

	@FXML
	private TableColumn<Klijent, String> brojTelefonaColumn;

	@FXML
	private TableColumn<Klijent, String> eMailColumn;

	@FXML
	private TableColumn<Klijent, String> datumRodenjaColumn;

	@FXML
	public void initialize() {
		imeColumn.setCellValueFactory(new PropertyValueFactory<Klijent, String>("ime"));
		prezimeColumn.setCellValueFactory(new PropertyValueFactory<Klijent, String>("prezime"));
		oibColumn.setCellValueFactory(new PropertyValueFactory<Klijent, String>("oib"));
		brojTelefonaColumn.setCellValueFactory(new PropertyValueFactory<Klijent, String>("brojTelefona"));
		eMailColumn.setCellValueFactory(new PropertyValueFactory<Klijent, String>("email"));
		datumRodenjaColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Klijent, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Klijent, String> param) {
						return new ReadOnlyObjectWrapper<String>(
								param.getValue().getDatumRodjenja().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));
					}
				});

		listaKlijenata = Main.ucitajKlijente(Main.PUTANJA_KLIJENTI);
		ispuniTablicu();
	}

	public void ispuniTablicu() {
		List<Klijent> filtriraniKlijenti = new ArrayList<>();
		if (klijentiFilterTextField.getText().isEmpty() == false) {
			filtriraniKlijenti = listaKlijenata.stream()
					.filter(p -> p.getPrezime().toLowerCase().contains(klijentiFilterTextField.getText().toLowerCase()))
					.collect(Collectors.toList());
		} else {
			filtriraniKlijenti = listaKlijenata;
		}
		ObservableList<Klijent> listaKlijenataObservable = FXCollections.observableArrayList(filtriraniKlijenti);
		klijentiTableView.setItems(listaKlijenataObservable);
	}
}
