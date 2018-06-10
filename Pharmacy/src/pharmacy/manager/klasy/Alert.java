/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pharmacy.manager.klasy;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Yser
 */
public class Alert {
    

    static Label label;
    static Button yes;
    static boolean answer;

    public static boolean bledy(String title, String message) {//kala podaje tytuł okna oraz label
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);//czeka na zamkniecie okna
        window.setTitle(title);//tytułokana
        window.setMinWidth(400);//rozmiary 

        label = new Label();
        label.setText(message);//text w label

        yes = new Button("OK!");

        yes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                answer = false;
                window.close();
            }

        });

        HBox layout = new HBox(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(label, yes);
        layout.setAlignment(Pos.CENTER);// na środku

        Scene scene = new Scene(layout);

        window.setScene(scene);
        window.showAndWait();// czeka aż okno zostanie zamkniete 

        return answer;

    }
}
    

