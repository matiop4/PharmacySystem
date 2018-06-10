/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.manager.pracownicy;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Yser
 */
public class PracownicyClassa {
     private IntegerProperty id_pracownika;
    private StringProperty imie_pracownika;
    private StringProperty nazwisko_pracownika;
    //private StringProperty peselpracownika;
    private StringProperty telefonpracownika;
    private StringProperty rolapracownika;
    //private StringProperty placowka;

    public PracownicyClassa(int id_pracownika, String imie_pracownika, String nazwisko_pracownika, String telefonpracownika, String rolapracownika) {
        this.id_pracownika = new SimpleIntegerProperty(id_pracownika);
        this.imie_pracownika =  new SimpleStringProperty(imie_pracownika);
        this.nazwisko_pracownika = new SimpleStringProperty(nazwisko_pracownika);
        //this.peselpracownika =  new SimpleStringProperty(peselpracownika);
        this.telefonpracownika =  new SimpleStringProperty(telefonpracownika);
        this.rolapracownika =  new SimpleStringProperty(rolapracownika);
       // this.placowka =  new SimpleStringProperty(placowka);
    }

    public int getId_pracownika() {
        return id_pracownika.get();
    }

    public void setId_pracownika(int value) {
        this.id_pracownika.set(value);
    }

    public String getImie_pracownika() {
        return imie_pracownika.get();
    }

    public void setImie_pracownika(String value) {
        this.imie_pracownika.set(value);
    }

    public String getNazwisko_pracownika() {
        return nazwisko_pracownika.get();
    }

    public void setNazwisko_pracownika(String value) {
        this.nazwisko_pracownika.set(value);
    }



    public String getTelefonpracownika() {
        return telefonpracownika.get();
    }

    public void setTelefonpracownika(String value) {
        this.telefonpracownika.set(value);
    }

    public String getRolapracownika() {
        return rolapracownika.get();
    }

    public void setRolapracownika(String value) {
        this.rolapracownika.set(value);
    }

     @Override
    public String toString() {
        return imie_pracownika.get() + " " + nazwisko_pracownika.get() + "\n Rola: " + rolapracownika.get() + "\n Telefon: " + telefonpracownika.get();
    }
    
    
}
