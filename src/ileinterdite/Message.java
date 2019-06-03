/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite;

import ileinterdite.PackageCarteTresor.CTresor;
import ileinterdite.PackageTuile.Tuile;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Message {
    TypesMessage type;
    Tuile tuile;
    CTresor carte_tresor;
    Pion ami;
    ArrayList<String> nomJoueurs;
    String difficulte;
    String modeInitialisation;
    
    public Message(TypesMessage type){
        setType(type);
    }

    public Message(TypesMessage type, ArrayList<String> nomJoueurs, String difficulte, String modeInitialisation) {
        this.type = type;
        this.nomJoueurs = nomJoueurs;
        this.difficulte = difficulte;
        this.modeInitialisation = modeInitialisation;
    }
    
    
    
    public void setType(TypesMessage type){
        this.type=type;
    }
}
