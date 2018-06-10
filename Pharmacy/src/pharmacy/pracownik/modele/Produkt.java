/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.pracownik.modele;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author BlackHawk
 */
public class Produkt {
    
    private IntegerProperty id_produktu;
    private StringProperty nazwa_produktu;
    private DoubleProperty cena_produktu;
    private StringProperty opis_produktu;
    private IntegerProperty ilosc_produktow;
    private IntegerProperty sztuki;
    private DoubleProperty suma;
    
    
    
    //      ***Konstruktory***
    
    public Produkt (int id_produktu, String nazwa_produktu, Double cena_produktu, String opis_produktu, int ilosc_produktow){
        this.id_produktu = new SimpleIntegerProperty(id_produktu);
        this.nazwa_produktu = new SimpleStringProperty(nazwa_produktu);
        this.cena_produktu = new SimpleDoubleProperty(cena_produktu);
        this.opis_produktu = new SimpleStringProperty(opis_produktu);
        this.ilosc_produktow = new SimpleIntegerProperty(ilosc_produktow);
    }
    
    public Produkt (int id_produktu, String nazwa_produktu, Double cena_produktu, String opis_produktu, int sztuki, Double suma){
        this.id_produktu = new SimpleIntegerProperty(id_produktu);
        this.nazwa_produktu = new SimpleStringProperty(nazwa_produktu);
        this.cena_produktu = new SimpleDoubleProperty(cena_produktu);
        this.opis_produktu = new SimpleStringProperty(opis_produktu);        
        this.sztuki = new SimpleIntegerProperty(sztuki);
        this.suma = new SimpleDoubleProperty(suma);
    }
    
        public Produkt (int id_produktu, String nazwa_produktu, Double cena_produktu, String opis_produktu, int sztuki, Double suma, int ilosc_produktow){
        this.id_produktu = new SimpleIntegerProperty(id_produktu);
        this.nazwa_produktu = new SimpleStringProperty(nazwa_produktu);
        this.cena_produktu = new SimpleDoubleProperty(cena_produktu);
        this.opis_produktu = new SimpleStringProperty(opis_produktu);        
        this.sztuki = new SimpleIntegerProperty(sztuki);
        this.suma = new SimpleDoubleProperty(suma);
        this.ilosc_produktow = new SimpleIntegerProperty(ilosc_produktow);
    }
    
    public Produkt (int id_produktu, String nazwa_produktu, Double cena_produktu, String opis_produktu){
         this.id_produktu = new SimpleIntegerProperty(id_produktu);
        this.nazwa_produktu = new SimpleStringProperty(nazwa_produktu);
        this.cena_produktu = new SimpleDoubleProperty(cena_produktu);
        this.opis_produktu = new SimpleStringProperty(opis_produktu); 
    }

    
    
    //      ***Gettery***
    
    public Integer getId_produktu(){
        return id_produktu.get();
    }
    
    public String getNazwa_produktu(){
        return nazwa_produktu.get();
    }
    
    public Double getCena_produktu(){
        return cena_produktu.get();
    }
    
    public String getOpis_produktu(){
        return opis_produktu.get();
    }
    
    public int getIlosc_produktow(){
        return ilosc_produktow.get();
    }
    
    public int getSztuki(){
        return sztuki.get();
    }
    
    public Double getSuma(){
        return suma.get();
    }
    
    
    //      ***Settery***
    
    public void setNazwa_produktu (String value){
        nazwa_produktu.set(value);
    }
    
    public void setCena_produktu (Double value){
        cena_produktu.set(value);
    }
    
    public void setOpis_produktu (String value){
        opis_produktu.set(value);
    }
    
    public void setIlosc_produktow (int value){
        ilosc_produktow.set(value);
    }
    
    
    
    
    //      ***Properties***
    
    public IntegerProperty id_produktuProperty(){
        return id_produktu;
    }
    
    public StringProperty nazwa_produktuProperty(){
        return nazwa_produktu;
    }
    
    public DoubleProperty cena_produktuProperty(){
        return cena_produktu;
    }
    
    public StringProperty opis_produktuProperty(){
        return opis_produktu;
    }
    
    public IntegerProperty ilosc_produktowProperty(){
        return ilosc_produktow;
    }
    
    public IntegerProperty sztukiProperty(){
        return sztuki;
        
    }
    
    public DoubleProperty sumaProperty(){
        return suma;
    }
    
    
}
