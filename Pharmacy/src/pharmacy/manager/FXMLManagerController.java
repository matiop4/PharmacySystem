/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.manager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pharmacy.admin.modele.Produkty;
import pharmacy.admin.modele.Pracownik;
import pharmacy.alert.AlertMaker;
import pharmacy.connection.DBConnection;
import pharmacy.manager.klasy.Ladowanie_danych;
import pharmacy.pracownik.klient.FXMLKlientController;
import pharmacy.pracownik.modele.Klient;
import pharmacy.sprawdzanie.Sprawdzanie;

/**
 * FXML Controller class
 *
 * @author jkero
 */
public class FXMLManagerController implements Initializable {

    
    
    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXComboBox<String> cbRola;
    @FXML
    private TableView<Produkty> tableProdukty;
    @FXML
    private TableColumn<Produkty, String> colNazwa;
    @FXML
    private TableColumn<Produkty, Integer> colCena;
    @FXML
    private TableColumn<Produkty, String> colOpis;
    @FXML
    private TableColumn<Produkty, Integer> colIlosc;
    @FXML
    private TableColumn<Produkty, Integer> colPlacowka;
    @FXML
    private TableColumn<Produkty, String> colLogin;
    @FXML
    private JFXTextField txOpis,txSzukaj;
    @FXML
    private JFXTextField txNazwa;
    @FXML
    private JFXTextField txIlosc;
    @FXML
    private JFXTextField txCena;
    @FXML
    private JFXTextField txNumerTel;
    @FXML
    private JFXTextField txLogin;
    @FXML
    private JFXListView<Klient> lvKlienci;
    @FXML
    private MenuItem cmEdytuj;
    @FXML
    private MenuItem cmUsun;
    @FXML
    private JFXTextField txHaslo;
    @FXML
    private JFXButton Button_Placowki,btnWyjscie;
    @FXML
    private JFXButton Button_Wiadomosci,btn_Dodaj,btnPanel,btnPracownicy;
    @FXML
    private Pane pnl_start,pnl_dodajTowar,pnl_pracownicy;
    @FXML
    private JFXButton Button_Wyjscie;
    @FXML
    private JFXButton Button_Pracownicy;
    @FXML
    private JFXButton Button_Raporty;
    Ladowanie_danych dane_combw;
    private ObservableList<Produkty> data1;
    public static Integer idManagera;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         Connection conn = DBConnection.Connect();
        
        
        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT id_klienta, imie_klienta, nazwisko_klienta, kod_pocztowy_klienta, "
                    + "miejscowosc_klienta, adres_klienta, telefon_klienta "
                    + "FROM klient;");
            
            
            while (rs.next()){
                lvKlienci.getItems().add(new Klient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            }
            
            ps.close();
            rs.close();
            conn.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(FXMLKlientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        LoadDataProdukty();
        //LoadDataWiadomosci();
        dane_combw = new Ladowanie_danych();


    }

    @FXML
    private void HandleButton (ActionEvent event){
        if(event.getSource() == btn_Dodaj){
            //System.out.println("pan2");
            pnl_dodajTowar.toFront();        }
        else if(event.getSource() == btnPracownicy){
            //System.out.println("pan2");
            pnl_pracownicy.toFront();        }
        else if (event.getSource() == btnPanel){
            //System.out.println("pan1");
        pnl_start.toFront();    }
    }
    
    
    public void LoadDataProdukty() {
        Connection conn = DBConnection.Connect();
        try {
            Statement ps = conn.createStatement();
            data1 = FXCollections.observableArrayList();
            ResultSet rs = ps.executeQuery("SELECT id_produktu,nazwa_produktu,cena_produktu,opis_produktu,ilosc FROM produkty;");
            while (rs.next()) {
                System.out.println("id_produktu"+"nazwa_produktu"+"cena_produktu"+"opis_produktu"+"ilosc");
                data1.add(new Produkty(rs.getInt("id_produktu"), rs.getString("nazwa_produktu"), rs.getString("cena_produktu"), rs.getString("opis_produktu"), rs.getString("ilosc")));
            }
            colNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_produktu"));
            colCena.setCellValueFactory(new PropertyValueFactory<>("cena_produktu"));
            colOpis.setCellValueFactory(new PropertyValueFactory<>("opis_produktu"));
            colIlosc.setCellValueFactory(new PropertyValueFactory<>("ilosc"));
            //colPlacowka.setCellValueFactory(new PropertyValueFactory<>("adres_placowki"));
            //colLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            //System.out.println("id_produktu"+"nazwa_produktu"+"cena_produktu"+"opis_produktu"+"ilosc");

            tableProdukty.setItems(null);
            tableProdukty.setItems(data1);

            tableProdukty.setOnMousePressed((MouseEvent event) -> {
                if (tableProdukty.getSelectionModel().getSelectedItem() != null) {

                    txNazwa.setText(tableProdukty.getSelectionModel().getSelectedItem().getNazwa_produktu());
                    txCena.setText(tableProdukty.getSelectionModel().getSelectedItem().getCena_produktu());
                    txOpis.setText(tableProdukty.getSelectionModel().getSelectedItem().getOpis_produktu());
                    txIlosc.setText(tableProdukty.getSelectionModel().getSelectedItem().getIlosc());
                    
                   
                }
            });
            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            //Logger.getLogger(FXMLEdytujKlientaController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    

    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) btnWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void goPracownicy(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("pracownicy/FXMLPracownicy.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel Pracownikow");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Pracownicy.getScene().getWindow());
        stage.showAndWait();

    }

    @FXML
    private void DodajKonto(ActionEvent event) {
            try {
                Connection conn = DBConnection.Connect();
                Statement ps = conn.createStatement();
                //ResultSet rs = ps.executeQuery("SELECT id_placowki from placowka where adres_placowki = '" + cbPlacowka.getValue() + "'");
                //rs.next();
                conn.createStatement().executeUpdate("INSERT INTO produkty(id_produktu,nazwa_produktu,cena_produktu,opis_produktu,ilosc) Values (null,'" + txNazwa.getText() + "','" + txCena.getText() + "','" + txOpis.getText() + "','" + txIlosc.getText() + "')");
                LoadDataProdukty();
                conn.close();
            } catch (SQLException e) {
                System.err.println("ERROR" + e);
            }
        }


    @FXML
    private void WyczyscPolaKonta(ActionEvent event) {
        txNazwa.setText("");
        txCena.setText("");
        txOpis.setText("");
        txIlosc.setText("");
 
//        cbPlacowka.setValue("");
    }

    @FXML
    private void AktualizujKonto(ActionEvent event) {

            try {
                Connection conn = DBConnection.Connect();
                Statement ps2 = conn.createStatement();
//                ResultSet rs = ps2.executeQuery("SELECT id_placowki from placowka where adres_placowki = '" + cbPlacowka.getValue() + "'");
               // rs.next();
                String query = "Update Produkty set nazwa_produktu = ?, cena_produktu = ?,  opis_produktu = ?,ilosc = ? where id_produktu = '" + tableProdukty.getSelectionModel().getSelectedItem().getId_produktu() + "'";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, txNazwa.getText());
                ps.setString(2, txCena.getText());
                ps.setString(3, txOpis.getText());
                ps.setString(4, txIlosc.getText());
 
                //ps.setInt(7, rs.getInt(1));
                ps.execute();
                ps.close();
                LoadDataProdukty();
            } catch (SQLException ex) {

                System.out.println("error" + ex);

            }
        }

    
    
  
    
    
    
    

}
