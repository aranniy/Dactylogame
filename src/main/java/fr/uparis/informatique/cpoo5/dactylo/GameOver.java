package fr.uparis.informatique.cpoo5.dactylo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import java.net.URL;
import java.nio.file.Paths;

public class GameOver {

    @FXML
    private Button retry;
    
     @FXML
    protected void retry(){
        try {
            URL url = Paths.get("./src/main/resources/fr/uparis/informatique/cpoo5/dactylo/dactylogame.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root, 1000, 725);
            primaryStage.setTitle("Dactylo!");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            Stage stage = (Stage) retry.getScene().getWindow();
            stage.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
