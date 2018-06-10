/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.manager.pracownicy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import pharmacy.connection.DBConnection;
import pharmacy.pracownik.klient.FXMLKlientController;

/**
 * FXML Controller class
 *
 * @author Yser
 */
public class FXMLSzczegolyController implements Initializable {

    @FXML
    private JFXButton Button_Znajdz;
    @FXML
    private JFXButton Button_Wyjscie;
    DBConnection dc;
    @FXML
    private JFXTextField tf_imie;
    @FXML
    private JFXTextField tf_nazwisko;
    @FXML
    private JFXTextField tf_placowka;
    @FXML
    private BarChart<String, Double> wykresefektywnosci;
    @FXML
    private JFXDatePicker dataod;
    @FXML
    private JFXDatePicker datado;
    int id_pracownika = FXMLPracownicyController.idPracownika;
    int id_pracownika2 = pharmacy.manager.placowki.FXMLSzczegolyController.id_pracownika;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Button_Znajdz.setFocusTraversable(false);
        Button_Wyjscie.setFocusTraversable(false);
        dc = new DBConnection();
        java.sql.Connection con = dc.Connect();
        wykres();
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT l.id_pracownika, l.imie_pracownika,l.nazwisko_pracownika,Concat(p.miejscowosc_placowki,\" \",p.adres_placowki)as Adres FROM pracownik l, placowka p where l.id_placowki=p.id_placowki and l.id_pracownika=" + id_pracownika);
            while (rs.next()) {
                tf_imie.setText(rs.getString(2));
                tf_nazwisko.setText(rs.getString(3));
                tf_placowka.setText(rs.getString(4));
            }

            ps.close();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLKlientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Znajdz_sprzedaz(ActionEvent event) {
        wykresefektywnosci.getData().removeAll(Collections.singleton(wykresefektywnosci.getData().setAll()));
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try {
            String dataod = this.dataod.getValue().toString();
            String datado = this.datado.getValue().toString();
            java.sql.Connection conn = dc.Connect();
            String query;
            query = "Select t.data, sum(t.calkowity_koszt)as Wynik_Sprzedazy"
                    + " From pracownik p, placowka l , transakcja t Where t.data "
                    + "BETWEEN '" + dataod + "' and '" + datado + "' and p.id_pracownika=" + id_pracownika + " And p.id_pracownika=t.id_pracownika"
                    + " and l.id_placowki=l.id_placowki group by t.data";

            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data(rs.getString(1), rs.getDouble(2)));
            }
            wykresefektywnosci.getData().add(series);

        } catch (Exception e) {
           wykres();

        }
    }

    @FXML
    private void Wyjscie(ActionEvent event) {
        Stage stage = (Stage) Button_Wyjscie.getScene().getWindow();
        stage.close();
    }

    public void wykres() {
        wykresefektywnosci.getData().removeAll(Collections.singleton(wykresefektywnosci.getData().setAll()));
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        try {

            java.sql.Connection conn = dc.Connect();
            String query;

            query = "Select t.data, sum(t.calkowity_koszt)as Wynik_Sprzedazy"
                    + " From pracownik p, placowka l , transakcja t Where p.id_pracownika=" + id_pracownika + " And p.id_pracownika=t.id_pracownika"
                    + " and l.id_placowki=l.id_placowki group by t.data";

            ResultSet rs = conn.createStatement().executeQuery(query);
            while (rs.next()) {
                series.getData().add(new XYChart.Data(rs.getString(1), rs.getDouble(2)));

            }
            wykresefektywnosci.getData().add(series);

        } catch (Exception e) {
            System.out.println("Nie udało się pobrać danych" + e);

        }

    }
}
