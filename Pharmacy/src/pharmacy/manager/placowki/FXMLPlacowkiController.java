/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.manager.placowki;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import pharmacy.connection.DBConnection;
import pharmacy.manager.pracownicy.PracownicyClassa;

/**
 * FXML Controller class
 *
 * @author Yser
 */
public class FXMLPlacowkiController implements Initializable {

    @FXML
    private TableView<PlacowkiClassa> Tabela_Placowki;
    @FXML
    private MenuItem Menu_Szczegoly;
    @FXML
    private TableColumn<PlacowkiClassa, String> Kolumna_Lp;
    @FXML
    private TableColumn<PlacowkiClassa, String> Kolumna_kod_pocztowy;
    @FXML
    private TableColumn<PlacowkiClassa, String> Kolumna_Miejscowosc;
    @FXML
    private TableColumn<PlacowkiClassa, String> Kolumna_Adres;
    @FXML
    private TableColumn<PlacowkiClassa, String> Kolumna_Telefon;
    @FXML
    private JFXTextField TextField_Placowki;
    @FXML
    private JFXButton Button_Wyjscie;
    private ObservableList<PlacowkiClassa> placowki_lista;
    DBConnection dc;
    public static int id_placowki;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Button_Wyjscie.setFocusTraversable(false);
        TextField_Placowki.setFocusTraversable(false);
        dc = new DBConnection();
        filter_tabela();
    }

    @FXML
    private void goSzczegoly(ActionEvent event) throws IOException {
        if (Tabela_Placowki.getSelectionModel().getSelectedItem() != null) {
            id_placowki = Tabela_Placowki.getSelectionModel().getSelectedItem().getId_placowki();
            Stage stage;
            Parent root;

            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("FXMLSzczegoly.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Panel dodawania klienta");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(Button_Wyjscie.getScene().getWindow());
            stage.showAndWait();
        }
    }

    @FXML
    private void wyjscie(ActionEvent event) {
        Stage stage = (Stage) Button_Wyjscie.getScene().getWindow();
        stage.close();
    }

    public void filter_tabela() {
        Kolumna_Lp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlacowkiClassa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PlacowkiClassa, String> p) {
                return new ReadOnlyObjectWrapper(Tabela_Placowki.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        Kolumna_Lp.setSortable(false);
        try {
            java.sql.Connection con = dc.Connect();
            placowki_lista = FXCollections.observableArrayList();
            ResultSet res = con.createStatement().executeQuery("Select kod_pocztowy_placowki, miejscowosc_placowki,adres_placowki,telefon_placowki,id_placowki from placowka");
            while (res.next()) {
                placowki_lista.add(new PlacowkiClassa(res.getString(1), res.getString(2), res.getString(3), res.getString(4), res.getInt(5)));
                System.out.println(res.getString(2));
            }
            Kolumna_kod_pocztowy.setCellValueFactory(new PropertyValueFactory<>("kod_pocztowy_placowki"));
            Kolumna_Miejscowosc.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));
            Kolumna_Adres.setCellValueFactory(new PropertyValueFactory<>("adres"));
            Kolumna_Telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
            Tabela_Placowki.setItems(null);
            Tabela_Placowki.setItems(placowki_lista);
            con.close();
        } catch (SQLException ex) {        }

        FilteredList<PlacowkiClassa> filteredKlient = new FilteredList<>(Tabela_Placowki.getItems(), e -> true);
        TextField_Placowki.setOnKeyReleased(e -> {
            TextField_Placowki.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredKlient.setPredicate((Predicate<? super PlacowkiClassa>) k -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getMiejscowosc().toLowerCase().contains(lcFilter) || k.getTelefon().toLowerCase().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<PlacowkiClassa> sortedKlient = new SortedList<>(filteredKlient);
            Tabela_Placowki.setItems(sortedKlient);
        });
    }
}
