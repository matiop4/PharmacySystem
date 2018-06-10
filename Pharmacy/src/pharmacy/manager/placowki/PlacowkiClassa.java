/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.manager.placowki;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yser
 */
public class PlacowkiClassa {

    private IntegerProperty id_placowki;
    private StringProperty kod_pocztowy_placowki;
    private StringProperty miejscowosc;
    private StringProperty adres;
    private StringProperty telefon;

    public PlacowkiClassa(String kod_pocztowy_placowki, String miejscowosc, String adres, String telefon) {
        this.kod_pocztowy_placowki = new SimpleStringProperty(kod_pocztowy_placowki);
        this.miejscowosc = new SimpleStringProperty(miejscowosc);
        this.adres = new SimpleStringProperty(adres);
        this.telefon = new SimpleStringProperty(telefon);
    }

    public PlacowkiClassa(String kod_pocztowy_placowki, String miejscowosc, String adres, String telefon, int id_placowki) {

        this.kod_pocztowy_placowki = new SimpleStringProperty(kod_pocztowy_placowki);
        this.miejscowosc = new SimpleStringProperty(miejscowosc);
        this.adres = new SimpleStringProperty(adres);
        this.telefon = new SimpleStringProperty(telefon);
        this.id_placowki = new SimpleIntegerProperty(id_placowki);
    }

    public int getId_placowki() {
        return id_placowki.get();
    }

    public void setId_placowki(int value) {
        this.id_placowki.set(value);
    }

    public String getKod_pocztowy_placowki() {
        return kod_pocztowy_placowki.get();
    }

    public void setKod_pocztowy_placowki(String value) {
        this.kod_pocztowy_placowki.set(value);
    }

    public String getMiejscowosc() {
        return miejscowosc.get();
    }

    public void setMiejscowosc(String value) {
        this.miejscowosc.set(value);
    }

    public String getAdres() {
        return adres.get();
    }

    public void setAdres(String value) {
        this.adres.set(value);
    }

    public String getTelefon() {
        return telefon.get();
    }

    public void setTelefon(String value) {
        this.telefon.set(value);
    }
     @Override
    public String toString() {
        return miejscowosc.get() + " " + telefon.get()+" " +kod_pocztowy_placowki.get() + " " + adres.get();
    }
     
    

}
