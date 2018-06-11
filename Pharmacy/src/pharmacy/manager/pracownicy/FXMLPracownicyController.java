package pharmacy.manager.pracownicy;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pharmacy.connection.DBConnection;
import pharmacy.pracownik.klient.FXMLKlientController;
import static pharmacy.pracownik.klient.FXMLKlientController.idKlienta;
import pharmacy.pracownik.modele.Klient;

/**
 * FXML Controller class
 *
 * @author Yser
 */
public class FXMLPracownicyController implements Initializable {

    @FXML
    private JFXButton Button_Wyjscie;
    @FXML
    private JFXTextField TextField_Szukaj;
    @FXML
    private JFXListView<PracownicyClassa> LIstView_Pracownicy;
    @FXML
    private MenuItem Menu_Szczegoly;
    DBConnection dc;
 public static Integer idPracownika;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dc = new DBConnection();
        java.sql.Connection con = dc.Connect();
        Button_Wyjscie.setFocusTraversable(false);
        TextField_Szukaj.setFocusTraversable(false);
        
        
        try {
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery("SELECT `id_pracownika`, `imie_pracownika`, `nazwisko_pracownika`, `telefon_pracownika`,  `rola` FROM `pracownik` where rola='Sprzedawca' or rola='Menadzer'");
            while (rs.next()) {
                LIstView_Pracownicy.getItems().add(new PracownicyClassa(rs.getInt(1), rs.getString(2).toString(), rs.getString(3).toString(), rs.getString(4).toString(), rs.getString(5).toString()));
            }

            ps.close();
            rs.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(FXMLKlientController.class.getName()).log(Level.SEVERE, null, ex);
        }

        FilteredList<PracownicyClassa> filteredKlient = new FilteredList<>(LIstView_Pracownicy.getItems(), e -> true);
        TextField_Szukaj.setOnKeyReleased(e -> {
            TextField_Szukaj.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredKlient.setPredicate((Predicate<? super PracownicyClassa>) k -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lcFilter = newValue.toLowerCase();
                    if (k.getImie_pracownika().toLowerCase().contains(lcFilter) || k.getNazwisko_pracownika().toLowerCase().contains(lcFilter) || k.getRolapracownika().toLowerCase().contains(lcFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<PracownicyClassa> sortedKlient = new SortedList<>(filteredKlient);
            LIstView_Pracownicy.setItems(sortedKlient);
        });

    }

    @FXML
    private void zamknijOkno(ActionEvent event) {
        Stage stage = (Stage) Button_Wyjscie.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Szczegoly(ActionEvent event) throws IOException {
           if (LIstView_Pracownicy.getSelectionModel().getSelectedItem() != null){
        
            idPracownika = LIstView_Pracownicy.getSelectionModel().getSelectedItem().getId_pracownika();
        Stage stage;
        Parent root;
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("FXMLSzczegoly.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel dodawania klienta");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(Button_Wyjscie.getScene().getWindow());
        stage.showAndWait();

    }
    }

}
