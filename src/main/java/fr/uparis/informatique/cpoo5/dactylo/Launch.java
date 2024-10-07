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

public class Launch extends Application {

    @FXML
    private Button play;

    @Override
    public void start(Stage stage) throws IOException {
        URL url = Paths.get("./src/main/resources/fr/uparis/informatique/cpoo5/dactylo/choix.fxml").toUri().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root,600, 725);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
     @FXML
    protected void play(){
        try {
            URL url = Paths.get("./src/main/resources/fr/uparis/informatique/cpoo5/dactylo/parametres.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root, 600, 725);
            primaryStage.setTitle("Dactylo!");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();

            Stage stage = (Stage) play.getScene().getWindow();
            stage.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
