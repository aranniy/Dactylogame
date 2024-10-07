package fr.uparis.informatique.cpoo5.dactylo;

public class Joueur {

    private final String nom;
    private int vies;

    public Joueur(String n) {
        this.nom = n;
        this.vies = 3;
        
    }
    
    public int getVies() { return vies; }
    public void setVies(int vies) { this.vies = vies; }
    public String getNom() { return this.nom; }

}
