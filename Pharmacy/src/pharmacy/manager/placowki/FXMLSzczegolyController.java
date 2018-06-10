/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.manager.placowki;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import pharmacy.pharmacy;
import pharmacy.connection.DBConnection;
import pharmacy.manager.klasy.Alert;
import pharmacy.manager.klasy.Ladowanie_danych;

/**
 * FXML Controller class
 *
 * @author Yser
 */
public class FXMLSzczegolyController implements Initializable {

    @FXML
    private JFXButton Button_Pracownicy;
    @FXML
    private JFXButton Button_Produkty;
    @FXML
    private JFXButton Button_Sprzedaz;
    @FXML
    private JFXButton Button_Wstecz;
    @FXML
    private Pane Pane_Glowna;
    @FXML
    private Pane Pane_Pracownicy;
    @FXML
    private Pane Pane_Produkty;
    @FXML
    private Pane Pane_Sprzedaz;
    private JFXComboBox Combo_pracownik;
    private JFXTextField Text_Stanowisko;
    private JFXTextField Text_Imie;
    private JFXTextField Text_Nazwisko;
    private JFXTextField Text_Telefon;
    private JFXTextField Text_Pesel;
    private JFXButton Button_Statystyki;
    @FXML
    private TableView<PlacowkiClassa> Tabela_Produkty;
    @FXML
    private TableColumn<PlacowkiClassa, String> Tab_Lp;
    @FXML
    private TableColumn<PlacowkiClassa, String> Tab_NazwaProdukty;
    @FXML
    private TableColumn<PlacowkiClassa, String> Tab_CenaProduktu;
    @FXML
    private TableColumn<PlacowkiClassa, String> Tab_OpisProduktu;
    @FXML
    private TableColumn<PlacowkiClassa, String> Tab_Dostepnosc;
    @FXML
    private JFXDatePicker Data_Od;
    @FXML
    private JFXDatePicker Data_Do;
    @FXML
    private JFXButton Button_Szukaj;
    DBConnection dc;
    int id_placowki = FXMLPlacowkiController.id_placowki;
    private JFXTextField tf_info;
    public static int id_pracownika;
    private ObservableList<PlacowkiClassa> produkty_lista;
    private ObservableList<PlacowkiClassa> pracownicy_lista;
    @FXML
    private BarChart<String, Double> Statssprzedazy;
    @FXML
    private TableView<PlacowkiClassa> Tabela_pracownicy;
    @FXML
    private TableColumn<PlacowkiClassa, String> koluma_lp;
    @FXML
    private TableColumn<PlacowkiClassa, String> kolumna_stanowisko;
    @FXML
    private TableColumn<PlacowkiClassa, String> kolumna_imie;
    @FXML
    private TableColumn<PlacowkiClassa, String> kolumna_nazwisko;
    @FXML
    private TableColumn<PlacowkiClassa, String> kolumna_telefon;
    @FXML
    private JFXTextField szukajpracownikow;
    @FXML
    private JFXTextField szukajproduktu;
    @FXML
    private Label info_placowka;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Pane_Glowna.setVisible(true);
        Pane_Pracownicy.setVisible(true);
        Pane_Produkty.setVisible(false);
        Pane_Sprzedaz.setVisible(false);

        dc = new DBConnection();
        java.sql.Connection con = dc.Connect();
        loadinfoplacowka();
        filterpracownicy();

    }

    @FXML
    private void goPracownicy(ActionEvent event) {
        Pane_Pracownicy.setVisible(true);
        Pane_Produkty.setVisible(false);
        Pane_Sprzedaz.setVisible(false);
        filterpracownicy();
    }

    @FXML
    private void goProdukty(ActionEvent event) {

        Pane_Pracownicy.setVisible(false);
        Pane_Produkty.setVisible(true);
        Pane_Sprzedaz.setVisible(false);
        filterprodukty();
    }

    @FXML
    private void goSprzedaz(ActionEvent event) {

        Pane_Pracownicy.setVisible(false);
        Pane_Produkty.setVisible(false);
        Pane_Sprzedaz.setVisible(true);
        String query = "Select l.id_placowki, t.data, sum(t.calkowity_koszt)as Wynik_Sprzedazy From pracownik p, placowka l , transakcja t Where l.id_placowki=" + id_placowki + " And p.id_pracownika=t.id_pracownika and p.id_placowki=l.id_placowki group by t.data";
        wykres_metoda(query, Statssprzedazy);
    }

    @FXML
    private void wstecz(ActionEvent event) {
        Stage stage = (Stage) Button_Wstecz.getScene().getWindow();
        stage.close();
    }

    private void goStatystykiPracownika(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(pharmacy.class.getResource("manager/pracownicy/FXMLSzczegoly.fxml"));//odnieść się do pakietu pracownicy do pliku FXMLSZCZEGOLY 
        stage.setScene(new Scene(root));
        stage.setTitle("Panel Pracownikow");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Statystyki.getScene().getWindow());
        stage.showAndWait();

    }

    @FXML
    private void goStatystkiProduktu(ActionEvent event) {
        String query;
        try {
            String dataod = Data_Od.getValue().toString();
            String datado = Data_Do.getValue().toString();
            query = "Select l.id_placowki, t.data, sum(t.calkowity_koszt)as Wynik_Sprzedazy From pracownik p, placowka l , transakcja t Where l.id_placowki=" + id_placowki + " And p.id_pracownika=t.id_pracownika and p.id_placowki=l.id_placowki and t.data between'" + dataod + "' and'" + datado + "' group by t.data";
        wykres_metoda(query, Statssprzedazy);
        } catch (NullPointerException e) {
          
        }
        
    }

    public void loadinfoplacowka() {
        try {
            java.sql.Connection con = dc.Connect();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("select concat(`kod_pocztowy_placowki`,\" \",`miejscowosc_placowki`,\" \",`adres_placowki`,\", Telefon: \",`telefon_placowki`)as adres from placowka"
                    + " where placowka.id_placowki=" + id_placowki);

            while (rs.next()) {
                info_placowka.setText(rs.getString(1));
            }
            ps.close();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLPlacowkiController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void wykres_metoda(String query, BarChart Wykres) {
        Wykres.getData().removeAll(Collections.singleton(Wykres.getData().setAll()));
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try {
            java.sql.Connection conn = dc.Connect();
            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data(rs.getString(2), rs.getDouble(3)));
            }
            Wykres.getData().add(series);

        } catch (Exception e) {
            System.out.println("Nie udało się pobrać danych" + e);

        }
    }

    public void filterpracownicy() {
        koluma_lp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlacowkiClassa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PlacowkiClassa, String> p) {
                return new ReadOnlyObjectWrapper(Tabela_pracownicy.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        koluma_lp.setSortable(false);
        try {
            java.sql.Connection con = dc.Connect();
            pracownicy_lista = FXCollections.observableArrayList();
            ResultSet res = con.createStatement().executeQuery("select imie_pracownika,nazwisko_pracownika,rola,telefon_pracownika from pracownik where id_placowki=" + id_placowki);
            while (res.next()) {
                pracownicy_lista.add(new PlacowkiClassa(res.getString(1), res.getString(2), res.getString(3), res.getString(4)));

            }
            kolumna_imie.setCellValueFactory(new PropertyValueFactory<>("kod_pocztowy_placowki"));
            kolumna_nazwisko.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));
            kolumna_stanowisko.setCellValueFactory(new PropertyValueFactory<>("adres"));
            kolumna_telefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
            Tabela_pracownicy.setItems(null);
            Tabela_pracownicy.setItems(pracownicy_lista);
            con.close();
        } catch (SQLException ex) {

        }

        FilteredList<PlacowkiClassa> filteredKlient = new FilteredList<>(Tabela_pracownicy.getItems(), e -> true);
        szukajpracownikow.setOnKeyReleased(e -> {
            szukajpracownikow.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredKlient.setPredicate((Predicate<? super PlacowkiClassa>) k -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getMiejscowosc().toLowerCase().contains(lcFilter) || k.getAdres().toLowerCase().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<PlacowkiClassa> sortedKlient = new SortedList<>(filteredKlient);
            Tabela_pracownicy.setItems(sortedKlient);
        });
    }

    public void filterprodukty() {
        Tab_Lp.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<PlacowkiClassa, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<PlacowkiClassa, String> p) {
                return new ReadOnlyObjectWrapper(Tabela_Produkty.getItems().indexOf(p.getValue()) + 1 + "");
            }
        });
        Tab_Lp.setSortable(false);
        try {
            java.sql.Connection con = dc.Connect();
            produkty_lista = FXCollections.observableArrayList();
            ResultSet res = con.createStatement().executeQuery("select p.nazwa_produktu, p.opis_produktu, p.cena_produktu, r.ilosc_produktow from produkty p, placowka_produkt r where p.id_produktu=r.id_produktu and r.id_placowki=" + id_placowki);
            while (res.next()) {
                produkty_lista.add(new PlacowkiClassa(res.getString(1), res.getString(2), res.getString(3), res.getString(4)));

            }
            Tab_NazwaProdukty.setCellValueFactory(new PropertyValueFactory<>("kod_pocztowy_placowki"));
            Tab_OpisProduktu.setCellValueFactory(new PropertyValueFactory<>("miejscowosc"));
            Tab_CenaProduktu.setCellValueFactory(new PropertyValueFactory<>("adres"));
            Tab_Dostepnosc.setCellValueFactory(new PropertyValueFactory<>("telefon"));
            Tabela_Produkty.setItems(null);
            Tabela_Produkty.setItems(produkty_lista);
            con.close();
        } catch (SQLException ex) {

        }

        FilteredList<PlacowkiClassa> filteredKlient = new FilteredList<>(Tabela_Produkty.getItems(), e -> true);
        szukajproduktu.setOnKeyReleased(e -> {
            szukajproduktu.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredKlient.setPredicate((Predicate<? super PlacowkiClassa>) k -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getMiejscowosc().toLowerCase().contains(lcFilter) || k.getKod_pocztowy_placowki().toLowerCase().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<PlacowkiClassa> sortedKlient = new SortedList<>(filteredKlient);
            Tabela_Produkty.setItems(sortedKlient);
        });
    }

}
