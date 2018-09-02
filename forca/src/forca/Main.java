package forca;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.UnknownHostException;



public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TelaInicialClient.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        stage.setFullScreen(true);
    }

    public static void main(String[] args) throws UnknownHostException, IOException {

        launch(args);
    }


}
