/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.pracownik.produkty;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pharmacy.alert.AlertMaker;
import pharmacy.connection.DBConnection;
import pharmacy.pracownik.FXMLPracownikController;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLDodajNowyProduktController implements Initializable {

    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXTextField tfNazwa;
    @FXML
    private JFXTextArea tfOpis;
    @FXML
    private JFXButton bZamowProdukt;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        
        
    }    

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void zamowProdukt(ActionEvent event) throws SQLException {
        //wyslanie prosby o zamowienie nowego produktu do kierownika placówki
        JFXButton bCancel = new JFXButton("Anuluj");
        JFXButton bOkay = new JFXButton("Zamów");
        AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay,bCancel), "Zamówienie produktu", "Czy na pewno chcesz złożyć to zamówienie?");
        
        bOkay.setOnAction((ActionEvent event1) -> {
            try {
                Connection conn = DBConnection.Connect();
                   
                String tresc = "Opis produktu, który należy sprowadzić do sklepu : " + tfOpis.getText();
                LocalDate ld = LocalDate.now();
                conn.createStatement().executeUpdate("INSERT INTO wiadomosci (id_wiadomosci,id_pracownika_nadawcy,id_pracownika_odbiorca,temat_wiadomosci,tresc_wiadomosci,Data,status_wiadomosci) "
                        + "VALUES (null,"+FXMLPracownikController.idPracownika+","+FXMLPracownikController.idKierownika+",'Produkt do zamówienia - "+tfNazwa.getText()+"','"+tresc+"','"+ld.toString()+"','Nieodebrana');");
                
                JFXButton bOkay2 = new JFXButton("OK");
                AlertMaker.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay2), "Zamówienie złożone", "Proces zamówienia przebiegł pomyślnie. Wiadomość została przekazana do kierownika oddziału.");
        
        
        conn.close();
            } catch (Exception exp) {
                //nieobsłużone
                //System.out.println(exp);
            }
        });
        
        
        
    }
    
}
