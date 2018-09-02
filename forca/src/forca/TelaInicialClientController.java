package forca;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;


public class TelaInicialClientController implements Initializable {

    @FXML
    private AnchorPane pane_espera;

    @FXML
    private TextField text_field;

    public void initialize(URL url, ResourceBundle rb) {
        //btn_ghost.setVisible(true);
        text_field.setVisible(true);

        text_field.setOnKeyReleased(E -> {
            switch(E.getCode()) {
                case ENTER: {
                    String ip = text_field.getText();
                    Client cliente = new Client();

                    try {
                        cliente.runClient(ip);
//                        Parent root = FXMLLoader.load(getClass().getResource("TelaEsperandoTemaClient.fxml"));
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("TelaEsperandoTemaClient.fxml"));
                        pane_espera.getChildren().setAll(pane);
                        return;

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //		Socket cliente = new Socket("191.52.64.26", 12346);
                }
                default:
                    E.consume();
            }
        });

        Platform.runLater(() -> text_field.requestFocus());
    }

    public void loadEnter(ActionEvent actionEvent) throws Exception{


                    String ip = text_field.getText();
                    Client cliente = new Client();
                    try {
                        cliente.runClient(ip);
//                        Parent root = FXMLLoader.load(getClass().getResource("TelaEsperandoTemaClient.fxml"));
                        AnchorPane pane = FXMLLoader.load(getClass().getResource("TelaEsperandoTemaClient.fxml"));
                        pane_espera.getChildren().setAll(pane);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }




    }
}
