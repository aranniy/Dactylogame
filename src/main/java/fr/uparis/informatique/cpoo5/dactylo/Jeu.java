package fr.uparis.informatique.cpoo5.dactylo;

import javafx.scene.control.Button;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.util.Objects;
import java.util.Queue;
import javafx.scene.Node;
import java.net.URL;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.util.LinkedList;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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
import java.util.Random;
import fr.uparis.informatique.cpoo5.dactylo.Parametres.ParametresBuilder;

public class Jeu implements Initializable {
    
    @FXML
    private Label niveau;
    
    @FXML
    private Label score;
    
    @FXML
    private Label vies;
    
    @FXML
    private Label nbr_erreurs;
    @FXML
    private Label timer;
    @FXML
    private TextField word = new TextField();
    @FXML
    private AnchorPane liste_de_mots;
    
    private Mot head; // mot en cours
    
    private int tentative;
    private int erreurs;
    private int mot_courant;
    
    //private final Dictionnarisable banqueDeMots;
    
    private Timer chrono;
    
    private Parametrable settings;
    
    private Queue<Mot> file = new LinkedList<Mot>();
    
    private Joueur joueur = new Joueur("joueur");

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    
    	this.settings = new ParametresBuilder().build();
        this.chrono = new Timer(settings.getTempsMax());
    
		Random random = new Random();
		int nb;

        /* Génère une liste de mots qu'on associe à plusieurs labels */
        File f = new File("./src/main/resources/fr/uparis/informatique/cpoo5/dactylo/txt/test.txt");
        try {
            Lecteur l1 = new Lecteur(f);
            for (Node m : liste_de_mots.getChildren()){
                Label l = (Label) m;
                Mot tmp = new Mot(l1.choisirMotAleatoire().toUpperCase());
                l.setText("" + tmp.getMotEntier());
                nb = random.nextInt(5);
                if (nb == 0) {
                	l.setTextFill(Color.BLUE);
                }
                file.add(tmp);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        this.head = file.peek();
        this.tentative = 0;
        this.mot_courant = 0;
        this.erreurs = 0;
    
    }
    
    public Jeu() {}

    /* effectue une action en fonction de la touche tapé par le joueur */
    @FXML
    private void keyPressed(KeyEvent keyEvent) {

		/* si ESPACE -> on valide le mot et on effectue les vérifications */
        if (keyEvent.getCode() == KeyCode.SPACE) {
            validation();

		/* si LETTRE -> on l'ajoute au mot en cours */
        } else if (keyEvent.getCode().isLetterKey() || keyEvent.getCode() == KeyCode.SEMICOLON) {
            String lettre;
                switch (keyEvent.getCode().getName()) {
                        case "A" : lettre = "Q"; break;
                        case "Q" : lettre = "A"; break;
                        case "W" : lettre = "Z"; break;
                        case "Z" : lettre = "W"; break;
                        case "M" : lettre = ","; break;
                        case "Semicolon" : lettre = "M"; break;
                        default : lettre = keyEvent.getCode().getName(); break;
                }
            maj_word(lettre);
            
        /* si RETOUR -> on retire une lettre au mot en cours */
        } else if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
            this.head.retirerLettre();
        }
    }
    
    /* aurait permit de supprimer graphiquement le mot en tête de la file */
    public void decalageFile() { }
    
    /* ajoute une lettre */
    public void maj_word(String s) {
        
        /* vérifie que la lettre est correcte */
        if (this.head.ajouterLettre(s.charAt(0))) {
  			  
  			  /* met à jour la couleur de la lettre en rouge */
  
        } else {
            
          	/* met à jour la couleur de la lettre en vert */
            
        }
        
    }
    
    /* retire une lettre */
    public void retour() { this.head.retirerLettre(); }
    
    /* renvoie vers la page de défaite */
    public void gameOver() {
        
        try {
            URL url = Paths.get("./src/main/resources/fr/uparis/informatique/cpoo5/dactylo/GameOver.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root,600, 725);
            primaryStage.setTitle("Game Over :(");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
            Stage stage = (Stage) score.getScene().getWindow();
            stage.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /* renvoie vers la page des statistiques après une victoire */
    public void win() {
        
        try {
            URL url = Paths.get("./src/main/resources/fr/uparis/informatique/cpoo5/dactylo/statistiques.fxml").toUri().toURL();
            Parent root = FXMLLoader.load(url);
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root,600, 725);
            primaryStage.setTitle("Felicitations!");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
            Stage stage = (Stage) score.getScene().getWindow();
            stage.close();

        }catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    /* met à jour le nombre de vies */
    public void miseAjourVies() {
        joueur.setVies(joueur.getVies() - this.head.getNombreErreurs());
        vies.setText("VIES : " + joueur.getVies());
        if (joueur.getVies() < 0 || aPerduLaPartie()) gameOver();
    }
    
    /* récupère le label courant pour pouvoir ensuite effectuer des changements dessus */
    public Label getLabelCourant(int pos) {
    	int i = 0;
    	for (Node m : liste_de_mots.getChildren()){
             if (i == mot_courant) return ((Label) m);
             i++;
    	}
    	return null;    	
    }
    
    /* traitement du mot que le joueur a validé */
    public void validation() {
    
    	if (this.head == null) win();
        
        decalageFile();
        Label LabelCourant = this.getLabelCourant(mot_courant);
        
        // on vérifie la distance d'édition
        if (this.head.estCorrect()) {
            
            /* si c'est bleu et que le joueur l'a bien écrit du premier coup on rajoute un nombre de vies équivalent à la taille du mot*/
            if ((LabelCourant.getTextFill().toString()).equals("0x0000ffff") && tentative == 0) {
            
            	joueur.setVies(joueur.getVies() + head.getTailleMot());
             	vies.setText("VIES : " + joueur.getVies());
            
            } 
            
            LabelCourant.setTextFill(Color.GREEN);
 
 			/* on passe au mot suivant dans la file */
           	file.poll();
            head = file.peek();
            
            /* s'il n'y a plus de mots dans la file le joueur a gagné */
            if (head == null) { 
            	win(); 
            	
            /* sinon on remet à 0 le nombre de tentatives, la position du mot courant et le mot en cours d'écriture */
            } else {
            
            	tentative = 0;
            	mot_courant++;
            	score.setText("SCORE : " + this.mot_courant);
            	head.reset();
            	
            }
           
        /* le mot n'est pas correcte*/ 
        } else {
            miseAjourVies();
            LabelCourant.setTextFill(Color.RED);
            this.tentative++;
            erreurs++;
            nbr_erreurs.setText("ERREURS : " + erreurs);
            head.reset();
        }
    
        word.setText(""); 
    }
    
    public boolean IsOutOfTime(){ return !settings.getNoTimer() && this.chrono.isOutOfTime(); }
    
    public void ajouterMot(String mot) {
        this.file.add(new Mot(mot));
        if(this.file.size() >= settings.getLimiteMots() || IsOutOfTime()) gameOver();
    }
    
    public boolean doitAjouterMotALaFile(){ 
    	return (settings.getMode().equals("jeu") && (file.size() <= settings.getLimiteMots() / 2 ))
        || settings.getMode().equals("normal");
    }
    
    public boolean aPerduLaPartie (){
    	return (this.settings.getMode().equals("jeu") && this.IsOutOfTime())
    	|| (this.settings.getMode().equals("jeu") && (this.joueur.getVies() <= 0));
	}

	public boolean aGagneLaPartie (){
    	return (this.settings.getMode().equals("normal") && !this.settings.getNoTimer() && this.IsOutOfTime())
    	|| (this.settings.getMode().equals("normal") && this.settings.getNoTimer() && (this.mot_courant >= this.settings.getMotsMax()));
	}
    
}

