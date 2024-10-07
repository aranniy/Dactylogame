package fr.uparis.informatique.cpoo5.dactylo;

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
import fr.uparis.informatique.cpoo5.dactylo.Parametres.ParametresBuilder;

public class ParametresController {

	private ParametresBuilder p = new ParametresBuilder();

    @FXML
    private Button play;
    
     @FXML
    protected void play(){
        try {
            URL url = Paths.get("./src/main/resources/fr/uparis/informatique/cpoo5/dactylo/dactylogame.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root, 1000, 725);
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
    
    @FXML
    protected void defaut(){
       p.setMotsMax(50);
       
    }
    
    @FXML
    protected void cent(){
       p.setMotsMax(100);
       
    }
    
    @FXML
    protected void un(){
       p.setNiveauMax(1);
       
    }
    
    @FXML
    protected void deux(){
       p.setNiveauMax(2);
       
    }
    
    @FXML
    protected void zero(){
       p.setNiveauMax(0);
       
    }
    
    @FXML
    protected void oui(){
       p.setNoTimer(false);
       
    }
    
    @FXML
    protected void non(){
       p.setNoTimer(true);
       
    }

    
  
}
