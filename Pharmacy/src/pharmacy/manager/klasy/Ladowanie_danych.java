/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.manager.klasy;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import pharmacy.connection.DBConnection;

/**
 *
 * @author Yser
 */
public class Ladowanie_danych {
    
    DBConnection dc = new DBConnection();

    public void ladujCombo(String zapytanie, ComboBox combo, String kolumna) {

        java.sql.Connection conn = dc.Connect();
        ObservableList lista = FXCollections.observableArrayList();

        try {
            ResultSet rsCb = conn.createStatement().executeQuery(zapytanie);

            while (rsCb.next()) {
                lista.add(rsCb.getString(kolumna));
            }
            
//            combo.setItems(lista);
            rsCb.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public ObservableList ladujliste(String zapytanie, String kolumna) {

        java.sql.Connection conn = dc.Connect();
        ObservableList lista = FXCollections.observableArrayList();

        try {
            ResultSet rsCb = conn.createStatement().executeQuery(zapytanie);

            while (rsCb.next()) {
                lista.add(rsCb.getString(kolumna));

            }
            rsCb.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return lista;
    }

}

