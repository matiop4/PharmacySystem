/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.pracownik.produkty;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pharmacy.alert.AlertMaker;
import pharmacy.connection.DBConnection;
import pharmacy.pracownik.FXMLPracownikController;
import pharmacy.pracownik.modele.Produkt;
import pharmacy.pracownik.zakupy.FXMLZakupyController;
import pharmacy.sprawdzanie.Sprawdzanie;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLProduktyController implements Initializable {
    
    
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bDodaj;
    @FXML
    private TableView<Produkt> tablePrzedmioty;
    @FXML
    private TableColumn<Produkt, Integer> columnIdPrzedmiotu;
    @FXML
    private TableColumn<Produkt, String> columnNazwa;
    @FXML
    private TableColumn<Produkt, String> columnOpis;
    @FXML
    private TableColumn<Produkt, Integer> columnNaStanie;
    @FXML
    private TableColumn<Produkt, Double> columnCena;
    
    @FXML
    private TableView<Produkt> tableProdukty;
    @FXML
    private TableColumn<Produkt, Integer> columnIDProduktu;
    
    @FXML
    private TableColumn<Produkt, Integer> columnIlosc;
    
   
    @FXML
    private JFXTextField tfSzukaj;
    @FXML
    private MenuItem miZapotrzebowanie;
    @FXML
    private JFXButton bNowyProdukt;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    private ObservableList<Produkt> dataProdukt;
    private Produkt pr;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        dataProdukt = FXCollections.observableArrayList();
        boolean dodaj = true;
        Connection conn = DBConnection.Connect();

        try {
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT id_produktu, nazwa_produktu, cena_produktu,opis_produktu, ilosc FROM produkty");

            while (rs.next()) {
                    dataProdukt.add(new Produkt(rs.getInt("id_produktu"), rs.getString("nazwa_produktu"), rs.getDouble("cena_produktu"), rs.getString("opis_produktu"), rs.getInt("ilosc")));

            }

            rs.close();
            ps.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLProduktyController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        for (Produkt p1 : dataProdukt){
//            if (FXMLZakupyController.dataRachunek.contains(p1))
//                dataProdukt.remove(p1);
//        }
        columnIdPrzedmiotu.setCellValueFactory(new PropertyValueFactory<>("id_produktu"));
        columnNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_produktu"));
        columnNaStanie.setCellValueFactory(new PropertyValueFactory<>("ilosc_produktow"));
        columnCena.setCellValueFactory(new PropertyValueFactory<>("cena_produktu"));
        columnOpis.setCellValueFactory(new PropertyValueFactory<>("opis_produktu"));

        tablePrzedmioty.setItems(null);
        tablePrzedmioty.setItems(dataProdukt);

       

    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();

    }
    
  
        
        
        
        
        
    

    @FXML
    private void zamowNowyProdukt(ActionEvent event) throws IOException {
        //wyslanie wiadomosci do góry o prośbie dodania nowego produktu

        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLDodajNowyProdukt.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel zamawiania produktu");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bNowyProdukt.getScene().getWindow());
        stage.showAndWait();

    }

    @FXML
    private void zaznaczProdukt(MouseEvent event) {
        
        if (tableProdukty.getSelectionModel().getSelectedItem() != null) pr = tableProdukty.getSelectionModel().getSelectedItem();
        
    }

}
