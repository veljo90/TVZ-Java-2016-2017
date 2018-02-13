package hr.java.vjezbe.javafx;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.entitet.Alarm;
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

public class AlarmiController {

	private List<Alarm> listaAlarma;

	@FXML
	private TextField alarmiFilterTextField;

	@FXML
	private TableView<Alarm> alarmiTableView;

	@FXML
	private TableColumn<Klijent, String> klijentColumn;

	@FXML
	private TableColumn<Alarm, String> opisColumn;

	@FXML
	private TableColumn<Alarm, String> vrijemeColumn;

	@FXML
	public void initialize() {
		klijentColumn.setCellValueFactory(new PropertyValueFactory<Klijent, String>("klijent"));
		opisColumn.setCellValueFactory(new PropertyValueFactory<Alarm, String>("opis"));
		vrijemeColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Alarm, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Alarm, String> param) {
						return new ReadOnlyObjectWrapper<String>(param.getValue().getVrijeme()
								.format(DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm:ss")));
					}
				});

		listaAlarma = Main.ucitajAlarme(Main.PUTANJA_ALARMI);
		prikaziAlarme();
		alarmiTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	public void prikaziAlarme() {
		List<Alarm> filtriraniAlarmi = new ArrayList<Alarm>();
		if (alarmiFilterTextField.getText().isEmpty() == false) {
			filtriraniAlarmi = listaAlarma.stream()
					.filter(p -> p.getKlijent().getOib().contains(alarmiFilterTextField.getText()))
					.collect(Collectors.toList());
		} else {
			filtriraniAlarmi = listaAlarma;
		}
		ObservableList<Alarm> listaAlarma = FXCollections.observableArrayList(filtriraniAlarmi);
		alarmiTableView.setItems(listaAlarma);
	}
}
