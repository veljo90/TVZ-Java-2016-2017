package hr.java.vjezbe.javafx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Artikl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ArtikliController {

	private List<Artikl> listaArtikala;

	@FXML
	private TextField artikliNazivFilterTextField;

	@FXML
	private TextField artikliKategorijaFilterTextField;

	@FXML
	private TableView<Artikl> artikliTableView;

	@FXML
	private TableColumn<Artikl, String> nazivColumn;

	@FXML
	private TableColumn<Artikl, String> kategorijaColumn;

	@FXML
	public void initialize() {
		nazivColumn.setCellValueFactory(new PropertyValueFactory<Artikl, String>("naziv"));
		kategorijaColumn.setCellValueFactory(new PropertyValueFactory<Artikl, String>("kategorija"));

		listaArtikala = Main.ucitajArtikle(Main.PUTANJA_ARTIKLI);
		ispuniTablicu();
	}

	public void ispuniTablicu() {
		List<Artikl> filtriraniArtikli = new ArrayList<>();
		if (artikliNazivFilterTextField.getText().isEmpty() == false) {
			filtriraniArtikli = listaArtikala.stream().filter(
					p -> p.getNaziv().toLowerCase().contains(artikliNazivFilterTextField.getText().toLowerCase()))
					.collect(Collectors.toList());

			if (artikliKategorijaFilterTextField.getText().isEmpty() == false) {
				filtriraniArtikli = filtriraniArtikli.stream()
						.filter(p -> p.getKategorija().name().toLowerCase()
								.contains(artikliKategorijaFilterTextField.getText().toLowerCase()))
						.collect(Collectors.toList());
			}
		} else {
			filtriraniArtikli = listaArtikala;
			if (artikliKategorijaFilterTextField.getText().isEmpty() == false) {
				filtriraniArtikli = filtriraniArtikli.stream()
						.filter(p -> p.getKategorija().name().toLowerCase()
								.contains(artikliKategorijaFilterTextField.getText().toLowerCase()))
						.collect(Collectors.toList());
			}
		}
		ObservableList<Artikl> listaArtikalaObservable = FXCollections.observableArrayList(filtriraniArtikli);
		artikliTableView.setItems(listaArtikalaObservable);
	}
	
	public void prikaziDetalje(){
		Stage dialog = new Stage();
		dialog.initStyle(StageStyle.UTILITY);
		Scene scene = new Scene(new Group(), 300, 300);
		dialog.setScene(scene);
		dialog.show();
	}
}
