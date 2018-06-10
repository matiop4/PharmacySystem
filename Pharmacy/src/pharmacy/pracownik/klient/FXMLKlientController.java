/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.pracownik.klient;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pharmacy.connection.DBConnection;
import pharmacy.pracownik.modele.Klient;
import pharmacy.sprawdzanie.Sprawdzanie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pharmacy.connection.DBConnection;
import pharmacy.sprawdzanie.Sprawdzanie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLKlientController implements Initializable {
    
    @FXML
    private JFXTextField tfImie;
    @FXML
    private JFXTextField tfKodPocztowy;
    @FXML
    private JFXTextField tfAdres;
    @FXML
    private JFXTextField tfNumerKarty;
    @FXML
    private JFXTextField tfMiejscowosc;
    @FXML
    private JFXTextField tfNrTelefonu;
    @FXML
    private JFXTextField tfNazwisko;
    @FXML
    private JFXButton bNowyKlient;
    @FXML
    private JFXTextField tfWyszukaj;
    @FXML
    private JFXButton bWyjscie,bUsunKlienta,bDodaj,bTabela;
    @FXML
    private Pane pnl_dodaj,pnl_tabela;
    @FXML
    private JFXListView<Klient> lvKlienci;
    @FXML
    private MenuItem cmEdytuj;
    @FXML
    private MenuItem cmUsun;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private void HandleButton (ActionEvent event){
        if(event.getSource() == bNowyKlient){
            pnl_dodaj.toFront();        }
        else if(event.getSource() == bTabela){
            //System.out.println("pan2");
            pnl_tabela.toFront();        }
    }
    
    
    @FXML
    private void dodajKlienta(ActionEvent event) throws SQLException {
        Connection conn = DBConnection.Connect();
        
            conn.createStatement().executeUpdate("INSERT INTO klient (id_klienta, imie_klienta, nazwisko_klienta, kod_pocztowy_klienta, miejscowosc_klienta,adres_klienta,"
                    + " telefon_klienta, liczba_punktow) VALUES (NULL,'"+tfImie.getText()+"','"+tfNazwisko.getText()+"','"+tfKodPocztowy.getText()+"','"+tfMiejscowosc.getText()+"'"
                            + ",'"+tfAdres.getText()+"','"+tfNrTelefonu.getText()+"',0);");
        System.out.println("Pomyślnie dodano nowego klienta");
        
    }
    
    
    public static Integer idKlienta;
    //private ObservableList <Klient> dataKlienci;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        bDodaj.setFocusTraversable(false);

        bWyjscie.setFocusTraversable(false);
        bUsunKlienta.setFocusTraversable(false);
        bTabela.setFocusTraversable(false);
        bNowyKlient.setFocusTraversable(false);
        tfWyszukaj.setFocusTraversable(false);
        
        //dataKlienci = FXCollections.observableArrayList();
        
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
            
            
            
            
            
            
            
            FilteredList<Klient> filteredKlient = new FilteredList <>(lvKlienci.getItems(), e->true);
            tfWyszukaj.setOnKeyReleased(e->{
                tfWyszukaj.textProperty().addListener((observableValue, oldValue, newValue) ->{
                    filteredKlient.setPredicate((Predicate<? super Klient>) k->{
                        if (newValue==null || newValue.isEmpty()){
                            return true;
                        }
                        String lcFilter = newValue.toLowerCase();
                        if (k.getImie_klienta().toLowerCase().contains(lcFilter) || k.getNazwisko_klienta().toLowerCase().contains(lcFilter)){
                            return true;
                        }
                        return false;
                        });
                    });
                SortedList<Klient> sortedKlient = new SortedList<>(filteredKlient);
                lvKlienci.setItems(sortedKlient);
            });
            
            
        
        
              
    }   
    

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void edytuj(ActionEvent event) throws IOException {
        if (lvKlienci.getSelectionModel().getSelectedItem() != null){
        
            idKlienta = lvKlienci.getSelectionModel().getSelectedItem().getId_klienta();


            Stage stage;
            Parent root;

            stage = new Stage();
            root = FXMLLoader.load(getClass().getResource("FXMLEdytujKlienta.fxml"));
            stage.setScene(new Scene(root));
            stage.setTitle("Panel dodawania klienta");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            //stage.initOwner(bNowyKlient.getScene().getWindow());
            stage.showAndWait();
        
        }
    }

    @FXML
    private void usun(ActionEvent event) {
        //okno dialogowe TAK/NIE czy usunac klienta

        if (lvKlienci.getSelectionModel().getSelectedItem() != null) {

            //JFXButton bCancel = new JFXButton("Nie");
            //JFXButton bOkay = new JFXButton("Tak");
            //AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay, bCancel), "Usuwanie klienta.", "Czy na pewno chcesz usunąć zaznaczonego klienta?");

            
                try {
                    Connection conn = DBConnection.Connect();
                    int id = lvKlienci.getSelectionModel().getSelectedItem().getId_klienta();
                    lvKlienci.getItems().remove(lvKlienci.getSelectionModel().getSelectedIndex());
                    
                    conn.createStatement().executeUpdate("DELETE FROM klient WHERE id_klienta=" + id + ";");

                    conn.close();
                } catch (Exception exp) {
                    //nieobsłużone
                    //System.out.println(exp);
                }
            };

        }

    }
