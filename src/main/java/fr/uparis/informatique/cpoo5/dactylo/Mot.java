package fr.uparis.informatique.cpoo5.dactylo;

public final class Mot implements Lisable {

    private final String mot;
    private String avancee = "";
    private int distanceEdition = 0;

    public Mot(String m){
        mot = m;
    }

    /*
     * @return Vrai si avancee est égal au mot complet
     */
    public boolean estCorrect () { return distanceEdition == 0; }

    /*
     * Ajoute une lettre à avancee et renvoie vrai s'il s'agit d'une lettre utile
     */
    @Override
    public boolean ajouterLettre(char c){
        avancee += c;
        majDistance();
        if(avancee.length() > mot.length()) return false;
        return (avancee.charAt(avancee.length()-1) == mot.charAt(avancee.length()-1));
    }

    /*
     * Retire la dernière lettre de avancee, si avancee n'est pas vide
     */
    @Override
    public void retirerLettre(){
        if(avancee.length() == 0) return;
        avancee = avancee.substring(0, avancee.length() - 1);
        majDistance();
    }

    /*
     * Met à jour la distance d'édition (= nombre d'erreurs dans le mot)
     */
    private void majDistance(){
        int nouvDistance = 0;
        for(int i=0; i < Math.min(mot.length(), avancee.length()); i++){
            if (mot.charAt(i) != avancee.charAt(i)) nouvDistance++;
        }
        if (mot.length() != avancee.length()) nouvDistance += Math.abs(mot.length() - avancee.length());
        distanceEdition = nouvDistance;
    }

    @Override
    public String getMotEntier() { return mot; }
    
    public int getNombreErreurs() { return distanceEdition; }
    public String getAvancee() { return avancee; }
    
    public void reset() {
        this.avancee = "";
        this.distanceEdition = 0;
    }
    
    public int getTailleMot() { return mot.length(); }
    public int getTailleAvancee() { return avancee.length(); }

}
