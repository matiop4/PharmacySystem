/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.admin;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pharmacy.pharmacy;
import pharmacy.admin.modele.Produkty;
import pharmacy.admin.modele.Pracownik;
import pharmacy.alert.MakeAlert;
import pharmacy.connection.DBConnection;
import pharmacy.manager.klasy.Alert;
import pharmacy.manager.klasy.Ladowanie_danych;
import pharmacy.pracownik.modele.Klient;
import pharmacy.sprawdzanie.Sprawdzanie;

/**
 * FXML Controller class
 *
 * @author Łukasz
 */
public class AdminFXMLController implements Initializable {

    @FXML
    private TableView<Pracownik> tableKonta;
    @FXML
    private TableColumn<Pracownik, String> colImie;
    @FXML
    private TableColumn<Pracownik, String> colNazwisko;
    @FXML
    private TableColumn<Pracownik, Integer> colRola;
    @FXML
    private TableColumn<Pracownik, Integer> colNrTel;
    @FXML
    private TableColumn<Pracownik, Integer> colPlacowka;
    @FXML
    private TableColumn<Pracownik, String> colLogin;
    @FXML
    private TableView<Pracownik> tableKonta1;
    @FXML
    private TableColumn<Pracownik, String> colImie1;
    @FXML
    private TableColumn<Pracownik, String> colNazwisko1;
    @FXML
    private TableColumn<Pracownik, Integer> colRola1;
    @FXML
    private TableColumn<Pracownik, Integer> colNrTel1;
    @FXML
    private TableColumn<Pracownik, Integer> colPlacowka1;
    @FXML
    private TableColumn<Pracownik, String> colLogin1;
    @FXML
    private GridPane GridDane;
    @FXML
    private JFXTextField txSzukaj;
    @FXML
    private JFXButton btnDodajKonto;
    @FXML
    private JFXButton btnWyczyscKonta,btnZwolnij;
    @FXML
    private JFXButton btnAktualizujKonto;
    @FXML
    private JFXTextField txImie;
    @FXML
    private JFXTextField txNazwisko;
    @FXML
    private JFXTextField txPesel;
    @FXML
    private JFXTextField txNumerTel;
    @FXML
    private JFXTextField txLogin;
    @FXML
    private JFXTextField txHaslo;
    @FXML
    private JFXTextField txMiejscowosc;
    @FXML
    private JFXTextField txAdres;
    @FXML
    private JFXTextField txKodPocztowy;
    @FXML
    private JFXTextField txTelefonKontaktowyPlacowki;
    @FXML
    private JFXButton btnDostawa;
    @FXML
    private JFXButton btnKonta;
    @FXML
    private JFXButton btnWyczyscPlacowka;
    @FXML
    private JFXButton btnWyjscie;
    @FXML
    private JFXComboBox<String> cbRola;
    private ObservableList<Pracownik> data;
    private ObservableList<Pracownik> data1;
    @FXML
    private JFXButton btnHasloAdmina;
    Ladowanie_danych dane_combo;
    @FXML
    private JFXComboBox<String> cbPlacowka;
    @FXML
    private AnchorPane AnchorKonta;
    @FXML
    private AnchorPane AnchorZamowienia;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    JFXButton bOk = new JFXButton("OK");
    @FXML
    private JFXButton btnAktywuj,btnPanel;
    @FXML
    private TextArea taTresc;
    @FXML
    private JFXButton btnOdpowiedz;
    @FXML
    private TextArea taOdpowiedz;
    @FXML
    private JFXTextField txTemat;
    Date currentDate = new Date();
    @FXML
    private Label labelTresc;
    @FXML
    private TableColumn<Pracownik, String> colStatusKonta;
    @FXML
    private Pane pnl_one,pnl_two,pnl_clear;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private void HandleButton (ActionEvent event){
        if(event.getSource() == btnDostawa){
            //System.out.println("pan2");
            pnl_two.toFront();        }
        else if(event.getSource() == btnPanel){
            //System.out.println("pan2");
            pnl_clear.toFront();        }
        else if (event.getSource() == btnKonta){
            //System.out.println("pan1");
        pnl_one.toFront();    }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbRola.getItems().addAll(
                "sprzedawca",
                "Menager"
        );
        LoadDataZwolnij();
        LoadDataPracownik();
        //LoadDataWiadomosci();
        dane_combo = new Ladowanie_danych();
//        dane_combo.ladujCombo("SELECT CONCAT(adres_placowki)AS Adres,id_placowki FROM placowka order by id_placowki", cbPlacowka, "Adres");

        FilteredList<Pracownik> filteredPracownik = new FilteredList<>(tableKonta.getItems(), e -> true);
        txSzukaj.setOnKeyReleased(e -> {
            txSzukaj.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredPracownik.setPredicate((Predicate<? super Pracownik>) p -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (p.getImie_pracownika().toLowerCase().contains(lcFilter) || p.getNazwisko_pracownika().toLowerCase().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Pracownik> sortedPracownik = new SortedList<>(filteredPracownik);
            tableKonta.setItems(sortedPracownik);
        });
    }

    public void LoadDataPracownik() {
        Connection conn = DBConnection.Connect();
        try {
            Statement ps = conn.createStatement();
            data = FXCollections.observableArrayList();
            ResultSet rs = ps.executeQuery("SELECT id_pracownika,imie_pracownika,nazwisko_pracownika,telefon_pracownika,login,haslo,rola,adres_placowki,status FROM pracownik,placowka where pracownik.id_placowki = placowka.id_placowki and imie_pracownika != 'Administrator' and status ='aktywny';");
            while (rs.next()) {
                data.add(new Pracownik(rs.getInt("id_pracownika"), rs.getString("imie_pracownika"), rs.getString("nazwisko_pracownika"), rs.getString("telefon_pracownika"), rs.getString("login"), rs.getString("haslo"), rs.getString("rola")));
            }
            colImie.setCellValueFactory(new PropertyValueFactory<>("imie_pracownika"));
            colNazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko_pracownika"));
            colRola.setCellValueFactory(new PropertyValueFactory<>("rola"));
            colNrTel.setCellValueFactory(new PropertyValueFactory<>("telefon_pracownika"));
            //colPlacowka.setCellValueFactory(new PropertyValueFactory<>("adres_placowki"));
            colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));

            tableKonta.setItems(null);
            tableKonta.setItems(data);

            tableKonta.setOnMousePressed((MouseEvent event) -> {
                if (tableKonta.getSelectionModel().getSelectedItem() != null) {

                    txImie.setText(tableKonta.getSelectionModel().getSelectedItem().getImie_pracownika());
                    txNazwisko.setText(tableKonta.getSelectionModel().getSelectedItem().getNazwisko_pracownika());
                    txNumerTel.setText(tableKonta.getSelectionModel().getSelectedItem().getTelefon_pracownika());
                    txLogin.setText(tableKonta.getSelectionModel().getSelectedItem().getLogin());
                    txHaslo.setText(tableKonta.getSelectionModel().getSelectedItem().getHaslo());
                    cbRola.setValue(tableKonta.getSelectionModel().getSelectedItem().getRola());
                   
                }
            });
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void LoadDataZwolnij() {
        Connection conn = DBConnection.Connect();
        try {
            Statement ps = conn.createStatement();
            data1 = FXCollections.observableArrayList();
            ResultSet rs = ps.executeQuery("SELECT id_pracownika,imie_pracownika,nazwisko_pracownika,telefon_pracownika,login,haslo,rola,adres_placowki,status FROM pracownik,placowka where pracownik.id_placowki = placowka.id_placowki and imie_pracownika != 'Administrator' and status ='nieaktywne';");
            while (rs.next()) {
                data1.add(new Pracownik(rs.getInt("id_pracownika"), rs.getString("imie_pracownika"), rs.getString("nazwisko_pracownika"), rs.getString("telefon_pracownika"), rs.getString("login"), rs.getString("haslo"), rs.getString("rola")));
            }
            colImie1.setCellValueFactory(new PropertyValueFactory<>("imie_pracownika"));
            colNazwisko1.setCellValueFactory(new PropertyValueFactory<>("nazwisko_pracownika"));
            colRola1.setCellValueFactory(new PropertyValueFactory<>("rola"));
            colNrTel1.setCellValueFactory(new PropertyValueFactory<>("telefon_pracownika"));
            //colPlacowka.setCellValueFactory(new PropertyValueFactory<>("adres_placowki"));
            colLogin1.setCellValueFactory(new PropertyValueFactory<>("login"));

            tableKonta1.setItems(null);
            tableKonta1.setItems(data1);
 
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

    
    
    
    @FXML
    private void DodajKonto(ActionEvent event) {
        if (txImie.getText().isEmpty() || txNazwisko.getText().isEmpty() || txNumerTel.getText().isEmpty() || txLogin.getText().isEmpty() || txHaslo.getText().isEmpty()) {
            MakeAlert.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z dodawaniem", "Uzupełnij wszystkie wymagane pola.");
        } else if (Sprawdzanie.telefonPoprawny(txNumerTel.getText()) == false) {
            MakeAlert.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z dodawaniem", "Podaj 9 cyfr telefonu komórkowego.");
        } 

        else {
            try {
                Connection conn = DBConnection.Connect();
                Statement ps = conn.createStatement();
                //ResultSet rs = ps.executeQuery("SELECT id_placowki from placowka where adres_placowki = '" + cbPlacowka.getValue() + "'");
                //rs.next();
                conn.createStatement().executeUpdate("INSERT INTO pracownik(id_pracownika, imie_pracownika, nazwisko_pracownika, telefon_pracownika,login,haslo,rola,id_placowki,status) Values (null,'" + txImie.getText() + "','" + txNazwisko.getText() + "','" + txNumerTel.getText() + "','" + txLogin.getText() + "','" + txHaslo.getText() + "','" + cbRola.getValue() + "','" + 1 + "','" + "aktywny')");
                LoadDataPracownik();
                conn.close();
            } catch (SQLException e) {
                System.err.println("ERROR" + e);
            }
        }

    }

    @FXML
    private void WyczyscPolaKonta(ActionEvent event) {
        txImie.setText("");
        txNazwisko.setText("");
        txNumerTel.setText("");
        txLogin.setText("");
        txHaslo.setText("");
        cbRola.setValue("");
//        cbPlacowka.setValue("");
    }

    @FXML
    private void AktualizujKonto(ActionEvent event) {

        if (tableKonta.getSelectionModel().getSelectedItem() == null) {
            MakeAlert.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Wybierz konto do aktualizacji.");
        } else if (txImie.getText().isEmpty() || txNazwisko.getText().isEmpty() || txNumerTel.getText().isEmpty() || txLogin.getText().isEmpty() || txHaslo.getText().isEmpty()) {
            MakeAlert.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Uzupełnij wszystkie wymagane pola.");
        } else if (Sprawdzanie.telefonPoprawny(txNumerTel.getText()) == false) {
            MakeAlert.showMaterialDialog(spMain, apMain, Arrays.asList(bOk), "Błąd z aktualizacją", "Poprawny numer telefonu zawiera 9 cyfr.");
        } else {
            try {
                Connection conn = DBConnection.Connect();
                Statement ps2 = conn.createStatement();
//                ResultSet rs = ps2.executeQuery("SELECT id_placowki from placowka where adres_placowki = '" + cbPlacowka.getValue() + "'");
               // rs.next();
                String query = "Update Pracownik set imie_pracownika = ?, nazwisko_pracownika = ?,  telefon_pracownika = ?,login = ?, haslo = ?, rola = ? where id_pracownika = '" + tableKonta.getSelectionModel().getSelectedItem().getId_pracownika() + "'";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, txImie.getText());
                ps.setString(2, txNazwisko.getText());
                ps.setInt(3, Integer.parseInt(txNumerTel.getText()));
                ps.setString(4, txLogin.getText());
                ps.setString(5, txHaslo.getText());
                ps.setString(6, cbRola.getValue());
                //ps.setInt(7, rs.getInt(1));
                ps.execute();
                ps.close();
                LoadDataPracownik();
            } catch (SQLException ex) {

                System.out.println("error" + ex);

            }
        }
    }
    
    
    @FXML
    private void Zwolnij(ActionEvent event) {

      
            try {
                Connection conn = DBConnection.Connect();
                Statement ps2 = conn.createStatement();
//                ResultSet rs = ps2.executeQuery("SELECT id_placowki from placowka where adres_placowki = '" + cbPlacowka.getValue() + "'");
               // rs.next();
                String query = "Update Pracownik set status = 'nieaktywne' where id_pracownika = '" + tableKonta.getSelectionModel().getSelectedItem().getId_pracownika() + "'";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.execute();
                ps.close();
                LoadDataPracownik();
            } catch (SQLException ex) {

                System.out.println("error" + ex);

            }
            LoadDataZwolnij();
  }


 
    
    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) btnWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ZmienHasloAdmina(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("haslo_admina/HasloAdminaFXML.fxml"));
        root.getStylesheets().add(pharmacy.class.getResource("admin/fxmladmin.css").toExternalForm());
        stage.setScene(new Scene(root));
        stage.setTitle("Panel managera");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(btnHasloAdmina.getScene().getWindow());
        stage.showAndWait();
    }

}
