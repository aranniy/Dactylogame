package fr.uparis.informatique.cpoo5.dactylo;

public interface Lisable {
    
    public String getMotEntier();
    public boolean estCorrect();
    public boolean ajouterLettre(char c);
    public void retirerLettre();
}
