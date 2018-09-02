package forca;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaClientController implements Initializable {

    @FXML
    private Label qtd_letras;
    private Label tema;

    @FXML
    private void loadTela(ActionEvent actionEvent) {

    }

    public void changeQuantidadeLetras(String string) {
        qtd_letras.setText(string);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Controller controller = new Controller();

        System.out.println("AAAAAAAAAAAAA");
        Client client = new Client();
        String tema = null;
        System.out.println("socketttt "+client.socket);
        try {
            tema = client.esperaTema();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("TEMA: "+tema);
//        try {

//        } catch (IOException e) {
//            e.printStackTrace();
//        }



    }

}
