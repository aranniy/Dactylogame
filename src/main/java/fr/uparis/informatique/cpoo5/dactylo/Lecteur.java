package fr.uparis.informatique.cpoo5.dactylo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Lecteur implements Dictionnarisable {
    private ArrayList<String> dico;
    private Scanner sc;

    public Lecteur(File f) throws FileNotFoundException{
        sc = new Scanner(f);
        dico = construireDico();

    }

    @Override
    public ArrayList<String> construireDico(){
        ArrayList<String> ret = new ArrayList<String>();

        while ( sc.hasNextLine() ){
            String ligne = sc.nextLine();
            if(ligne.length() == 0) continue;
            ligne = ligne.substring(0, ligne.length()-1);
            String[] mots = ligne.split(" ");

            for(String s: mots){
                String trimmed = "";
                for(int i = 0; i < s.length() ; i++){
                    if(Character.isAlphabetic(s.charAt(i))) trimmed += s.charAt(i);
                }
                ret.add(trimmed);
            }
            
        }
        return ret;
    }

    @Override
    public String choisirMotAleatoire(){
        int indexRandom = new Random().nextInt(this.dico.size());
        return dico.get(indexRandom);
    }

    @Override
    public String toString(){
        return dico.toString();
    }

    /*public static void main (String[] args){
        File f = new File("test.txt");
        try {
            Lecteur l1 = new Lecteur(f);
            System.out.println(l1.choisirMotAleatoire());

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
    }*/

}