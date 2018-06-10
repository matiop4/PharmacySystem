/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.admin.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Łukasz
 */
public class Produkty {

    private IntegerProperty id_produktu;
    private StringProperty nazwa_produktu;
    private StringProperty cena_produktu;
    private StringProperty opis_produktu;
    private StringProperty ilosc;

    
    public Produkty(int id_produktu, String nazwa_produktu, String cena_produktu, String opis_produktu, String ilosc) {
        this.id_produktu = new SimpleIntegerProperty(id_produktu);
        this.nazwa_produktu = new SimpleStringProperty(nazwa_produktu);
        this.cena_produktu = new SimpleStringProperty(cena_produktu);
        this.opis_produktu = new SimpleStringProperty(opis_produktu);
        this.ilosc = new SimpleStringProperty(ilosc);
    }

    public int getId_produktu() {
        return id_produktu.get();
    }

    public void setId_produktu(IntegerProperty id_produktu) {
        this.id_produktu = id_produktu;
    }

    public String getNazwa_produktu() {
        return nazwa_produktu.get();
    }

    public void setNazwa_produktu(String value) {
        nazwa_produktu.set(value);
    }

    public String getCena_produktu() {
        return cena_produktu.get();
    }

    public void setCena_produktu(StringProperty cena_produktu) {
        this.cena_produktu = cena_produktu;
    }

    public String getOpis_produktu() {
        return opis_produktu.get();
    }

    public void setOpis_produktu(String value) {
        opis_produktu.set(value);
    }

    public String getIlosc() {
        return ilosc.get();
    }

    public void setIlosc(StringProperty ilosc) {
        this.ilosc = ilosc;
    }

    
    
    
}