/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pharmacy.alert.MakeAlert;
import pharmacy.connection.DBConnection;
import pharmacy.manager.FXMLManagerController;
import pharmacy.pracownik.FXMLPracownikController;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLLogowanieController implements Initializable {

    
    public static int id_pracownika;

    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private JFXPasswordField tfHaslo;
    @FXML
    private JFXTextField tfLogin;
    @FXML
    private JFXButton bZaloguj;
    @FXML
    private JFXButton bWyjscie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    public void zaloguj(ActionEvent event) throws IOException {
        String rola = "";
        boolean sprawdzenie = false;
        Integer idPracownika = 0;
        Integer idPlacowki = 0;
        
        Connection conn = DBConnection.Connect();
        
        
        try {
            
            Statement ps = conn.createStatement();
            ResultSet rs = ps.executeQuery("SELECT login, haslo FROM pracownik WHERE login='"+tfLogin.getText()+"' AND haslo='"+tfHaslo.getText()+"';");
            
            if (rs.isBeforeFirst()){
                //rs.next();
                //rola = "admin";
            }
            
            
            
            
            if (!rola.equals("admin")){
                rs = ps.executeQuery("SELECT id_pracownika,id_placowki,login,haslo,rola,status FROM pracownik WHERE login='"+tfLogin.getText()+"' AND haslo='"+tfHaslo.getText()+"'"+" and status ='aktywny';");
                if(rs.isBeforeFirst()){
                    rs.next();
                    System.out.println(rs.getString("rola"));
                    rola = rs.getString("rola");
                    idPracownika = rs.getInt("id_pracownika");
                    idPlacowki = rs.getInt("id_placowki");
                   id_pracownika =rs.getInt(("id_pracownika"));
                }
                
            }
            Stage stage;
        Parent root;
            
            
            switch(rola){
                case "admin":
                    //okno admina                   
                    
                    
                    stage = (Stage) bWyjscie.getScene().getWindow();
                    
                    root = FXMLLoader.load(getClass().getResource("admin/AdminsFXML.fxml"));
                    stage.setScene(new Scene(root));
                    
                    stage.centerOnScreen();
                    break;
                case "Menager":
                    //okno managera
                    FXMLManagerController.idManagera=idPracownika;
                    stage = (Stage) bWyjscie.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("manager/FXMLManager.fxml"));
                    stage.setScene(new Scene(root));
                    
                    stage.centerOnScreen();
                    break;
                
                case "sprzedawca":
                    //okno pracownika
                    stage = (Stage) bWyjscie.getScene().getWindow();
                    root = FXMLLoader.load(getClass().getResource("pracownik/FXMLPracownik.fxml"));
                    stage.setScene(new Scene(root));
                    
                    stage.centerOnScreen();
                    
                    
                    ResultSet rs2 = conn.createStatement().executeQuery("SELECT id_pracownika FROM pracownik WHERE rola='sprzedawca' AND id_placowki="+idPlacowki+";");
                    rs2.next();
                    FXMLPracownikController.idKierownika = rs2.getInt("id_pracownika");
                    
                    rs2.close();
                    
                    FXMLPracownikController.idPlacowki = idPlacowki;
                    FXMLPracownikController.idPracownika = idPracownika;
                    break;
                default :
                    
                    JFXButton bCancel1 = new JFXButton("Ok");
                    MakeAlert.showSimpleAlert("Nie udało się zalogować","Wpisz inny login i hasło");
                    break;
            }
            
            
            rs.close();
            ps.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLogowanieController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
        
        
    }

    
    //public String asd = tfLogin.getText();
    
    @FXML
    private void wyjscie(ActionEvent event) {
        //System.out.println(asd);

        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }
    
}
