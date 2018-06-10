/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.pracownik.zakupy;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jkero
 */
public class FXMLPracownikController implements Initializable {

    @FXML
    private JFXButton bZakupy;
    @FXML
    private JFXButton bReklamacje;
    @FXML
    private JFXButton bProdukty;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bKlient;

    
    public static Integer idPlacowki; //tymczasowo
    public static Integer idPracownika; //tymczasowo
    public static Integer idKierownika;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        bZakupy.setFocusTraversable(false);
        bKlient.setFocusTraversable(false);
        bProdukty.setFocusTraversable(false);
        bReklamacje.setFocusTraversable(false);
        bWyjscie.setFocusTraversable(false);
        
        
    }    

    @FXML
    private void zrobZakupy(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("zakupy/FXMLZakupy.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel zakupów");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bZakupy.getScene().getWindow());
        stage.showAndWait();
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
    private void wylogujSie(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void otworzKlient(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("klient/FXMLKlient.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel klientów");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bKlient.getScene().getWindow());
        stage.showAndWait();
    }
    
}
