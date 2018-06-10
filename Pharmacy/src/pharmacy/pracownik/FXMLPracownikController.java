/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.pracownik;

import com.jfoenix.controls.JFXButton;
//import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pharmacy.FXMLLogowanieController;
import pharmacy.admin.modele.Pracownik;
import pharmacy.alert.AlertMaker;
import pharmacy.connection.DBConnection;
import static pharmacy.FXMLLogowanieController.id_pracownika;
import pharmacy.manager.pracownicy.PracownicyClassa;
import pharmacy.pracownik.klient.FXMLKlientController;


/**
 * FXML Controller class
 *
 * @author jkero
 */
public class FXMLPracownikController implements Initializable {

    DBConnection dc;

    
    @FXML
    private Text Text1;
    @FXML
    private ImageView Img1;
    @FXML
    private Image img;
    @FXML
    private JFXButton bReklamacje,btnDostawa,btnKlienci,btnZakupy,btnLeki,bProdukty,bWyjscie,bKlient,bKlienci,bZakupy,btnPanel,btn123;
    @FXML
    private Pane pnl_Zakupy,pnl_Klienci,pnl_Leki,pnl_clear;
    @FXML
    private void HandleButton (ActionEvent event){
        if(event.getSource() == btnKlienci){
            pnl_Klienci.toFront();        }
        else if(event.getSource() == btnPanel){
            //System.out.println("pan2");
            pnl_clear.toFront();        }
        else if (event.getSource() == btnLeki){
            //System.out.println("pan1");
        pnl_Leki.toFront();    }
        else if (event.getSource() == btnZakupy){
            //System.out.println("pan1");
        pnl_Zakupy.toFront();    }
    }
    
    public static Integer idPracownikaa;
    public static Integer idPlacowki; //tymczasowo
    public static Integer idPracownika; //tymczasowo
    public static Integer idKierownika;
    public static String pracownik;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //btn123.setText(pracownik);
        
        
        bZakupy.setFocusTraversable(false);
        //bKlient.setFocusTraversable(false);
        bProdukty.setFocusTraversable(false);
        //bReklamacje.setFocusTraversable(false);
        bWyjscie.setFocusTraversable(false);
        
            
        dc = new DBConnection();
        java.sql.Connection con = dc.Connect();
        //Button_Wyjscie.setFocusTraversable(false);
        //TextField_Szukaj.setFocusTraversable(false);
        
        
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT id_pracownika,imie_pracownika ,nazwisko_pracownika FROM pracownik where id_pracownika="+ pharmacy.FXMLLogowanieController.id_pracownika + ";");
            while (rs.next()) {
                btn123.setText(rs.getString("imie_pracownika")+" "+rs.getString("nazwisko_pracownika"));
            }

            ps.close();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLKlientController.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }    

    @FXML
    private void zrobZakupy(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        try {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("zakupy/FXMLZakupy.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel zakupów");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bZakupy.getScene().getWindow());
        stage.show(); 
        }
        catch (Exception ex) {
            
        }
        
    }

    @FXML
    private void otworzReklamacje(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("reklamacje/FXMLReklamacje.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel reklamacji");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bReklamacje.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void otworzProdukty(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("produkty/FXMLProdukty.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel produktów");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bProdukty.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void otworzKlienci(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("klient/FXMLKlient.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel klientów");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bKlienci.getScene().getWindow());
        stage.showAndWait();
    }
    
}
