/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ileinterdite.PackageAventurier;

import ileinterdite.Grille;
import ileinterdite.PackageTuile.Tuile;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author dusema
 */
public class Explorateur extends Aventurier{

    public Explorateur(String nomA, String description, Tuile tuileDepart) {
        super(nomA, description, tuileDepart);
        this.couleur=Color.GREEN;
        
    }
    public Explorateur() {
        super("Explorateur",
                "L'Explorateur peut se déplacer et assécher en diagonale.");
        this.couleur=Color.GREEN;
    }
        
    
     @Override
     public ArrayList<Tuile> getTuilesDispoPourDeplacement(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuilesDispoPourDeplacement = super.getTuilesDispoPourDeplacement(grille,tuile);
       
        tuilesDispoPourDeplacement.addAll(grille.getNonSubmerge(grille.getTuilesDiagonale(tuile)));
      

        return tuilesDispoPourDeplacement;

    }
     
     
     @Override
     public ArrayList<Tuile> getTuilesAdjacentesInnondees(Grille grille, Tuile tuile) {
        ArrayList<Tuile> tuilesAdjacentesInnondees = super.getTuilesAdjacentesInnondees(grille, tuile);
        
        
        tuilesAdjacentesInnondees= grille.getTuilesCroix(tuile);
        tuilesAdjacentesInnondees.addAll(grille.getTuilesInondees(grille.getTuilesDiagonale(tuile)));
       
        
        
        return tuilesAdjacentesInnondees;
        
     
     }
     
     
     
     
     
     
     
     

}
