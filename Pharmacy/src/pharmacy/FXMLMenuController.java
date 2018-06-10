/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author jkero
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button bAdmin;
    @FXML
    private Button bManager;
    @FXML
    private Button bKierownik;
    @FXML
    private Button bPracownik;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void otworzAdmin(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("admin/AdminFXML.fxml"));
        stage.setScene(new Scene(root));
        root.getStylesheets().add(pharmacy.class.getResource("admin/fxmladmin.css").toExternalForm());
        stage.setTitle("Panel administracyjny");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bAdmin.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void otworzManager(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("manager/FXMLManager.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel managera");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bManager.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void otworzKierownik(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("kierownik/FXMLKierownik.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel kierownika");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bKierownik.getScene().getWindow());
        stage.showAndWait();
    }

    @FXML
    private void otworzPracownik(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("pracownik/FXMLPracownik.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Panel pracownika");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(bPracownik.getScene().getWindow());
        stage.showAndWait();
    }

}
