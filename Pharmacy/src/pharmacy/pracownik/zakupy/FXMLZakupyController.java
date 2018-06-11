/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.pracownik.zakupy;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.awt.Checkbox;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import pharmacy.connection.DBConnection;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pharmacy.pharmacy;
import pharmacy.alert.MakeAlert;
import pharmacy.pracownik.modele.Klient;
import pharmacy.pracownik.modele.Produkt;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author BlackHawk
 */
public class FXMLZakupyController implements Initializable {

    
    
    
    
    
    @FXML
    private void DoPDF(ActionEvent event) {
 
        
        
        Stage stage = (Stage) bPdf.getScene().getWindow();
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Zapisz do PDF");
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);
        File saveLoc = fileChooser.showSaveDialog(stage);
        
        if (!saveLoc.getName().endsWith(".pdf")) {
                saveLoc = new File(saveLoc.getAbsolutePath() + ".pdf");
            }

        Document document = new Document();
        try {
           
            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1250, BaseFont.EMBEDDED);
            Font helvetica16=new Font(helvetica,16);
            
           PdfWriter.getInstance(document,
           new FileOutputStream(saveLoc));

            File fille = new File("C:/Users/rachunek.pdf");
            OutputStream file = new FileOutputStream(fille);
            
            if(!fille.exists()){
            fille.createNewFile();
            }   else{
                    System.out.println("Stworzono kopie ostatniego rachunku.pdf");
                    }
           
           
           PdfWriter.getInstance(document, file);
 
 //Inserting Image in PDF
     Image image = Image.getInstance ("src/pharmacy/pracownik/logo.jpg");
     image.scaleAbsolute(250f, 150f);//image width,height 
     
     Image podpisy = Image.getInstance ("src/pharmacy/pracownik/podpisy.png");
     podpisy.scaleAbsolute(190f, 110f);//image width,height 
     
 //Inserting Table in PDF
 
        

 
      PdfPTable tables=new PdfPTable(5);
 
                      
                      PdfPCell cell1 = new PdfPCell(new Paragraph("ID"));
                      PdfPCell cell2 = new PdfPCell(new Paragraph("Nazwa"));
                      PdfPCell cell3 = new PdfPCell(new Paragraph("Cena szt."));            
                      PdfPCell cell4 = new PdfPCell(new Paragraph("Ilość",helvetica16));
                      PdfPCell cell5 = new PdfPCell(new Paragraph("Suma",helvetica16));
                      
                      
                      
       cell1.setColspan (1);
       cell1.setHorizontalAlignment (Element.ALIGN_CENTER);
       cell1.setPadding (10.0f);
       cell1.setBackgroundColor (new BaseColor (140, 221, 8));
       
       cell2.setColspan (1);
       cell2.setHorizontalAlignment (Element.ALIGN_CENTER);
       cell2.setPadding (10.0f);
       cell2.setBackgroundColor (new BaseColor (140, 221, 8));
       
       cell3.setColspan (1);
       cell3.setHorizontalAlignment (Element.ALIGN_CENTER);
       cell3.setPadding (10.0f);
       cell3.setBackgroundColor (new BaseColor (140, 221, 8));
       
       cell4.setColspan (1);
       cell4.setHorizontalAlignment (Element.ALIGN_CENTER);
       cell4.setPadding (10.0f);
       cell4.setBackgroundColor (new BaseColor (140, 221, 8));
       
       cell5.setColspan (1);
       cell5.setHorizontalAlignment (Element.ALIGN_CENTER);
       cell5.setPadding (10.0f);
       cell5.setBackgroundColor (new BaseColor (140, 221, 8));
 
       tables.addCell(cell1);
       tables.addCell(cell2); 
       tables.addCell(cell3); 
       tables.addCell(cell4); 
       tables.addCell(cell5); 
 
       
       
       tables.setSpacingBefore(30.0f);       // Space Before table starts, like margin-top in CSS
       tables.setSpacingAfter(30.0f);        // Space Af Before table starts, like margin-top in CSS
       tables.setSpacingAfter(30.0f);        // Space Ater table starts, like margin-Bottom in CSS								          

       String sztuki;
            
            for (Produkt pr : dataRachunek){
                tables.addCell(new Paragraph(" "+pr.getId_produktu().toString(),helvetica16));
                tables.addCell(new Paragraph(pr.getNazwa_produktu(),helvetica16));
                tables.addCell(new Paragraph(pr.getCena_produktu().toString(),helvetica16));
                sztuki = pr.getSztuki()+"";
                tables.addCell(new Paragraph(sztuki));
                tables.addCell(new Paragraph(pr.getSuma().toString(),helvetica16));
            }
            
            
            tables.addCell(new Paragraph(" "));
            tables.addCell(new Paragraph(" "));
            tables.addCell(new Paragraph(" "));
            tables.addCell(new Paragraph("Suma"));
            tables.addCell(new Paragraph(lSumaWartosc.getText(),helvetica16));
            
            
            

            
            //document.add(tables);
 
 //Text formating in PDF    
Chunk chunk=new Chunk("ZAPRASZAMY PONOWNIE...");
 chunk.setUnderline(+1f,-2f);//1st co-ordinate is for line width,2nd is space between
 
 //Now Insert Every Thing Into PDF Document
          document.open();//PDF document opened........			       
 
 document.add(image);
 
 document.add(Chunk.NEWLINE);   //Something like in HTML :-)
 
 Date currentDate = new Date();
SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
String dateString = dateFormat.format(currentDate);
 
 
                    document.add(new Paragraph("Dziękujemy za zakup lekow.",helvetica16));
                 document.add(new Paragraph("Rachunek wygenerowany - "+dateString )); 
 
 document.add(tables);
 
 document.add(chunk);
 
 if(cRencista.isSelected()){
            document.add(new Paragraph("Rachunek wydrukowany z rabatem 30%!. Dziękujemy za zakupy w \"Pharmacy System\"",helvetica16));}
            else if(cPoniedzialki.isSelected())
            {
            document.add(new Paragraph("Rachunek wydrukowany z rabatem 15%!. Dziękujemy za zakupy w \"Pharmacy System\"",helvetica16));
            }
            else if(cSrody.isSelected())
            {
            document.add(new Paragraph("Rachunek wydrukowany z rabatem 10%!. Dziękujemy za zakupy w \"Pharmacy System\"",helvetica16));
            }
 
 document.add(Chunk.NEWLINE);   //Something like in HTML :-)							    
 document.add(podpisy);
       
 
 
          document.close();
 
              file.close();
 
            System.out.println("Pdf stworzony..");
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    
    
    @FXML
    private JFXButton bDoRachunku,bPdf;
    @FXML
    private JFXButton bWyjscie;
    @FXML
    private JFXButton bUsunPrzedmiot;
    @FXML
    private TableView<Produkt> tableRachunek;
    @FXML
    private StackPane spMain;
    @FXML
    private AnchorPane apMain;
    @FXML
    private JFXButton bZatwierdz;
    @FXML
    private Label lSuma;
    @FXML
    private Label lSumaWartosc;
    @FXML
    private TableColumn<Produkt, Integer> columnIdProduktu;
    @FXML
    private TableColumn<Produkt, String> columnNazwa;
    @FXML
    private TableColumn<Produkt, String> columnOpis;
    @FXML
    private TableColumn<Produkt, Double> columnCenaSzt;
    @FXML
    private TableColumn<Produkt, Integer> columnIlosc;
    @FXML
    private TableColumn<Produkt, Double> columnCena;
    @FXML
    private JFXCheckBox cSrody,cRencista,cPoniedzialki;
    @FXML
    private Label lStalyKlient;
    @FXML
    private FontAwesomeIconView bListaKlientow;
    
    public ItemListener asd;
    //public FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLZakupy.fxml"));
    public static ObservableList<Produkt> dataRachunek;
    public static Klient k1;
    Integer idKlienta;
    private static Double price;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(FXMLListaPrzedmiotowController.p.getOpis_produktu());
        //bUsunPrzedmiot.setDisable(true);
        //lStalyKlient.setText("c");
        dataRachunek = FXCollections.observableArrayList();
        bUsunPrzedmiot.setDisable(true);
        bPdf.setVisible(false);
        bZatwierdz.setVisible(true);
        odswiezRachunek();
        
        // ***
//        if (FXMLListaKlientowController.k != null) {
//            lStalyKlient.setText(k1.getImie_klienta() + " " + k1.getNazwisko_klienta() + " (" + k1.getNumer_karty() + ")");
//        }
        // ***
        dataRachunek.addListener(new ListChangeListener<Produkt>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Produkt> c) {
                Double suma = 0.0;
                while (c.next()) {

                    for (Produkt p1 : dataRachunek) {
                        suma += p1.getSuma();
                    }

                    lSumaWartosc.setText(suma.toString() + " zł");
                    setCena(suma);
                    
                    
                }
            }
                
        });

                      
    }
    
   
    
    private void setCena(double cena){
        price = cena;
    }
    
    public String getCen(){
        return lSumaWartosc.getText();
    }
    
    public static Double getCena(){
        return price;
    }

    @FXML
    private void dodajDoRachunku(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLListaLekow.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Lista produktów");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bDoRachunku.getScene().getWindow());
        UstawRabaty();
        stage.showAndWait();
        
    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) bWyjscie.getScene().getWindow();
        stage.close();
        k1 = null;
    }

    @FXML
    private void usunZRachunku(ActionEvent event) {
        
        if (!dataRachunek.isEmpty()){

        JFXButton bOkay = new JFXButton("Tak, usuń");
        JFXButton bCancel = new JFXButton("Anuluj");
        MakeAlert.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay, bCancel), "Potwierdź decyzję", "Usuwamy lek?");
        bOkay.setOnAction((ActionEvent event1) -> {
            try {
                //zrobic usuniecie, ak problem to wywali błąd

                tableRachunek.getItems().remove(tableRachunek.getSelectionModel().getSelectedIndex());
                UstawRabaty();
            } catch (Exception exp) {
                MakeAlert.showMaterialDialog(spMain, apMain, Arrays.asList(bCancel), "Błąd", "Zaznacz jakąś pozycję do usunięcia");
            }
        });
        }
    }

    @FXML
    private void zaznaczonyRenta(ActionEvent event) {
        double Sroda = (getCena() *(0.9));
        double Poniedzialek = (getCena() *(0.85));

        double Renta = (getCena() *(0.7));
        
        String wartos = String.valueOf(getCena()+" zł");
        
        double nowyPoniedzialek = Math.round(Poniedzialek);
        String cenaPoniedzialek = String.valueOf(nowyPoniedzialek);
        
        Renta = Renta * 100;
        double nowaRenta = Math.round(Renta);
        nowaRenta = nowaRenta / 100;
        String cenaRenta = String.valueOf(nowaRenta);
        
        double nowaSroda = Math.round(Sroda);
        String cenaSroda = String.valueOf(nowaSroda);
        
        if (cRencista.isSelected()){
            lSumaWartosc.setText(cenaRenta + " zł");
                cPoniedzialki.setDisable(true);
                cSrody.setDisable(true);}
            else { lSumaWartosc.setText(wartos);
                    cPoniedzialki.setDisable(false);
                    cSrody.setDisable(false);
                  
        }
        
    }
    
    private void UstawRabaty(){
                cPoniedzialki.setSelected(false);
                cPoniedzialki.setDisable(false);
                cSrody.setSelected(false);
                cSrody.setDisable(false);
                cRencista.setSelected(false);
                cRencista.setDisable(false);
    }
    @FXML
    private void zaznaczony(ActionEvent event) {
        double Sroda = (getCena() *(0.9));
        double Poniedzialek = (getCena() *(0.85));

        double Renta = (getCena() *(0.7));
        
        String wartos = String.valueOf(getCena()+" zł");
        
        double nowyPoniedzialek = Math.round(Poniedzialek);
        String cenaPoniedzialek = String.valueOf(nowyPoniedzialek);
        
        double nowaRenta = Math.round(Renta);
        String cenaRenta = String.valueOf(nowaRenta);
        
        double nowaSroda = Math.round(Sroda);
        String cenaSroda = String.valueOf(nowaSroda);
        
        if (cSrody.isSelected()){
            lSumaWartosc.setText(cenaSroda + " zł");
                cPoniedzialki.setDisable(true);
                cRencista.setDisable(true);}
            else { lSumaWartosc.setText(wartos);
                    cPoniedzialki.setDisable(false);
                    cRencista.setDisable(false);
                  
        }
        
    }
    
    @FXML
    private void zaznaczonyPrzedmiot(ActionEvent event) {
        double Sroda = (getCena() *(0.9));
        double Poniedzialek = (getCena() *(0.85));

        double Renta = (getCena() *(0.7));
        
        String wartos = String.valueOf(getCena()+" zł");
        
        double nowyPoniedzialek = Math.round(Poniedzialek);
        String cenaPoniedzialek = String.valueOf(nowyPoniedzialek);
        
        double nowaRenta = Math.round(Renta);
        String cenaRenta = String.valueOf(nowaRenta);
        
        double nowaSroda = Math.round(Sroda);
        String cenaSroda = String.valueOf(nowaSroda);
        
        if (cPoniedzialki.isSelected()){
            cSrody.setDisable(true);
            cRencista.setDisable(true);
            lSumaWartosc.setText(cenaPoniedzialek + " zł");}
        else { 
            cSrody.setDisable(false);
            cRencista.setDisable(false);
            lSumaWartosc.setText(wartos);}

    }
        //odswiezRachunek();

    @FXML
    private void finalizujTransakcje(ActionEvent event) throws IOException {
               odswiezRachunek();

        
        if (!dataRachunek.isEmpty()){

        JFXButton bOkay = new JFXButton("Tak");
        JFXButton bCancel = new JFXButton("Nie");
        MakeAlert.showMaterialDialog(spMain, apMain, Arrays.asList(bOkay, bCancel), "Potwierdzenie transakcji", "Zatwierdzamy kupno leków?");
        bOkay.setOnAction((ActionEvent event1) -> {
            
            
            
                try {
                    bZatwierdz.setVisible(false);
                    bPdf.setVisible(true);
                    }

                 catch (Exception ex) {
                    System.out.println(ex);
                } 
            
            
            
            });
        } else {
            JFXButton bCancel1 = new JFXButton("Ok");
            MakeAlert.showMaterialDialog(spMain, apMain, Arrays.asList(bCancel1), "Błąd", "By móc wygenerować rachunek potrzebujemy dodac jakieś leki.");
   
        }
    }

    @FXML
    private void wybierzKlienta(ActionEvent event) {
      
    }

    @FXML
    private void wybierzKlientaZListy(MouseEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLListaKlientow.fxml"));
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bDoRachunku.getScene().getWindow());
        stage.showAndWait();

    }

    public void setKlient(Klient k) {
        idKlienta = k.getId_klienta();
        //System.out.println(idKlienta);
        //this.lStalyKlient.setText("1iofajeof aewfjewoif");
        //lStalyKlient.setText(k1.getImie_klienta()+ " " + k1.getNazwisko_klienta() + " (" + k1.getNumer_karty() + ")");
    }

    public void odswiezRachunek() {
        bUsunPrzedmiot.setDisable(false);
        columnIdProduktu.setCellValueFactory(new PropertyValueFactory<>("id_produktu"));
        columnNazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa_produktu"));
        columnCenaSzt.setCellValueFactory(new PropertyValueFactory<>("cena_produktu"));
        columnOpis.setCellValueFactory(new PropertyValueFactory<>("opis_produktu"));
        columnIlosc.setCellValueFactory(new PropertyValueFactory<>("sztuki"));
        columnCena.setCellValueFactory(new PropertyValueFactory<>("suma"));

        tableRachunek.setItems(null);
        tableRachunek.setItems(dataRachunek);

    }

    @FXML
    private void uzupelnijKlienta(MouseEvent event) {
        if (k1 != null)
            lStalyKlient.setText(k1.getImie_klienta()+ " " + k1.getNazwisko_klienta());
    }

}
